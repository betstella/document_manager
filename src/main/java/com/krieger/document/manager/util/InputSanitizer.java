package com.krieger.document.manager.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class InputSanitizer {
    // we want to sanitize in order to prevent XSS attacks
    public static String sanitize(String input) {
        return Jsoup.clean(input, Safelist.basicWithImages());
    }
}
