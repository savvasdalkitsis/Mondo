package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeBalanceUseCase;
import com.savvasdalkitsis.mondo.fakes.FakeTransactionsUseCase;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Collections.singletonList;

public class TransactionsPresenterTest {

    @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();
    @Mock private TransactionsView view;
    private final FakeBalanceUseCase balanceUseCase = new FakeBalanceUseCase();
    private final FakeTransactionsUseCase transactionsUseCase = new FakeTransactionsUseCase();
    private final TransactionsPresenter presenter = new TransactionsPresenter(balanceUseCase, transactionsUseCase);

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
        transactionsUseCase.emitPage(TransactionsPage.builder().build());
    }

    @Test
    public void displaysErrorWhenBalanceCannotBeRetrieved() {
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayError();
        }});

        balanceUseCase.emitError();
    }

    @Test
    public void displaysTransactionsWhenStarted() {
        TransactionsPage transactionsPage = TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .amount(99)
                        .merchantName("merchant")
                        .build()))
                .build();

        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayTransactionsPage(with(sameBeanAs(transactionsPage)));
        }});

        transactionsUseCase.emitPage(transactionsPage);
    }

    private void startPresenting() {
        presenter.startPresenting(view);
    }
}