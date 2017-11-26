package internet;

import java.net.MalformedURLException;
import java.net.URL;

import oslek.cartunator.RechargeActivity;

/**
 * Created by Matheus on 11/25/17.
 */

public class HttpSaveData{
    private StringBuilder strBuilderURL = new StringBuilder();
    private String ra;
    private String credit;
    private URL urlSavePHP;
    private String output;

    public HttpSaveData() {
        strBuilderURL.append("http://192.168.43.178:6969/arduino/savedata.php?flag=1&");
    }

    public void setData(String ra, String credit) {
        this.ra = ra;
        this.credit = credit;
        System.out.println("Credito " + this.credit);

        strBuilderURL.append("ra=");
        strBuilderURL.append(this.ra);
        strBuilderURL.append("&");
        strBuilderURL.append("cred=");
        strBuilderURL.append(this.credit);
        strBuilderURL.append("00");
        try {
            System.out.println(strBuilderURL.toString());
            urlSavePHP = new URL(strBuilderURL.toString());
        } catch (MalformedURLException e) {}
    }


    public void connect() {
        HttpThread httpThread = new HttpThread();
        httpThread.setUrlSavePHP(urlSavePHP);
        httpThread.start();
        output = httpThread.getOutput();
    }




}
