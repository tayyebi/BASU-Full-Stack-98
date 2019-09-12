package ir.kukuapp.mobile;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.*;
import ir.kukuapp.mobile.internal.HomeAdapter;
import ir.kukuapp.mobile.internal.SharedMem;
import ir.kukuapp.mobile.model.PostModel;
import ir.kukuapp.mobile.model.UserModel;
import ir.kukuapp.mobile.network.HttpConnection;
import ir.kukuapp.mobile.presenter.HomePresenter;

import java.util.ArrayList;

public class HomeActivity extends Activity implements HomePresenter.IHomeView {

    EditText et_status ;
    Button btn_send , btn_logout, btn_refresh;
    ListView itemsListView;
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call the presenter
        presenter = new HomePresenter(this);

        // Redirect to login if not loggedin
        presenter.CheckLogin();

        // Set content view
        setContentView(LinearLayout.inflate(this, R.layout.activity_home, null));

        // Sync with XML
        et_status = (EditText) findViewById(R.id.et_status);
        itemsListView = (ListView) findViewById(R.id.list_home);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);


        // Refresh
        presenter.RefreshFeed();

        // Logout button
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Logout();
            }
        });

        // Send post
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send the post to net
                presenter.SendPost(et_status.getText().toString());
                // reload the feed
                presenter.RefreshFeed();
            }
        });

        // Refresh button
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.RefreshFeed();
            }
        });
    }

    @Override
    public void ReloadHomeFeed(HomeAdapter adapter) {
        // Attach adapter to widget
        itemsListView.setAdapter(adapter);
    }

    @Override
    public void ShowError(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_LONG).show();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public void RestartApp () {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
