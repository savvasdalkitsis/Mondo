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
package com.savvasdalkitsis.mondo.injector.usecase.transactions;

import com.savvasdalkitsis.mondo.usecase.transactions.MondoTransactionsUseCase;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;

import static com.savvasdalkitsis.mondo.injector.infra.network.CachedNetworkCallInjector.cachedNetworkCall;
import static com.savvasdalkitsis.mondo.injector.model.mappers.MapperInjector.apiTransactionsToTransactionsPageMapper;
import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class TransactionsUserCaseInjector {
    public static TransactionsUseCase transactionsUseCase() {
        return new MondoTransactionsUseCase(mondoApi(), apiTransactionsToTransactionsPageMapper(), cachedNetworkCall());
    }

}
