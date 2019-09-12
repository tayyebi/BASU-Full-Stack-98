package ir.kukuapp.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.*;
import ir.kukuapp.mobile.network.HttpConnection;
import ir.kukuapp.mobile.presenter.LoginPresenter;

public class LoginActivity extends Activity implements LoginPresenter.View {

    LoginPresenter presenter;
    EditText et_username, et_password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(RelativeLayout.inflate(this, R.layout.activity_login, null));

         presenter = new LoginPresenter(this);

        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);

        // Login button clicked
        ((Button)findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Login(et_username.getText().toString(), et_password.getText().toString());
            }
        });

    }

    @Override
    public void ShowError(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_LONG).show();
    }
}
