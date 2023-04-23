package com.example.module_1.controller.restapi.users;

import com.example.module_1.controller.model.UserDTO;
import com.example.module_1.controller.repository.AchievementRepository;
import com.example.module_1.controller.repository.UserRepository;
import com.example.module_1.controller.restapi.achievement.AchievementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, mongoTemplate);
    }

    @Test
    public void testUpdateUserById() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname("newNickname");
        userDTO.setProfileDescription("newDescription");

        UserDTO savedUser = new UserDTO();
        userRepository.save(userDTO);

        userDTO.setNickname("newNickname1");
        userDTO.setProfileDescription("newDescription1");

        userService.updateUserById(savedUser.getId(), userDTO);

        Optional<UserDTO> updatedUserDTO = userRepository.findById(savedUser.getId());
        if(updatedUserDTO.isPresent())
        {
            UserDTO updatedUser = updatedUserDTO.orElse(new UserDTO());
            assertThat(updatedUser.getNickname()).isEqualTo(userDTO.getNickname());
            assertThat(updatedUser.getProfileDescription()).isEqualTo(userDTO.getProfileDescription());
        }
    }

    @Test
    public void testAddAchievementById() {
        String userId = "1";
        String achievementId = "2";

        UserDTO user = new UserDTO();
        user.setId(userId);
        List<String> achievements = new ArrayList<>();
        user.setAchievements(achievements);

        Optional<UserDTO> optionalUser = Optional.of(user);

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(optionalUser);

        userService.addAchievementById(userId, achievementId);

        assertEquals(1, achievements.size());
        assertEquals(achievementId, achievements.get(0));
    }

    @Test
    public void testDeleteUserById() {
        UserDTO user = new UserDTO();
        user.setNickname("testuser");
        user.setProfileDescription("testpass");
        userRepository.save(user);

        userRepository.deleteById(user.getId());

        assertFalse(userRepository.findById(user.getId()).isPresent(), "User was not deleted");
    }
}