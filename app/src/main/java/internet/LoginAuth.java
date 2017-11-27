package internet;

import java.lang.Thread;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mathe on 26/11/2017.
 */

public class LoginAuth {
	private String ra;
	private String password;
	private String queryResult;
	private String[] userInfo;

	public static int RA     = 0;
	public static int NAME   = 1;
	public static int EMAIL  = 2;
	public static int CREDIT = 3;

    public LoginAuth(String ra, String password) {
    	this.ra = ra;
    	this.password = password;
    }

    public boolean credentialsAreOk() {
    	StringBuilder query = new StringBuilder();
    	query.append("localhost/Cartunator/check-credentials.php?ra=");
    	query.append(ra);
    	query.append("&senha=");
    	query.append(password);

    	HttpThread httpThread = new HttpThread();
		try {
			httpThread.setUrlPHP(new URL(query.toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		httpThread.start();

    	queryResult = httpThread.getOutput();

    	System.out.println("Result from query    " + queryResult);

    	if (queryResult.equals("true")) {
    		System.out.println(queryResult);
    		return true;
    	}

    	setUserInfo();

    	return false;
    }

    public void setUserInfo() {
    	StringBuilder query = new StringBuilder();
    	query.append("localhost/Cartunator/get-user-info.php?ra=");
    	query.append(ra);

    	HttpThread httpThread = new HttpThread();
		try {
			httpThread.setUrlPHP(new URL(query.toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		httpThread.start();

    	queryResult = httpThread.getOutput();

    	userInfo = queryResult.split(",");
    }

    public String[] getUserInfo() {
		return userInfo;
    }
}
