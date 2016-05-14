package com.savvasdalkitsis.mondo.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiAccounts {

    @SerializedName("accounts")
    private List<ApiAccount> accounts;
}
