package com.savvasdalkitsis.mondo.model.balance;

import com.savvasdalkitsis.mondo.model.money.Money;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

@Builder
@Getter
@ToString
public class Balance {

    Money balance;
    Money spentToday;
}
