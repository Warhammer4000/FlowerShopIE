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


class EditProduct {

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

    EditProduct(String tableName, TableView table, Product p){
        this.tableName=tableName;
        this.table=table;

        idLable=new Label("Product Id");
        id=new NumericTextField();
        id.setText(String.valueOf(p.getId()));
        id.setMaxSize(200, 20);

        nameLable=new Label("Product Name");
        name=new TextField();
        name.setText(p.getName());
        name.setMaxSize(200, 20);



        submitButton=new Button("Submit");
        submitButton.setOnAction(event -> EditProductTable());
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
        window.show();


    }

    private void EditProductTable(){
        //validate Values
        try {
            Product p;
            int ID=Integer.parseInt(id.getText());
            String Name=name.getText();

            //create a product object
            p=new Product(ID,Name);


            //todo Show on table
            //todo Update Database





        }catch (Exception e){
            status.setText("Invalid Price input");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

}
