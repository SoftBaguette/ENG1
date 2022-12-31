package Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class JsonTestingThings {
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        //System.out.println(new File(".").getAbsolutePath());
        
        Object obj = new JSONParser().parse(new FileReader("assets/Stations.json"));
        JSONArray jsonArray = (JSONArray) obj;
        int length = jsonArray.size();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            System.out.println(jsonObject.get("type"));
        }
        
    
    }
}
