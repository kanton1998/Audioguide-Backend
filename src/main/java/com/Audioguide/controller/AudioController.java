package com.Audioguide.controller;

import com.Audioguide.model.Audio;
import com.Audioguide.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class AudioController {

    @Autowired
    public AudioRepository audioRepository;

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/audios")
    public @ResponseBody
    Iterable<Audio> findAllAudios() {
        return audioRepository.findAll();
    }

    @DeleteMapping(path = "/audio/delete/{id}/{authToken}")
    public @ResponseBody
    String deleteAudio(@PathVariable int id, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if (user.isPresent()) {
            if (user.get().isAdmin()) {
                audioRepository.deleteById(id);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/audio")
    public @ResponseBody
    String addAudio(@RequestParam String authToken,@RequestParam String audioName, @RequestParam String audioDescription ) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if (user.isPresent()) {
            if (user.get().isAdmin()) {
                Audio audio = new Audio(audioName, audioDescription);
                audioRepository.save(audio);
                return "added";
            }
        }
        return "Not allowed";
    }

    @GetMapping(path = "/audio/{audioName}")
    public @ResponseBody
    Audio getAudioByName(@PathVariable String audioName){
        Optional<Audio> audio = audioRepository.findByFileName(audioName);
        return audio.orElse(null);
    }
}