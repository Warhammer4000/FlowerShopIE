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
import root.DataClass.User;
import root.Database.DBService;


class DashBoard {
    private Main main;
    private User user;

    private Scene scene;
    private BorderPane layout;
    private MenuBar menuBar;

    private TabPane Tabpane;


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
        //addNewProduct.setOnAction(event -> new AddProduct("Product"));
        productMenu.getItems().add(addNewProduct);

        MenuItem viewProduct= new MenuItem("View");
        viewProduct.setOnAction(event -> {
            //shows a list of unique products
            new productView();
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


        HBox Tabs=new HBox(5);


        layout.setTop(topContainer);
        menuBar=new MenuBar();
        setupMenuBar();
        topContainer.getChildren().addAll(menuBar);
        Tabs.setAlignment(Pos.CENTER);

    }


    private void setupCenterContainer(){
        //center
        //Show Log msg and bla blas
        Tabpane=new TabPane();
        SetupInventoryTab();
        SetupProductsTab();

        layout.setCenter(Tabpane);
    }

    private void setupRightContainer(){
        //Right
        VBox rightContainer=new VBox(10);


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

    private void SetupInventoryTab(){
        Tab InventoryTab = new Tab();
        InventoryTab.setClosable(false);
        InventoryTab.setText("Inventory");
        InventoryView inventoryView=new InventoryView();
        InventoryTab.setContent(inventoryView.getLayout());



        Tabpane.getTabs().add(InventoryTab);
    }

    private void SetupProductsTab(){
        Tab ProductTab = new Tab();
        ProductTab.setClosable(false);
        ProductTab.setText("Product");
        productView productView=new productView();
        ProductTab.setContent(productView.getLayout());

        Tabpane.getTabs().add(ProductTab);
    }

}
