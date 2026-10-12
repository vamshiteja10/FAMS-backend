-- ==========================================================
-- FarmAssist - Spring Boot Seed Data for H2/MySQL
-- ==========================================================

-- 1. Users
INSERT INTO users (id, username, password, full_name, email, phone, role, created_at) VALUES
(1, 'farmer_ramesh', 'pass123', 'Ramesh Patel', 'ramesh.farmer@farmassist.in', '9876543210', 'ROLE_FARMER', CURRENT_TIMESTAMP),
(2, 'farmer_anita', 'pass123', 'Anita Sharma', 'anita.sharma@farmassist.in', '9876543211', 'ROLE_FARMER', CURRENT_TIMESTAMP),
(3, 'officer_verma', 'pass123', 'Dr. S. K. Verma', 'skverma.agri@gov.in', '9876543220', 'ROLE_OFFICER', CURRENT_TIMESTAMP),
(4, 'admin_kiran', 'pass123', 'Kiran Kumar', 'admin@farmassist.in', '9876543299', 'ROLE_ADMIN', CURRENT_TIMESTAMP);

-- 2. Farmer Profiles
INSERT INTO farmer_profiles (id, user_id, village, district, state, total_experience_years, primary_water_source, created_at) VALUES
(1, 1, 'Khed', 'Pune', 'Maharashtra', 14, 'Borewell & Drip Irrigation', CURRENT_TIMESTAMP),
(2, 2, 'Kurnool Rural', 'Kurnool', 'Andhra Pradesh', 9, 'Canal & Open Well', CURRENT_TIMESTAMP);

-- 3. Land Parcels
INSERT INTO land_parcels (id, user_id, parcel_name, survey_number, total_area_acres, soil_type, irrigation_type, village_location, created_at) VALUES
(1, 1, 'North Canal Field', 'SY-104/A', 4.50, 'Black', 'Drip', 'Khed North Sector', CURRENT_TIMESTAMP),
(2, 1, 'Riverside Plot', 'SY-108/2', 3.00, 'Alluvial', 'Canal', 'Near Bhima River Basin', CURRENT_TIMESTAMP),
(3, 2, 'Hill View Meadow', 'SY-45/B', 5.20, 'Red', 'Borewell', 'Kurnool East Ridge', CURRENT_TIMESTAMP);

-- 4. Crops
INSERT INTO crops (id, land_parcel_id, user_id, crop_name, variety, season, sowing_date, expected_harvest_date, expected_yield_quintals, status, notes, created_at) VALUES
(1, 1, 1, 'Cotton', 'Bt-Cotton RCH-659', 'Kharif', '2026-06-15', '2026-11-20', 35.00, 'GROWING', 'Second weeding completed; boll formation stage.', CURRENT_TIMESTAMP),
(2, 2, 1, 'Paddy (Basmati)', 'Pusa 1121', 'Kharif', '2026-07-01', '2026-10-30', 50.00, 'FLOWERING', 'Irrigation scheduled every 3 days; panicle initiation observed.', CURRENT_TIMESTAMP),
(3, 3, 2, 'Groundnut', 'Kadiri-6', 'Kharif', '2026-06-25', '2026-10-15', 28.00, 'GROWING', 'Gypsum application completed; healthy pod development.', CURRENT_TIMESTAMP);

