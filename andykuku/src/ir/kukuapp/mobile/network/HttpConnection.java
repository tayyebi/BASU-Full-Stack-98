package ir.kukuapp.mobile.network;

import android.annotation.TargetApi;
import android.os.Build;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpConnection {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String END_POINT = "http://kukuapp.ir/";

    private String username = null, password = null;

    public HttpConnection()
    {

    }

    public HttpConnection(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // HTTP GET request
    @TargetApi(Build.VERSION_CODES.O)
    public String getRequest(String path) throws Exception {

        String url = END_POINT + path;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        // auth
        if (username != null){
            String encoded = Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
            con.setRequestProperty("Authorization", "Basic "+encoded);
        }

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

    // Get home
    public String getHome() throws Exception {
        return getRequest("home.php");
    }
    // Get home
    public String getLogin() throws Exception {
        return getRequest("testlogin.php");
    }

    // HTTP POST request
    @TargetApi(Build.VERSION_CODES.O)
    public String sendPost(String UserID, String Text) throws Exception {

        String url = END_POINT + "post.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // auth
        if (username != null){
            String encoded = Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
            con.setRequestProperty("Authorization", "Basic "+encoded);
        }

        String urlParameters = "send=&Text=" + Text + "&UserId=" + UserID;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
       return response.toString();

    }
}