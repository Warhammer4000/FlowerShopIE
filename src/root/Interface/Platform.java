package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import root.App.Main;
import root.DataClass.Product;

import java.util.Date;

/**
 * Created by Ehtesham on 7/19/2016.
 */
public class Platform {
    private Main main;

    private Scene scene;
    private BorderPane layout;
    private MenuBar menuBar;
    private TableView table;

    private Button Inventory1;
    private Button Inventory2;
    private Button Inventory3;

    private Button addButton;
    private Button removeButton;
    private Button editButton;

    private Button searchButton;

    private Label status;

    public Platform(Main main){
        this.main=main;
        layout=new BorderPane();
        //top
        VBox topContainer=new VBox();//this will hold top stuffs
        HBox Tabs=new HBox(5);
        Tabs.setAlignment(Pos.CENTER);
        Inventory1=new Button("Show Room");
        Inventory2=new Button("Godown 1");
        Inventory3=new Button("Godown 2");

        layout.setTop(topContainer);
        menuBar=new MenuBar();
        setupMenuBar();
        topContainer.getChildren().addAll(menuBar);
        Tabs.getChildren().addAll(Inventory1,Inventory2,Inventory3);
        topContainer.getChildren().addAll(Tabs);


        //center
        table=new TableView();
        setTable();
        layout.setCenter(table);


        //Right
        VBox rightContainer=new VBox(10);
        rightContainer.setAlignment(Pos.CENTER);
        layout.setRight(rightContainer);
        addButton=new Button("+ADD");
        editButton=new Button("Edit");
        removeButton=new Button("Remove");
        rightContainer.getChildren().addAll(addButton,editButton,removeButton);


        //Left
        VBox leftContainer=new VBox(10);
        leftContainer.setAlignment(Pos.CENTER);
        layout.setLeft(leftContainer);
        searchButton=new Button("Search");
        leftContainer.getChildren().addAll(searchButton);

        //bottom
        //bot will show A status Update after each exection Performed
        status=new Label();
        layout.setBottom(status);

        scene=new Scene(layout,800,600);
    }

    public Scene GetScene(){
        return this.scene;
    }

    private void setTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //id
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idColumn.setStyle("-fx-alignment:CENTER;");


        //name
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setStyle("-fx-alignment:CENTER;");

        //Price
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        priceColumn.setStyle("-fx-alignment:CENTER;");


        //Quantity
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        quantityColumn.setStyle("-fx-alignment:CENTER;");


        //Date
        TableColumn<Product, Date> dateColumn = new TableColumn<>("Purchase Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        dateColumn.setStyle("-fx-alignment:CENTER;");

        //Vendor
        TableColumn<Product, String> vendorColumn = new TableColumn<>("Vendor");
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

    public void updateTable(String tableName){
        //Search the DB using name and update table
    }

    public void setupMenuBar(){
        Menu accountMenu= new Menu();
        accountMenu.setText("Account");

        MenuItem changePassword= new MenuItem("Change Password");
        changePassword.setOnAction(event ->{
            //new window with passChange option
        });
        accountMenu.getItems().add(changePassword);

        MenuItem logout= new MenuItem("Logout");
        logout.setOnAction(event -> {
            main.getWindow().setScene(new Login(main).GetScene());
        });
        accountMenu.getItems().add(logout);

        menuBar.getMenus().addAll(accountMenu);
    }

}
