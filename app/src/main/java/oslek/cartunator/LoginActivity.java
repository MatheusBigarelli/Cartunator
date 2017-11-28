package oslek.cartunator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import internet.LoginAuth;
import user.User;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton;
    private EditText editRA;
    private EditText editPassword;
    private User user = new User();
    private MainActivity mainActivity = MainActivity.context;
    public static LoginActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        signInButton = (Button) findViewById(R.id.buttonLogin);

        editRA = (EditText) findViewById(R.id.editRA);
        editPassword = (EditText) findViewById(R.id.editPassword);
        context = this;
    }


    public void signIn(View view) {
        getUserDataFromEdits();


        if (authenticateUser() == true) {
            setUserMain();
            finish();
        }

        else {
            Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
            editRA.requestFocus();
        }
    }


    public void getUserDataFromEdits() {
        user.ra = editRA.getText().toString();
        user.password = editPassword.getText().toString();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserMain() {
        mainActivity.setCurrentUser(user);
    }

    public boolean authenticateUser() {

        LoginAuth loginAuth = new LoginAuth(user.ra, user.password);

        if (loginAuth.credentialsAreOk()) {
            String[] userInfo = loginAuth.getUserInfo();

            user.name = userInfo[LoginAuth.NAME];
            user.credit = userInfo[LoginAuth.CREDIT];

            return true;
        }
        
        return false;
    }
}
