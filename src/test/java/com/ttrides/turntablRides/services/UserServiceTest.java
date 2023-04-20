package com.ttrides.turntablRides.services;

import com.ttrides.turntablRides.models.UserModel;
import com.ttrides.turntablRides.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() {
        List<UserModel> list = new ArrayList<UserModel>();
        UserModel empOne = new UserModel(1, "John", "John", "profileUri", "howtodoinjava@gmail.com");
        UserModel empTwo = new UserModel(2, "Alex", "kolenchiski", "profileUri", "alexk@yahoo.com");
        UserModel empThree = new UserModel(3, "Steve", "Waugh", "profileUri", "swaugh@gmail.com");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(repository.findAll())
                .thenReturn(list);

        List<UserModel> users = service.getUsers();

        assertEquals(3, users.size(), "users");
        verify(repository, times(1)).findAll();
    }

    @Test
    void insertUserDetails() {
        UserModel user = new UserModel(1, "John", "Doe", "profileUri", "user@mail.com");
        when(repository.insert(user)).thenReturn(user);
        assertEquals(user, service.insertUserDetails(user), "user profile created");
        verify(repository, times(1)).insert(user);
    }

    @Test
    void getUser() {
        when(repository.findById(1)).thenReturn(Optional.of(new UserModel(1, "John", "Doe", "profileUri", "user@email.com")));

        UserModel user = service.getUser(1).get();

        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("user@email.com", user.getEmail());
    }

    @Test
    void getUserThatDoesNotExists() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), service.getUser(1));
    }

    @Test
    void updateUserDetails() {
        int id = 1;
        UserModel user = new UserModel(1, "John", "Doe", "profileUri", "email@mail.com");
        UserModel updateUser = new UserModel(1, "John", "Doe", "profileUri", "user@email.com");

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(repository.save(any(UserModel.class))).thenAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        UserModel updatedUser = service.updateUserDetails(id, updateUser);

        assertEquals(updateUser, updatedUser);
        assertEquals("user@email.com", updatedUser.getEmail());
    }

    @Test
    void deleteUserDetails() {
        int id = 1;
        willDoNothing().given(repository).deleteById(id);

    }

    @Test
    void fetchUserByEmail() {
        String email = "email@mail.com";
        UserModel user = new UserModel(1, "John", "Doe", "profileUri", "email@mail.com");

        when(repository.findUserByEmail(email)).thenReturn(Optional.of(user));

        UserModel searchedUser = service.fetchUserByEmail(email).get();

        assertEquals(email, searchedUser.getEmail());
    }

    @Test
    void fetchNonExistingUserByEmail() {
        String email = "email@mail.com";

        when(repository.findUserByEmail(email)).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), service.fetchUserByEmail(email));
    }
}