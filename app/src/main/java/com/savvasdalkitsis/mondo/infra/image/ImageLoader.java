package com.savvasdalkitsis.mondo.infra.image;

import android.widget.ImageView;

public interface ImageLoader {

    void load(String url, ImageView imageView);
}
