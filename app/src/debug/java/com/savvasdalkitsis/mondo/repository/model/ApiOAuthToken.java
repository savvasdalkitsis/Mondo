package com.savvasdalkitsis.mondo.repository.model;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class ApiOAuthToken {
    private String authToken;
}
