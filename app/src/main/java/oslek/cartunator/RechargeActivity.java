package oslek.cartunator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import internet.HttpGetData;
import internet.HttpSaveData;
import internet.LoginAuth;
import user.User;

public class RechargeActivity extends AppCompatActivity {

    private RadioButton[] buttonsRecharge = new RadioButton[4];
    public static RechargeActivity context;
    public static User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonsRecharge[0] = (RadioButton) findViewById(R.id.buttonRecharge10);
        buttonsRecharge[1] = (RadioButton) findViewById(R.id.buttonRecharge20);
        buttonsRecharge[2] = (RadioButton) findViewById(R.id.buttonRecharge30);
        buttonsRecharge[3] = (RadioButton) findViewById(R.id.buttonRecharge50);

        RechargeActivity.context = this;
    }

    public void confirm(View view) {
//        HttpSaveData httpSaveData = new HttpSaveData(HttpSaveData.SAVE_BIGA);
//        httpSaveData.setData(user.ra, getValueOfRecharge());
//        httpSaveData.connect();
//
//        httpSaveData.waitForConnection();


        HttpSaveData httpSaveDataLeo = new HttpSaveData(HttpSaveData.SAVE_LEO);
        httpSaveDataLeo.setData(user.ra, getValueOfRecharge());
        httpSaveDataLeo.connect();

        httpSaveDataLeo.waitForConnection();

        updateUserInfo();

        finish();
    }

    public void updateUserInfo() {
        HttpGetData httpGetData = new HttpGetData(HttpGetData.GET_USER_DATA);
        httpGetData.setData(user);
        httpGetData.connect();

        String output = httpGetData.getOutput();
        String[] userData = output.split(",");

        user.ra = userData[LoginAuth.RA];
        user.name = userData[LoginAuth.NAME];
        user.credit = userData[LoginAuth.CREDIT];

        MainActivity mainActivity = MainActivity.context;
        mainActivity.setCurrentUser(user);
    }

    public String getValueOfRecharge() {
        for (RadioButton button : buttonsRecharge)
            if (button.isChecked()) {
                String recharge = button.getText().toString();
                recharge = recharge.substring(recharge.indexOf("$") + 1, recharge.indexOf(","));
                return recharge;
            }
        return null;
    }


    public void chooseRecharge10(View view) {
        for (RadioButton button : buttonsRecharge)
            if (button.isChecked())
                button.setChecked(false);
        buttonsRecharge[0].setChecked(true);
    }

    public void chooseRecharge20(View view) {
        for (RadioButton button : buttonsRecharge)
            if (button.isChecked())
                button.setChecked(false);
        buttonsRecharge[1].setChecked(true);
    }

    public void chooseRecharge30(View view) {
        for (RadioButton button : buttonsRecharge)
            if (button.isChecked())
                button.setChecked(false);
        buttonsRecharge[2].setChecked(true);
    }

    public void chooseRecharge50(View view) {
        for (RadioButton button : buttonsRecharge)
            if (button.isChecked())
                button.setChecked(false);
        buttonsRecharge[3].setChecked(true);
    }
}
