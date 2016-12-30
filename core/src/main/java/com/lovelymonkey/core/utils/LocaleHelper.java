package com.lovelymonkey.core.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import com.google.common.collect.ImmutableList;
import com.lovelymonkey.core.exceptions.LocaleException;

/**
 * Local helper to translate string to country specific value.
 *
 */
public final class LocaleHelper {

    private static final ImmutableList<Locale> SUPPORT_LOCALES = ImmutableList.<Locale>builder()
            .add(Locale.SIMPLIFIED_CHINESE)
            .add(Locale.CHINA)
            .add(Locale.CHINESE)
            .add(Locale.US)
            .build();

    /**
     * Return country specific value to the corresponding key.
     * @param key Key 
     * @param countryCode Country code.
     * @return Country specific value corresponding to the key.
     * @throws LocaleException
     */
    public static String translate(final String key, final Locale locale) throws LocaleException {
        if (!SUPPORT_LOCALES.contains(locale)) {
            throw new LocaleException("Currently this country code is not supported by country!");
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle(System.getProperty("international-base-name") == null ?
                    "message-resources" : System.getProperty("international-base-name"), locale);
            return bundle.getString(key);
        }
    }

    /**
     * Return default locale specific value to the corresponding key.
     * @param key Key
     * @return The default locale specific value corresponding to the kay.
     */
    public static String tranlateWithDefaultLocale(final String key) {
        Locale defaultLocale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle(System.getProperty("international-base-name") == null ?
                "message-resources" : System.getProperty("international-base-name"), defaultLocale);
        return bundle.getString(key);
    }
}
