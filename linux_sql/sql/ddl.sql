--  Name: Hunain Khalid
--  Date: Wed, Dec, 8, 2021
--  Purpose: Create automated SQL program initiating
--           necessary tables in host_agent the database
--  Filename: ddl.sql

-- switch to host
\c host_agent

-- create necessary tables, only
-- if they do not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_info
(
    id                  SERIAL NOT NULL PRIMARY KEY,
    hostname            VARCHAR NOT NULL,
    cpu_number          INT NOT NULL,
    cpu_architecture    VARCHAR NOT NULL,
    cpu_model           VARCHAR NOT NULL,
    cpu_mhz             FLOAT NOT NULL,
    L2_cache            INT NOT NULL,
    total_mem           INT NOT NULL,
    timestamp           TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp"         TIMESTAMP NOT NULL,
    host_id             SERIAL NOT NULL REFERENCES host_info(id) ON DELETE CASCADE,
    memory_free         INT NOT NULL,
    cpu_idle            INT NOT NULL,
    cpu_kernel          INT NOT NULL,
    disk_io             INT NOT NULL,
    disk_available      INT NOT NULL
);