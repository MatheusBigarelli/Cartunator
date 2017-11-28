package oslek.cartunator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import user.User;

public class MainActivity extends AppCompatActivity {

    private TextView textHello;
//    private TextView textCredit;
    public User user = new User();
    public static MainActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;


        textHello = (TextView) findViewById(R.id.textHello);
//        textCredit = (TextView) findViewById(R.id.textCredit);

        login();
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
        if (id == R.id.action_logout) {
            resetUser();
            login();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetUser() {
        user.name = null;
        user.credit = null;
        user.ra = null;

    }

    public void setCurrentUser(User currentUser) {
        try {

            user.name = currentUser.name;
            user.ra = currentUser.ra;
            user.credit = currentUser.credit;

            textHello.setText(new StringBuilder("Olá ").append(user.name).toString());
            //We use .trim() to take away the end line characters or any white spaces
//            textCredit.setText(new StringBuilder("Seu saldo é R$").append(user.credit.trim()).append(",00").toString());
        } catch (NullPointerException e) {
        }
    }


    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    public void recharge(View view) {
        RechargeActivity.user.ra = this.user.ra;

        Intent intent = new Intent(this, RechargeActivity.class);
        startActivity(intent);
    }
}
