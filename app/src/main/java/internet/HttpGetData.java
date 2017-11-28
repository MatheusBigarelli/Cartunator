package internet;

import java.net.MalformedURLException;
import java.net.URL;

import user.User;

/**
 * Created by Matheus on 11/27/17.
 */

public class HttpGetData {
    private StringBuilder query = new StringBuilder();
    private String[] services = {
        "http://192.168.43.178:80/apps/Cartunator/check-credentials.php?ra=",
        "http://192.168.43.178:80/apps/Cartunator/get-user-info.php?ra="
    };
    private int requiredService;
    public static int CHECK_CREDENTIALS = 0;
    public static int GET_USER_DATA = 1;
    private String output;

    public HttpGetData(int service) {
        requiredService = service;
        query.append(services[service]);
    }

    public void setData(User user) {
        if (requiredService == CHECK_CREDENTIALS) {
            query.append(user.ra);
            query.append("&senha=");
            query.append(user.password);
        }
        else if (requiredService == GET_USER_DATA) {
            query.append(user.ra);
        }
    }

    public void connect() {

        HttpThread httpThread = new HttpThread();
        try {
            httpThread.setUrlPHP(new URL(query.toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        httpThread.start();

        waitForConnection();

        output = httpThread.getOutput();
    }

    private void waitForConnection() {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {}
    }

    public String getOutput() {return output;}
}
