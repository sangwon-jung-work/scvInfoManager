package net.ddns.scvstorage.scvInfoManager.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList.contentType;

@Converter(autoApply = true)
public class ContentTypeConverter implements AttributeConverter<contentType, String> {

    @Override
    public String convertToDatabaseColumn(contentType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    public contentType convertToEntityAttribute(String contentType) {
        if (contentType == null) {
            return null;
        }

        return Stream.of(ContentList.contentType.values())
                        .filter(c -> c.getCode().equals(contentType))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }
    
}
