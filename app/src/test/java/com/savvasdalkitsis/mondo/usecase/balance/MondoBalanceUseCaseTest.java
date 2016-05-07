package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    @Test
    public void retrievesBalanceFromMondoApi() {
        FakeMondoApi mondoApi = new FakeMondoApi();
        MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi);
        Balance balance = Balance.builder().balance(999).build();

        HamcrestTestSubscriber<Balance> subscriber = new HamcrestTestSubscriber<>();

        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitSuccess(balance);

        subscriber.assertFinishedWithItem(sameBeanAs(balance));
    }

}