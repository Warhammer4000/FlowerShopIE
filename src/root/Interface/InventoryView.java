package root.Interface;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.Database.DBService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

class InventoryView {
    private BorderPane layout;

    private static TableView table;
    private UpdateProductInfo UpdateProductInfo;


    private Button removeButton;
    private Button editButton;
    private Button refreshButton;


    private Label status;

    InventoryView(){

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


        //Left
        VBox leftContainer=new VBox(10);
        leftContainer.setAlignment(Pos.CENTER);
        layout.setLeft(leftContainer);

        editButton=new Button("Edit");
        removeButton=new Button("Remove");
        removeButton.setOnAction(event -> {
           // DeleteButtonPress();
        });
        refreshButton=new Button("Refresh");
        refreshButton.setOnAction(event -> {
            updateTableData();
        });
        leftContainer.getChildren().addAll(editButton,removeButton,refreshButton);


        //Right
        VBox rightContainer=new VBox(10);
        rightContainer.setAlignment(Pos.CENTER);
        UpdateProductInfo=new UpdateProductInfo();
        rightContainer.getChildren().addAll(UpdateProductInfo.getLayout());
        layout.setRight(rightContainer);

        //bottom
        //bot will show A status Update after each exection Performed
        status=new Label();
        layout.setBottom(status);


    }

    BorderPane getLayout(){
        return this.layout;
    }


    private void setTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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



        //InventoryId
        TableColumn<ProductInfo, Integer> InventoryidColumn = new TableColumn<>("Inventory No");
        InventoryidColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryNo"));
        InventoryidColumn.setStyle("-fx-alignment:CENTER;");

        table.setOnMouseClicked(event -> {
            ProductInfo p = (ProductInfo) table.getSelectionModel().getSelectedItem();

            UpdateProductInfo.setQuantity(p.getQuantity());
            UpdateProductInfo.setInventoryNo(p.getInventoryNo());
            UpdateProductInfo.setPrice(p.getPrice());
            UpdateProductInfo.setPurchaseDate(p.getPurchaseDate());
            UpdateProductInfo.setProduct(p.getId());

        });

        //addColumns on table

        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(dateColumn);
        table.getColumns().add(InventoryidColumn);

    }







    public static void updateTableData(){
        table.getItems().clear();
        DBService dbService=new DBService();
        List <ProductInfo>data;
        data=dbService.getInvetoryData();
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
