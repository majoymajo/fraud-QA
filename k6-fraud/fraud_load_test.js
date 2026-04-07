import http from 'k6/http';
import { check, sleep, group } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

const metrics = {
  evaluateAmountDuration: new Trend('evaluate_amount_duration'),
  suspiciousCorrectlyFlagged: new Rate('suspicious_correctly_flagged'),
  locationEvaluations: new Counter('location_evaluations'),
};

const BASE_URL = __ENV.BASE_URL ?? 'http://localhost:8080/api/v1';
const EVALUATE_URL = `${BASE_URL}/fraud/evaluate`;
const JSON_HEADERS = { headers: { 'Content-Type': 'application/json' } };

const stages = [
  { duration: '30s', target: 10 },
  { duration: '1m', target: 50 },
  { duration: '20s', target: 0 },
];

export const options = {
  stages,
  thresholds: {
    'http_req_duration': ['p(95)<500'],
    'http_req_failed': ['rate<0.05'],
    'evaluate_amount_duration': ['p(95)<400'],
    'suspicious_correctly_flagged': ['rate>0.95'],
  },
};

function parseJsonOrEmpty(value) {
  if (value == null || value === '') return {};
  try {
    return JSON.parse(value);
  } catch (error) {
    return {};
  }
}

function evaluateFraud(requestBody) {
  const response = http.post(EVALUATE_URL, JSON.stringify(requestBody), JSON_HEADERS);
  return { response, body: parseJsonOrEmpty(response.body) };
}

export default function () {
  group('HU1 – Evaluación de monto (Amount Threshold)', () => {
    const requestBody = {
      amount: 20000,
      transactionCountry: 'CO',
      userCountry: 'CO',
    };
    const { response, body } = evaluateFraud(requestBody);
    metrics.evaluateAmountDuration.add(response.timings.duration);
    check(response, {
      'status 200': (r) => r.status === 200,
      'risk detected': () => body.riskLevel !== undefined,
    });
  });

  group('HU3 – Marcado de sospecha (Suspicious Flag)', () => {
    const requestBody = {
      amount: 20000,
      transactionCountry: 'KY',
      userCountry: 'CO',
    };
    const { response, body } = evaluateFraud(requestBody);
    metrics.suspiciousCorrectlyFlagged.add(body.suspicious === true);
    check(response, {
      'status 200': (r) => r.status === 200,
      'suspicious detected': () => body.suspicious === true,
    });
  });

  group('HU2 – Evaluación de ubicación (Location Check)', () => {
    const requestBody = {
      amount: 5000,
      transactionCountry: 'RU',
      userCountry: 'CO',
    };
    const { response, body } = evaluateFraud(requestBody);
    metrics.locationEvaluations.add(1);
    check(response, {
      'status 200': (r) => r.status === 200,
      'risk evaluated': () => body.riskLevel !== undefined,
    });
  });

  sleep(1);
}
