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
    private Button DeleteButton;
    private  Label status;

    private VBox layout;

    VBox getLayout() {
        return layout;
    }

    void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    void setType(String type) {
        this.type.setText(type);
    }

    void setVendor(String vendor) {
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
        type.setMaxSize(200, 20);

        VendorLable=new Label("Vendor Name");
        vendor=new TextField();
        vendor.setMaxSize(200, 20);




        AddButton =new Button("+Add");
        AddButton.setOnAction(event -> addProduct());

        EditButton=new Button("Edit");
        EditButton.setOnAction(event -> EditButtonPress());

        DeleteButton=new Button("Delete");
        DeleteButton.setOnAction(event -> DeleteButtonPress());

        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(idLable,id);
        layout.getChildren().addAll(nameLable,name);
        layout.getChildren().addAll(typeLable,type);
        layout.getChildren().addAll(VendorLable,vendor);

        layout.getChildren().addAll(AddButton,EditButton,DeleteButton);

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
            if(dbService.insertNewProduct(p)){
                //show status ToolTip
                status.setText("Row Inserted");
                status.setTextFill(Color.valueOf("Green"));
                //update Table
                productView.updateTableData();
            }else {
                status.setText("Invalid Input");
                status.setTextFill(Color.valueOf("Red"));
            }



        }catch (Exception e){
            status.setText("Invalid Input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

    private void EditButtonPress(){
        //update Database
        int ID=-1;
        String Name="";
        String Type="";
        String Vendor="";
        try{
            ID = Integer.parseInt(id.getText());
            Name = this.name.getText();
            Type = this.type.getText();
            Vendor = this.vendor.getText();
        }catch(Exception e){
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }


        //create a product object
        Product p=new Product(ID,Name,Type,Vendor);
        if (ID>0) {

            //Update From DB
            try{

                DBService dbService=new DBService();
                if(dbService.EditProduct(p)){
                    status.setText("Row Edited");
                    status.setTextFill(Color.web("Blue"));
                }else {
                    status.setText("Couldn't Update");
                    status.setTextFill(Color.web("Red"));
                }


            }catch (Exception e){
                status.setText("Database Exception call your DbManager");
                status.setTextFill(Color.web("Red"));
            }



        }



        //update when done
        productView.updateTableData();
    }

    private void DeleteButtonPress(){
        int productID=-1;
        try{
          productID = Integer.parseInt(id.getText());
        }catch (Exception e){
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }

        if (productID!=-1) {

            //Delete From DB
            try{

                DBService dbService=new DBService();
                if(dbService.deleteProduct(productID)) {
                    status.setText("Row Deleted");
                    status.setTextFill(Color.web("Green"));
                }


            }catch (Exception e){
                status.setText(e.getLocalizedMessage());
                status.setTextFill(Color.web("Red"));
            }



        }

        //update when done
        productView.updateTableData();
    }

    public Label getStatus() {
        return status;
    }
}
