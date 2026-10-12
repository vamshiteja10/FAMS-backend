-- ==========================================================
-- FarmAssist - Farmer Resource & Crop Management System
-- Database Schema (MySQL Compatible)
-- ==========================================================

CREATE DATABASE IF NOT EXISTS farmassist_db;
USE farmassist_db;

-- 1. Users Table (Authentication & Role Based Access)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_FARMER', -- ROLE_FARMER, ROLE_OFFICER, ROLE_ADMIN
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Farmer Profiles Table
CREATE TABLE IF NOT EXISTS farmer_profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    village VARCHAR(100),
    district VARCHAR(100),
    state VARCHAR(100),
    total_experience_years INT DEFAULT 1,
    primary_water_source VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 3. Land Parcels Table (Field & Land Registration)
CREATE TABLE IF NOT EXISTS land_parcels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    parcel_name VARCHAR(100) NOT NULL,
    survey_number VARCHAR(50) NOT NULL,
    total_area_acres DECIMAL(6, 2) NOT NULL,
    soil_type VARCHAR(50) NOT NULL, -- Alluvial, Black, Red, Sandy, Clay, Loamy
    irrigation_type VARCHAR(50) NOT NULL, -- Drip, Sprinkler, Canal, Borewell, Rainfed
    village_location VARCHAR(120),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_land_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 4. Crops Table (Crop Management & Harvest Details)
CREATE TABLE IF NOT EXISTS crops (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    land_parcel_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    variety VARCHAR(100),
    season VARCHAR(30) NOT NULL, -- Kharif, Rabi, Zaid
    sowing_date DATE NOT NULL,
    expected_harvest_date DATE NOT NULL,
    expected_yield_quintals DECIMAL(8, 2),
    status VARCHAR(30) NOT NULL DEFAULT 'GROWING', -- SOWN, GROWING, FLOWERING, HARVESTED, DAMAGED
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_crop_land FOREIGN KEY (land_parcel_id) REFERENCES land_parcels(id) ON DELETE CASCADE,
    CONSTRAINT fk_crop_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 5. Cultivation Records Table (Activity Logs & Monitoring)
CREATE TABLE IF NOT EXISTS cultivation_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crop_id BIGINT NOT NULL,
    activity_type VARCHAR(50) NOT NULL, -- SOWING, IRRIGATION, FERTILIZER, PEST_CONTROL, WEEDING, HARVESTING
    activity_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    inputs_used VARCHAR(150),
    quantity_used VARCHAR(50),
    cost_incurred DECIMAL(10, 2) DEFAULT 0.00,
    observations TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_record_crop FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE CASCADE
);

-- 6. Farming Resources Table (Catalog for Seeds, Fertilizers, Machinery, Govt Schemes)
CREATE TABLE IF NOT EXISTS farming_resources (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    category VARCHAR(50) NOT NULL, -- SEEDS, FERTILIZERS, PESTICIDES, MACHINERY, GOVT_SCHEME
    description TEXT NOT NULL,
    recommended_dosage VARCHAR(150),
    contact_or_link VARCHAR(255),
    status VARCHAR(30) DEFAULT 'AVAILABLE', -- AVAILABLE, SUBSIDIZED, INFO_ONLY
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. Announcements Table (Advisories & Officer Communications)
CREATE TABLE IF NOT EXISTS announcements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    category VARCHAR(50) NOT NULL, -- WEATHER_ALERT, PEST_WARNING, GOVT_SCHEME, MARKET_UPDATE, GENERAL
    content TEXT NOT NULL,
    urgency VARCHAR(20) NOT NULL DEFAULT 'MEDIUM', -- LOW, MEDIUM, HIGH, EMERGENCY
    posted_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for fast querying
CREATE INDEX idx_land_user ON land_parcels(user_id);
CREATE INDEX idx_crop_user ON crops(user_id);
CREATE INDEX idx_crop_land ON crops(land_parcel_id);
CREATE INDEX idx_record_crop ON cultivation_records(crop_id);
CREATE INDEX idx_resource_cat ON farming_resources(category);
