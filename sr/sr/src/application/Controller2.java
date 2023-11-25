package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller2 {
	



    @FXML
    private Label nameLabel ;

    public void setName(String name) {
        nameLabel.setText(name);
    }
    
    @FXML
    public void logout(ActionEvent event) {
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
    public void placeOrder(ActionEvent event) {
    	Stage primaryStage;
    	try {
			
			
			

  		  System.out.println("init init");
  		FXMLLoader loader = new FXMLLoader(getClass().getResource("placeorder.fxml"));
	        Parent root = loader.load();
	        placeorderController orderController = loader.getController();
	       orderController.initCombo();
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
    public void viewFB(ActionEvent event) {
    	Stage primaryStage;
    	try {
			
			
			

  		 // System.out.println("init init");
  		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFeedback.fxml"));
	        Parent root = loader.load();
	        AdminFuctionsCon fbController = loader.getController();
	       fbController.initCombo();
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
	 
	 public  void orderStatus(ActionEvent event) {
			
		
		 
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

public  void report(ActionEvent event) {
		
	
	 
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("generateReport.fxml"));
	        Parent root = loader.load();
	        AdminFuctionsCon  fbController = loader.getController();
	       fbController.initCombotype();
	   
	     
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
public  void manage(ActionEvent event) {
	
	
	 
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
// customers
@FXML
public  void giveFB(ActionEvent event) {
	
	
	 
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("giveFeedback.fxml"));
	        Parent root = loader.load();
	        CustomerFunctionsCon  fbController = loader.getController();
	       fbController.initCombo();
	   
	     
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
public  void statusViews(ActionEvent event) {
	
	
	 
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("checkStatus.fxml"));
	        Parent root = loader.load();
	        CustomerFunctionsCon  fbController = loader.getController();
	       fbController.initComboall();
	       
	   
	     
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
public  void ordersView(ActionEvent event) {
	
	
	 
	  Stage primaryStage;
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("viewOrders.fxml"));
	        Parent root = loader.load();
	        CustomerFunctionsCon  fbController = loader.getController();
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


    

}
