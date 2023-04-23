package com.example.module_1.controller.restapi.achievement;

import com.example.module_1.controller.model.AchievementDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    @GetMapping("/getAllAchievements")
    public String getAllAchievements(Model model){
        List<AchievementDTO> achievementDTOList = achievementService.getAllAchievements();
        model.addAttribute("achievementList", achievementDTOList);
        return "getAllAchievements";
    }
}
