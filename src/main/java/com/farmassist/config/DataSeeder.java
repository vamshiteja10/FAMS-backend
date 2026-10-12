package com.farmassist.config;

import com.farmassist.entity.*;
import com.farmassist.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FarmerProfileRepository farmerProfileRepository;
    private final LandParcelRepository landParcelRepository;
    private final CropRepository cropRepository;
    private final CultivationRecordRepository cultivationRecordRepository;
    private final FarmingResourceRepository farmingResourceRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            log.info("Database already contains data; skipping initial data seeding.");
            return;
        }

        log.info("Starting initial seed data population via DataSeeder...");

        // 1. Users
        User ramesh = userRepository.save(User.builder()
                .username("farmer_ramesh")
                .password("pass123")
                .fullName("Ramesh Patel")
                .email("ramesh.farmer@farmassist.in")
                .phone("9876543210")
                .role("ROLE_FARMER")
                .build());

        User anita = userRepository.save(User.builder()
                .username("farmer_anita")
                .password("pass123")
                .fullName("Anita Sharma")
                .email("anita.sharma@farmassist.in")
                .phone("9876543211")
                .role("ROLE_FARMER")
                .build());

        User verma = userRepository.save(User.builder()
                .username("officer_verma")
                .password("pass123")
                .fullName("Dr. S. K. Verma")
                .email("skverma.agri@gov.in")
                .phone("9876543220")
                .role("ROLE_OFFICER")
                .build());

        User kiran = userRepository.save(User.builder()
                .username("admin_kiran")
                .password("pass123")
                .fullName("Kiran Kumar")
                .email("admin@farmassist.in")
                .phone("9876543299")
                .role("ROLE_ADMIN")
                .build());

        // 2. Farmer Profiles
        farmerProfileRepository.save(FarmerProfile.builder()
                .user(ramesh)
                .village("Khed")
                .district("Pune")
                .state("Maharashtra")
                .totalExperienceYears(14)
                .primaryWaterSource("Borewell & Drip Irrigation")
                .build());

        farmerProfileRepository.save(FarmerProfile.builder()
                .user(anita)
                .village("Kurnool Rural")
                .district("Kurnool")
                .state("Andhra Pradesh")
                .totalExperienceYears(9)
                .primaryWaterSource("Canal & Open Well")
                .build());

        // 3. Land Parcels
        LandParcel parcel1 = landParcelRepository.save(LandParcel.builder()
                .user(ramesh)
                .parcelName("North Canal Field")
                .surveyNumber("SY-104/A")
                .totalAreaAcres(new BigDecimal("4.50"))
                .soilType("Black")
                .irrigationType("Drip")
                .villageLocation("Khed North Sector")
                .build());

        LandParcel parcel2 = landParcelRepository.save(LandParcel.builder()
                .user(ramesh)
                .parcelName("Riverside Plot")
                .surveyNumber("SY-108/2")
                .totalAreaAcres(new BigDecimal("3.00"))
                .soilType("Alluvial")
                .irrigationType("Canal")
                .villageLocation("Near Bhima River Basin")
                .build());

        LandParcel parcel3 = landParcelRepository.save(LandParcel.builder()
                .user(anita)
                .parcelName("Hill View Meadow")
                .surveyNumber("SY-45/B")
                .totalAreaAcres(new BigDecimal("5.20"))
                .soilType("Red")
                .irrigationType("Borewell")
                .villageLocation("Kurnool East Ridge")
                .build());

        // 4. Crops
        Crop crop1 = cropRepository.save(Crop.builder()
                .landParcel(parcel1)
                .user(ramesh)
                .cropName("Cotton")
                .variety("Bt-Cotton RCH-659")
                .season("Kharif")
                .sowingDate(LocalDate.of(2026, 6, 15))
                .expectedHarvestDate(LocalDate.of(2026, 11, 20))
                .expectedYieldQuintals(new BigDecimal("35.00"))
                .status("GROWING")
                .notes("Second weeding completed; boll formation stage.")
                .build());

        Crop crop2 = cropRepository.save(Crop.builder()
                .landParcel(parcel2)
                .user(ramesh)
                .cropName("Paddy (Basmati)")
                .variety("Pusa 1121")
                .season("Kharif")
                .sowingDate(LocalDate.of(2026, 7, 1))
                .expectedHarvestDate(LocalDate.of(2026, 10, 30))
                .expectedYieldQuintals(new BigDecimal("50.00"))
                .status("FLOWERING")
                .notes("Irrigation scheduled every 3 days; panicle initiation observed.")
                .build());

        Crop crop3 = cropRepository.save(Crop.builder()
                .landParcel(parcel3)
                .user(anita)
                .cropName("Groundnut")
                .variety("Kadiri-6")
                .season("Kharif")
                .sowingDate(LocalDate.of(2026, 6, 25))
                .expectedHarvestDate(LocalDate.of(2026, 10, 15))
                .expectedYieldQuintals(new BigDecimal("28.00"))
                .status("GROWING")
                .notes("Gypsum application completed; healthy pod development.")
                .build());

        // 5. Cultivation Records
        cultivationRecordRepository.saveAll(List.of(
                CultivationRecord.builder()
                        .crop(crop1)
                        .activityType("LAND_PREPARATION")
                        .activityDate(LocalDate.of(2026, 6, 5))
                        .description("Deep ploughing and disc harrowing")
                        .inputsUsed("Tractor & Harrow")
                        .quantityUsed("2 passes")
                        .costIncurred(new BigDecimal("2500.00"))
                        .observations("Soil texture optimized for moisture retention")
                        .build(),
                CultivationRecord.builder()
                        .crop(crop1)
                        .activityType("SOWING")
                        .activityDate(LocalDate.of(2026, 6, 15))
                        .description("Direct seed drilling with spacing 90x60cm")
                        .inputsUsed("Bt-Cotton Seeds")
                        .quantityUsed("2 packets/acre")
                        .costIncurred(new BigDecimal("3800.00"))
                        .observations("Good germination rate observed (>90%)")
                        .build(),
                CultivationRecord.builder()
                        .crop(crop1)
                        .activityType("FERTILIZER")
                        .activityDate(LocalDate.of(2026, 7, 10))
                        .description("First basal dose of DAP and Potash")
                        .inputsUsed("DAP + MOP")
                        .quantityUsed("50 kg DAP + 25 kg MOP")
                        .costIncurred(new BigDecimal("2200.00"))
                        .observations("Applied along root zone before drip irrigation")
                        .build(),
                CultivationRecord.builder()
                        .crop(crop1)
                        .activityType("IRRIGATION")
                        .activityDate(LocalDate.of(2026, 7, 28))
                        .description("Drip fertigation run")
                        .inputsUsed("Water + Micronutrient mix")
                        .quantityUsed("4 hours run")
                        .costIncurred(new BigDecimal("450.00"))
                        .observations("Soil moisture maintained at optimum field capacity")
                        .build(),
                CultivationRecord.builder()
                        .crop(crop2)
                        .activityType("SOWING")
                        .activityDate(LocalDate.of(2026, 7, 1))
                        .description("Transplantation of 25-day old seedlings")
                        .inputsUsed("Pusa 1121 Seedlings")
                        .quantityUsed("2 seedlings/hill")
                        .costIncurred(new BigDecimal("3200.00"))
                        .observations("Standing water maintained at 2-3 cm")
                        .build(),
                CultivationRecord.builder()
                        .crop(crop2)
                        .activityType("PEST_CONTROL")
                        .activityDate(LocalDate.of(2026, 8, 14))
                        .description("Preventive spray against stem borer")
                        .inputsUsed("Chlorantraniliprole 18.5 SC")
                        .quantityUsed("60 ml/acre")
                        .costIncurred(new BigDecimal("1150.00"))
                        .observations("No visible pest infestation afterwards")
                        .build()
        ));

        // 6. Farming Resources
        farmingResourceRepository.saveAll(List.of(
                FarmingResource.builder()
                        .title("Neem Coated Urea (46% N)")
                        .category("FERTILIZERS")
                        .description("Slow-release nitrogenous fertilizer providing sustained nutrition and minimizing leaching losses.")
                        .recommendedDosage("45 kg per acre in split doses")
                        .contactOrLink("Local Agri Cooperative Society")
                        .status("AVAILABLE")
                        .build(),
                FarmingResource.builder()
                        .title("Single Super Phosphate (SSP - 16% P)")
                        .category("FERTILIZERS")
                        .description("Rich in Phosphorus and Calcium; ideal for root development and oilseed crops.")
                        .recommendedDosage("100 kg per acre as basal dose")
                        .contactOrLink("IFFCO Kisan Kendra")
                        .status("AVAILABLE")
                        .build(),
                FarmingResource.builder()
                        .title("Trichoderma Viride (Bio-Fungicide)")
                        .category("PESTICIDES")
                        .description("Eco-friendly biocontrol agent against root rot, wilt, and damping-off fungal pathogens.")
                        .recommendedDosage("2.5 kg mixed with 100 kg FYM per acre")
                        .contactOrLink("KVK Agri Center")
                        .status("SUBSIDIZED")
                        .build(),
                FarmingResource.builder()
                        .title("Custom Hiring Center - Laser Land Leveler")
                        .category("MACHINERY")
                        .description("Precision GPS/laser land leveling to conserve up to 25% irrigation water and increase crop yield.")
                        .recommendedDosage("Once every 3 years before Kharif")
                        .contactOrLink("Toll-free: 1800-180-1551")
                        .status("AVAILABLE")
                        .build(),
                FarmingResource.builder()
                        .title("Pradhan Mantri Fasal Bima Yojana (PMFBY)")
                        .category("GOVT_SCHEME")
                        .description("Comprehensive crop insurance coverage against non-preventable natural risks from pre-sowing to post-harvest.")
                        .recommendedDosage("2% premium for Kharif, 1.5% for Rabi")
                        .contactOrLink("https://pmfby.gov.in")
                        .status("INFO_ONLY")
                        .build(),
                FarmingResource.builder()
                        .title("PM-KISAN Samman Nidhi")
                        .category("GOVT_SCHEME")
                        .description("Direct income support of Rs. 6,000 per year in three equal installments to all landholding farmer families.")
                        .recommendedDosage("Direct bank transfer to Aadhaar linked account")
                        .contactOrLink("https://pmkisan.gov.in")
                        .status("SUBSIDIZED")
                        .build()
        ));

        // 7. Announcements
        announcementRepository.saveAll(List.of(
                Announcement.builder()
                        .title("Heavy Rainfall & Gusty Winds Advisory for Next 48 Hours")
                        .category("WEATHER_ALERT")
                        .content("District meteorological department forecasts moderate to heavy rain. Postpone fertilizer top-dressing and ensure proper drainage.")
                        .urgency("HIGH")
                        .postedBy("Dr. S. K. Verma, District Agri Officer")
                        .build(),
                Announcement.builder()
                        .title("Subsidized Drip Irrigation Equipment Applications Open")
                        .category("GOVT_SCHEME")
                        .content("State Agriculture Department is accepting applications for up to 80% subsidy on micro-irrigation systems under PMKSY.")
                        .urgency("MEDIUM")
                        .postedBy("Agricultural Officer, Horticulture Wing")
                        .build(),
                Announcement.builder()
                        .title("Pink Bollworm Vigilance in Cotton Fields")
                        .category("PEST_WARNING")
                        .content("Install 4-5 pheromone traps per acre to monitor moth activity. Spray recommended biopesticides if threshold crossed.")
                        .urgency("HIGH")
                        .postedBy("State Agricultural Extension Center")
                        .build()
        ));

        log.info("Initial seed data successfully populated by DataSeeder!");
    }
}
