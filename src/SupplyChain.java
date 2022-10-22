import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class SupplyChain extends Application {

    public static final int width=700, height=300, upperLine=50;
    ProductDetails productDetails = new ProductDetails();
    Button loginButton;
    Pane bodyPane;
    boolean customerLoggedIn=false;
    String customerEmail = "";
    Label welcomeUser;

    private Pane headerBar(){

        Pane topPane=new Pane();
        topPane.setPrefSize(width, upperLine-10);

        TextField searchText = new TextField();
        searchText.setPromptText("Please Search Here");
        searchText.setTranslateX(100);

        Button searchButton=new Button("Search");
        searchButton.setTranslateX(350);

        loginButton=new Button("Login");
        loginButton.setTranslateX(420);

        loginButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });

        welcomeUser=new Label ("Hi! User");
        welcomeUser.setTranslateX(480);

        topPane.getChildren().addAll(searchText, searchButton, loginButton, welcomeUser);
        topPane.setTranslateY(10);

        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                String search = searchText.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(search));
                productDetails.getProductsByName(search);
            }
        });

        return topPane;
    }

    private Pane footerBar(){
        Pane bottomPane=new Pane();
        bottomPane.setPrefSize(width, upperLine-10);
        bottomPane.setTranslateY((upperLine+height+50));

        Button buyNowButton=new Button("Buy Now");
        buyNowButton.setTranslateX(350);
        buyNowButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                String userEmail;
                int productId;
                userEmail = customerEmail;
                productId = ProductDetails.getProductId();
                if(!userEmail.isBlank() && productId != -1){
                    boolean status = order.orderProduct(productId, userEmail);
                    if(status){
                        System.out.println("Order Placed");
                    }
                    else{
                        System.out.println("Order Failed");
                    }
                }
                else{
                    System.out.println("Invalid order Values");
                }
            }
        });

        bottomPane.getChildren().addAll(buyNowButton);

        return bottomPane;
    }

    private GridPane loginPage(){
        Label emailLabel=new Label("email");
        TextField emailText = new TextField();
        emailText.setPromptText("Please Enter Your Email");

        Label passwordLabel = new Label("Password");
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("Please Enter Your Password.");

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login Message");
        dialog.setContentText("Login Failed !! Please enter correct email & password");
        
        ButtonType buttonType = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().add(buttonType);



        Button localLoginButton = new Button("Login");
        localLoginButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                String email = emailText.getText();
                String password = passwordText.getText();
                if(email.isBlank()|| password.isBlank()){
                    
                }
                else{
                    if(login.customerLogin(email, password)){
                        customerLoggedIn=true;
                        customerEmail=email;
                        welcomeUser.setText("Welcome "+ customerEmail);
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().add(productDetails.getAllProducts());
                    }
                    else{
                        welcomeUser.setText("Login Failed");
                        emailText.clear();
                        passwordText.clear();
                        dialog.showAndWait();
                    }
                    
                }
            }
        });
        

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                emailText.setText("");
                passwordText.setText("");
            }
        });

        GridPane gridPane =new GridPane();
        gridPane.setMinSize(bodyPane.getWidth(), bodyPane.getHeight());
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailText, 1, 0);
        gridPane.add(passwordLabel, 0,  1);
        gridPane.add(passwordText, 1, 1);
        gridPane.add(localLoginButton, 0, 2);
        gridPane.add(clearButton, 1, 2);


        return gridPane;
    }


    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width, (height + 2*upperLine + 100));

        bodyPane =new Pane();
        bodyPane.setPrefSize(width, height);
        bodyPane.setTranslateY(upperLine);
        bodyPane.getChildren().add(productDetails.getAllProducts());

        root.getChildren().addAll(headerBar(), bodyPane, footerBar());

        return root;
    }
    
    public void start(Stage stage){
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain");
        stage.setScene(scene);
        stage.show();
    }
   
    public static void main(String[] args) throws IOException {
        launch();
    }
}