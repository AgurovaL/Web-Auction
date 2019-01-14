package dal.jsonFiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.User;
import staticData.UserList;

import java.io.File;
import java.io.IOException;

public class JSONWriter {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String FILE_NAME = "usersInfo.json";

    public void writeUsers() {
        //найти файл с информацией
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        try {
            objectMapper.writeValue(file, UserList.usersList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUserToJSON(User user) {
        //найти файл с информацией
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        try {
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode node = objectMapper.convertValue(user, JsonNode.class);

            ArrayNode arrayNode = ((ArrayNode) rootNode).add(node);

            objectMapper.writeValue(file, arrayNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
