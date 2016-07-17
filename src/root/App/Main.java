package root.App;

import javafx.application.Application;
import javafx.stage.Stage;
import root.Interface.Login;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Login loginWindow=new Login();
        loginWindow.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
