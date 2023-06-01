package ua.malysh.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.malysh.domain.ActivityLevel;
import ua.malysh.domain.DietType;
import ua.malysh.domain.Gender;
import ua.malysh.domain.Physique;
import ua.malysh.domain.UserProfile;
import ua.malysh.service.UserProfileService;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "spring.datasource.url=jdbc:tc:postgresql:15:///test_db")
@Transactional
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserProfileServiceImplTest {


    @Autowired
    private UserProfileService service;

    private long userId;
    private UserProfile profile;

    @BeforeEach
    void setUp() {
        userId = 1L;
        var username = "john Doe";
        var physique = new Physique();
        physique.setActivityLevel(ActivityLevel.LOW_ACTIVE);
        physique.setAge(30);
        physique.setGender(Gender.FEMALE);
        physique.setHight(165D);
        physique.setWeight(65D);

        profile = new UserProfile();
        profile.setDietType(DietType.MAINTENANCE_WEIGHT);
        profile.setUserId(userId);
        profile.setName(username);
        profile.setPhysique(physique);
    }

    @Test
    void shouldSaveNewRecord() {
        Long savedProfileId = service.save(profile);
        assertNotNull(savedProfileId);
    }

    @Test
    void shouldRetrieveExistingRecordByUserId() {
        Long profileId = service.save(profile);
        var profileFromDb = service.findByUserId(userId);

        profile.setId(profileId);

        assertEquals(profile, profileFromDb);
    }
}
