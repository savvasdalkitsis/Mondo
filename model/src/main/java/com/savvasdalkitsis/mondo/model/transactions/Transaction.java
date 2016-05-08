package com.savvasdalkitsis.mondo.model.transactions;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class Transaction {
    private double amount;
    private String merchantName;
    private String logoUrl;
}
