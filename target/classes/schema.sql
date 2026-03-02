-- =====================================================
-- LDC Configuration Schema for H2 Database
-- =====================================================

-- Drop tables if they exist (for clean restart)
DROP TABLE IF EXISTS price_products CASCADE;
DROP TABLE IF EXISTS pooling_points CASCADE;
DROP TABLE IF EXISTS monthly_tol CASCADE;
DROP TABLE IF EXISTS ldc_tariffs CASCADE;
DROP TABLE IF EXISTS ldc_quantities CASCADE;
DROP TABLE IF EXISTS ldc_storage_types CASCADE;
DROP TABLE IF EXISTS ldc_meter_types CASCADE;
DROP TABLE IF EXISTS ldc_charges CASCADE;

-- -----------------------------------------------------
-- LDC Charges Table
-- -----------------------------------------------------
CREATE TABLE ldc_charges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    charge_code VARCHAR(50) NOT NULL UNIQUE,
    charge_name VARCHAR(100) NOT NULL,
    charge_type VARCHAR(20) NOT NULL,
    amount DECIMAL(15,4) NOT NULL,
    effective_from TIMESTAMP NOT NULL,
    effective_to TIMESTAMP,
    service_area_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_charge_service_area ON ldc_charges(service_area_id);

-- -----------------------------------------------------
-- LDC Meter Types Table
-- -----------------------------------------------------
CREATE TABLE ldc_meter_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meter_type_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(250) NOT NULL,
    pressure_category VARCHAR(20) NOT NULL,
    max_capacity DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- -----------------------------------------------------
-- LDC Quantities Table
-- -----------------------------------------------------
CREATE TABLE ldc_quantities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meter_id BIGINT NOT NULL,
    quantity_type VARCHAR(20) NOT NULL,
    volume DECIMAL(15,4) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    reading_date DATE NOT NULL,
    remarks VARCHAR(500),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_meter_id ON ldc_quantities(meter_id);

-- -----------------------------------------------------
-- LDC Storage Types Table
-- -----------------------------------------------------
CREATE TABLE ldc_storage_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    storage_type_name VARCHAR(100) NOT NULL UNIQUE,
    capacity DECIMAL(15,2) NOT NULL,
    injection_rate DECIMAL(15,4) NOT NULL,
    withdrawal_rate DECIMAL(15,4) NOT NULL,
    current_storage_volume DECIMAL(15,2) DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- -----------------------------------------------------
-- LDC Tariffs Table
-- -----------------------------------------------------
CREATE TABLE ldc_tariffs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tariff_code VARCHAR(50) NOT NULL UNIQUE,
    meter_type_id BIGINT NOT NULL,
    rate_per_unit DECIMAL(15,6) NOT NULL,
    slab_from DECIMAL(15,2) NOT NULL,
    slab_to DECIMAL(15,2) NOT NULL,
    effective_from TIMESTAMP NOT NULL,
    effective_to TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_meter_type_id ON ldc_tariffs(meter_type_id);

-- -----------------------------------------------------
-- Monthly TOL Table
-- -----------------------------------------------------
CREATE TABLE monthly_tol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meter_id BIGINT NOT NULL,
    month_code VARCHAR(10) NOT NULL,
    allowed_percentage DECIMAL(5,2) NOT NULL,
    actual_variance DECIMAL(15,4) DEFAULT 0,
    penalty_amount DECIMAL(15,4) DEFAULT 0,
    penalty_applied BOOLEAN DEFAULT FALSE,
    remarks VARCHAR(500),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT unique_meter_month UNIQUE (meter_id, month_code)
);

-- -----------------------------------------------------
-- Pooling Points Table
-- -----------------------------------------------------
CREATE TABLE pooling_points (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pooling_code VARCHAR(50) NOT NULL UNIQUE,
    location VARCHAR(250) NOT NULL,
    capacity DECIMAL(15,2) NOT NULL,
    current_volume DECIMAL(15,2) DEFAULT 0,
    active_contracts INT DEFAULT 0,
    service_area_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_pooling_service_area ON pooling_points(service_area_id);

-- -----------------------------------------------------
-- Price Products Table
-- -----------------------------------------------------
CREATE TABLE price_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(50) NOT NULL UNIQUE,
    product_name VARCHAR(150) NOT NULL,
    pricing_type VARCHAR(20) NOT NULL,
    base_price DECIMAL(15,6) NOT NULL,
    formula_reference VARCHAR(250),
    effective_from TIMESTAMP NOT NULL,
    effective_to TIMESTAMP,
    description VARCHAR(500),
    pooling_point_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_pooling_point_id ON price_products(pooling_point_id);