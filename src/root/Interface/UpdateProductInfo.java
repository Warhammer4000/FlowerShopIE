package root.Interface;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import root.CustomControl.AutoCompleteComboBox;
import root.CustomControl.NumericTextField;
import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.Database.DBService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


class UpdateProductInfo {
    private List<Product> availableProducts;
    private AutoCompleteComboBox<Product> productPicker=new AutoCompleteComboBox<>();
    private ProductInfo SelectedProduct;

    private Label productLable;

    private Label quantityLable;
    private NumericTextField quantity;

    private Label purchaseDateLable;
    private DatePicker purchaseDate;

    private Label priceLable;
    private TextField price;


    private  Label InventoryNoLable;
    private NumericTextField InventoryNo;


    private  Button AddButton;
    private Button DeleteButton;
    private Button EditButton;
    private  Label status;

    private VBox layout;

    void setProduct(int id){
        ObservableList<Product> observableList=productPicker.getItems();
        Product product=null;
        for(Product p : observableList){
            if(p.getId()==id){
                product=p;
                break;
            }
        }

        productPicker.setValue(product);

    }
    void setQuantity(int quantity) {
        this.quantity.setText(String.valueOf(quantity));
    }

    void setPurchaseDate(Date purchaseDate) {
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(purchaseDate) );
        this.purchaseDate.setValue(localDate);
    }

    void setSelectedProduct(ProductInfo selectedProduct) {
        this.SelectedProduct = selectedProduct;
    }

    void setPrice(Double price) {
        this.price.setText(String.valueOf(price));
    }

    void setInventoryNo(int inventoryNo) {
        InventoryNo.setText(String.valueOf(inventoryNo));
    }

    UpdateProductInfo(){

        productLable=new Label("Product");
        availableProducts=new DBService().getProducts();
        for(Product p : availableProducts){
            productPicker.getItems().addAll(p);
        }
        productPicker.setEditable(true);

        quantityLable=new Label("Quantity");
        quantity=new NumericTextField();
        quantity.setPromptText("Ex.500");
        quantity.setMaxSize(200, 20);



        purchaseDateLable=new Label("Purchase Date");
        purchaseDate=new DatePicker();
        purchaseDate.setValue(LocalDate.now());


        priceLable= new Label("Price");
        price=new TextField();
        price.setPromptText("Ex.500");
        price.setMaxSize(200, 20);




        InventoryNoLable=new Label("Inventory No");
        InventoryNo=new NumericTextField();
        InventoryNo.setMaxSize(200, 20);


        AddButton=new Button("+Add");
        AddButton.setOnAction(event -> addProduct());

        DeleteButton=new Button("Delete");
        DeleteButton.setOnAction(event -> DeleteProductInfo());

        EditButton=new Button("Edit");
        EditButton.setOnAction(event -> EditProductInfo());

        status = new Label("");

        layout=new VBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(productLable,productPicker);

        layout.getChildren().addAll(quantityLable,quantity);
        layout.getChildren().addAll(purchaseDateLable,purchaseDate);
        layout.getChildren().addAll(priceLable,price);
        layout.getChildren().addAll(InventoryNoLable,InventoryNo);
        layout.getChildren().addAll(AddButton,EditButton,DeleteButton);
        layout.getChildren().addAll(status);
    }


    VBox getLayout() {
        return layout;
    }

    private void addProduct(){
        //validate Values
        try {
            ProductInfo p;
            int ID=productPicker.getValue().getId();
            String Name=productPicker.getValue().getName();
            int Quantity=Integer.parseInt(quantity.getText());
            //date
            LocalDate localDate = purchaseDate.getValue();
            Date Date = java.sql.Date.valueOf(localDate);

            double Price=Double.parseDouble(this.price.getText());

            int InventoryNO=Integer.parseInt(this.InventoryNo.getText());



            //create a product object
            p=new ProductInfo(ID,Quantity,Date,Price,InventoryNO);

            DBService dbService=new DBService();
            dbService.insertNewProductInfo(p);
            InventoryView.updateTableData();

        }catch (Exception e){
            e.printStackTrace();
            status.setText("Invalid Price input");
            status.setTextFill(Color.valueOf("Red"));
        }catch (Error e){
            status.setText("Database Error");
            status.setTextFill(Color.valueOf("Red"));
        }



    }

    private void EditProductInfo(){
        ProductInfo p;
        int ID=productPicker.getValue().getId();
        String Name=productPicker.getValue().getName();
        int Quantity=Integer.parseInt(quantity.getText());
        //date
        LocalDate localDate = purchaseDate.getValue();
        Date Date = java.sql.Date.valueOf(localDate);

        double Price=Double.parseDouble(this.price.getText());

        int InventoryNO=Integer.parseInt(this.InventoryNo.getText());



        //create a product object
        p=new ProductInfo(ID,Quantity,Date,Price,InventoryNO);

        if (p.getId()>-1) {

            //Delete From DB
            try{
                DBService dbService=new DBService();
                if(dbService.EditProductInfo(p,SelectedProduct)){
                    status.setText("Row Deleted");
                    status.setTextFill(Color.web("Green"));
                }else {
                    status.setText("Child record Found");
                    status.setTextFill(Color.web("Red"));
                }


            }catch (Exception e){
                status.setText("Database Exception call your DbManager");
                status.setTextFill(Color.web("Red"));
            }



        }
        else {
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }



        //update when done
        InventoryView.updateTableData();
    }


    private void DeleteProductInfo(){
        ProductInfo p;
        int ID=productPicker.getValue().getId();
        String Name=productPicker.getValue().getName();
        int Quantity=Integer.parseInt(quantity.getText());
        //date
        LocalDate localDate = purchaseDate.getValue();
        Date Date = java.sql.Date.valueOf(localDate);

        double Price=Double.parseDouble(this.price.getText());

        int InventoryNO=Integer.parseInt(this.InventoryNo.getText());



        //create a product object
        p=new ProductInfo(ID,Quantity,Date,Price,InventoryNO);

        if (p.getId()>-1) {

            //Delete From DB
            try{
                DBService dbService=new DBService();
                if(dbService.deleteProductInfo(p)){
                    status.setText("Row Deleted");
                    status.setTextFill(Color.web("Green"));
                }else {
                    status.setText("Child record Found");
                    status.setTextFill(Color.web("Red"));
                }


            }catch (Exception e){
                status.setText("Database Exception call your DbManager");
                status.setTextFill(Color.web("Red"));
            }



        }
        else {
            status.setText("No Rows Selected");
            status.setTextFill(Color.web("Blue"));
        }



        //update when done
        InventoryView.updateTableData();
    }


}
