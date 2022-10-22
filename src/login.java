import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class login {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    
    static String getEncriptedPassword(String passwordText) throws NoSuchAlgorithmException{
        try {
            BigInteger number = new BigInteger(1, getSHA(passwordText));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return "";
    }

    public static boolean customerLogin (String email, String password){
        try {
            DatabaseConnection con=new DatabaseConnection();
            String encyptedPassword = getEncriptedPassword(password);
            String querry = String.format("SELECT * FROM customer WHERE email like '%s' AND password = '%s'", email, encyptedPassword);
            ResultSet rs = con.getQueryTable(querry);
            if(rs.next()){
                return true;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return false;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String pass = "Rommel@123";
        System.out.println(pass);
        System.out.println(login.getEncriptedPassword(pass));

        System.out.println(login.customerLogin("moassis.rommel@gmail.com", "Rommel@123"));

    }
}
