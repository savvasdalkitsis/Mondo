package com.savvasdalkitsis.mondo.injector.infra.image;

import com.savvasdalkitsis.mondo.infra.external.image.PicassoImageLoader;
import com.savvasdalkitsis.mondo.infra.image.ImageLoader;

import static com.savvasdalkitsis.mondo.injector.infra.external.image.PicassoInjector.picasso;

public class ImageLoaderInjector {

    public static ImageLoader imageLoader() {
        return new PicassoImageLoader(picasso());
    }

}
