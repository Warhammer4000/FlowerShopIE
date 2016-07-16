package root.DataClass;

/**
 * Created by Fahmida Milee on 15/07/2016.
 */
public class Inventory
{
    private int invenId;
    private String invenName;

    public Inventory ()
    {
        this.invenId = 0;
        this.invenName = "no name";

    }

    public Inventory(int iId, String iName)
    {
        this.invenId = iId;
        this.invenName = iName;
    }

    public void showinfo()
    {
        System.out.println("Inventory Id: " +invenId);
        System.out.println("inventory Name:" +invenName);
    }

    public void setinvenId(int iId)
    {
        this.invenId=iId;
    }

    public int getinvenId()
    {
        return invenId;
    }

    public void setinvenName(String iName)
    {
        invenName=iName;
    }

    public String getinvenName()
    {
        return invenName;
    }
}