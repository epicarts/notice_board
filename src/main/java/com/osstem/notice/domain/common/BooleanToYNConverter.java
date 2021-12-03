package com.osstem.notice.domain.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    // Boolean 을 Y, N 으로 변환
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    // Y, N 을 Boolean 으로 변환
    public Boolean convertToEntityAttribute(String s) {
        return "Y".equals(s);
    }
}
