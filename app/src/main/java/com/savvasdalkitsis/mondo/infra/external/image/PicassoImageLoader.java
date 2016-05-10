package com.savvasdalkitsis.mondo.infra.external.image;

import android.widget.ImageView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.infra.image.ImageLoader;
import com.squareup.picasso.Picasso;

import static com.savvasdalkitsis.mondo.util.StringUtils.isEmptyOrNull;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        if (isEmptyOrNull(url)) {
            url = null; // setting an empty path doesn't work but setting null does
        }
        picasso.load(url)
                .fit()
                .placeholder(R.drawable.app_icon)
                .error(R.drawable.app_icon)
                .into(imageView);
    }
}
