package com.savvasdalkitsis.mondo.repository;

public interface CredentialsRepository {

    void saveAccessToken(String accessToken);

    void saveRefreshToken(String refreshToken);

    void saveAccountId(String accountId);

    String getAccessToken();

    String getRefreshToken();

    String getAccountId();
}
