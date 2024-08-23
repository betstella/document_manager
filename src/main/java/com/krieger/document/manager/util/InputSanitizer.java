package com.krieger.document.manager.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class InputSanitizer {
    public static String sanitize(String input) {
        return Jsoup.clean(input, Safelist.basic());
    }
}
