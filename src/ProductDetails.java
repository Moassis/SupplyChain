import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class ProductDetails {
    
    static TableView<Product> productTable;

    public Pane getAllProducts(){
        TableView<Product> table = new TableView<>();
        
        TableColumn pidCol = new TableColumn("Pid");
        pidCol.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantityCol = new TableColumn("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ObservableList<Product> productData = Product.getAllProducts();
        
        table.setItems(productData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().addAll(pidCol, nameCol, priceCol, quantityCol);
        table.setPrefSize(SupplyChain.width - 10, SupplyChain.height - 10);

        productTable = table;
        Pane tpane=new Pane();
        tpane.setPrefSize(SupplyChain.width, SupplyChain.height);
        tpane.getChildren().add(table);
        //tpane.setTranslateY(SupplyChain.upperLine);
        
        return tpane;
    }

    public Pane getProductsByName(String searchText){
        TableView<Product> table = new TableView<>();

        TableColumn pidCol = new TableColumn("Pid");
        pidCol.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantityCol = new TableColumn("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ObservableList<Product> productData = Product.getProductsByName(searchText);
        
        table.setItems(productData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().addAll(pidCol, nameCol, priceCol, quantityCol);
        table.setPrefSize(SupplyChain.width - 10, SupplyChain.height - 10);

        productTable = table;
        Pane tpane=new Pane();
        tpane.setPrefSize(SupplyChain.width, SupplyChain.height);
        tpane.getChildren().add(table);
        //tpane.setTranslateY(SupplyChain.upperLine);
        
        return tpane;
    }

    public void getSelectedRowValues(){
        TableView<Product> table = productTable;
        if(productTable==null){
            System.out.println("Table object not found");
            return;
        }
        if (productTable.getSelectionModel(). getSelectedIndex() != -1){
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            System.out.println("Rommel");
            System.out.println(selectedProduct.getPid() + " " + selectedProduct.getName() + " " + selectedProduct.getPrice());
        }
        else{
            System.out.println("Nothing Selected");
        }
    }

    public static int getProductId(){
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
           return selectedProduct.getPid();
        }
        return -1;
    }

}
