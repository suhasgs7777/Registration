const { Pool } = require('pg');

const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'register',
  password: 'vijay1995',
  port: 5432,
});

module.exports = pool;