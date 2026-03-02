-- -----------------------------------------------------
-- LDC Charges
-- -----------------------------------------------------
INSERT INTO ldc_charges
(charge_code, charge_name, charge_type, amount, effective_from, effective_to, service_area_id, status, created_at, updated_at)
VALUES
('CHG001', 'Service Charge', 'FIXED', 100.0000, CURRENT_TIMESTAMP, NULL, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CHG002', 'Premium Charge', 'VARIABLE', 50.5000, CURRENT_TIMESTAMP, NULL, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- LDC Meter Types
-- -----------------------------------------------------
INSERT INTO ldc_meter_types
(meter_type_code, description, pressure_category, max_capacity, status, created_at, updated_at)
VALUES
('MT001', 'Residential Meter', 'LOW', 1000.00, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('MT002', 'Industrial Meter', 'HIGH', 5000.00, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- LDC Quantities
-- -----------------------------------------------------
INSERT INTO ldc_quantities
(meter_id, quantity_type, volume, unit, reading_date, remarks, created_at, updated_at)
VALUES
(1, 'CONSUMPTION', 150.1234, 'MCF', '2026-02-28', 'Initial reading', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'DELIVERY', 250.5678, 'MCF', '2026-02-28', 'Monthly reading', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- LDC Storage Types
-- -----------------------------------------------------
INSERT INTO ldc_storage_types
(storage_type_name, capacity, injection_rate, withdrawal_rate, current_storage_volume, status, created_at, updated_at)
VALUES
('Underground Storage', 10000.00, 500.0000, 400.0000, 0, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Surface Storage', 5000.00, 200.0000, 150.0000, 0, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- LDC Tariffs
-- -----------------------------------------------------
INSERT INTO ldc_tariffs
(tariff_code, meter_type_id, rate_per_unit, slab_from, slab_to, effective_from, effective_to, status, created_at, updated_at)
VALUES
('TRF001', 1, 3.456789, 0.00, 100.00, CURRENT_TIMESTAMP, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('TRF002', 2, 5.123456, 101.00, 500.00, CURRENT_TIMESTAMP, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- Monthly TOL
-- -----------------------------------------------------
INSERT INTO monthly_tol
(meter_id, month_code, allowed_percentage, actual_variance, penalty_amount, penalty_applied, remarks, status, created_at, updated_at)
VALUES
(1, '2026-02', 10.00, 0.0000, 0.0000, FALSE, 'No issues', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '2026-02', 15.50, 2.3456, 50.0000, TRUE, 'Exceeded limit', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- Pooling Points
-- -----------------------------------------------------
INSERT INTO pooling_points
(pooling_code, location, capacity, current_volume, active_contracts, service_area_id, status, created_at, updated_at)
VALUES
('POOL001', 'North Hub', 10000.00, 0.00, 0, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('POOL002', 'South Hub', 5000.00, 0.00, 1, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -----------------------------------------------------
-- Price Products
-- -----------------------------------------------------
INSERT INTO price_products
(product_code, product_name, pricing_type, base_price, formula_reference, effective_from, effective_to, description, pooling_point_id, status, created_at, updated_at)
VALUES
('PRD001', 'Natural Gas Standard', 'VARIABLE', 25.500000, 'Formula1', CURRENT_TIMESTAMP, NULL, 'Standard product', 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PRD002', 'Natural Gas Premium', 'VARIABLE', 30.750000, 'Formula2', CURRENT_TIMESTAMP, NULL, 'Premium product', 2, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);