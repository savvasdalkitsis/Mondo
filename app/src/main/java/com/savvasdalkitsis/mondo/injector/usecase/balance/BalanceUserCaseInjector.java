package com.savvasdalkitsis.mondo.injector.usecase.balance;

import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.usecase.balance.MondoBalanceUseCase;

import static com.savvasdalkitsis.mondo.injector.infra.network.CachedNetworkCallInjector.cachedNetworkCall;
import static com.savvasdalkitsis.mondo.injector.model.mappers.MapperInjector.apiBalanceToBalanceMapper;
import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class BalanceUserCaseInjector {
    public static BalanceUseCase balanceUseCase() {
        return new MondoBalanceUseCase(mondoApi(), apiBalanceToBalanceMapper(), cachedNetworkCall());
    }
}
