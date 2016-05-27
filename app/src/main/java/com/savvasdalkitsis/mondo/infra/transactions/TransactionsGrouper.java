/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
