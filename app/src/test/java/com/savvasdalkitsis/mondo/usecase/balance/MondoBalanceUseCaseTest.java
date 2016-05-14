package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.fakes.NoOpObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import rx.Observable;
import rx.functions.Func1;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    private static final Balance BALANCE = Balance.builder().balance(Money.builder().currency("$").build()).build();

    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(2);

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final Func1<ApiBalance, Balance> mapper = apiBalance -> BALANCE;
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, mapper, new NoOpObservableCache<>());
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void retrievesBalanceFromMondoApi() {
        getBalance().subscribe(subscriber);

        mondoApi.emitSuccessfulBalance(ApiBalance.builder().build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(BALANCE)));
    }

    @Test
    public void respondsWithErrorWhenApiErrors() {
        getBalance().subscribe(subscriber);

        mondoApi.emitBalanceError();

        subscriber.assertFinishedWithItems(sameBeanAs(Response.<Balance>error()));
    }

    @Test
    public void respondsWithErrorWhenApiReturnsNon200Response() {
        getBalance().subscribe(subscriber);

        mondoApi.emitBalanceErrorResponse();

        subscriber.assertFinishedWithItems(sameBeanAs(Response.<Balance>error()));
    }

    private Observable<Response<Balance>> getBalance() {
        return useCase.getBalance();
    }

}