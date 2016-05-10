package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiMerchant;
import com.savvasdalkitsis.mondo.repository.model.ApiTransaction;
import com.savvasdalkitsis.mondo.rx.RxTransformers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;

import static com.savvasdalkitsis.mondo.util.StringUtils.isNotEmptyNorNull;

public class MondoTransactionsUseCase implements TransactionsUseCase {

    private MondoApi mondoApi;

    public MondoTransactionsUseCase(MondoApi mondoApi) {
        this.mondoApi = mondoApi;
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return mondoApi.getTransactions()
                .map(apiTransactionsResult -> {
                    if (!apiTransactionsResult.isError() && apiTransactionsResult.response().isSuccessful()) {
                        List<Transaction> transactions = new ArrayList<>();
                        for (ApiTransaction apiTransaction : apiTransactionsResult.response().body().getTransactions()) {
                            ApiMerchant merchant = nullSafe(apiTransaction.getMerchant());
                            String merchantName = merchant.getName();
                            transactions.add(Transaction.builder()
                                    .amount(Money.builder()
                                            .wholeValue(Math.abs(apiTransaction.getAmount()))
                                            .expense(apiTransaction.getAmount() < 0)
                                            .currency(apiTransaction.getCurrency())
                                            .build())
                                    .description(isNotEmptyNorNull(merchantName) ? merchantName : apiTransaction.getDescription())
                                    .logoUrl(merchant.getLogo())
                                    .build());
                        }
                        // in a real app, this would be sorted by a comparator using the transaction date
                        Collections.reverse(transactions);
                        return Response.success(TransactionsPage.builder()
                                .transactions(transactions)
                                .build());
                    }
                    return Response.<TransactionsPage>error();
                })
                .compose(RxTransformers.androidNetworkCall())
                .onErrorResumeNext(Observable.just(Response.error()));
    }

    private ApiMerchant nullSafe(ApiMerchant merchant) {
        return merchant != null ? merchant : ApiMerchant.builder().build();
    }
}
