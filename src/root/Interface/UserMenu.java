package root.Interface;

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
import root.App.Main;
import root.DataClass.Product;
import root.DataClass.User;
import root.Database.DBService;

import java.util.Date;
import java.util.List;

/**
 * Created by Ehtesham on 7/19/2016.
 */
public class UserMenu {
    private Main main;
    private User user;

    private Scene scene;
    private BorderPane layout;
    private MenuBar menuBar;
    private TableView table;

    private Button Inventory1Button;
    private Button Inventory2Buton;
    private Button Inventory3Button;

    private Button addButton;
    private Button removeButton;
    private Button editButton;

    private Button searchButton;

    private Label status;

    public UserMenu(Main main, User user){
        this.main=main;
        this.user=user;
        layout=new BorderPane();
        //top
        VBox topContainer=new VBox();//this will hold top stuffs
        HBox Tabs=new HBox(5);
        Tabs.setAlignment(Pos.CENTER);
        Inventory1Button =new Button("Inventory1");
        Inventory1Button.setOnAction(event -> {
            List<Product> data=new DBService().getTableData("Inventory1");

            updateTableData(data);
        });
        Inventory2Buton =new Button("Inventory2");
        Inventory2Buton.setOnAction(event -> {
            List<Product> data=new DBService().getTableData("Inventory2");
            updateTableData(data);
        });
        Inventory3Button =new Button("Inventory3");
        Inventory3Button.setOnAction(event -> {
            List<Product> data=new DBService().getTableData("Inventory3");
            updateTableData(data);
        });

        layout.setTop(topContainer);
        menuBar=new MenuBar();
        setupMenuBar();
        topContainer.getChildren().addAll(menuBar);
        Tabs.getChildren().addAll(Inventory1Button, Inventory2Buton, Inventory3Button);
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
        addButton.setOnAction(event -> {
            new AddProduct();
        });
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
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



    public void setupMenuBar(){
        Menu accountMenu= new Menu();
        accountMenu.setText("Account");

        MenuItem changePassword= new MenuItem("Change Password");
        changePassword.setOnAction(event ->{
            //new window with passChange option
            passwordChange(user);
        });
        accountMenu.getItems().add(changePassword);

        MenuItem logout= new MenuItem("Logout");
        logout.setOnAction(event -> {
            main.getWindow().setScene(new Login(main).GetScene());
        });
        accountMenu.getItems().add(logout);

        menuBar.getMenus().addAll(accountMenu);
    }

    public void passwordChange(User user){
        Stage passowordChangeWindow= new Stage();
        passowordChangeWindow.setTitle("Change Password");
        Label status=new Label("");


        VBox vBox=new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        PasswordField oldPass= new PasswordField();
        oldPass.setMaxSize(200,20);

        PasswordField newPass= new PasswordField();
        newPass.setMaxSize(200,20);

        PasswordField newPass2= new PasswordField();
        newPass2.setMaxSize(200,20);

        Label label1= new Label("Old Password");
        Label label2=new Label("New Password");
        Label label3=new Label("Confirm password");

        Button submitButton= new Button("Submit");
        submitButton.setOnAction(event -> {
            if(user.getUserPassword().equals(oldPass.getText()) &&!newPass.getText().isEmpty()){
                if( newPass.getText().equals(newPass2.getText())){
                    user.setUserPassword(newPass.getText());
                    DBService Ds=new DBService();
                    if(Ds.updatePassword(user.getUserName(),user.getUserPassword())){
                        status.setTextFill(Color.web("Green"));
                        status.setText("Password Change Successful");
                    }else {
                        status.setTextFill(Color.web("RED"));
                        status.setText("Could Not Update Database");
                    }
                }
                else{
                    newPass.setText("");
                    newPass2.setText("");
                    status.setTextFill(Color.web("Red"));
                    status.setText("Retype Password");
                }


            }
            else{
                status.setTextFill(Color.web("Red"));
                status.setText("Old Password Doesn't Match");
            }


        });
        vBox.getChildren().addAll(label1,oldPass,label2,newPass,label3,newPass2,submitButton,status);

        Scene scene= new Scene(vBox,300,400);
        passowordChangeWindow.setScene(scene);
        passowordChangeWindow.initModality(Modality.APPLICATION_MODAL);
        passowordChangeWindow.show();

    }

    public void updateTableData(List<Product> data){
        table.getColumns().removeAll();
        table.refresh();
        try {
            for (Product p : data) {

                table.getItems().addAll(p);
                System.out.println(p.getId());
            }

            table.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
