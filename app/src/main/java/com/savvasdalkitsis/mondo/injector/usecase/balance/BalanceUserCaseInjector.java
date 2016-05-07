package com.savvasdalkitsis.mondo.injector.usecase.balance;

import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.usecase.balance.MondoBalanceUseCase;

public class BalanceUserCaseInjector {
    public static BalanceUseCase balanceUseCase() {
        return new MondoBalanceUseCase(null);
    }
}
