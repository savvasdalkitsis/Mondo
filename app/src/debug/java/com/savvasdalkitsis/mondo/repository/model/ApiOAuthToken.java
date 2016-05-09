package com.savvasdalkitsis.mondo.repository.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiOAuthToken {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
}
