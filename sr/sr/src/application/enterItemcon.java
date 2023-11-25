package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class enterItemcon {
	String t;
	  @FXML
	    private ComboBox type ;

	  @FXML
		private TextField itemid;
	  @FXML
		private TextField price;
	
	  public  void initCombotype() {
		  System.out.println("init");
		  
		   ObservableList<String> types=  FXCollections.observableArrayList();
		  
		 types.add("curtain");
		 types.add("blanket");
		 types.add("coat");
		 types.add("shirt");
		 types.add("pant");
	
		 type.setItems(types);
	  }
	   
	  
	  
 @FXML
	  
	  public  void EnterItem(ActionEvent event) {
			
		  int i = Integer.parseInt(itemid.getText());
		  //String v=  type.getValue().toString();
		  double p=0;
		 if(t=="curtain" || t=="blanket")
			 p=1200;
		 else  if(t=="shirt" || t=="pant")
			 p=200;
		 else  if(t=="coat"  )
			 p=600;
		 
		  System.out.println(i);
		  
		  
		  // create order
		  application.Main.enterItem(i, t, p);
		  Stage primaryStage;
			try {
				
				

				FXMLLoader loader = new FXMLLoader(getClass().getResource("enteritem.fxml"));
		        Parent root = loader.load();
		       enterItemcon itemController = loader.getController();
		      // itemController.initCombotype();
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
 
 public  void endOrder(ActionEvent event) {
		
	
	 
	
	  
	  
	  // create order
	  application.Main.endOrder();
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
	        Parent root = loader.load();
	        placeorderController orderController = loader.getController();
	       orderController.inittable();
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
 
 public  void shirtButton(ActionEvent event) {
		price.setText("200");
		t="shirt";
	 
	  
	  
	  
 }


@FXML

public  void pantButton(ActionEvent event) {
		price.setText("200");
		t="pant";
	 
	  
	  
	  
}



@FXML

public  void curtainButton(ActionEvent event) {
		price.setText("1200");
		t="curtain";
	 
	  
	  
	  
}

@FXML

public  void blanketButton(ActionEvent event) {
		price.setText("1200");
		t=" blanket";
	 
	  
	  
	  
}


@FXML

public  void coatButton(ActionEvent event) {
		price.setText("600");
		t="coat";
	 
	  
	  
	  
}


 
 
 

}
