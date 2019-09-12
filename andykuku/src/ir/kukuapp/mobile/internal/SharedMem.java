package ir.kukuapp.mobile.internal;

import android.content.Context;
import android.content.SharedPreferences;
import ir.kukuapp.mobile.R;
import ir.kukuapp.mobile.model.UserModel;


public class SharedMem {
    Context applicationContext ;
    public SharedMem(Context applicationContext ) {
        this.applicationContext = applicationContext;
    }

    public void setLoginCredits(String username, String password, String userid) {
        SharedPreferences sharedPref = applicationContext.getSharedPreferences("Login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.putString("User ID", userid);
        editor.commit();
    }
    public void setLoginCredits(String username) { // Mostly used for logout
        SharedPreferences sharedPref = applicationContext.getSharedPreferences("Login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Username", username);
        editor.commit();
    }

    public UserModel getLoginCredits() {
        SharedPreferences sharedPref = applicationContext.getSharedPreferences("Login",Context.MODE_PRIVATE);
        String username = sharedPref.getString("Username", "");
        String password = sharedPref.getString("Password", "");
        String userid = sharedPref.getString("User ID", "");
        return new UserModel(username, password, userid);
    }
}
