package root.Interface;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import root.DataClass.Product;
import root.Database.DBService;


import java.util.List;

/**
 * Created by Ehtesham on 7/19/2016.
 */
public class productListView {


    private Stage window;
    private Scene scene;
    private BorderPane layout;

    private TableView table;



    private Button addButton;
    private Button removeButton;
    private Button editButton;


    private Label status;

    public productListView(){

        layout=new BorderPane();
        //top
        VBox topContainer=new VBox();//this will hold top stuffs
        HBox Tabs=new HBox(5);
        Tabs.setAlignment(Pos.CENTER);


        layout.setTop(topContainer);



        //center
        table=new TableView();
        setTable();
        updateTableData();
        layout.setCenter(table);


        //Right
        VBox rightContainer=new VBox(10);
        rightContainer.setAlignment(Pos.CENTER);
        layout.setRight(rightContainer);


        addButton=new Button("+ADD");
        addButton.setOnAction(event -> {
            //new AddProduct("Product",table);
        });

        editButton=new Button("Edit");
        editButton.setOnAction(event -> {
            //EditButtonPress();
        });

        removeButton=new Button("Remove");
        removeButton.setOnAction(event -> {
            DeleteButtonPress();
        });
        rightContainer.getChildren().addAll(addButton,editButton,removeButton);


        //Left
        VBox leftContainer=new VBox(10);
        leftContainer.setAlignment(Pos.CENTER);
        layout.setLeft(leftContainer);

        //bottom
        //bot will show A status Update after each exection Performed
        status=new Label();
        layout.setBottom(status);

        scene=new Scene(layout,800,600);
        window=new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Products");
        window.show();

    }

    public Scene GetScene(){
        return this.scene;
    }

    private void setTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //id
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment:CENTER;");


        //name
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setStyle("-fx-alignment:CENTER;");




        //addColumns on table
        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);

    }







    public void updateTableData(){
        //get data fromDatabase
        List<Product> data=new DBService().getProducts();
        try {
            for (Product p : data) {

                table.getItems().addAll(p);
            }

            table.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void DeleteButtonPress(){
        ObservableList<Product> productSelected , allProducts;
        productSelected = table.getSelectionModel().getSelectedItems();
        allProducts = table.getItems();

        if (!table.getSelectionModel().isEmpty()) {
            productSelected.forEach(allProducts::remove);
            status.setText("Row Deleted");
            status.setTextFill(Color.web("Red"));

        }
        else {
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }


    }

    public  void EditButtonPress(){
        ObservableList<Product> productSelected;
        productSelected = table.getSelectionModel().getSelectedItems();

        if (!table.getSelectionModel().isEmpty()) {
            new EditProduct("PRODUCT",table,productSelected.get(0));
        }
        else {
            status.setText("No Row Selected");
            status.setTextFill(Color.web("Blue"));
        }


    }




}
