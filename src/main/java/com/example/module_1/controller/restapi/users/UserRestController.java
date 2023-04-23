package com.example.module_1.controller.restapi.users;

import com.example.module_1.controller.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    @PutMapping("/updateUser/{id}")
    public void updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO){
        userService.updateUserById(id, userDTO);
    }

    @GetMapping("/updateUserGame/{userId}/{gameId}")
    public void updateGameUser(@PathVariable("userId") String userId, @PathVariable("gameId") String gameId){
        userService.addGameById(userId, gameId);
    }

    @GetMapping("/updateUserAchievement/{userId}/{achievementId}")
    public void updateUserAchievement(@PathVariable("userId") String userId, @PathVariable("achievementId") String achievementId){
        userService.addAchievementById(userId, achievementId);
    }

    @GetMapping ("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteUserById(id);
        return "User has been deleted";
    }
}
