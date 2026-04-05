function fn() {
  var env = karate.env || 'local';
  var config = {
    baseUrl: 'http://localhost:8080/api/v1'
  };

  if (env === 'qa') {
    config.baseUrl = 'http://qa.fraud-engine.sofka.co/api/v1';
  } else if (env === 'staging') {
    config.baseUrl = 'http://staging.fraud-engine.sofka.co/api/v1';
  } else if (env === 'prod') {
    config.baseUrl = 'https://api.fraud-engine.sofka.co/api/v1';
  }

  karate.log('Base URL:', config.baseUrl);
  return config;
}
