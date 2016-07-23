package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import root.App.Main;
import root.DataClass.ProductInfo;
import root.DataClass.User;
import root.Database.DBService;

import java.util.List;


class DashBoard {
    private Main main;
    private User user;

    private Scene scene;
    private BorderPane layout;
    private MenuBar menuBar;

    private Button Inventory1Button;
    private Button Inventory2Buton;
    private Button Inventory3Button;



    private Button searchButton;

    private Label status;

    DashBoard(Main main, User user){
        this.main=main;
        this.user=user;
        layout=new BorderPane();
        setupTopContainer();
        setupCenterContainer();
        setupRightContainer();
        setupLeftContainer();
        setupCenterContainer();

        scene=new Scene(layout,800,600);

    }
    private void setupMenuBar(){

        //account Menu
        Menu accountMenu= new Menu();
        accountMenu.setText("Account");

        MenuItem changePassword= new MenuItem("Change Password");
        changePassword.setOnAction(event ->{
            //new window with passChange option
            passwordChange(user);
        });
        accountMenu.getItems().add(changePassword);

        MenuItem logout= new MenuItem("Logout");
        logout.setOnAction(event -> main.getWindow().setScene(new Login(main).GetScene()));
        accountMenu.getItems().add(logout);
        menuBar.getMenus().addAll(accountMenu);

        //productMenu
        Menu productMenu= new Menu();
        productMenu.setText("Product");

        MenuItem addNewProduct= new MenuItem("Add");
        addNewProduct.setOnAction(event -> new AddProduct("Product"));
        productMenu.getItems().add(addNewProduct);

        MenuItem viewProduct= new MenuItem("View");
        viewProduct.setOnAction(event -> {
            //shows a list of unique products
            new productListView();
        });
        productMenu.getItems().add(viewProduct);

        menuBar.getMenus().addAll(productMenu);


    }

    private void passwordChange(User user){
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

    private void setupTopContainer(){
        //top
        VBox topContainer=new VBox();//this will hold top stuffs
        searchButton=new Button("Search");

        HBox Tabs=new HBox(5);


        layout.setTop(topContainer);
        menuBar=new MenuBar();
        setupMenuBar();
        topContainer.getChildren().addAll(menuBar);
        topContainer.getChildren().addAll(searchButton);
        Tabs.setAlignment(Pos.CENTER);

    }


    private void setupCenterContainer(){
        //center
        //Show Log msg and bla blas
        TabPane Tabpane=new TabPane();
        Tab log = new Tab();
        log.setClosable(false);
        log.setText("Log");
        Tabpane.getTabs().add(log);

        layout.setCenter(Tabpane);
    }

    private void setupRightContainer(){
        //Right
        VBox rightContainer=new VBox(10);

        Inventory1Button =new Button("Inventory1");
        Inventory1Button.setOnAction(event -> {
            List<ProductInfo> data=new DBService().getTableData("Inventory1");
            new InventoryView("Inventory1",data);

        });
        Inventory2Buton =new Button("Inventory2");
        Inventory2Buton.setOnAction(event -> {
            List<ProductInfo> data=new DBService().getTableData("Inventory2");
            new InventoryView("Inventory2",data);


        });
        Inventory3Button =new Button("Inventory3");
        Inventory3Button.setOnAction(event -> {
            List<ProductInfo> data=new DBService().getTableData("Inventory3");
            new InventoryView("Inventory3",data);

        });
        rightContainer.setAlignment(Pos.CENTER);
        layout.setRight(rightContainer);
        rightContainer.getChildren().addAll(Inventory1Button,Inventory2Buton,Inventory3Button);
    }

    private void setupLeftContainer(){
        //Left
        VBox leftContainer=new VBox(10);
        leftContainer.setAlignment(Pos.CENTER);
        layout.setLeft(leftContainer);
    }

    public void setupBotContainer(){
        //bottom
        //bot will show A status Update after each exection Performed
        status=new Label();
        layout.setBottom(status);
    }


    Scene GetScene(){
        return this.scene;
    }
}
