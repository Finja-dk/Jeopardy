package backend;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataHandler {
    public List<Category> Categories = new ArrayList<>();
    public DataHandler() {

    }

    public List<Category> loadQuestions() {      
        String path = "/data/questions.json"; 
        
        InputStream inputStream = DataHandler.class.getResourceAsStream(path); 

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String jsonString = content.toString();

        Gson gson = new Gson();

        final Type QUESTION_TYPE = new TypeToken<List<Category>>() {
        }.getType();

        List<Category> data = gson.fromJson(jsonString, QUESTION_TYPE);

        Categories.addAll(data);
        return data;
    }

}