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
package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;
import rx.functions.Func1;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private Func1<ApiBalance, Balance> mapper;
    private CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall;

    public MondoBalanceUseCase(MondoApi mondoApi, Func1<ApiBalance, Balance> mapper,
                               CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall) {
        this.mondoApi = mondoApi;
        this.mapper = mapper;
        this.cachedNetworkCall = cachedNetworkCall;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return mondoApi.getBalance().compose(cachedNetworkCall.withMapper(mapper, ApiBalance.class));
    }
}