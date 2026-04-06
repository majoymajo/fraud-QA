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
  group('HU1 – Evaluación de monto (Amount Threshold)', () => {
    // High amount to trigger fraud detection
    const payload = JSON.stringify({
      amount: 20000,
      transactionCountry: 'CO',
      userCountry: 'CO',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/fraud/evaluate`, payload, params);

    evaluateTrend.add(res.timings.duration);
    const body = JSON.parse(res.body);
    check(res, {
      'status 200': (r) => r.status === 200,
      'risk detected': (r) => body.riskLevel !== undefined,
    });
  });

  group('HU3 – Marcado de sospecha (Suspicious Flag)', () => {
    // Transaction with unusual location to trigger suspicion
    const payload = JSON.stringify({
      amount: 20000,
      transactionCountry: 'KY',  // Cayman Islands
      userCountry: 'CO',         // Colombia
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/fraud/evaluate`, payload, params);

    const body = JSON.parse(res.body);
    suspectRate.add(body.suspicious === true ? 1 : 0);
    check(res, {
      'status 200': (r) => r.status === 200,
      'suspicious detected': (r) => body.suspicious === true,
    });
  });

  group('HU2 – Evaluación de ubicación (Location Check)', () => {
    // Different countries to trigger location anomaly
    const payload = JSON.stringify({
      amount: 5000,
      transactionCountry: 'RU',  // Russia
      userCountry: 'CO',         // Colombia
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/fraud/evaluate`, payload, params);

    locationEvalCount.add(1);
    check(res, {
      'status 200': (r) => r.status === 200,
      'risk evaluated': (r) => JSON.parse(r.body).riskLevel !== undefined,
    });
  });

  sleep(1);
}
