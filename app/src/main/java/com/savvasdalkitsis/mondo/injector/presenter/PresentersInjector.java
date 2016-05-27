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
package com.savvasdalkitsis.mondo.injector.presenter;

import com.savvasdalkitsis.mondo.presenter.authentication.AuthenticationPresenter;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;

import static com.savvasdalkitsis.mondo.injector.usecase.authentication.AuthenticationUserCaseInjector.authenticationUseCase;
import static com.savvasdalkitsis.mondo.injector.usecase.balance.BalanceUserCaseInjector.balanceUseCase;
import static com.savvasdalkitsis.mondo.injector.usecase.transactions.TransactionsUserCaseInjector.transactionsUseCase;

public class PresentersInjector {
    public static TransactionsPresenter transactionsPresenter() {
        return new TransactionsPresenter(balanceUseCase(), transactionsUseCase());
    }

    public static AuthenticationPresenter authenticationPresenter() {
        return new AuthenticationPresenter(authenticationUseCase());
    }
}
