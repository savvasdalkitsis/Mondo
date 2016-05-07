package com.savvasdalkitsis.mondo.model.balance;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

@Builder
@Getter
@ToString
public class Balance {
    double balance;
    double spentToday;
    String currencySymbol;
}
