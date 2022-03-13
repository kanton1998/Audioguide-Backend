package com.Audioguide.controller;

import com.Audioguide.model.Audio;
import com.Audioguide.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;

import static com.Audioguide.model.User.hashPassword;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired AudioRepository audioRepository;

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{authToken}")
    public @ResponseBody
    Optional<User> getOneUserByAuthToken(@PathVariable String authToken) {
        return userRepository.findByAuthToken(authToken);
    }

    @DeleteMapping("/user/{userId}/{authToken}")
    public @ResponseBody
    String deleteUser(@PathVariable Integer userId, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                userRepository.deleteById(userId);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/user")
    public @ResponseBody
    User addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password){
        Optional<User> tempUser = userRepository.findByEmail(email);
        if(tempUser.isPresent()){
            return tempUser.get();
        }
        UUID authToken = UUID.randomUUID();
        String passwordHash = hashPassword(password);
        User user = new User(firstName, lastName, email, authToken.toString(), passwordHash);
        return userRepository.save(user);
    }

    @GetMapping(path= "/user/{email}/{password}")
    public @ResponseBody
    User findUserByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPasswordHash().equals(hashPassword(password))) {
            return user.get();
        }
        return null;
    }

   /* @PutMapping(path = "/user")
    public @ResponseBody
    User updateUserAudioAccess(@RequestParam String authToken, @RequestParam String userEmail, @RequestParam String audioName ){
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<User> user2= userRepository.findByAuthToken(authToken);
        Optional<Audio> audio= audioRepository.findByFileName(audioName);
        if(user2.isPresent()){
            if (user.isPresent()){
                if (user2.get().isAdmin()){
                    if (audio.isPresent()){
                        userRepository.findByEmail(userEmail).get().addAudio(audio.get());
                        userRepository.save(user.get());
                    }
                }
            }
        }
        return null;
    }*/
}
