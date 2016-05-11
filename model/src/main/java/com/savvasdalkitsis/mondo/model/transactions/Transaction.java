package com.savvasdalkitsis.mondo.model.transactions;

import com.savvasdalkitsis.mondo.model.money.Money;

import java.util.Date;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class Transaction {
    private Money amount;
    private String description;
    private String logoUrl;
    private Date created;
}