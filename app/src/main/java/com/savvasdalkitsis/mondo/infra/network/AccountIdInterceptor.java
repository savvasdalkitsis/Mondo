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
package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.savvasdalkitsis.mondo.util.StringUtils.isNotEmptyNorNull;

public class AccountIdInterceptor implements Interceptor {

    private CredentialsRepository credentialsRepository;

    public AccountIdInterceptor(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("account_id", accountId())
                    .build();
            return chain.proceed(request.newBuilder().url(url).build());
        }
        return chain.proceed(request);
    }

    private String accountId() {
        String accountId = credentialsRepository.getAccountId();
        return isNotEmptyNorNull(accountId) ? accountId : "INVALID_ACCOUNT_ID";
    }
}
