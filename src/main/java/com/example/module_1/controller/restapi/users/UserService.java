package com.example.module_1.controller.restapi.users;

import com.example.module_1.controller.model.UserDTO;
import com.example.module_1.controller.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public void addUser(UserDTO userDTO){
        userRepository.save(userDTO);
    }

    public UserDTO getUserById(String id){
        Optional<UserDTO> userDTO = userRepository.findById(id);
        return userDTO.orElseGet(UserDTO::new);
    }

    public List<UserDTO> getUserByName(String nickname){
        Query query = new Query();
        query.addCriteria(Criteria.where("nickname").is(nickname));
        return mongoTemplate.find(query, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
       return userRepository.findAll();
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public void updateUserById(String id, UserDTO userDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update().set("nickname", userDTO.nickname);
        Update updateSecond = new Update().set("profileDescription", userDTO.profileDescription);

        mongoTemplate.updateFirst(query, update, UserDTO.class);
        mongoTemplate.updateFirst(query, updateSecond, UserDTO.class);
    }

    public void addGameById(String userId, String gameId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));

        Update update;

        List<String> gameDTOS = getUserById(userId).getGameIds();
        if(gameDTOS == null){
            List<String> gameDTOSNew = new ArrayList<String>();
            gameDTOSNew.add(gameId);
            update = new Update().set("gameIds", gameDTOSNew);
        }
        else {
            gameDTOS.add(gameId);
            update = new Update().set("gameIds", gameDTOS);
        }

        mongoTemplate.updateFirst(query, update, UserDTO.class);
    }

    public void addAchievementById(String userId, String achievementID) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));

        Update update;

        List<String> achievements = getUserById(userId).getAchievements();
        if(achievements == null){
            List<String> achievementsNew = new ArrayList<String>();
            achievementsNew.add(achievementID);
            update = new Update().set("achievements", achievementsNew);
        }
        else {
            achievements.add(achievementID);
            update = new Update().set("achievements", achievements);
        }

        mongoTemplate.updateFirst(query, update, UserDTO.class);
    }
}
