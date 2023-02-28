package com.catapult.backend.controller;

import com.catapult.backend.config.Web3Authentication;
import com.catapult.backend.exception.AccountNotFoundException;
import com.catapult.backend.model.SignRequest;
import com.catapult.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class accountController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/challenge/{address}")
    public String challenge(@PathVariable String address) throws AccountNotFoundException {
        if (userService.findByAddress(address) != null) {
            return userService.findByAddress(address).getNonce();
        }
        throw new AccountNotFoundException("Account Not Found");
    }

    @PostMapping("/auth")
    public Authentication auth(@RequestBody SignRequest request) {
        var val = new Web3Authentication(request.getAddress(), request.getSignature());
        return authenticationManager.authenticate(val);
    }

}