-- 5. Cultivation Records
INSERT INTO cultivation_records (id, crop_id, activity_type, activity_date, description, inputs_used, quantity_used, cost_incurred, observations, created_at) VALUES
(1, 1, 'LAND_PREPARATION', '2026-06-05', 'Deep ploughing and disc harrowing', 'Tractor & Harrow', '2 passes', 2500.00, 'Soil texture optimized for moisture retention', CURRENT_TIMESTAMP),
(2, 1, 'SOWING', '2026-06-15', 'Direct seed drilling with spacing 90x60cm', 'Bt-Cotton Seeds', '2 packets/acre', 3800.00, 'Good germination rate observed (>90%)', CURRENT_TIMESTAMP),
(3, 1, 'FERTILIZER', '2026-07-10', 'First basal dose of DAP and Potash', 'DAP + MOP', '50 kg DAP + 25 kg MOP', 2200.00, 'Applied along root zone before drip irrigation', CURRENT_TIMESTAMP),
(4, 1, 'IRRIGATION', '2026-07-28', 'Drip fertigation run', 'Water + Micronutrient mix', '4 hours run', 450.00, 'Soil moisture maintained at optimum field capacity', CURRENT_TIMESTAMP),
(5, 2, 'SOWING', '2026-07-01', 'Transplantation of 25-day old seedlings', 'Pusa 1121 Seedlings', '2 seedlings/hill', 3200.00, 'Standing water maintained at 2-3 cm', CURRENT_TIMESTAMP),
(6, 2, 'PEST_CONTROL', '2026-08-14', 'Preventive spray against stem borer', 'Chlorantraniliprole 18.5 SC', '60 ml/acre', 1150.00, 'No visible pest infestation afterwards', CURRENT_TIMESTAMP);

-- 6. Farming Resources
INSERT INTO farming_resources (id, title, category, description, recommended_dosage, contact_or_link, status, created_at) VALUES
(1, 'Neem Coated Urea (46% N)', 'FERTILIZERS', 'Slow-release nitrogenous fertilizer providing sustained nutrition and minimizing leaching losses.', '45 kg per acre in split doses', 'Local Agri Cooperative Society', 'AVAILABLE', CURRENT_TIMESTAMP),
(2, 'Single Super Phosphate (SSP - 16% P)', 'FERTILIZERS', 'Rich in Phosphorus and Calcium; ideal for root development and oilseed crops.', '100 kg per acre as basal dose', 'IFFCO Kisan Kendra', 'AVAILABLE', CURRENT_TIMESTAMP),
(3, 'Trichoderma Viride (Bio-Fungicide)', 'PESTICIDES', 'Eco-friendly biocontrol agent against root rot, wilt, and damping-off fungal pathogens.', '2.5 kg mixed with 100 kg FYM per acre', 'KVK Agri Center', 'SUBSIDIZED', CURRENT_TIMESTAMP),
(4, 'Custom Hiring Center - Laser Land Leveler', 'MACHINERY', 'Precision GPS/laser land leveling to conserve up to 25% irrigation water and increase crop yield.', 'Once every 3 years before Kharif', 'Toll-free: 1800-180-1551', 'AVAILABLE', CURRENT_TIMESTAMP),
(5, 'Pradhan Mantri Fasal Bima Yojana (PMFBY)', 'GOVT_SCHEME', 'Comprehensive crop insurance coverage against non-preventable natural risks from pre-sowing to post-harvest.', '2% premium for Kharif, 1.5% for Rabi', 'https://pmfby.gov.in', 'INFO_ONLY', CURRENT_TIMESTAMP),
(6, 'PM-KISAN Samman Nidhi', 'GOVT_SCHEME', 'Direct income support of Rs. 6,000 per year in three equal installments to all landholding farmer families.', 'Direct bank transfer to Aadhaar linked account', 'https://pmkisan.gov.in', 'SUBSIDIZED', CURRENT_TIMESTAMP);

-- 7. Announcements
INSERT INTO announcements (id, title, category, content, urgency, posted_by, created_at) VALUES
(1, 'Heavy Rainfall & Gusty Winds Advisory for Next 48 Hours', 'WEATHER_ALERT', 'District meteorological department forecasts moderate to heavy rain. Postpone fertilizer top-dressing and ensure proper drainage.', 'HIGH', 'Dr. S. K. Verma, District Agri Officer', CURRENT_TIMESTAMP),
(2, 'Subsidized Drip Irrigation Equipment Applications Open', 'GOVT_SCHEME', 'State Agriculture Department is accepting applications for up to 80% subsidy on micro-irrigation systems under PMKSY.', 'MEDIUM', 'Agricultural Officer, Horticulture Wing', CURRENT_TIMESTAMP),
(3, 'Pink Bollworm Vigilance in Cotton Fields', 'PEST_WARNING', 'Install 4-5 pheromone traps per acre to monitor moth activity. Spray recommended biopesticides if threshold crossed.', 'HIGH', 'State Agricultural Extension Center', CURRENT_TIMESTAMP);
