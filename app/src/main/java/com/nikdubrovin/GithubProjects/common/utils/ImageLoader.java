package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nikdubrovin.GithubProjects.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.R.attr.width;

/**
 * Created by NikDubrovin on 07.10.2017.
 */

public class ImageLoader {
    /**
     * Load image from source and set it into the imageView.
     * @param context context.
     * @param source could be Uri/String/File/ResourceId.
     * @param view the imageView.
     */

    public static void load(Context context, Object source, ImageView view, int width, int height) {
        Glide.with(context)
                .load(source)
                .override(width,height)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(view);
    }

    public static void load(Object source, ImageView view, int width, int height) {
        Glide.with(view.getContext())
                .load(source)
                .override(width,height)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(view);
    }

    public static void load(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(view);
    }

    public static void loadWithCircle(Context context, Object source, ImageView view, int width, int height) {
        Glide.with(context)
                .load(source)
                .bitmapTransform(new CropCircleTransformation(context))
                .override(width,height)
                .placeholder(R.drawable.image_placeholder)
                .into(view);
    }

    public static void loadWithCircle(Object source, ImageView view, int width, int height) {
        Glide.with(view.getContext())
                .load(source)
                .override(width,height)
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .placeholder(R.drawable.image_placeholder)
                .crossFade(300)
                .into(view);
    }

    public static void loadWithCircle(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .placeholder(R.drawable.image_placeholder)
                .crossFade(300)
                .into(view);
    }
}
