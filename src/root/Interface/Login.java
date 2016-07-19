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
import root.App.Main;
import root.DataClass.User;
import root.Database.DBService;


/**
 * Created by tazim on 7/17/2016.
 */
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
                main.getWindow().setScene(new Platform(main).GetScene());

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

    public boolean LoginRequest(String name,String password){
        user= new DBService().getUser(name,password);
        //check database if found return true
        if(user!=null){
            return  true;
        }

        else{
            System.out.print("Null");
            statusUpdate("User Name/Password Mismatch");
            return false;
        }

    }

    public void statusUpdate(String status){
        this.status.setText(status);
        this.status.setVisible(true);
    }

}
