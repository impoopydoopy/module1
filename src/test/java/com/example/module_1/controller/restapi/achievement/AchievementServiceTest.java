package com.example.module_1.controller.restapi.achievement;

import com.example.module_1.controller.model.AchievementDTO;
import com.example.module_1.controller.repository.AchievementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AchievementServiceTest {
    @Mock
    private AchievementRepository achievementRepository;

    private AchievementService achievementService;

    public AchievementServiceTest() {
        MockitoAnnotations.openMocks(this);
        achievementService = new AchievementService(achievementRepository);
    }

    @Test
    void testGetAchievementById() {

        String id = "1";
        AchievementDTO expectedAchievementDTO = new AchievementDTO();
        expectedAchievementDTO.setId(id);

        when(achievementRepository.findById(id)).thenReturn(Optional.of(expectedAchievementDTO));

        AchievementDTO actualAchievementDTO = achievementService.getAchievementById(id);

        assertEquals(expectedAchievementDTO, actualAchievementDTO);
    }

    @Test
    void testGetAllAchievements() {

        AchievementDTO achievementDTO1 = new AchievementDTO();
        AchievementDTO achievementDTO2 = new AchievementDTO();
        List<AchievementDTO> expectedAchievements = Arrays.asList(achievementDTO1, achievementDTO2);

        when(achievementRepository.findAll()).thenReturn(expectedAchievements);

        List<AchievementDTO> actualAchievements = achievementService.getAllAchievements();

        assertEquals(expectedAchievements.size(), actualAchievements.size());
    }

}