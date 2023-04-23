package com.example.module_1.controller.restapi.achievement;

import com.example.module_1.controller.model.AchievementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AchievementRestController {

    private final AchievementService achievementService;

    @PostMapping("/addAchievement")
    public void addAchievement(@RequestBody AchievementDTO achievementDTO){
        achievementService.addAchievement(achievementDTO);
    }
}
