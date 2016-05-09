package com.savvasdalkitsis.mondo.repository;

public interface CredentialsRepository {

    void saveAuthToken(String authToken);

    String getAuthToken();
}
