package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

public class FakeCredentialsRepository implements CredentialsRepository {

    private String authToken;
    private String refreshToken;
    private String accountId;

    @Override
    public void saveAccessToken(String accessToken) {
        this.authToken = accessToken;
    }

    @Override
    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public void saveAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getAccessToken() {
        return authToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    public void waitUntilAccessTokenIs(String accessToken) {
        while (!accessToken.equals(getAccessToken())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted while waiting for token", e);
            }
        }
    }
}
