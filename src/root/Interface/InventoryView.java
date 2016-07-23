package root.Interface;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import root.DataClass.ProductInfo;

import java.util.Date;
import java.util.List;

public class InventoryView {


    private Stage window;
    private Scene scene;
    private BorderPane layout;

    private TableView table;



    private Button addButton;
    private Button removeButton;
    private Button editButton;


    private Label status;

    public InventoryView(String tableName,List<ProductInfo> data){

        layout=new BorderPane();
        //top
        VBox topContainer=new VBox();//this will hold top stuffs
        HBox Tabs=new HBox(5);
        Tabs.setAlignment(Pos.CENTER);


        layout.setTop(topContainer);



        //center
        table=new TableView();
        setTable();
        updateTableData(data);
        layout.setCenter(table);


        //Right
        VBox rightContainer=new VBox(10);
        rightContainer.setAlignment(Pos.CENTER);
        layout.setRight(rightContainer);
        addButton=new Button("+ADD");
        addButton.setOnAction(event -> {
            new AddProductInfo(tableName,table);
        });
        editButton=new Button("Edit");
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
        window.setTitle(tableName);
        window.show();

    }

    public Scene GetScene(){
        return this.scene;
    }

    private void setTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //id
        TableColumn<ProductInfo, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment:CENTER;");


        //name
        TableColumn<ProductInfo, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setStyle("-fx-alignment:CENTER;");

        //Price
        TableColumn<ProductInfo, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        priceColumn.setStyle("-fx-alignment:CENTER;");


        //Quantity
        TableColumn<ProductInfo, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        quantityColumn.setStyle("-fx-alignment:CENTER;");


        //Date
        TableColumn<ProductInfo, Date> dateColumn = new TableColumn<>("Purchase Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        dateColumn.setStyle("-fx-alignment:CENTER;");

        //Vendor
        TableColumn<ProductInfo, String> vendorColumn = new TableColumn<>("Vendor");
        vendorColumn.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        vendorColumn.setStyle("-fx-alignment:CENTER;");


        //addColumns on table
        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(dateColumn);
        table.getColumns().add(vendorColumn);
    }







    public void updateTableData(List<ProductInfo> data){
        try {
            for (ProductInfo p : data) {

                table.getItems().addAll(p);
            }

            table.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void DeleteButtonPress(){
        //// TODO Delete item from DB 
        ObservableList<ProductInfo> accountSelected , allAccounts;
        accountSelected = table.getSelectionModel().getSelectedItems();
        allAccounts = table.getItems();

        if (!table.getSelectionModel().isEmpty()) {
            accountSelected.forEach(allAccounts::remove);
            status.setText("Row Deleted");
            status.setTextFill(Color.web("Red"));
            table.refresh();
        }
        else {
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }


    }



}
