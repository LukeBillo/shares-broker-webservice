package org.lukebillington.university.sharesbroker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

public class ObjectMapperHelper {
    public static <I> Document MapToDocument(I InputModel) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        try {
            json = mapper.writeValueAsString(InputModel);
        } catch (JsonProcessingException e) {
            System.err.println("Exception thrown when mapping model to JSON string in ObjectMapperHelper.");
            e.printStackTrace();
        }

        return Document.parse(json);
    }
}
