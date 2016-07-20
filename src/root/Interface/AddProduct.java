package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Created by Ehtesham on 7/20/2016.
 */
public class AddProduct {
    private Label idLable;
    private TextField id;
    private Label nameLable;
    private TextField name;
    private Label quantityLable;
    private TextField quantity;
    private Label purchaseDateLable;
    private DatePicker purchaseDate;
    private Label priceLable;
    private TextField price;
    private  Label vendorLable;
    private TextField vendor;

    private  Button submitButton;

    private VBox layout;
    private Stage window;
    private Scene scene;
    public AddProduct(){
        idLable=new Label("ID");
        id=new TextField();
        id.setPromptText("Ex.1");
        id.setMaxSize(200, 20);

        nameLable=new Label("Name");
        name=new TextField();
        name.setPromptText("Rose");
        name.setMaxSize(200, 20);


        quantityLable=new Label("Quantity");
        quantity=new TextField();
        quantity.setPromptText("Ex.500");
        quantity.setMaxSize(200, 20);



        purchaseDateLable=new Label("Purchase Date");
        purchaseDate=new DatePicker();
        purchaseDate.setValue(LocalDate.now());


        priceLable= new Label("Price");
        price=new TextField();
        price.setPromptText("Ex.500");
        price.setMaxSize(200, 20);

        vendorLable=new Label("Vendor");
        vendor=new TextField();
        vendor.setPromptText("Ex.500");
        vendor.setMaxSize(200, 20);

        submitButton=new Button("Submit");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(idLable,id);
        layout.getChildren().addAll(nameLable,name);
        layout.getChildren().addAll(quantityLable,quantity);
        layout.getChildren().addAll(purchaseDateLable,purchaseDate);
        layout.getChildren().addAll(priceLable,price);
        layout.getChildren().addAll(vendorLable,vendor);
        layout.getChildren().addAll(submitButton);

        scene=new Scene(layout,300,400);
        window=new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();


    }

    public void addProduct(){
        //todo add product on current table
    }

}
