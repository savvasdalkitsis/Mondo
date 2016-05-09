package com.savvasdalkitsis.mondo.model.authentication;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
@EqualsAndHashCode
public class AuthenticationData implements Serializable {

    private static final long serialVersionUID = 1972648154831654L;

    private String code;
    private String state;
}
