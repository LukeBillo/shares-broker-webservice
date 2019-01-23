package org.lukebillington.university.sharesbroker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonArray;
import org.bson.Document;

import java.util.List;

public class ObjectMapperHelper {
    public static <I> Document MapToDocument(I inputModel) {
        String json = MapToJson(inputModel);
        return Document.parse(json);
    }

    public static <T> BsonArray MapListToBson(List<T> inputList) {
        String json = MapToJson(inputList);
        return BsonArray.parse(json);
    }

    private static <I> String MapToJson(I input) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        try {
            json = mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            System.err.println("Exception thrown when mapping model to JSON string in ObjectMapperHelper.");
            e.printStackTrace();
        }

        return json;
    }
}
