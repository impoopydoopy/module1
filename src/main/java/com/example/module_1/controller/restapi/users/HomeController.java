package com.example.module_1.controller.restapi.users;

import com.example.module_1.controller.model.AchievementDTO;
import com.example.module_1.controller.model.GameDTO;
import com.example.module_1.controller.model.UserDTO;
import com.example.module_1.controller.restapi.achievement.AchievementService;
import com.example.module_1.controller.restapi.games.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final GameService gameService;
    private final AchievementService achievementService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {
        UserDTO userDTO = userService.getUserById(id);
        model.addAttribute("userDTO", userDTO);

        List<GameDTO> gameList = new ArrayList<>();
        if(userDTO.getGameIds() != null){
            for (String idGame: userDTO.getGameIds()) {
                gameList.add(gameService.getGameById(idGame));
            }
        }
        model.addAttribute("gameList", gameList);

        List<AchievementDTO> achievementsList = new ArrayList<>();
        if(userDTO.getAchievements() != null){
            for (String idAchievement: userDTO.getAchievements()) {
                achievementsList.add(achievementService.getAchievementById(idAchievement));
            }
        }
        model.addAttribute("achievementsList", achievementsList);

        return "getUser";
    }

    @GetMapping("/getAllUsers")
    public String getaAlUsers(Model model) {
        List<UserDTO> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "getAllUsers";
    }

    @GetMapping("/users/search")
    public String searchUsersByNickname(@RequestParam("nickname") String nickname, Model model) {
        List<UserDTO> userList = userService.getUserByName(nickname);
        model.addAttribute("userList", userList);
        return "getAllUsers";
    }

    @GetMapping("/newUser")
    public String addUser(Model model) {
        return "addUser";
    }

    @GetMapping("/updateUser/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        UserDTO userDTO = userService.getUserById(id);
        String nickname = userDTO.getNickname();
        String description = userDTO.getProfileDescription();
        System.out.println(userDTO.getProfileDescription());
        model.addAttribute("id", id);
        model.addAttribute("nickname", nickname);
        model.addAttribute("description", description);

        List<GameDTO> allGameList = gameService.getAllGames();
        model.addAttribute("allGameList", allGameList);

        List<AchievementDTO> allAchievementsList = achievementService.getAllAchievements();
        model.addAttribute("allAchievementsList", allAchievementsList);

        return "updateUser";
    }

}
