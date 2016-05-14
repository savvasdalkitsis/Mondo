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

    private static final TransactionsPage A_TRANSACTIONS_PAGE = TransactionsPage.builder().build();
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
            ignoring(view).hideBalanceLoading();
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
        transactionsUseCase.emitPage(A_TRANSACTIONS_PAGE);
    }

    @Test
    public void displaysErrorWhenBalanceCannotBeRetrieved() {
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayErrorGettingBalance();
            oneOf(view).hideBalanceLoading();
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
            oneOf(view).hideTransactionsLoading();
        }});

        transactionsUseCase.emitPage(transactionsPage);
    }

    @Test
    public void displaysErrorWhenTransactionsCannotBeReceived() {
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).displayErrorGettingTransactions();
            oneOf(view).hideTransactionsLoading();
        }});

        transactionsUseCase.emitError();
    }

    @Test
    public void hidesBalanceProgressWhenCompleted() {
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            oneOf(view).hideBalanceLoading();
        }});

        balanceUseCase.complete();
    }

    @Test
    public void hidesTransactionsProgressWhenCompleted() {
        ignoreLoading();
        startPresenting();

        mockery.checking(new Expectations() {{
            ignoring(view).displayTransactionsPage(with(any(TransactionsPage.class)));
            oneOf(view).hideTransactionsLoading();
        }});

        transactionsUseCase.emitPage(A_TRANSACTIONS_PAGE);
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