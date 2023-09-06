package com.pigeon.logistics.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author dxy
 */
@Component
public class String2LongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String source) {
        if (StringUtils.hasText(source)) {
            return Long.parseLong(source);
        }
        return 0L;
    }
}
