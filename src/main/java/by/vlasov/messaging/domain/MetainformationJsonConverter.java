package by.vlasov.messaging.domain;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

public class MetainformationJsonConverter implements AttributeConverter<Metainformation, String> {

    private Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(Metainformation attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Metainformation convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, Metainformation.class);
    }
}
