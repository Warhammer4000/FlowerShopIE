package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import root.CustomControl.NumericTextField;
import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.Database.DBService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Ehtesham on 7/20/2016.
 */
public class EditProduct {
    private Label idLable;
    private NumericTextField id;

    private Label nameLable;
    private TextField name;

    private Label quantityLable;
    private NumericTextField quantity;

    private Label purchaseDateLable;
    private DatePicker purchaseDate;

    private Label priceLable;
    private TextField price;

    private  Label vendorLable;
    private TextField vendor;

    private  Button submitButton;
    private  Label status;

    private VBox layout;
    private Stage window;
    private Scene scene;

    private  TableView table;
    private String tableName;

    public EditProduct(String tableName, TableView table,ProductInfo p){
        this.tableName=tableName;
        this.table=table;

        idLable=new Label("ID");
        id=new NumericTextField();
        id.setText(String.valueOf(p.getId()));
        id.setPromptText("Ex.1(number)");
        id.setMaxSize(200, 20);

        nameLable=new Label("Name");
        name=new TextField();
        name.setText(p.getName());
        name.setPromptText("Rose");
        name.setMaxSize(200, 20);


        quantityLable=new Label("Quantity");
        quantity=new NumericTextField();
        quantity.setPromptText("Ex.500");
        quantity.setMaxSize(200, 20);
        quantity.setText(String.valueOf(p.getQuantity()));


        purchaseDateLable=new Label("Purchase Date");
        purchaseDate=new DatePicker();
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        purchaseDate.setValue(LocalDate.parse(p.getPurchaseDate().toString(), dateFormatter));


        priceLable= new Label("Price");
        price=new TextField();
        price.setPromptText("Ex.500");
        price.setMaxSize(200, 20);
        price.setText(String.valueOf(p.getPrice()));

        vendorLable=new Label("Vendor");
        vendor=new TextField();
        vendor.setPromptText("Ex.500");
        vendor.setMaxSize(200, 20);
        vendor.setText(String.valueOf(p.getVendor()));

        submitButton=new Button("Submit");
        submitButton.setOnAction(event -> {
            addProduct();
        });
        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(idLable,id);
        layout.getChildren().addAll(nameLable,name);
        layout.getChildren().addAll(quantityLable,quantity);
        layout.getChildren().addAll(purchaseDateLable,purchaseDate);
        layout.getChildren().addAll(priceLable,price);
        layout.getChildren().addAll(vendorLable,vendor);
        layout.getChildren().addAll(submitButton);
        layout.getChildren().addAll(status);

        scene=new Scene(layout,300,400);
        window=new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();


    }

    public void addProduct(){
        //validate Values
        try {
            ProductInfo p;
            int ID=Integer.parseInt(id.getText());
            String Name=this.name.getText();
            int Quantity=Integer.parseInt(quantity.getText());
            //date
            LocalDate localDate = purchaseDate.getValue();
            Date Date = java.sql.Date.valueOf(localDate);

            double Price=Double.parseDouble(this.price.getText());
            String Vendor=vendor.getText();

            //create a product object
            p=new ProductInfo(ID,Name,Quantity,Date,Price,Vendor);


            table.getItems().addAll(p);

            id.setText("");
            name.setText("");
            price.setText("");
            quantity.setText("");
            vendor.setText("");



        }catch (Exception e){
            status.setText("Invalid Price input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

}
