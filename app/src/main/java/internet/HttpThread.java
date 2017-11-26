package internet;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import oslek.cartunator.RechargeActivity;

/**
 * Created by Matheus on 11/25/17.
 */

public class HttpThread extends Thread {

    public URL urlSavePHP;
    private HttpURLConnection httpURLConnection;
    private String output;

    public void run() {
        connect();

    }

    public void setUrlSavePHP(URL url) {
        this.urlSavePHP = url;
    }

    public void connect() {
        try {
            httpURLConnection = (HttpURLConnection) urlSavePHP.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            getHttpResponse();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }


    private void getHttpResponse() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder outputBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                outputBuilder.append(line);
                outputBuilder.append("\n");
            }
            bufferedReader.close();
            output = outputBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOutput() { return output; }
}
