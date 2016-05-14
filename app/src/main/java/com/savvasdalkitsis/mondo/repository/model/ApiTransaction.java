package com.savvasdalkitsis.mondo.repository.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiTransaction {

    @SerializedName("amount")
    private int amount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("local_amount")
    private int localAmount;
    @SerializedName("local_currency")
    private String localCurrency;
    @SerializedName("description")
    private String description;
    @SerializedName("created")
    private String created;
    @SerializedName("merchant")
    private ApiMerchant merchant;
}
