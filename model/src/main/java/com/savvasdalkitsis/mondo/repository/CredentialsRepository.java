package com.savvasdalkitsis.mondo.repository;

public interface CredentialsRepository {

    void saveAccessToken(String accessToken);

    void saveRefreshToken(String refreshToken);

    String getAccessToken();

    String getRefreshToken();
}
