package com.savvasdalkitsis.mondo.infra.external.image;

import android.widget.ImageView;

import com.savvasdalkitsis.mondo.infra.image.ImageLoader;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        if (url == null || url.isEmpty()) {
            imageView.setImageBitmap(null);
            return;
        }
        picasso.load(url)
                .fit()
                .into(imageView);
    }
}
