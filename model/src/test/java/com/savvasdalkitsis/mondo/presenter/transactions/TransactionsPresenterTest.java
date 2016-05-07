package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeBalanceUseCase;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class TransactionsPresenterTest {

    @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();
    @Mock private TransactionsView view;
    private final FakeBalanceUseCase balanceUseCase = new FakeBalanceUseCase();
    private final TransactionsPresenter presenter = new TransactionsPresenter(balanceUseCase);

    @Test
    public void displaysBalanceWhenStarted() {
        Balance balance = Balance.builder()
                .balance(100)
                .spentToday(99)
                .currencySymbol("$")
                .build();

        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayBalance(with(sameBeanAs(balance)));
        }});

        balanceUseCase.emitBalance(balance);
    }

    @Test
    public void stopsDisplayingToTheViewWhenStopped() {
        startPresenting();
        presenter.stopPresenting();

        mockery.checking(new Expectations() {{
            never(view);
        }});

        balanceUseCase.emitBalance(Balance.builder().build());
    }

    @Test
    public void displaysErrorWhenBalanceCannotBeRetrieved() {
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayError();
        }});

        balanceUseCase.emitError();
    }

    private void startPresenting() {
        presenter.startPresenting(view);
    }
}