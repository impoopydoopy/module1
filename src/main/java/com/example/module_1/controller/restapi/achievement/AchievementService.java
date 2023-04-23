package com.example.module_1.controller.restapi.achievement;

import com.example.module_1.controller.model.AchievementDTO;
import com.example.module_1.controller.model.GameDTO;
import com.example.module_1.controller.repository.AchievementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AchievementService {
    AchievementRepository achievementRepository;

    public void addAchievement(AchievementDTO achievementDTO){
        achievementRepository.save(achievementDTO);
    }

    public List<AchievementDTO> getAllAchievements(){
        return achievementRepository.findAll();
    }

    public AchievementDTO getAchievementById(String id){
        Optional<AchievementDTO> achievementDTO = achievementRepository.findById(id);
        return achievementDTO.orElseGet(AchievementDTO::new);
    }
}
