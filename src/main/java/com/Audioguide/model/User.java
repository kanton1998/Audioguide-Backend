package com.Audioguide.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user
 */
@Entity
@Table(name = "user")
public class User extends DatabaseEntity {


    @NotNull
    @Size(min = 3, max = 128)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 128)
    private String lastName;

    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 256)
    private String email;

    @NotNull
    @Column(unique = true)
    private String authToken;

    @NotNull
    private String passwordHash;

    @NotNull
    private boolean admin;

    @ManyToMany
    private List<Audio> audio= new ArrayList<Audio>();


    public User() {

    }

    public User(@Size(min = 3, max = 128) String firstName, @Size(min = 3, max = 128) String lastName, @Size(min = 3, max = 256) String email, String authToken, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authToken = authToken;
        this.passwordHash = passwordHash;
        this.admin = false;
        this.audio = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Audio> getAudioAccess() {
        return audio;
    }

    public void setAudioAccess(List<Audio> audioAccess) {
        this.audio = audioAccess;
    }

    public void addAudio(Audio audio) {
        this.audio.add(audio);
    }

    public static String hashPassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }
}
