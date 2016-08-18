package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.Database.DBService;

import java.util.Date;
import java.util.List;

class InventoryView {
    private BorderPane layout;

    private static TableView table;
    private UpdateInventory updateInventory;





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



        //Right
        VBox rightContainer=new VBox(10);
        rightContainer.setAlignment(Pos.CENTER);
        updateInventory =new UpdateInventory();
        rightContainer.getChildren().addAll(updateInventory.getLayout());
        layout.setRight(rightContainer);

        //bottom
        //bot will show A status Update after each exection Performed
        status=updateInventory.getStatus();
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
            if(p!=null){
                updateInventory.setQuantity(p.getQuantity());
                updateInventory.setInventoryNo(p.getInventoryNo());
                updateInventory.setPrice(p.getPrice());
                updateInventory.setPurchaseDate(p.getPurchaseDate());
                updateInventory.setProduct(p.getId());
                updateInventory.setSelectedProduct(p);
            }


        });

        //addColumns on table

        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(dateColumn);
        table.getColumns().add(InventoryidColumn);

    }







    static void updateTableData(){
        table.getItems().clear();
        List<Product> products;
        DBService dbService=new DBService();
        List <ProductInfo>data;
        data=dbService.getInvetoryData();
        products=dbService.getProducts();
        //looks in effecient
        try {
            for (ProductInfo p : data) {
                products.stream().filter(product -> product.getId() == p.getId()).forEach(product -> p.setName(product.getName()));


                table.getItems().add(p);
            }

            table.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
