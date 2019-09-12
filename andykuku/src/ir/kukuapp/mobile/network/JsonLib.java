package ir.kukuapp.mobile.network;

import ir.kukuapp.mobile.model.PostModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonLib {
    public ArrayList<PostModel> decode_posts (String input) throws JSONException {

        ArrayList<PostModel> output = new ArrayList<PostModel>();

        JSONArray jsonArray = new JSONArray(input);
        if (jsonArray != null) {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                output.add(
                        new PostModel(
                                jsonObject.getString("Username"),
                                jsonObject.getString("Text")
                        )
                );
            }
        }
        return output;
    }
}
