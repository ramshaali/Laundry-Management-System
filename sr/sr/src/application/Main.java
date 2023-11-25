package application;
	
import java.util.Random;

import application.classes.Customer;
import application.classes.CustomerCatalog;
import application.classes.LaundryShop;
import application.classes.Order;
import application.classes.OrderCatalog;
import application.classes.item2;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	

	public   void start(Stage primaryStage) {
		try {
			
			

			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("CV");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		
		
			
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static LaundryShop rs;
	public  static String checkloginn(int id, String pass) {
	 System.out.println("yes");
		 return rs.checkLogin(id, pass);
	}
	
	public  static int getCurrId() {
    	 return rs.getCurrId();
     }
     
	public static void insertAdmin(int id, String pass, String name) {
		 System.out.println("yes");
			  rs.insertDataAdmin(id, pass, name);
		}
	
	public static void  logout() {
		rs.logout();
	}
	
	public static void insertDataCust(int idd, String passs, String namee, String add, String  num) {
		rs.insertDataCust(idd, passs, namee, add, num);
	}
	

	  
	public static void  getcustids( ObservableList<String> ids) {
		rs.getcustids(ids);
	}
	public static String  getname( ) {
		return rs.getName();
	}
	public static void  getorderids( ObservableList<String> ids) {
		rs.getorderids(ids);
	}
	public static void  createOrder(int oid, int cid) {
		rs.placeNewOrder(oid, cid);
	}
	
	public static void  enterItem(int id,String type, double price ) {
		rs.enterItem( id, type, price );
	}
	public static double getamountOrder() {
		return rs.getamountOrder();
	}
	public static void endOrder() {
	  rs.endOrder();
	}
	
	public static double makePayment(double cash, String type) {
		return rs.makePayment(cash, type);
	}
	public static void generatePin() {
		rs.generatePin();
	}
	public static boolean confirmPin(int pin) {
		return  rs.confirmPin(pin);
	}
	
	public static void changeStatus(int orderID, String st) {
		rs.changeStatus(orderID, st);
	}
	public static String viewFeedback(int orderId) {
		return rs.viewFeedback(orderId);
	}
	
	public static void  generateReport(String format, ObservableList<item2> items){
		
		  rs.generateReport(format, items);
		
	}
	
	public static void  selectOrder(int oid){
		
		  rs.selectOrder(oid);
		
	}
	
	public static void enterComplain(String comm) {
	rs.enterComplain(comm);
	};
	public static Customer  selectcust(int cid){
		
		 return  rs.enterId(cid);
		
	}
	public  static int getCurrent_order() {
		return rs.getCurrent_order();
	}
	
	public static void  selectOption(int cid, int opt, String name, String add, String num, ObservableList<item2> items){
		
		  rs.selectOption(opt, cid, name, add, num,items);
		
	}
	
	public static void  viewOrders( ObservableList<String> ids) {
		rs.viewOrders(ids);
		 
		}
	public static void  viewOrdersall( ObservableList<String> ids) {
		rs.viewOrdersall(ids);
		 
		}
	
	public static double getamOrder(int oid) {
  	  return rs.getamOrder(oid);
		
	}
   
	public static String getstatusOrder(int oid) {
 	return rs.getstatusOrder(oid);
		
		
	}
	
	public static void saveReport() {
	rs.saveReport();
	}
	public static ObservableList<item2> viewOrder(){
		return rs.viewOrder();
	}
    
	
  
	public static String getType() {
		return rs.page();
	}
	public static void main(String[] args) {
		 rs= new LaundryShop("isb");
		rs.loadData();

		
		
    	 
		launch(args);
	}
}
