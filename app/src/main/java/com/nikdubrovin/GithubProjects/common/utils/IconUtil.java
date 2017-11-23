package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.nikdubrovin.GithubProjects.common.constant.Constants;

/**
 * Created by NikDubrovin on 25.09.2017.
 */

public class IconUtil {

    /**
     * @param context
     * @param icon type IIcon
     *             <p>
     *             Simple :
     *             <pre><string>
     *             DevIcon.Icon.dev_java_plain_wordmark
     *             </string></pre>
     * @param color (int)Color
     *               <p>
     *              Simple :
     *               <pre><string>
     *              Color.Black
     *              </string></pre>
     * @return Drawable
     */

    public static Drawable CreateIcon(Context context, IIcon icon, int color, int size) {
        return new IconicsDrawable(context)
                .icon(icon)
                .color(color)
                .sizeDp(size);
    }
}
