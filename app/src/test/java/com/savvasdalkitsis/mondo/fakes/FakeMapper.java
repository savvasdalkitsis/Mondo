package com.savvasdalkitsis.mondo.fakes;

import java.util.IdentityHashMap;

import rx.functions.Func1;

public class FakeMapper<F, T> implements Func1<F, T> {

    private IdentityHashMap<F, T> data = new IdentityHashMap<>();

    @Override
    public T call(F f) {
        return data.get(f);
    }

    public void mapping(F from, T to) {
        data.put(from, to);
    }
}
