package internet;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Matheus on 11/25/17.
 */

public class HttpSaveData{
    private StringBuilder strBuilderURL = new StringBuilder();
    private String ra;
    private String credit;
    private URL urlSavePHP;
    private String output;
    private String[] services = {
            "http://192.168.43.178:6969/arduino/savedata.php?flag=1&",
            "http://10.10.36.103:80/apps/Cartunator/update-user-credit.php?ra="
    };
    private int requiredService;
    public static int SAVE_LEO = 0;
    public static int SAVE_BIGA = 1;



    public HttpSaveData(int service) {
        requiredService = service;
        strBuilderURL.append(services[service]);
    }

    public void setData(String ra, String credit) {
        this.ra = ra;
        this.credit = credit;

        if (requiredService == SAVE_LEO) {
            strBuilderURL.append("ra=");
            strBuilderURL.append(this.ra);
            strBuilderURL.append("&");
            strBuilderURL.append("cred=");
            strBuilderURL.append(this.credit);
            strBuilderURL.append("00");
            try {
                urlSavePHP = new URL(strBuilderURL.toString());
            } catch (MalformedURLException e) {}
        }

        else if (requiredService == SAVE_BIGA) {
            strBuilderURL.append(this.ra);
            strBuilderURL.append("&");
            strBuilderURL.append("credito=");
            strBuilderURL.append(this.credit);

            try {
                urlSavePHP = new URL(strBuilderURL.toString());
            }
            catch (MalformedURLException e) {}
        }
    }


    public void connect() {
        HttpThread httpThread = new HttpThread();

        if (requiredService == SAVE_LEO) {
            try {
                httpThread.setUrlPHP(urlSavePHP);

                httpThread.start();
                output = httpThread.getOutput();
            } catch (Exception e){}

        }

        else if (requiredService == SAVE_BIGA) {
            try {
                //DB do biga
                httpThread = new HttpThread();
                httpThread.setUrlPHP(urlSavePHP);

                httpThread.start();
            } catch (Exception e) {}

        }

        waitForConnection();

        output = httpThread.getOutput();
    }


    public void waitForConnection() {
        try {
            Thread.sleep(500);
        }
        catch (Exception e) {}
    }

}
