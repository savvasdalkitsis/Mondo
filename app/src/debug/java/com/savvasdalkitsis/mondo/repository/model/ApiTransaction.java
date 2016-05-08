package com.savvasdalkitsis.mondo.repository.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiTransaction {

    @SerializedName("amount")
    private double amount;
    @SerializedName("merchant")
    private ApiMerchant merchant;
}
