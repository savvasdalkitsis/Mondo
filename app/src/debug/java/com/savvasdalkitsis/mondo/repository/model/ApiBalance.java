package com.savvasdalkitsis.mondo.repository.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiBalance {

    @SerializedName("balance")
    double balance;
    @SerializedName("spent_today")
    double spentToday;
    @SerializedName("currency")
    String currency;
}