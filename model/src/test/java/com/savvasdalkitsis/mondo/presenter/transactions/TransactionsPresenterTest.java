package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeBalanceUseCase;
import com.savvasdalkitsis.mondo.fakes.FakeTransactionsUseCase;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.money.Money;
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
                .balance(Money.builder().wholeValue(999).build())
                .build();

        mockery.checking(new Expectations() {{
            oneOf(view).displayLoadingBalance();
            ignoring(view).displayLoadingTransactions();
        }});

        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayBalance(with(sameBeanAs(balance)));
        }});

        balanceUseCase.emitBalance(balance);
    }

    @Test
    public void stopsDisplayingToTheViewWhenStopped() {
        ignoreLoading();
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
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayErrorGettingBalance();
        }});

        balanceUseCase.emitError();
    }

    @Test
    public void displaysTransactionsWhenStarted() {
        TransactionsPage transactionsPage = TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .amount(Money.builder().wholeValue(99).build())
                        .description("merchant")
                        .build()))
                .build();

        mockery.checking(new Expectations() {{
            ignoring(view).displayLoadingBalance();
            oneOf(view).displayLoadingTransactions();
        }});
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayTransactionsPage(with(sameBeanAs(transactionsPage)));
        }});

        transactionsUseCase.emitPage(transactionsPage);
    }

    @Test
    public void displaysErrorWhenTransactionsCannotBeReceived() {
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayErrorGettingTransactions();
        }});

        transactionsUseCase.emitError();
    }

    private void startPresenting() {
        presenter.startPresenting(view);
    }

    private void ignoreLoading() {
        mockery.checking(new Expectations() {{
            ignoring(view).displayLoadingBalance();
            ignoring(view).displayLoadingTransactions();
        }});
    }
}