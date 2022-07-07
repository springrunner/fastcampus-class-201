package com.fastcampus.springrunner.divelog.config;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ExtendReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
    public Properties getProperties(Locale locale) {
        return getMergedProperties(locale).getProperties();
    }
}
