package com.catapult.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String address;
    private String nonce = UUID.randomUUID().toString();
}
