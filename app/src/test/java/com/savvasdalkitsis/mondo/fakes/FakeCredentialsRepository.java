package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

public class FakeCredentialsRepository implements CredentialsRepository {

    private String authToken;
    private String refreshToken;

    @Override
    public void saveAccessToken(String accessToken) {
        this.authToken = accessToken;
    }

    @Override
    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String getAccessToken() {
        return authToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }
}
