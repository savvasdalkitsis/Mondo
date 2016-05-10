package com.savvasdalkitsis.mondo.model.transactions;

import com.savvasdalkitsis.mondo.model.money.Money;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class Transaction {
    private Money amount;
    private String merchantName;
    private String logoUrl;
}
