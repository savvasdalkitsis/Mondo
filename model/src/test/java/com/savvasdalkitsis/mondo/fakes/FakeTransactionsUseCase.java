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

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeTransactionsUseCase implements TransactionsUseCase {

    private PublishSubject<Response<TransactionsPage>> publishSubject = PublishSubject.create();

    public void emitPage(TransactionsPage transactionsPage) {
        publishSubject.onNext(Response.success(transactionsPage));
        publishSubject.onCompleted();
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return publishSubject;
    }

    public void emitError() {
        publishSubject.onNext(Response.error());
        publishSubject.onCompleted();
    }
}
