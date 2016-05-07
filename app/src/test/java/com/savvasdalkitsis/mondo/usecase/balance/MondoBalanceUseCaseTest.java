package com.savvasdalkitsis.mondo.usecase.balance;

import com.google.common.collect.ImmutableMap;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.Map;

import rx.Observable;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    private static final String USD_SYMBOL = "$";
    private static final String USD = "USD";

    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(2);

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final Map<String, String> currencySymbolMap = ImmutableMap.of(USD, USD_SYMBOL);
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, currencySymbolMap);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void retrievesBalanceFromMondoApi() {
        getBalance().subscribe(subscriber);

        mondoApi.emitSuccess(ApiBalance.builder()
                .balance(999)
                .currency(USD)
                .build());

        subscriber.assertFinishedWithItem(sameBeanAs(Response.success(Balance.builder()
                .balance(999)
                .currencySymbol(USD_SYMBOL)
                .build())));
    }

    @Test
    public void respondsWithErrorWhenApiErrors() {
        getBalance().subscribe(subscriber);

        mondoApi.emitError();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.<Balance>error()));
    }

    @Test
    public void respondsWithErrorWhenApiReturnsNon200Response() {
        getBalance().subscribe(subscriber);

        mondoApi.emitErrorResponse();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.<Balance>error()));
    }

    private Observable<Response<Balance>> getBalance() {
        return useCase.getBalance();
    }

}