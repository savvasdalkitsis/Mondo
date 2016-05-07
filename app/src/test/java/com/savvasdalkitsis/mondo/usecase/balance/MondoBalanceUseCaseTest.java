package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    @Rule public TestRule timeout = Timeout.seconds(2);
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void retrievesBalanceFromMondoApi() {
        Balance balance = Balance.builder().balance(999).build();

        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitSuccess(balance);

        subscriber.assertFinishedWithItem(sameBeanAs(Response.success(balance)));
    }

    @Test
    public void respondsWithErrorWhenApiErrors() {
        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitError();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.<Balance>error()));
    }

    @Test
    public void respondsWithErrorWhenApiReturnsNon200Response() {
        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitErrorResponse();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.<Balance>error()));
    }

}