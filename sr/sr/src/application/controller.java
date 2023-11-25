package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controller {
	@FXML
	private TextField id;
	
	@FXML
	private TextField pass;
	

	@FXML
	private Label wrong;
	
	@FXML
	private TextField username;
	
	  @FXML
	   private Button okButton ;
	  
		@FXML
		private TextField newid;
		
		@FXML
		private TextField newpass;
		@FXML
		private TextField add;
		
		@FXML
		private TextField num;
	
	
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
	
	@FXML
	public void onHandle2(ActionEvent event) {
		Stage primaryStage;
		try {
			
			

			Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
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
	public void submitLogin(ActionEvent event) {
	
		if(id.getText().isEmpty() || pass.getText().isEmpty() ) {
			wrong.setText("Fill all fields");
		}
		else {
			int i = Integer.parseInt(id.getText());
			String n=application.Main.checkloginn(i, pass.getText().toString());
			if(n!="no") {
			System.out.println("yt");
			System.out.println(n);
			wrong.setText("success");
		Stage primaryStage;//= new Stage();
		String t= application.Main.getType();
		String page="admin_menu.fxml";
		if(t.equals("cust"))
			page="CustomerMenu.fxml";
		try {
		
			
			
			/*Parent root = FXMLLoader.load(getClass().getResource("admin_menu.fxml"));
			//username=new TextField();
			username.setText(n);
		
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("CV");
			primaryStage.setScene(scene);
			primaryStage.show();
			*/
			FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
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
	}else {
		//wrong= new Label();
		wrong.setText("Incorrect password or id");
	}
	}
	}
		
	
	@FXML
	public void signup(ActionEvent event) {
		System.out.println("signup");
		int i = Integer.parseInt(newid.getText());
		application.Main.insertAdmin(i, newpass.getText().toString(), username.getText().toString());
		onHandle( event);
		
		
	
	}
	
	@FXML
	public void signupcust(ActionEvent event) {
		System.out.println("signup");
		int i = Integer.parseInt(newid.getText());
		Stage primaryStage;
		application.Main.insertDataCust(i, newpass.getText().toString(), username.getText().toString(), add.getText().toString(), num.getText().toString() );
		 String n= application.Main.getname();
	
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
	public void home(ActionEvent event) {
		Stage primaryStage;
		try {
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
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
	public void price(ActionEvent event) {
		Stage primaryStage;
		try {
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("pricing.fxml"));
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
		public void services(ActionEvent event) {
			Stage primaryStage;
			try {
				
				
				
				Parent root = FXMLLoader.load(getClass().getResource("services.fxml"));
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


