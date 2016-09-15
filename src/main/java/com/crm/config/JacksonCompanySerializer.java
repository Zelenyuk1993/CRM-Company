package com.crm.config;
import com.crm.models.Company;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Optional;
/**
 * JacksonCompanySerializer
 * Custom JSON serializer fo Company json serialization
 *
 * @author Andrii Blyznuk
 */
public class JacksonCompanySerializer extends JsonSerializer<Company> {

    @Override
    public void serialize(Company value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        if(value.getProfit() != null){
            gen.writeNumberField("profit", value.getProfit());
        }

        gen.writeFieldName("childrenCompanies");
        gen.writeStartArray();
        Optional.ofNullable(value.getChildrenCompanies()).ifPresent(childrens -> childrens.forEach(children -> {
            try {
                gen.writeStartObject();
                gen.writeNumberField("id", children.getId());
                gen.writeStringField("name", children.getName());
                if(value.getProfit() != null){
                    gen.writeNumberField("profit", value.getProfit());
                }

                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        gen.writeEndArray();

        gen.writeFieldName("parentCompanies");
        gen.writeStartArray();
        Optional.ofNullable(value.getParentCompanies()).ifPresent(parents -> parents.forEach(parent -> {
            try {
                gen.writeStartObject();
                gen.writeNumberField("id", parent.getId());
                gen.writeStringField("name", parent.getName());
                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        gen.writeEndArray();

        gen.writeEndObject();
    }
}