package com.task.backtask.configuration.converter;


import com.task.backtask.types.Kind;
import org.springframework.core.convert.converter.Converter;

public class StringToKindConverter implements Converter<String, Kind> {

    @Override
    public Kind convert(String source) {
        return Kind.valueOf(source.toUpperCase());
    }
}