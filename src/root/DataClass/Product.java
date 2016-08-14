package root.DataClass;


public class Product {
    private int id;
    private String name;
    private String vendor;
    private String type;

    public Product(int id, String name, String vendor, String type) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
