package {{ PACKAGE_NAME }};

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.widget.*;

public class HomeActivity extends Activity {
	RelativeLayout layout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout)RelativeLayout.inflate(this, R.layout.activity_main, null);
        setContentView(layout);
	}
}
