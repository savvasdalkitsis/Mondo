package com.savvasdalkitsis.mondo.model.balance;

import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;

import rx.functions.Func1;

public class ApiBalanceToBalanceMapper implements Func1<ApiBalance, Balance> {

    @Override
    public Balance call(ApiBalance apiBalance) {
        String currency = apiBalance.getCurrency();
        return Balance.<Balance>builder()
                .balance(Money.builder().wholeValue(apiBalance.getBalance()).currency(currency).build())
                .spentToday(Money.builder()
                        .wholeValue(Math.abs(apiBalance.getSpendToday()))
                        .expense(apiBalance.getSpendToday() < 0)
                        .currency(currency)
                        .build())
                .build();
    }
}
