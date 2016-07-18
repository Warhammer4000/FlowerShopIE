package root.DataClass;

/**
 * Created by Fahmida Milee on 15/07/2016.
 */
public class Inventory
{
    private int Id;
    private String  Name ;

    public Inventory(int id, String name) {
        Id = id;
        this.Name = name;
    }

    public Inventory() {
        Id=0;
        this.Name="";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}