package com.savvasdalkitsis.mondo.model.transactions;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class TransactionsPage {
    private List<Transaction> transactions;
}
