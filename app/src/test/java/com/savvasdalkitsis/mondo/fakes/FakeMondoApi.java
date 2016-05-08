package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeMondoApi implements MondoApi {

    private PublishSubject<Result<ApiBalance>> balanceSubject;
    private PublishSubject<Result<ApiTransactions>> transactionsSubject;

    @Override
    public Observable<Result<ApiBalance>> getBalance() {
        balanceSubject = PublishSubject.create();
        return balanceSubject;
    }

    @Override
    public Observable<Result<ApiTransactions>> getTransactions() {
        transactionsSubject = PublishSubject.create();
        return transactionsSubject;
    }


    public void emitSuccessfulBalance(ApiBalance balance) {
        emitBalanceAndFinish(Result.response(Response.success(balance)));
    }

    public void emitBalanceError() {
        balanceSubject.onError(new IOException("Error during api call"));
    }

    public void emitBalanceErrorResponse() {
        emitBalanceAndFinish(Result.response(Response.error(500, ResponseBody.create(null, ""))));
    }

    private void emitBalanceAndFinish(Result<ApiBalance> result) {
        balanceSubject.onNext(result);
        balanceSubject.onCompleted();
    }

    public void emitSuccessfulTransactionPage(ApiTransactions apiTransactions) {
        transactionsSubject.onNext(Result.response(Response.success(apiTransactions)));
        transactionsSubject.onCompleted();
    }
}
