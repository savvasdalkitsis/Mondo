package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiAccounts;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiOAuthToken;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeMondoApi implements MondoApi {

    private PublishSubject<Result<ApiBalance>> balanceSubject;
    private PublishSubject<Result<ApiAccounts>> accountsSubject;
    private PublishSubject<Result<ApiTransactions>> transactionsSubject;
    private final Map<String, PublishSubject<Result<ApiOAuthToken>>> oAuthSubjects = new HashMap<>();

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

    @Override
    public Observable<Result<ApiOAuthToken>> oAuthToken(String clientId, String clientSecret, String code) {
        return oAuthSubjects.get(keyFor(clientId, clientSecret, code));
    }

    public Observable<Result<ApiAccounts>> getAccounts() {
        accountsSubject = PublishSubject.create();
        return accountsSubject;
    }

    public void emitSuccessfulBalance(ApiBalance balance) {
        emitBalanceAndFinish(Result.response(Response.success(balance)));
    }

    public void emitBalanceError() {
        balanceSubject.onError(ioException());
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

    public void emitTransactionsError() {
        transactionsSubject.onError(ioException());
    }

    private IOException ioException() {
        return new IOException("Error during api call");
    }

    public void emitTransactionsErrorResponse() {
        transactionsSubject.onNext(Result.response(Response.error(500, ResponseBody.create(null, ""))));
        transactionsSubject.onCompleted();
    }

    public void acceptsOAuthCall(String clientId, String clientSecret, String code) {
        oAuthSubjects.put(keyFor(clientId, clientSecret, code), PublishSubject.create());
    }

    public void emitSuccessfulOAuthFor(String clientId, String clientSecret, String code, ApiOAuthToken apiOAuthToken) {
        PublishSubject<Result<ApiOAuthToken>> subject = oAuthSubjects.get(keyFor(clientId, clientSecret, code));
        subject.onNext(Result.response(Response.success(apiOAuthToken)));
        subject.onCompleted();
    }

    public void emitErrorOAuth(String clientId, String clientSecret, String code) {
        PublishSubject<Result<ApiOAuthToken>> subject = oAuthSubjects.get(keyFor(clientId, clientSecret, code));
        subject.onError(new IOException("error getting oauth token"));
    }

    public void emitSuccessfulAccounts(ApiAccounts apiAccounts) {
        accountsSubject.onNext(Result.response(Response.success(apiAccounts)));
        accountsSubject.onCompleted();
    }

    private String keyFor(String clientId, String clienSecret, String code) {
        return clientId + "::" + clienSecret + "::" + code;
    }
}
