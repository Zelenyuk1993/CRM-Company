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
 * @author Dima Zelenyuk
 */
public class JacksonCompanySerializer extends JsonSerializer<Company> {

    @Override
    public void serialize(Company value, JsonGenerator gen, SerializerProvider serializers) throws IOException{
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeNumberField("profit", value.getProfit());
        gen.writeNumberField("sum", value.getSum());


        gen.writeFieldName("childrenCompanies");
        gen.writeStartArray();
        Optional.ofNullable(value.getChildrenCompanies()).ifPresent(childrens -> childrens.forEach(children -> {
            try {
                gen.writeStartObject();
                gen.writeNumberField("id", children.getId());
                gen.writeStringField("name", children.getName());
                gen.writeNumberField("profit", children.getProfit());
                gen.writeNumberField("sum", children.getSum());
                Optional.ofNullable(children.getParentCompanies()).ifPresent(parent -> {
                    try {
                        gen.writeFieldName("parentCompanies");
                        gen.writeStartObject();
                        gen.writeNumberField("id", parent.getId());
                        gen.writeStringField("name", parent.getName());
                        if(value.getProfit() != null){
                            gen.writeNumberField("profit", parent.getProfit());
                        }
                        gen.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        gen.writeEndArray();

        Optional.ofNullable(value.getParentCompanies()).ifPresent(parent -> {
            try {
                gen.writeFieldName("parentCompanies");
                gen.writeStartObject();
                gen.writeNumberField("id", parent.getId());
                gen.writeStringField("name", parent.getName());
                if(value.getProfit() != null){
                    gen.writeNumberField("profit", parent.getProfit());
                }
                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gen.writeEndObject();
    }
}