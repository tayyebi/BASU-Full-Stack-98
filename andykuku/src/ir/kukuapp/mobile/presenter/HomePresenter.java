package ir.kukuapp.mobile.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import ir.kukuapp.mobile.HomeActivity;
import ir.kukuapp.mobile.LoginActivity;
import ir.kukuapp.mobile.internal.HomeAdapter;
import ir.kukuapp.mobile.internal.SharedMem;
import ir.kukuapp.mobile.model.PostModel;
import ir.kukuapp.mobile.model.UserModel;
import ir.kukuapp.mobile.network.HttpConnection;
import ir.kukuapp.mobile.network.JsonLib;
import org.json.JSONException;

import java.util.ArrayList;

public class HomePresenter {

    private IHomeView view;
    private UserModel model;

    public HomePresenter(IHomeView view)
    {
        this.view = view ;
        // Check for login
        UserModel LoggedInUser = new SharedMem(view.getApplicationContext()).getLoginCredits() ;
        this.model = LoggedInUser ;
    }

    public void RefreshFeed ( ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Refresh items
                String netcontent = "";
                try{
                    // Fetch data
                    HttpConnection http = new HttpConnection();
                    netcontent = http.getHome();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Decode items
                    JsonLib jsonLib = new JsonLib();
                    // Create a list from model
                    ArrayList<PostModel> items = null;
                    try {
                        items = jsonLib.decode_posts(netcontent);
                    } catch (JSONException e) {
                        view.ShowError("Invalid data");
                    }finally {
                        ArrayList<PostModel> finalItems = items;
                        view.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Create adapter
                                HomeAdapter adapter = new HomeAdapter(view.getApplicationContext(), finalItems);
                                // pass to Activity
                                view.ReloadHomeFeed(adapter);
                            }
                        });
                    }



                }

            }
        }).start();


    }

    public void SendPost (String Text) {
        // Open connection to server
        HttpConnection http = new HttpConnection(model.getUsername(), model.getPassword());
        // Send the post
        try {
            http.sendPost(model.getUserID(), Text);
        } catch (Exception e) {
            view.ShowError("Failed to send post");
        }
    }

    public void CheckLogin() {
        if (model.getUsername().equals(""))
            view.startActivity(new Intent(view.getApplicationContext(), LoginActivity.class));
        else
            view.ShowError("Welcome " + model.getUsername());
    }

    public void Logout(){
        // Remove username from memory
        new SharedMem(view.getApplicationContext()).setLoginCredits("");
        // Restart the app
        view.RestartApp();
    }

    public interface IHomeView {
        void ReloadHomeFeed(HomeAdapter adapter);
        void ShowError(String Message);
        void RestartApp();

        // Use Activity methods
        Context getApplicationContext();
        void startActivity(Intent intent);
        void runOnUiThread(Runnable runnable);
    }
}
