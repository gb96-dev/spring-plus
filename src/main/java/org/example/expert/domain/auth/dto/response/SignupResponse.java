package org.example.expert.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse { // SigninResponse도 동일한 구조
    private final String bearerToken;

    public SignupResponse(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}