package com.savvasdalkitsis.mondo.injector.usecase.balance;

import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.usecase.balance.MondoBalanceUseCase;

import java.util.HashMap;
import java.util.Map;

import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class BalanceUserCaseInjector {
    public static BalanceUseCase balanceUseCase() {
        return new MondoBalanceUseCase(mondoApi(), currencySymbols());
    }

    public static Map<String, String> currencySymbols() {
        HashMap<String, String> symbols = new HashMap<>();
        symbols.put("GBP", "£");
        return symbols;
    }
}
