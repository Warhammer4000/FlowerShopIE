package root.DataClass;

/**
 * Created by tazim on 7/15/2016.
 */

public class User
{
    private int userId;
    private String userName;
    private String userPassword;

    public User ()
    {
        this.userId = 0;
        this.userName = "";
        this.userPassword = "";
    }

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
