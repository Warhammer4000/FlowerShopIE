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


public class EditProductInfo {

    private ComboBox<Product> productPicker=new ComboBox<>();
    private Label productLable;


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

    public EditProductInfo(String tableName, TableView table, ProductInfo p){
        this.tableName=tableName;
        this.table=table;

        productLable=new Label("Product");
        productPicker=new ComboBox<>();



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
        submitButton.setOnAction(event -> addProduct());
        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
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
        window.setTitle("Product Info");
        window.show();


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
            String Vendor=vendor.getText();

            //create a product object
            p=new ProductInfo(ID,Name,Quantity,Date,Price,Vendor);


            table.getItems().addAll(p);


            price.setText("");
            quantity.setText("");
            vendor.setText("");



        }catch (Exception e){
            status.setText("Invalid Price input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

}
