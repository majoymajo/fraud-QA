function fn() {
  const env = karate.env || 'local';
  const baseUrlByEnv = {
    local: 'http://localhost:8080/api/v1',
    qa: 'http://qa.fraud-engine.sofka.co/api/v1',
    staging: 'http://staging.fraud-engine.sofka.co/api/v1',
    prod: 'https://api.fraud-engine.sofka.co/api/v1',
  };
  const baseUrl = baseUrlByEnv[env] || baseUrlByEnv.local;
  return { baseUrl };
}
