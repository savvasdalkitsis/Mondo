package com.savvasdalkitsis.mondo.injector.infra.external.image;

import com.savvasdalkitsis.mondo.injector.ApplicationInjector;
import com.squareup.picasso.Picasso;

public class PicassoInjector {

    private static final Picasso picasso = Picasso.with(ApplicationInjector.getApplication());

    public static Picasso picasso() {
        return picasso;
    }
}
