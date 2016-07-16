package root.DataClass;

/**
 * Created by Fahmida Milee on 15/07/2016.
 */
public class Inventory
{
    private int Id;
    private String  name ;

    public Inventory(int id, String name) {
        Id = id;
        this.name = name;
    }

    public Inventory() {
        Id=0;
        this.name="";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}