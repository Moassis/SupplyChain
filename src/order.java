public class order {
    public static boolean orderProduct(int productId, String customerEmail){
        String querry = String.format("INSERT INTO `supplychain`.`orders` (`quantity`, `customer_id`, `product_id`) VALUES ('2', (SELECT `cid` FROM `customer` WHERE `email` = '%s'), '%s')", customerEmail, productId);
        DatabaseConnection con = new DatabaseConnection();
        System.out.println(con.executeQuery(querry));
        return true;
    }
    public static void main(String[] args) {
        order.orderProduct(2, "moassis.rommel@gmail.com");
    }
}
