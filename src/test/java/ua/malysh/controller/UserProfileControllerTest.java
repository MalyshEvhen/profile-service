package ua.malysh.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.malysh.domain.Gender;
import ua.malysh.domain.Physique;
import ua.malysh.domain.UserProfile;
import ua.malysh.service.DietCalculatorService;
import ua.malysh.service.UserProfileService;
import ua.malysh.service.exceptions.ProfileAlreadyExistsException;
import ua.malysh.service.exceptions.ProfileNotFoundException;
import ua.malysh.service.impl.DietCalculatorServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserProfileController.class)
class UserProfileControllerTest {
    private static final String URL = "/api/v1/user-profiles";

    @MockBean
    private UserProfileService profileService;

    private DietCalculatorService dietService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserProfile profile;

    @BeforeEach
    void setUp() {
        dietService = new DietCalculatorServiceImpl();

        var physique = new Physique();
        physique.setAge(30);
        physique.setGender(Gender.MALE);
        physique.setHight(180D);
        physique.setWeight(80D);

        var diet = dietService.getDiet(null, null);

        profile = new UserProfile();
        profile.setUserId(1L);
        profile.setName("John Doe");
        profile.setPhysique(physique);
        profile.setDiet(diet);
    }

    @Test
    void shouldReturnStatusCreated() throws Exception {
        when(profileService.save(profile))
                .thenReturn(1L);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        when(profileService.save(any(UserProfile.class)))
                .thenThrow(ProfileAlreadyExistsException.class);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile)))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }

    @Test
    void shouldReturnStatusOk() throws Exception {
        when(profileService.findByUserId(profile.getUserId())).thenReturn(profile);

        mockMvc.perform(get(URL + "/by-user").param("userId", "1"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        when(profileService.findByUserId(anyLong()))
            .thenThrow(ProfileNotFoundException.class);

        mockMvc.perform(get(URL + "/by-user")
                .param("userId", "1"))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
}
