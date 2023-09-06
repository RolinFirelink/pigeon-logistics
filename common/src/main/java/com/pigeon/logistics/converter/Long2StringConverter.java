package com.pigeon.logistics.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 */
@Component
public class Long2StringConverter implements Converter<Long, String> {
    @Override
    public String convert(Long source) {
        return Long.toString(source);
    }
}
