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
import root.Database.DBService;


class AddProduct {
    private Label idLable;
    private NumericTextField id;

    private Label nameLable;
    private TextField name;


    private  Button submitButton;
    private  Label status;

    private VBox layout;
    private Stage window;
    private Scene scene;

    private  TableView table;
    private String tableName;


    private void setup(){
        idLable=new Label("Product ID");
        id=new NumericTextField();
        id.setPromptText("Ex.1(number)");
        id.setMaxSize(200, 20);

        nameLable=new Label("Product Name");
        name=new TextField();
        name.setPromptText("Rose");
        name.setMaxSize(200, 20);


        submitButton=new Button("+Add");
        submitButton.setOnAction(event -> addProduct());
        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(idLable,id);
        layout.getChildren().addAll(nameLable,name);

        layout.getChildren().addAll(submitButton);
        layout.getChildren().addAll(status);

        scene=new Scene(layout,300,400);
        window=new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("NEW PRODUCT");
        window.show();
    }



    AddProduct(String tableName){
        this.tableName=tableName;
        setup();

    }



    AddProduct(String tableName, TableView table){
        this.tableName=tableName;
        this.table=table;
        setup();
    }

    private void addProduct(){
        //validate Values
        try {
            Product p;
            int ID=Integer.parseInt(id.getText());
            String Name=this.name.getText();


            //create a product object
            p=new Product(ID,Name);
            DBService dbService=new DBService();
            dbService.insertNewProduct(p);
            status.setText("Row Inserted");
            status.setTextFill(Color.valueOf("Green"));

        }catch (Exception e){
            status.setText("Invalid Input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

}
