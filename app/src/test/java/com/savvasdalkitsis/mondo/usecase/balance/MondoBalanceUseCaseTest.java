package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeCachedNetworkCall;
import com.savvasdalkitsis.mondo.fakes.FakeMapper;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    private static final Balance BALANCE = Balance.builder().balance(Money.builder().currency("$").build()).build();

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final FakeMapper<ApiBalance, Balance> mapper = new FakeMapper<>();
    private final CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall = new FakeCachedNetworkCall<>();
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, mapper, cachedNetworkCall);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void usesCachedNetworkCall() {
        ApiBalance apiBalance = ApiBalance.builder().build();
        mapper.mapping(apiBalance, BALANCE);

        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitSuccessfulBalance(apiBalance);

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(BALANCE)));
    }

}