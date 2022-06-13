require('dotenv').config();

const{
  DB_USERNAME, 
  DB_PASSWORD,
  DB_HOSTNAME,
  DB_NAME,
  DB_DIALECT
}= process.env;

module.exports = {
  "development": {
    "username": DB_USERNAME,
    "password": DB_PASSWORD,
    "database": DB_NAME,
    "host": DB_HOSTNAME,
    "dialect": DB_DIALECT
  },
  "test": {
    "username": DB_USERNAME,
    "password": DB_PASSWORD,
    "database": DB_NAME,
    "host": DB_HOSTNAME,
    "dialect": DB_DIALECT
  },
  "production": {
    "username": DB_USERNAME,
    "password": DB_PASSWORD,
    "database": DB_NAME,
    "host": DB_HOSTNAME,
    "dialect": DB_DIALECT
  }
}
const createTcpPool = async config => {
  // Extract host and port from socket address
  const dbSocketAddr = process.env.DB_HOSTNAME.split(':');

  // Establish a connection to the database
  return mysql.createPool({
    user: process.env.DB_USERNAME, // e.g. 'my-db-user'
    password: process.env.DB_PASSWORD, // e.g. 'my-db-password'
    database: process.env.DB_NAME, // e.g. 'my-database'
    host: dbSocketAddr[0].process.env.DB_HOSTNAME, // e.g. '127.0.0.1'
    port: dbSocketAddr[1].process.env.PORT, // e.g. '3306'
    // ... Specify additional properties here.
    ...config,
  });
};

