package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import root.CustomControl.AutoCompleteComboBox;
import root.CustomControl.NumericTextField;
import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.Database.DBService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


class AddProductInfo {
    private List<Product> availableProducts;
    private AutoCompleteComboBox<Product> productPicker=new AutoCompleteComboBox<>();

    private Label productLable;

    private Label quantityLable;
    private NumericTextField quantity;

    private Label purchaseDateLable;
    private DatePicker purchaseDate;

    private Label priceLable;
    private TextField price;


    private  Label InventoryNoLable;
    private NumericTextField InventoryNo;


    private  Button AddButton;
    private  Label status;

    private VBox layout;

    AddProductInfo(){

        productLable=new Label("Product");
        availableProducts=new DBService().getProducts();
        for(Product p : availableProducts){
            productPicker.getItems().addAll(p);
        }
        productPicker.setEditable(true);

        quantityLable=new Label("Quantity");
        quantity=new NumericTextField();
        quantity.setPromptText("Ex.500");
        quantity.setMaxSize(200, 20);



        purchaseDateLable=new Label("Purchase Date");
        purchaseDate=new DatePicker();
        purchaseDate.setValue(LocalDate.now());


        priceLable= new Label("Price");
        price=new TextField();
        price.setPromptText("Ex.500");
        price.setMaxSize(200, 20);




        InventoryNoLable=new Label("Inventory No");
        InventoryNo=new NumericTextField();
        InventoryNo.setMaxSize(200, 20);


        AddButton=new Button("+Add");
        AddButton.setOnAction(event -> addProduct());
        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(productLable,productPicker);

        layout.getChildren().addAll(quantityLable,quantity);
        layout.getChildren().addAll(purchaseDateLable,purchaseDate);
        layout.getChildren().addAll(priceLable,price);
        layout.getChildren().addAll(InventoryNoLable,InventoryNo);
        layout.getChildren().addAll(AddButton);
        layout.getChildren().addAll(status);
    }


    VBox getLayout() {
        return layout;
    }

    private void addProduct(){
        //validate Values
        try {
            ProductInfo p;
            int ID=Integer.parseInt(String.valueOf(productPicker.getValue().getId()));
            String Name=productPicker.getValue().getName();
            int Quantity=Integer.parseInt(quantity.getText());
            //date
            LocalDate localDate = purchaseDate.getValue();
            Date Date = java.sql.Date.valueOf(localDate);

            double Price=Double.parseDouble(this.price.getText());

            int InventoryNO=Integer.parseInt(this.InventoryNo.getText());



            //create a product object
            p=new ProductInfo(ID,Name,Quantity,Date,Price,InventoryNO);
            DBService dbService=new DBService();
            dbService.insertNewProductInfo(p);

        }catch (Exception e){
            status.setText("Invalid Price input");
            status.setTextFill(Color.valueOf("Red"));
        }catch (Error e){
            status.setText("Database Error");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

}
