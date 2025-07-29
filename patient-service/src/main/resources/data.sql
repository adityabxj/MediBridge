-- Ensure the 'patient' table exists
CREATE TABLE IF NOT EXISTS patient
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    date_of_birth   DATE                NOT NULL,
    registered_date DATE                NOT NULL,
    gender          VARCHAR(255)        NOT NULL DEFAULT 'UNKNOWN',
    phone_number    VARCHAR(255)        NOT NULL DEFAULT 'N/A',
    medical_history VARCHAR(255),
    created_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Add new columns if they don't exist
ALTER TABLE patient ADD COLUMN IF NOT EXISTS gender VARCHAR(255) NOT NULL DEFAULT 'UNKNOWN';
ALTER TABLE patient ADD COLUMN IF NOT EXISTS phone_number VARCHAR(255) NOT NULL DEFAULT 'N/A';
ALTER TABLE patient ADD COLUMN IF NOT EXISTS medical_history VARCHAR(255);
ALTER TABLE patient ADD COLUMN IF NOT EXISTS created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE patient ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Ensure the 'doctor' table exists
CREATE TABLE IF NOT EXISTS doctor
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    gender          VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    mobile_number   VARCHAR(255)        NOT NULL,
    status          VARCHAR(255)        NOT NULL,
    details         TEXT,
    location        VARCHAR(255)        NOT NULL,
    available_from  TIMESTAMP,
    available_upto  TIMESTAMP,
    experience      INTEGER,
    rating          DOUBLE PRECISION,
    created_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Ensure the 'doctor_services' table exists
CREATE TABLE IF NOT EXISTS doctor_services
(
    doctor_id UUID NOT NULL,
    service   VARCHAR(255),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
    );

-- Ensure the 'health_records' table exists
CREATE TABLE IF NOT EXISTS health_records
(
    health_record_id UUID PRIMARY KEY,
    patient_id       UUID NOT NULL,
    symptoms         TEXT NOT NULL,
    diagnosis        TEXT NOT NULL,
    prescription     TEXT,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

-- Ensure the 'appointment' table exists
CREATE TABLE IF NOT EXISTS appointment
(
    appointment_id              UUID PRIMARY KEY,
    patient_id                 UUID NOT NULL,
    doctor_id                  UUID NOT NULL,
    health_record_id           UUID,
    details                    TEXT NOT NULL,
    status                     VARCHAR(255) NOT NULL,
    appointment_start_date_time TIMESTAMP NOT NULL,
    appointment_end_date_time   TIMESTAMP NOT NULL,
    duration_minutes           INTEGER NOT NULL,
    location                   VARCHAR(255) NOT NULL,
    created_at                 TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (health_record_id) REFERENCES health_records(health_record_id)
    );

-- Ensure the 'packages' table exists
CREATE TABLE IF NOT EXISTS packages
(
    package_id UUID PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    price      DECIMAL(10,2) NOT NULL,
    details    TEXT NOT NULL,
    city       VARCHAR(255) NOT NULL,
    doctor_id  UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
    );