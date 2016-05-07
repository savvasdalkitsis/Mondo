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

    @Test
    public void displaysBalanceWhenStarted() {
        FakeBalanceUseCase balanceUseCase = new FakeBalanceUseCase();
        TransactionsPresenter presenter = new TransactionsPresenter(balanceUseCase);
        Balance balance = Balance.builder()
                .balance(100)
                .currencySymbol("$")
                .build();

        mockery.checking(new Expectations() {{
            oneOf(view).displayBalance(with(sameBeanAs(balance)));
        }});

        presenter.startPresenting(view);

        balanceUseCase.emitBalance(balance);
    }
}