package lv.rigadevday.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 */
public class Utils {
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String imagePrefix(String img) {
        return "https://raw.githubusercontent.com/RigaDevDay/RigaDevDay.github.io/source/src/" + img;
    }

    public static void goToWeb(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void goToMaps(Context context, String coord) {
        goToWeb(context, String.format("http://maps.google.com/maps?daddr=%s", coord));
    }

    public static void goToMail(Context context, String email) {
        goToWeb(context, String.format("mailto:%s", email));
    }

    public static String nullToEmpty(String description) {
        return description == null ? "" : description;
    }

    public static void goToTwitter(Context context, String hashtag) {
        goToWeb(context, String.format("https://twitter.com/intent/tweet?text=%s", urlEncode(hashtag)));
    }

    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }
}
