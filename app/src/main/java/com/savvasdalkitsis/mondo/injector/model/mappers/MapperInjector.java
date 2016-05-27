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
package com.savvasdalkitsis.mondo.injector.model.mappers;

import com.savvasdalkitsis.mondo.model.balance.ApiBalanceToBalanceMapper;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.transactions.ApiTransactionsToTransactionsPageMapper;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.injector.infra.date.DateParserInjector.dateParser;

public class MapperInjector {

    public static Func1<ApiTransactions, TransactionsPage> apiTransactionsToTransactionsPageMapper() {
        return new ApiTransactionsToTransactionsPageMapper(dateParser());
    }

    public static Func1<ApiBalance, Balance> apiBalanceToBalanceMapper() {
        return new ApiBalanceToBalanceMapper();
    }
}
