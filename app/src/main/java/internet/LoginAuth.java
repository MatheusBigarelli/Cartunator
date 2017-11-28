package internet;

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
}
