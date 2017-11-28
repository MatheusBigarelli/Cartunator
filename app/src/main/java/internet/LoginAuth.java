package internet;

import java.lang.Thread;
import java.net.MalformedURLException;
import java.net.URL;

import oslek.cartunator.LoginActivity;
import user.User;

/**
 * Created by mathe on 26/11/2017.
 */

public class LoginAuth {
	private User user = new User();
	private String queryResult;
	private String[] userInfo;
	private LoginActivity loginActivity;

	public static int RA     = 0;
	public static int NAME   = 1;
	public static int CREDIT = 2;

    public LoginAuth(String ra, String password) {
    	this.user.ra = ra;
    	this.user.password = password;
    	loginActivity = LoginActivity.context;
    }

    public boolean credentialsAreOk() {
//    	String service = "http://192.168.1.103:80/apps/Cartunator/check-credentials.php?ra=";
//		HttpGetData httpGetData = new HttpGetData(service);
//		httpGetData.setData(user);
//		httpGetData.connect();
//
//    	queryResult = (String) httpGetData.getOutput();

		HttpGetData httpGetData = new HttpGetData(HttpGetData.CHECK_CREDENTIALS);
		httpGetData.setData(user);
		httpGetData.connect();

		queryResult = httpGetData.getOutput();

    	//We use .trim() to remove any white spaces in result string
    	if (queryResult.trim().equals("ok")) {
			setUserInfo();
    		return true;
    	}

    	return false;
    }

    public void setUserInfo() {
//    	StringBuilder query = new StringBuilder();
//    	query.append("http://192.168.1.103:80/apps/Cartunator/get-user-info.php?ra=");
//    	query.append(user.ra);
//
//    	System.out.println("Query: " + query.toString());
//
//    	HttpThread httpThread = new HttpThread();
//		try {
//			httpThread.setUrlPHP(new URL(query.toString()));
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		httpThread.start();
//
//		waitForConnection();
//
//    	queryResult = httpThread.getOutput();
//
//    	System.out.println("Query result: " + queryResult);

		HttpGetData httpGetData = new HttpGetData(HttpGetData.GET_USER_DATA);
		httpGetData.setData(user);
		httpGetData.connect();

		queryResult = httpGetData.getOutput();

    	userInfo = queryResult.split(",");

    	user.ra = userInfo[RA];
    	user.name = userInfo[NAME];
		user.credit = userInfo[CREDIT];

		loginActivity.setUser(user);
    }

    public String[] getUserInfo() {
		return userInfo;
    }

    public void waitForConnection() {
		//Waits for connection
		try {
			Thread.sleep(1000);
		}
		catch (Exception e) {}
	}
}
