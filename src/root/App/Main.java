package root.App;

import javafx.application.Application;
import javafx.stage.Stage;
import root.Interface.Login;

public class Main extends Application {

    private Stage window;
    public void start(Stage primaryStage) throws Exception{
        window =primaryStage;
        window.setScene(new Login(this).GetScene());
        window.setTitle("Login");
        window.show();


    }

    public Stage getWindow(){
        return this.window;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
