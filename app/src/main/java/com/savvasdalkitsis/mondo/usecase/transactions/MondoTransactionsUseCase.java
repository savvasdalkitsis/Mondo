package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.rx.RxTransformers.applyAndroidSchedulers;
import static com.savvasdalkitsis.mondo.rx.RxTransformers.onErrorToErrorResponse;

public class MondoTransactionsUseCase implements TransactionsUseCase {

    private MondoApi mondoApi;
    private Func1<ApiTransactions, TransactionsPage> mapper;
    private ObservableCache<ApiTransactions> observableCache;

    public MondoTransactionsUseCase(MondoApi mondoApi,
                                    Func1<ApiTransactions, TransactionsPage> mapper,
                                    ObservableCache<ApiTransactions> observableCache) {
        this.mondoApi = mondoApi;
        this.mapper = mapper;
        this.observableCache = observableCache;
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return mondoApi.getTransactions()
                .compose(observableCache.on(ApiTransactions.class))
                .map(result -> {
                    if (isSuccess(result)) {
                        return Response.success(mapper.call(result.response().body()));
                    }
                    return Response.<TransactionsPage>error();
                })
                .compose(applyAndroidSchedulers())
                .compose(onErrorToErrorResponse());
    }

    private boolean isSuccess(Result<ApiTransactions> result) {
        return !result.isError() && result.response().isSuccessful();
    }
}