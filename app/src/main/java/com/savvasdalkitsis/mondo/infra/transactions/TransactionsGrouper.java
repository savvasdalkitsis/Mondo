package com.savvasdalkitsis.mondo.infra.transactions;

import com.savvasdalkitsis.mondo.model.transactions.DayTransactions;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsGrouper {

    public static List<DayTransactions> groupTransactions(List<Transaction> transactions) {
        Map<LocalDate, List<Transaction>> groups = new HashMap<>();
        for (Transaction transaction : transactions) {
            Date javaDate = transaction.getCreated();
            LocalDate date = new LocalDate(javaDate);
            List<Transaction> day = groups.get(date);
            if (day == null) {
                day = new ArrayList<>();
                groups.put(date, day);
            }
            day.add(transaction);
        }
        List<DayTransactions> transactionGroups = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Transaction>> entry : groups.entrySet()) {
            transactionGroups.add(DayTransactions.builder()
                    .date(entry.getKey())
                    .transactions(entry.getValue())
                    .build());
        }
        Collections.sort(transactionGroups, (t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        // add an empty group before all to offset the recycler view by the height of the extended toolbar
        transactionGroups.add(0, DayTransactions.builder().transactions(Collections.emptyList()).build());
        return transactionGroups;
    }
}
