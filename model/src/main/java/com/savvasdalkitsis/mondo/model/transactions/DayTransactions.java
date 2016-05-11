package com.savvasdalkitsis.mondo.model.transactions;

import org.joda.time.LocalDate;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class DayTransactions {
    private LocalDate date;
    private List<Transaction> transactions;
}
