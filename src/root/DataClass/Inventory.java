package root.DataClass;


import java.util.ArrayList;
import java.util.List;



public class Inventory
{
    private int id;
    private String  Name ;
    private List<ProductInfo> productInfos;

    //Constructor 1 to Make new inventory
    public Inventory(int id,String name) {
        this.id=id;
        this.Name = name;
        productInfos =new ArrayList<>();
    }


    public boolean LoadList(){
        try{
            //fetch data from Dataase search using the this.name
            //fill the arraylist of productInfos
        }catch (Exception e){
            //if Failed
            System.out.println("Could not load list");
            return  false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }
}