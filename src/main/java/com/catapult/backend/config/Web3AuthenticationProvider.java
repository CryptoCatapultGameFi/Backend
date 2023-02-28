package com.catapult.backend.config;

import com.catapult.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;

@Component
@Slf4j
@RequiredArgsConstructor
public class Web3AuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private boolean valid(String signature, String address, String nonce) throws SignatureException {
        var r = signature.substring(0, 66);
        var s = "0x" + signature.substring(66,130);
        var v = "0x" + signature.substring(130,132);


        var data = new Sign.SignatureData(
                Numeric.hexStringToByteArray(v),
                Numeric.hexStringToByteArray(r),
                Numeric.hexStringToByteArray(s)
        );

        var key = Sign.signedPrefixedMessageToKey(nonce.getBytes(), data);
        return matches(key, address);
    }

    private boolean matches(BigInteger key, String address) {
        return ("0x"+ Keys.getAddress(key).toLowerCase()).equals(address.toLowerCase());
    }

//    private fun matches(key: BigInteger, address: String): Boolean {
//        return "0x${Keys.getAddress(key).lowercase()}" == address.lowercase()
//    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = userService.findByAddress(authentication.getName());
        log.info("1");
        var signature = authentication.getCredentials().toString();
        log.info("2");
        try {
            if (valid(signature, user.getAddress(), user.getNonce())) {
                return new Web3Authentication(user.getAddress(),signature);
            }
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Web3Authentication.class == authentication;
    }
}
