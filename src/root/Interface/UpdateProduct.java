package root.Interface;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import root.CustomControl.NumericTextField;
import root.DataClass.Product;
import root.Database.DBService;


class UpdateProduct {
    private Label idLable;
    private NumericTextField id;

    private Label nameLable;
    private TextField name;

    private Label typeLable;
    private TextField type;

    private Label VendorLable;
    private TextField vendor;



    private  Button AddButton;
    private Button EditButton;
    private  Label status;

    private VBox layout;

    VBox getLayout() {
        return layout;
    }

    public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setVendor(String vendor) {
        this.vendor.setText(vendor);
    }

    UpdateProduct(){
        idLable=new Label("Product ID");
        id=new NumericTextField();
        id.setPromptText("Ex.1(number)");
        id.setMaxSize(200, 20);

        nameLable=new Label("Product Name");
        name=new TextField();
        name.setPromptText("Rose");
        name.setMaxSize(200, 20);


        typeLable=new Label("Product Type");
        type=new TextField();
        name.setMaxSize(200, 20);

        VendorLable=new Label("Vendor Name");
        vendor=new TextField();
        vendor.setMaxSize(200, 20);




        AddButton =new Button("+Add");
        AddButton.setOnAction(event -> addProduct());

        EditButton=new Button("Edit");
        EditButton.setOnAction(event -> EditButtonPress());

        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(idLable,id);
        layout.getChildren().addAll(nameLable,name);
        layout.getChildren().addAll(typeLable,type);
        layout.getChildren().addAll(VendorLable,vendor);

        layout.getChildren().addAll(AddButton,EditButton);
        layout.getChildren().addAll(status);


    }





    private void addProduct(){
        //validate Values
        try {
            Product p;
            int ID=Integer.parseInt(id.getText());
            String Name=this.name.getText();
            String Type=this.type.getText();
            String Vendor=this.vendor.getText();
            //create a product object
            p=new Product(ID,Name,Type,Vendor);
            DBService dbService=new DBService();
            dbService.insertNewProduct(p);
            status.setText("Row Inserted");
            status.setTextFill(Color.valueOf("Green"));
            productView.updateTableData();

        }catch (Exception e){
            status.setText("Invalid Input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

    public  void EditButtonPress(){
        //update Database




    }

}
