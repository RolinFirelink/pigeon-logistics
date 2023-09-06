package com.pigeon.logistics.config;

import com.pigeon.logistics.converter.Long2StringConverter;
import com.pigeon.logistics.converter.String2LocalDateTimeConverter;
import com.pigeon.logistics.converter.String2LongConverter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dxy
 */
@AllArgsConstructor
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    final String2LongConverter string2LongConverter;
    final Long2StringConverter long2StringConverter;
    final String2LocalDateTimeConverter string2LocalDateTimeConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(string2LongConverter);
        registry.addConverter(long2StringConverter);
        registry.addConverter(string2LocalDateTimeConverter);
    }

}
