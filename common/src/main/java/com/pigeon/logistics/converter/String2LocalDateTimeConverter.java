package com.pigeon.logistics.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author dxy
 */
@Component
public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        if (StringUtils.hasText(source)) {
            return LocalDateTime.parse(source);
        }
        return null;
    }
}
