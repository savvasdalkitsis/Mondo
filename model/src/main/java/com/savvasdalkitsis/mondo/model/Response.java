package com.savvasdalkitsis.mondo.model;

import lombok.Getter;
import lombok.experimental.Builder;

@Builder
@Getter
public class Response<T> {

    private T data;
    private boolean isError;

    public static  <TT> Response<TT> success(TT data) {
        return Response.<TT>builder().data(data).<TT>build();
    }

    public static  <TT> Response<TT> error() {
        return Response.<TT>builder().isError(true).<TT>build();
    }
}
