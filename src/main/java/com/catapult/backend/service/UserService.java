package com.catapult.backend.service;

import com.catapult.backend.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    private final HashMap<String, UserModel> users = new HashMap<>();

    public void register(UserModel user) {
        users.put(user.getAddress(), user);
    }

    public UserModel findByAddress(String address) {
        return users.get(address);
    }
}
