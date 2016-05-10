package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeCurrencySymbols;
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

import rx.Observable;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    private static final String USD_SYMBOL = "$";
    private static final String USD = "USD";

    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(2);

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final FakeCurrencySymbols currencySymbols = new FakeCurrencySymbols();
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, currencySymbols);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void retrievesBalanceFromMondoApi() {
        currencySymbols.mapping(USD, USD_SYMBOL);

        getBalance().subscribe(subscriber);

        mondoApi.emitSuccessfulBalance(ApiBalance.builder()
                .balance(999)
                .spentToday(666)
                .currency(USD)
                .build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(Balance.builder()
                .balance(999)
                .spentToday(666)
                .currencySymbol(USD_SYMBOL)
                .build())));
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