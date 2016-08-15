package root.Interface;


import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import root.DataClass.Product;
import root.Database.DBService;


import java.util.List;


class productView {
    private BorderPane layout;

    private static TableView table;

    private Button removeButton;
    private Button editButton;
    private Button refreshButton;


    private Label status;

    productView(){

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
        AddProduct ap=new AddProduct();
        rightContainer.getChildren().addAll(ap.getLayout());
        layout.setRight(rightContainer);







        //Left

        VBox leftContainer=new VBox(10);
        editButton=new Button("Edit");
        editButton.setOnAction(event -> {
            //EditButtonPress();
        });

        removeButton=new Button("Remove");
        removeButton.setOnAction(event -> {
            //DeleteButtonPress();
        });

        refreshButton=new Button("Refresh");
        refreshButton.setOnAction(event -> {
            table.getItems().clear();
            updateTableData();
        });

        leftContainer.setAlignment(Pos.CENTER);
        leftContainer.getChildren().addAll(editButton,removeButton,refreshButton);
        layout.setLeft(leftContainer);

        //bottom
        //bot will show A status Update after each exection Performed
        status=new Label();
        layout.setBottom(status);



    }

    BorderPane getLayout() {
        return layout;
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

        //Type
        TableColumn<Product, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setStyle("-fx-alignment:CENTER;");

        //Vendor
        TableColumn<Product, String> vendorColumn = new TableColumn<>("Vendor");
        vendorColumn.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        vendorColumn.setStyle("-fx-alignment:CENTER;");




        //addColumns on table
        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(typeColumn);
        table.getColumns().add(vendorColumn);

    }







    public static void updateTableData(){
        //get data fromDatabase
        table.getItems().clear();
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
