package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class placeorderController {
	
	  @FXML
	    private ComboBox custidbox ;
	  @FXML
		private TextField id;
	  

	  @FXML
		private TextField message;
	  
	  @FXML
			private CheckBox cash;
	  @FXML
		private CheckBox credit;
	  
	  @FXML
			private TextField cb;
	  @FXML
			private TextField amountentered;
	  @FXML
		private TableView<item2> itemtable;
	  @FXML
			private TableColumn iid;
	  @FXML
			private TableColumn iprice;
	  @FXML
			private TableColumn itype;
	  @FXML
			private TextField amount;
	  
	  @FXML
		private TextField pin;
	  
	  
	  @FXML
		private Label pinmessage;
	  
	  

	  
	  
		
		
	  public  void inittable() {
		   ObservableList<item2> items=  FXCollections.observableArrayList();
		  // iid.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
		  // itype.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		  //iprice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		  
		  iid = new TableColumn("ID");
	      iid.setCellValueFactory(new PropertyValueFactory("id"));
	      iprice = new TableColumn("Price");
	      iprice.setCellValueFactory(new PropertyValueFactory("price"));
	      itype= new TableColumn("Type");
	      itype.setCellValueFactory(new PropertyValueFactory("type"));
		   String am= String.valueOf(application.Main.getamountOrder());
		   amount.setText(am);
		   int id= application.Main.getCurrent_order();
		   System.out.println("ere in tab ");
		   System.out.println(id);
		  try{  
	    		Class.forName("com.mysql.jdbc.Driver");  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
	    		System.out.println("Connected Successfully ");
	    		Statement st=con.createStatement();
	    	
	    		String query = "SELECT * FROM Item where oid = ? ";
	    	      PreparedStatement preparedStmt = con.prepareStatement(query);
	    	      preparedStmt.setInt(1,id);
	    	
	    	     
	    		ResultSet rs= preparedStmt.executeQuery();
	    		
	    		while(rs.next()) { 
	    			 System.out.println( rs.getInt("id"));
	    			items.add(new item2(String.valueOf(rs.getInt("id")),rs.getString("price"), rs.getString("type")
                             )
	    					);
	    			
	    	      
	    		}
	    		con.close();  
	    		
	    		itemtable.setItems(items);
	    		itemtable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    		itemtable.getColumns().addAll(iid, itype,iprice);
	    		}catch(Exception e){ System.out.println(e);} 
			
	  }
	  
	  public  void initCombo() {
		  System.out.println("init");
		  
		   ObservableList<String> ids=  FXCollections.observableArrayList();
		  
		 application.Main.getcustids(ids);
		 
		 custidbox.setItems(ids);
	  }
	   

	  @FXML
	  
	  public  void goEnterItem(ActionEvent event) {
			
		  int i = Integer.parseInt(id.getText());
		  String v=  custidbox.getValue().toString();
		  int idc=  Integer.parseInt(v);
		  
		  System.out.println(idc);
		  
		  // create order
		  application.Main.createOrder(i, idc);
		  Stage primaryStage;
			try {
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("enteritem.fxml"));
		        Parent root = loader.load();
		       enterItemcon itemController = loader.getController();
		       //sitemController.initCombotype();
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
	  

 

 public  void setCashb(double cashb, String am) {
	   System.out.println(cashb);
	 cb.setText(String.valueOf(cashb));
	 amountentered.setText(am);
	 if (cashb>=0)
		 message.setText("Successful ");
	 else
		 message.setText("Short on money ");
	 
 }
 
 
 @FXML
 
 public  void makePayement(ActionEvent event) {
	 double cashe = Double.parseDouble(amountentered.getText());
	 String type="credit";
	 if (cash.isSelected())
		 type="cash";
	 
	 double cashb= application.Main.makePayment(cashe, type);
	 
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
	        Parent root = loader.load();
	        placeorderController orderController = loader.getController();
	        orderController.inittable();
	       orderController.setCashb(cashb,String.valueOf(cashe) );
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
 
 public  void ConfirmOrder(ActionEvent event) {
	
	 application.Main.generatePin();
	  Stage primaryStage;
		try {
			
			

			Parent root = FXMLLoader.load(getClass().getResource("confirm.fxml"));
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
 
 public  void Confirmpin(ActionEvent event) {

		if(pin.getText().isEmpty()) {
			pinmessage.setText("Enter pin");
		}else {
			
			
	  int i = Integer.parseInt(pin.getText());
	  
	boolean correct= application.Main.confirmPin(i);
	
	if(correct==true) {
		pinmessage.setText("Successful");
	}else
		pinmessage.setText("Enter Again");
		}
		
	
	  
	  
	  
	  
 }



 @FXML
 
 public  void goBack(ActionEvent event) {
	
	
	  Stage primaryStage;
		try {
			
			

			Parent root = FXMLLoader.load(getClass().getResource("admin_menu.fxml"));
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
	public void onHandle(ActionEvent event) {
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
	}
	
	
	
	  

}
