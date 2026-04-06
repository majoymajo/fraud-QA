import http from 'k6/http';
import { check, sleep, group } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

// Custom metrics
const evaluateTrend = new Trend('evaluate_amount_duration');
const suspectRate = new Rate('suspicious_correctly_flagged');
const locationEvalCount = new Counter('location_evaluations');

export const options = {
  stages: [
    { duration: '30s', target: 10 },   // Ramp-up
    { duration: '1m',  target: 50 },   // Steady load
    { duration: '20s', target: 0 },    // Ramp-down
  ],
  thresholds: {
    'http_req_duration': ['p(95)<500'],
    'http_req_failed': ['rate<0.05'],
    'evaluate_amount_duration': ['p(95)<400'],
    'suspicious_correctly_flagged': ['rate>0.95'],
  },
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080/api/v1';

export default function () {
  group('HU1 – Evaluación de monto', () => {
    const payload = JSON.stringify({
      transaccion_id: `TXN-PERF-${__VU}-${__ITER}`,
      monto: 20000,
      fecha: '2026-03-24',
      usuario_id: 'USR-PERF',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/transacciones/evaluar-monto`, payload, params);

    evaluateTrend.add(res.timings.duration);
    check(res, {
      'status 200': (r) => r.status === 200,
      'clasificacion Inusual': (r) => JSON.parse(r.body).clasificacion === 'Inusual',
    });
  });

  group('HU3 – Marcado de sospecha', () => {
    const payload = JSON.stringify({
      monto: 20000,
      pais: 'Islas Caimán',
      usuario_id: 'USR-PERF',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/transacciones/marcar-sospecha`, payload, params);

    const body = JSON.parse(res.body);
    suspectRate.add(body.sospechosa === true);
    check(res, {
      'status 200': (r) => r.status === 200,
      'marcado como Sospechosa': (r) => body.sospechosa === true,
    });
  });

  group('HU2 – Evaluación de ubicación', () => {
    const payload = JSON.stringify({
      usuario_id: 'USR-PERF',
      pais_habitual: 'Colombia',
      pais_transaccion: 'Rusia',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/transacciones/evaluar-ubicacion`, payload, params);

    locationEvalCount.add(1);
    check(res, {
      'status 200': (r) => r.status === 200,
      'clasificacion Inusual': (r) => JSON.parse(r.body).clasificacion === 'Inusual',
    });
  });

  sleep(1);
}
