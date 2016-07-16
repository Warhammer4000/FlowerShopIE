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
        this.userName = "no name";
        this.userPassword = "no password";
    }

    public User(int uId, String uName, String uPass)
    {
        this.userId = uId;
        this.userName = uName;
        this.userPassword = uPass;
    }

    public void showinfo()
    {
        System.out.println("User Id: " +userId);
        System.out.println("User Name:" +userName);
    }

    public void setuserId(int uId)
    {
        this.userId=uId;
    }

    public int getuserId()
    {
        return userId;
    }

    public void setuserName(String uName)
    {
        userName=uName;
    }

    public String getuserName()
    {
        return userName;
    }

    public void setuserPassword(String uPass)
    {
        userPassword=uPass;
    }

    public String getuName()
    {
        return userPassword;
    }
}
