package com.savvasdalkitsis.mondo.model.transactions;

import java.util.List;

import lombok.experimental.Builder;

@Builder
public class TransactionsPage {
    private List<Transaction> transactions;
}
