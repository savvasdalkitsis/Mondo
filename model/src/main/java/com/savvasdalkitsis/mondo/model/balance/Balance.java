package com.savvasdalkitsis.mondo.model.balance;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class Balance {
    double balance;
    String currencySymbol;
}
