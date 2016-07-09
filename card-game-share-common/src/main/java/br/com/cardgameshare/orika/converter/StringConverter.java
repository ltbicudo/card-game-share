package br.com.cardgameshare.orika.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class StringConverter extends CustomConverter<String, String> {

    public String convert(String source, Type<? extends String> destinationType) {
        if (source != null && "".equals(source.trim())) {
            return "(null)"; // FIXME consertar quando o orika corrigir o converter
        }
        return source;
    }

}
