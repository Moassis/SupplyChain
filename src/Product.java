import java.sql.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    public SimpleIntegerProperty pid;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;
    public SimpleIntegerProperty quantity;


    public Product(int pid, String name, double price, int quantity){
        this.pid = new SimpleIntegerProperty(pid);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getPid(){return pid.get();}
    public String getName(){return name.get();}
    public double getPrice(){return price.get();}
    public int getQuantity(){return quantity.get();}

    public static ObservableList<Product> getAllProducts(){
        String selectProducts = "SELECT * FROM product";
        return getProductList(selectProducts);
    }

    public static ObservableList<Product> getProductsByName(String productName){
        String selectProducts = String.format("SELECT * FROM product WHERE name like '%%%s%%'", productName.toLowerCase());
        return getProductList(selectProducts);
    }
    
    private static ObservableList<Product> getProductList(String querry){
        DatabaseConnection con = new DatabaseConnection();
        ObservableList<Product> data= FXCollections.observableArrayList();

        try {            
            ResultSet rs=con.getQueryTable(querry);
            while(rs.next()){
                data.add(new Product(
                        rs.getInt("pid"), 
                        rs.getString("name"), 
                        rs.getDouble("price"), 
                        rs.getInt("quantity")
                    ));
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return data;
    }

}
