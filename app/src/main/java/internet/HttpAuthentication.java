package internet;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import oslek.cartunator.LoginActivity;
import user.User;

/**
 * Created by Matheus on 11/23/17.
 */

public class HttpAuthentication {
    private URL url;
    private HttpURLConnection httpURLConnection;
    private String output = null;
    private User user = new User();

    public HttpAuthentication() {
        try {
            url = new URL("http://192.168.1.103:6969/arduino/savedata.php?flag=1&ra=184449&cred=1304");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int httpResponse = httpURLConnection.getResponseCode();

            if (httpResponse == HttpURLConnection.HTTP_OK) {
                getHttpResponse();
                filterHttpResponse();
                Toast.makeText(LoginActivity.context, "Http:200", Toast.LENGTH_LONG).show();
                try {Thread.sleep(1000);}catch (Exception e) {}
            }
            else
                Toast.makeText(LoginActivity.context, "Something went wrong in httpAuth", Toast.LENGTH_LONG).show();
            try {Thread.sleep(1000);}catch (Exception e) {}
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.context, "Cannot make http connection", Toast.LENGTH_LONG).show();
            try {Thread.sleep(1000);}catch (Exception ex) {}
        }
    }

    private void getHttpResponse() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder outputBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                outputBuilder.append(line);
            }
            output = outputBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterHttpResponse() {

    }


    public void setUser(User user) {
        this.user.name = user.name;
        this.user.password = user.password;
    }

    public boolean isUserValid() {

        return false;
    }
}
