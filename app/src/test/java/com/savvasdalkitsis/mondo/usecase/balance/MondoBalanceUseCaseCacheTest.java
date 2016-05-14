package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.fakes.FakeObservableCache;
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

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseCacheTest {

    private static final Balance BALANCE = Balance.builder()
            .balance(Money.builder()
                    .wholeValue(1)
                    .build())
            .spentToday(Money.builder().build())
            .build();
    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(2);

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final FakeObservableCache<ApiBalance> observableCache = new FakeObservableCache<>();
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, apiBalance -> BALANCE, observableCache);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void usesCachedVersionOfTheApi() {
        useCase.getBalance().subscribe(subscriber);

        observableCache.emitSuccessfulResult(ApiBalance.builder().balance(1).build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(BALANCE)));
    }

}