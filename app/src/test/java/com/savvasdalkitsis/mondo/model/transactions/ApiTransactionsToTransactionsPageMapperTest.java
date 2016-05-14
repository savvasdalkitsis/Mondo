package com.savvasdalkitsis.mondo.model.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeDateParser;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiMerchant;
import com.savvasdalkitsis.mondo.repository.model.ApiTransaction;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import org.junit.Test;

import java.util.Date;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class ApiTransactionsToTransactionsPageMapperTest {

    private static final String USD = "USD";
    private static final String GBP = "GBP";
    private final FakeDateParser dateParser = new FakeDateParser();
    private final ApiTransactionsToTransactionsPageMapper mapper = new ApiTransactionsToTransactionsPageMapper(dateParser);

    @Test
    public void mapsFullTransactionsListToPage() {
        Date date = new Date(0);
        dateParser.format("date", date);

        ApiTransactions apiTransactions = ApiTransactions.builder()
                .transactions(singletonList(ApiTransaction.builder()
                        .amount(-100)
                        .currency(USD)
                        .localAmount(-800)
                        .localCurrency(GBP)
                        .created("date")
                        .merchant(ApiMerchant.builder()
                                .name("merchant1")
                                .logo("logo1")
                                .build())
                        .build()))
                .build();

        TransactionsPage expected = TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .description("merchant1")
                        .logoUrl("logo1")
                        .created(date)
                        .amount(Money.builder().wholeValue(100).currency(USD).expense(true).build())
                        .amountInLocalCurrency(Money.builder().wholeValue(800).currency(GBP).expense(true).build())
                        .build()))
                .build();

        assertThat(mapping(apiTransactions), sameBeanAs(expected));
    }

    @Test
    public void usesDescriptionWhenMerchantIsMissing() {
        ApiTransactions apiTransactions = ApiTransactions.builder()
                .transactions(singletonList(ApiTransaction.builder()
                        .description("description")
                        .amount(0)
                        .localAmount(0)
                        .merchant(null)
                        .build()))
                .build();

        TransactionsPage expected = TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .description("description")
                        .amount(Money.builder().wholeValue(0).build())
                        .amountInLocalCurrency(Money.builder().wholeValue(0).build())
                        .build()))
                .build();

        assertThat(mapping(apiTransactions), sameBeanAs(expected));
    }

    @Test
    public void reversesTransactionsToAppearInChronologicalOrder() {
        ApiTransactions apiTransactions = ApiTransactions.builder()
                .transactions(asList(
                        ApiTransaction.builder().amount(-1).build(),
                        ApiTransaction.builder().amount(-2).build()))
                .build();

        TransactionsPage expected = TransactionsPage.builder()
                .transactions(asList(
                        Transaction.builder()
                                .amount(Money.builder().wholeValue(2).expense(true).build())
                                .amountInLocalCurrency(Money.builder().build())
                                .build(),
                        Transaction.builder()
                                .amount(Money.builder().wholeValue(1).expense(true).build())
                                .amountInLocalCurrency(Money.builder().build())
                                .build()
                ))
                .build();

        assertThat(mapping(apiTransactions), sameBeanAs(expected));
    }

    private TransactionsPage mapping(ApiTransactions apiTransactions) {
        return mapper.call(apiTransactions);
    }

}