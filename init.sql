-- Create Healenium user
DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT
      FROM   pg_catalog.pg_roles
      WHERE  rolname = 'healenium_user') THEN

      CREATE USER healenium_user WITH PASSWORD 'secret_password';
   END IF;
END
$do$;

-- Create Healenium database
DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_database
      WHERE datname = 'healenium') THEN

      CREATE DATABASE healenium OWNER healenium_user;
   END IF;
END
$do$;

-- Connect to the healenium database
\connect healenium

-- Create schema if not exists
CREATE SCHEMA IF NOT EXISTS healenium AUTHORIZATION healenium_user;