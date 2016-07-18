package root.Interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import root.DataClass.User;
import root.Database.DBService;


/**
 * Created by tazim on 7/17/2016.
 */
public class Login {
    private User user;
    //Building block
    private Stage window;
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
    public Login(){
        loginButton=new Button("Login");
        loginButton.setOnAction(event -> {
            if(LoginRequest(nameField.getText(),passwordField.getText())){
                System.out.println("User Found");
            }
        });
        nameField=new TextField("Admin");
        passwordField=new PasswordField();
        passwordField.setText("admin");
        nameLable=new Label("User Name");
        passwordLable=new Label("Password");
        status=new Label("User Name/Password Mismatch");
        status.setTextFill(Paint.valueOf("Red"));
        status.setVisible(false);


        nameField.setMaxWidth(200);
        passwordField.setMaxWidth(200);

        layout=new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(nameLable,nameField,passwordLable,passwordField,loginButton,status);
        scene=new Scene(layout,300,400);


        //setup Window
        window=new Stage();
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Login");

    }
    public  void show(){
        window.show();
    }

    public boolean LoginRequest(String name,String password){
        user= new DBService().getUser(name,password);
        //check database if found return true
        if(user!=null){
            return  true;
        }

        else{
            System.out.print("Null");
            return false;
        }

    }

    public void statusUpdate(String status){
        this.status.setText(status);
        this.status.setVisible(true);
    }

}
