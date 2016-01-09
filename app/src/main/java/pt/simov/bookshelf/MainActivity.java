package pt.simov.bookshelf;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends Activity {

    private TextView info;
    private LoginButton loginButton;

    private CallbackManager callbackManager;

    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        //Ao carregar no menu de "terminar sessao", vai entrar aqui
        //Ve se tem texto a vir do outro lado, e se tiver TERMINAR, faz click no botao para terminar sessao!
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            String terminar = extras.getString("TERMINAR");
            if (terminar != null) {
                loginButton.performClick();
            }
        }else{
            if(AccessToken.getCurrentAccessToken() != null){
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        }



        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    info.setText("");
                    //fbPhoto.setImageResource(android.R.color.transparent);
                }
            }
        };

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.sign_in), Toast.LENGTH_LONG).show();
                /*info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );*/
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                startActivity(intent);

                //loginResult.getAccessToken().getUserId()
                //loginResult.getAccessToken().getToken()

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                info.setText("Login attempt failed.");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean pesquisar(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
