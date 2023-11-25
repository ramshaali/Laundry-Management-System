package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.classes.Customer;
import application.classes.item2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminFuctionsCon {
	
	  @FXML
	    private ComboBox orderidbox ;
	  @FXML
	    private ComboBox custidbox ;
	  
	  @FXML
	    private TextField fb ;
	  
	  @FXML
	    private TextField uname ;
	  
	  @FXML
	    private TextField uadd ;
	  @FXML
	    private TextField unum ;
	  
	  @FXML
	    private RadioButton  completed ;
	  @FXML
	    private RadioButton inprocess ;
	  @FXML
	    private RadioButton pending ;
	  @FXML
	    private RadioButton  name ;
	  @FXML
	    private RadioButton add ;
	  @FXML
	    private RadioButton num ;
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
	  @FXML
			private TextField amount;
	  @FXML
		private TextField id;
	  @FXML
		private Label save;
	  

	  @FXML
	    private ComboBox format ;

	  
	  public  void initCombotype() {
		  System.out.println("init");
		  
		   ObservableList<String> types=  FXCollections.observableArrayList();
		  
		 types.add("orders");
		 types.add("customers");
		 types.add("payments");
		 
	
		 format.setItems(types);
	  }
	   
	  
	
	  
	  public  void initCombocust() {
		  System.out.println("init");
		  
		   ObservableList<String> ids=  FXCollections.observableArrayList();
		  
		 application.Main.getcustids(ids);
		 
		 custidbox.setItems(ids);
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
	      istatus= new TableColumn("Customer-ID");
	      istatus.setCellValueFactory(new PropertyValueFactory("type"));
	    
		  
		   
		  try{  
	    		Class.forName("com.mysql.jdbc.Driver");  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
	    		System.out.println("Connected Successfully ");
	    		Statement st=con.createStatement();
	    	
	    		String query = "SELECT * FROM cust_Order where status != ? ";
	    	      PreparedStatement preparedStmt = con.prepareStatement(query);
	    	      preparedStmt.setString(1,"completed");
	    	
	    	     
	    		ResultSet rs= preparedStmt.executeQuery();
	    	
	    		
	    		while(rs.next()) { 
	    			 System.out.println( rs.getInt("id"));
	    			items.add(new item2(String.valueOf(rs.getInt("id")),rs.getString("status"), String.valueOf(rs.getInt("cid")))
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
	  
	  
		
	  public  void inittable2(  ObservableList<item2> items) {
		
		  // iid.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
		  // itype.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		  //iprice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		  
		  iid = new TableColumn("ID");
	      iid.setCellValueFactory(new PropertyValueFactory("id"));
	      iprice = new TableColumn("Name");
	      iprice.setCellValueFactory(new PropertyValueFactory("price"));
	      istatus= new TableColumn("Address");
	      istatus.setCellValueFactory(new PropertyValueFactory("type"));
	      cid= new TableColumn("Number");
	      cid.setCellValueFactory(new PropertyValueFactory("extra"));
		   

	    		ordertable.setItems(items);
	    		ordertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    		ordertable.getColumns().addAll(iid,iprice, istatus,cid);
	    	
			
	  }
	  
		
	  public  void inittable3(  ObservableList<item2> items) {
		
		  // iid.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
		  // itype.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		  //iprice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		  iid = new TableColumn("ID");
		  iid.setCellValueFactory(new PropertyValueFactory("id"));
	      iprice = new TableColumn("Customer-ID");
	      iprice.setCellValueFactory(new PropertyValueFactory("price"));
	      istatus= new TableColumn("Status");
	      istatus.setCellValueFactory(new PropertyValueFactory("type"));
	      cid= new TableColumn("Amount");
	      cid.setCellValueFactory(new PropertyValueFactory("extra"));
		   

	    		ordertable.setItems(items);
	    		ordertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    		ordertable.getColumns().addAll(iid,iprice, istatus,cid);
	    	
			
	  }
	  
		
	  public  void inittable4(  ObservableList<item2> items) {
		
		  // iid.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
		  // itype.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		  //iprice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		  iid = new TableColumn("Order-ID");
		  iid.setCellValueFactory(new PropertyValueFactory("id"));
	      iprice = new TableColumn("Amount");
	      iprice.setCellValueFactory(new PropertyValueFactory("price"));
	      istatus= new TableColumn("Mode");
	      istatus.setCellValueFactory(new PropertyValueFactory("type"));
	     
		   

	    		ordertable.setItems(items);
	    		ordertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    		ordertable.getColumns().addAll(iid,iprice, istatus);
	    	
			
	  }
	  
	  
	  
	public  void initCombo() {
		  System.out.println("init");
		  
		   ObservableList<String> ids=  FXCollections.observableArrayList();
		  
		 application.Main.getorderids(ids);
		 
		 orderidbox.setItems(ids);
	  }
	

	public  void setFB(String comm) {
		fb.setText(comm);
	  }
	

	 @FXML
	 
	 public  void viewFB(ActionEvent event) {
			
		
		 
		
		  String id=  orderidbox.getValue().toString();
		  
		  // create order
		  
		 String comm= application.Main.viewFeedback(Integer.parseInt(id));
		  Stage primaryStage;
			try {
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFeedback.fxml"));
		        Parent root = loader.load();
		        AdminFuctionsCon  fbController = loader.getController();
		       fbController.initCombo();
		       fbController.setFB(comm);
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
	  public  void OrderStatus(ActionEvent event) {
			
		
		 
		String status="pending";
		  String id=  orderidbox.getValue().toString();
		  if(completed.isSelected())
			  status="completed";
		  else  if(inprocess.isSelected())
			  status=" in-process";
		  
		  application.Main.changeStatus(Integer.parseInt(id), status);
		  
		  
		
		  Stage primaryStage;
			try {
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("orderStatus.fxml"));
		        Parent root = loader.load();
		        AdminFuctionsCon  fbController = loader.getController();
		       fbController.initCombo();
		       fbController.inittable();
		     
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
  public  void generateReport(ActionEvent event) {
		
	  ObservableList<item2> items=  FXCollections.observableArrayList();
	 String formatt=  format.getValue().toString();
	 application.Main.generateReport(formatt,items);
	

		 Stage primaryStage;
			try {
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("generateReport.fxml"));
		        Parent root = loader.load();
		        AdminFuctionsCon  fbController = loader.getController();
		       fbController.initCombotype();
		       if(formatt=="customers") {
		    	   fbController.inittable2(items);
		  	 }else  if (formatt=="orders")
		  		 fbController.inittable3(items);
		  	else 
		  		 fbController.inittable4(items);
		       
		  	 
		     
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

public  void saveText() {
	save.setText("saved successfully....");
}
@FXML
public  void savedReport(ActionEvent event) {
		
	 
	 application.Main.saveReport();
	 
	 
	
	  
	
	 Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("generateReport.fxml"));
	        Parent root = loader.load();
	        AdminFuctionsCon  fbController = loader.getController();
	       fbController.initCombotype();
	       fbController.saveText();
	      
	     
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
				
				
	    	
	    			
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_menu.fxml"));
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
		   public  void enterCust(ActionEvent event) {
				 Stage primaryStage;
				try {
					
					Parent root = FXMLLoader.load(getClass().getResource("signupcust.fxml"));
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
	  public  void SelectUpdate(ActionEvent event) {
			
	 
		
		  String id=  custidbox.getValue().toString();
		
		  
		  Customer cust= application.Main.selectcust(Integer.parseInt(id));
		  
		  
		
		  Stage primaryStage;
			try {
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
		        Parent root = loader.load();
		        AdminFuctionsCon  fbController = loader.getController();
		       
		     
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
 
 public  void initCustData(Customer cust) {
	 id.setText(String.valueOf(cust.getId()));
	 uname.setText(cust.getName());
	 uadd.setText(cust.getAddress());
	 unum.setText(cust.getCell());
	 
 }
 
@FXML
  public  void SelectSearch(ActionEvent event) {
		
	  ObservableList<item2> items=  FXCollections.observableArrayList();
	
	  String id=  custidbox.getValue().toString();
	  
	
	  
	  Customer cust=application.Main.selectcust(Integer.parseInt(id));
	  
	  application.Main.selectOption(Integer.parseInt(id), 3, "", "","",items);
	  
	  
	
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
	        Parent root = loader.load();
	        AdminFuctionsCon  fbController = loader.getController();
	        fbController.initCustData(cust);
	        fbController.inittable3(items);
	     
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
  public  void Update(ActionEvent event) {
		
	String n= uname.getText().toString();
	String add= uadd.getText().toString();
	String cnum= unum.getText().toString();
	 ObservableList<item2> items=  FXCollections.observableArrayList();
	
	 application.Main.selectOption(0, 2, n, add,cnum,items);
	
	  
	  
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("manageCustomer.fxml"));
	        Parent root = loader.load();
	        AdminFuctionsCon  fbController = loader.getController();
	       fbController.initCombocust();
	   
	     
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
