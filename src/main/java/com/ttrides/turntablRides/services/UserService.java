package com.ttrides.turntablRides.services;

import com.ttrides.turntablRides.models.UserModel;
import com.ttrides.turntablRides.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> getUsers() { return userRepository.findAll(); }

    public UserModel insertUserDetails(UserModel user) { return userRepository.insert(user); }

    public Optional<UserModel> getUser(int id) { return userRepository.findById(id);}

    public UserModel updateUserDetails(int userId, UserModel user) {
        if (getUser(userId).isPresent()) {
            UserModel userDetails = getUser(userId).get();
            userDetails.setFirstName(user.getFirstName());
            userDetails.setLastName(user.getLastName());
            userDetails.setEmail(user.getEmail());
            userDetails.setProfileUrl(user.getProfileUrl());
            return userRepository.save(userDetails);
        }
        return user;
    }

    public void deleteUserDetails(int userId) { userRepository.deleteById(userId); }

    public Optional<UserModel> fetchUserByEmail(String email) { return userRepository.findUserByEmail(email); }
}
