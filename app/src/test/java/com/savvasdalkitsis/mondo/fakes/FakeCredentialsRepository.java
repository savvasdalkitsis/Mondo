package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

public class FakeCredentialsRepository implements CredentialsRepository {

    private String authToken;

    @Override
    public void saveAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getAuthToken() {
        return authToken;
    }
}
