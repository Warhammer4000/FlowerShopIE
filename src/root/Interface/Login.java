package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import root.App.Main;
import root.DataClass.User;
import root.Database.DBService;



public class Login {
    private User user;
    //Building block
    private Scene scene;
    private VBox layout;
    //buttons
    private Button loginButton;

    //textBox
    private TextField nameField;
    private PasswordField passwordField;

    //lable
    private Label nameLable;
    private Label passwordLable;
    private Label status;

    //constructor
    public Login(Main main){
        loginButton=new Button("Login");
        loginButton.setOnAction(event -> {
            if(LoginRequest(nameField.getText(),passwordField.getText())){
                //set platformScene
                main.getWindow().setScene(new DashBoard(main,user).GetScene());
                main.getWindow().setTitle("Dashboard");

            }
        });
        nameField=new TextField("Admin");
        passwordField=new PasswordField();
        passwordField.setText("admin");
        nameLable=new Label("User Name");
        passwordLable=new Label("Password");
        status=new Label();
        status.setTextFill(Paint.valueOf("Red"));
        status.setVisible(false);


        nameField.setMaxWidth(200);
        passwordField.setMaxWidth(200);

        layout=new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(nameLable,nameField,passwordLable,passwordField,loginButton,status);
        scene=new Scene(layout,300,400);


    }
    public  Scene GetScene(){
        return this.scene;
    }

    private boolean LoginRequest(String name, String password){
        //check database if found return true
        if(new DBService().getUser(name,password)){
            return  true;
        }
        else{
            statusUpdate("User Name/Password Mismatch");
            return false;
        }

    }

    private void statusUpdate(String status){
        this.status.setText(status);
        this.status.setVisible(true);
    }

}
