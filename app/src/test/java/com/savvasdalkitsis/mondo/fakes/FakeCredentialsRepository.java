/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            waitForAShortTime();
        }
    }

    public void waitUntilRefreshTokenIs(String refreshToken) {
        while (!refreshToken.equals(getRefreshToken())) {
            waitForAShortTime();
        }
    }

    private void waitForAShortTime() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for token", e);
        }
    }
}
