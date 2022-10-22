import java.sql.*;

public class DatabaseConnection{
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/supplychain";
    private static final String USER = "root";
    private static final String PASS = "";

    public Statement getStatement(){
        Statement st=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            st = con.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return st;
    }
    
    public ResultSet getQueryTable(String querry){
        ResultSet rs = null;
        try{
            Statement st=getStatement();
            return st.executeQuery(querry);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return rs;
    }

    public boolean executeQuery(String querry){
        try{
            Statement st=getStatement();
            st.executeUpdate(querry);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Database not connected......");
            return false;
        }
    }

    /* 
    public static void main(String[] args){

        DatabaseConnection con = new DatabaseConnection();

        try {
            String querry = "SELECT * FROM `product`";
            ResultSet rs=con.getQueryTable(querry);
            while(rs.next()){
                    System.out.println(
                        rs.getInt("pid")+" "+
                        rs.getString("name")+" "+
                        rs.getDouble("price")+" "+
                        rs.getInt("quantity")
                    );
                }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Database not connected......");
        }

    }*/

}