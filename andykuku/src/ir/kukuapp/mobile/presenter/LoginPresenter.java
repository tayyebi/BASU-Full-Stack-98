package ir.kukuapp.mobile.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import ir.kukuapp.mobile.R;
import ir.kukuapp.mobile.internal.SharedMem;
import ir.kukuapp.mobile.model.UserModel;
import ir.kukuapp.mobile.network.HttpConnection;

public class LoginPresenter {

    View view ;

    public LoginPresenter(View view)
    {
        this.view = view;
    }

    public void Login (String username, String password) {
        // Check login
        HttpConnection http = new HttpConnection(username, password);

        try{
            String loginresponse = http.getLogin();
            // Save username and password for next requests
            new SharedMem(view.getApplicationContext()).setLoginCredits(username, password, loginresponse);
            view.finish();
        }catch (Exception exp) {
            view.ShowError("Login failed");
        }


    }

    public interface View {
        void ShowError(String Message);

        // Inherited methods
        Context getApplicationContext () ;
        void finish();
    }
}
