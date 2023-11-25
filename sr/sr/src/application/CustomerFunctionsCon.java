package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.classes.Order;
import application.classes.OrderCatalog;
import application.classes.item2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerFunctionsCon {
	
	 @FXML
	    private ComboBox orderidbox ;
	 
	 @FXML
	    private TextField message;
	 
	 @FXML
	    private TextField amount;
	 @FXML
	    private TextField status;
	  @FXML
	   private TableView<item2> ordertable;
		  @FXML
				private TableColumn iid;
		  @FXML
				private TableColumn iprice;
		  @FXML
				private TableColumn istatus;
		  @FXML
			private TableColumn cid;
	 
	public  void initCombo() {
		  System.out.println("init");
		  
		   ObservableList<String> ids=  FXCollections.observableArrayList();
		  
		 application.Main.viewOrders(ids);
		 
		 
		 orderidbox.setItems(ids);
	  }
	
	 
		public  void initComboall() {
			  System.out.println("init");
			  
			   ObservableList<String> ids=  FXCollections.observableArrayList();
			  
			 application.Main.viewOrdersall(ids);
			 
			 
			 orderidbox.setItems(ids);
		  }
		
	
	
	  public  void inittable() {
		   ObservableList<item2> items=  FXCollections.observableArrayList();
		  // iid.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
		  // itype.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		  //iprice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		  
		  iid = new TableColumn("ID");
	      iid.setCellValueFactory(new PropertyValueFactory("id"));
	      iprice = new TableColumn("Status");
	      iprice.setCellValueFactory(new PropertyValueFactory("price"));
	      istatus= new TableColumn("Amount");
	      istatus.setCellValueFactory(new PropertyValueFactory("type"));
	    
		  int id= application.Main.getCurrId();
		   
		  try{  
	    		Class.forName("com.mysql.jdbc.Driver");  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
	    		System.out.println("Connected Successfully ");
	    		Statement st=con.createStatement();
	    	
	    		String query = "SELECT * FROM cust_Order where cid = ? ";
	    	      PreparedStatement preparedStmt = con.prepareStatement(query);
	    	      preparedStmt.setInt(1,id);
	    	
	    	     
	    		ResultSet rs= preparedStmt.executeQuery();
	    	
	    		OrderCatalog oc= OrderCatalog.getInstance(101);
		    	
	    		while(rs.next()) { 
	    			 System.out.println( rs.getInt("id"));
	    			 Order o=oc.findOrder( rs.getInt("id"));
	    			items.add(new item2(String.valueOf(rs.getInt("id")),rs.getString("status"), String.valueOf(o.getAmount()))
	    					);
	    			
	    	      
	    		}
	    		con.close();  
	    		
	    		ordertable.setItems(items);
	    		ordertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    		ordertable.getColumns().addAll(iid, iprice,istatus);
	    	
		  } catch(Exception e) {
				e.printStackTrace();
			}
	  }
	  
	

	 @FXML
	 public  void login(ActionEvent event) {
			
		
		 
		 Stage primaryStage;
	    	try {
				
				
	    	
	    			
	    			
	    			
	    			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    			 primaryStage= (Stage)((Node)event.getSource() ).getScene().getWindow();
	    		       Scene scene = new Scene(root);
	    				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    				//primaryStage.setTitle("CV");
	    				primaryStage.setScene(scene);
	    				primaryStage.show();
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	application.Main.logout();
	    	
		  
		  
		  
		  
	 }
	 
	 @FXML
	 public  void back(ActionEvent event) {
			
		
		 String n= application.Main.getname();
		 Stage primaryStage;
	    	try {
				
				
	    	
	    			
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenu.fxml"));
		        Parent root = loader.load();
		       Controller2 adminController = loader.getController();
		       adminController.setName(n);
		       primaryStage= (Stage)((Node)event.getSource() ).getScene().getWindow();
		       Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//primaryStage.setTitle("CV");
				primaryStage.setScene(scene);
				primaryStage.show();
		       
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	   
	    	
		  
		  
		  
		  
	 }
	 
	 
	 @FXML
	 public  void submitFB(ActionEvent event) {
		 String id=  orderidbox.getValue().toString();
		 String comm;
		 if(message.getText().isEmpty()) {
			 comm="";
		 }else
		  comm= message.getText().toString();
		 application.Main.selectOrder(Integer.parseInt(id));
		 
		 application.Main.enterComplain(comm);
		 String n= application.Main.getname();
		 Stage primaryStage;
	    	try {
				
				
	    	
	    			
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenu.fxml"));
		        Parent root = loader.load();
		       Controller2 adminController = loader.getController();
		       adminController.setName(n);
		       primaryStage= (Stage)((Node)event.getSource() ).getScene().getWindow();
		       Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//primaryStage.setTitle("CV");
				primaryStage.setScene(scene);
				primaryStage.show();
		       
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	   
	    	
		  
		  
		  
		  
	 }
	 
	 public  void setData(double am, String st) {
		 amount.setText(String.valueOf(am));
		 status.setText(st);
		 
	 }
	 
	 @FXML
	 public  void statussViews(ActionEvent event) {
		 String id=  orderidbox.getValue().toString();
	    double am= application.Main.getamOrder(Integer.parseInt(id));
	    String st= application.Main.getstatusOrder(Integer.parseInt(id));
	    Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("checkStatus.fxml"));
	        Parent root = loader.load();
	        CustomerFunctionsCon  fbController = loader.getController();
	       fbController.initComboall();
	       fbController.setData(am, st);
	       
	   
	     
	       primaryStage= (Stage)((Node)event.getSource() ).getScene().getWindow();
	       Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setTitle("CV");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	    	
		  
		  
		  
		  
	 }
	
	
	

}
