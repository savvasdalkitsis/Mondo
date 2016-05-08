package com.savvasdalkitsis.mondo.model.transactions;

import lombok.experimental.Builder;

@Builder
public class Transaction {
    private double amount;
    private String merchantName;
}
