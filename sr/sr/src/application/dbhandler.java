package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.classes.LaundryAdmin;
import application.classes.Order;
import application.classes.OrderCatalog;
import application.classes.item2;
import javafx.collections.ObservableList;

public class dbhandler {
	
	public dbhandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	void makeConnection() {
		 
	}
	
	void insertDataAdmin(int idd, String passs, String namee) {
		
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO laundryAdmin(id, name, pass,ccat_id,ocat_id)VALUES(?,?,?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,idd);
    		        statement.setString(2,namee);
    		        statement.setString(3, passs);
    		        statement.setInt(4,102);
    		        statement.setInt(5, 101);
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * FROM laundryAdmin"); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("name") + "  " + rs.getString("pass"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		} 
	
	void insertDataCust(int idd, String passs, String namee,  String add, String num) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO Customer(id, name, pass,address,cnum,cat_id)VALUES(?,?,?,?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,idd);
    		        statement.setString(2,namee);
    		        statement.setString(3, passs);
    		        statement.setString(4, add);
    		        statement.setString(5, num);
    		        statement.setInt(6, 102);
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * FROM Customer"); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("name") + "  " + rs.getString("pass"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);} 
		
	}
	
	

	void insertDataItem(int oid, String price, int id, String type) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO Item(id, oid, price,type)VALUES(?,?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,id);
    		        statement.setInt(2,oid);
    		        statement.setString(3,price);
    		        statement.setString(4,type);
    		       
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * FROM Item "); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("price") + "  " + rs.getString("type"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);} 
		
	}
		
	

	void insertDataOrder(int oid, String status, int cid) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO cust_Order(id, cat_id, cid,status)VALUES(?,?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,oid);
    		        statement.setInt(2,101);
    		        statement.setInt(3,cid);
    		        statement.setString(4,status);
    		       
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * FROM cust_Order "); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getInt("cid") + "  " + rs.getString("status"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);} 
		
	}
	
	void UpdateStatus(int oid, String status) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String query = "update cust_Order set status = ? where id = ?";
    	      PreparedStatement preparedStmt = con.prepareStatement(query);
    	      preparedStmt.setString(1,status);
    	      preparedStmt.setInt(2, oid);
    	      preparedStmt.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * FROM cust_Order "); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getInt("cid") + "  " + rs.getString("status"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);} 
		
	}
	
	 static void  Updatecust(int cid, String name, String add, String num) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String query = "update Customer set name = ?, address=?, cnum= ? where id = ?";
    	      PreparedStatement preparedStmt = con.prepareStatement(query);
    	      preparedStmt.setString(1,name);
    	      preparedStmt.setString(2,add);
    	      preparedStmt.setString(3,num);
    	      preparedStmt.setInt(4, cid);
    	      preparedStmt.executeUpdate();
    		System.out.println("Data updated..");
    		ResultSet rs=st.executeQuery("SELECT * FROM cust_Order "); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("address") + "  " + rs.getString("name"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);} 
		
	}
	
	static void getCustOrders(int cid,ObservableList<item2> items) {
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    		String query = "SELECT * FROM cust_Order where id = ?";
  	      PreparedStatement preparedStmt = con.prepareStatement(query);
  	      preparedStmt.setInt(1,cid);
 
    		ResultSet rs=preparedStmt.executeQuery(); 
    		OrderCatalog oc= OrderCatalog.getInstance(101);
    	
    		while(rs.next()) { 
    			 System.out.println( rs.getInt("id"));
    				Order o=oc.findOrder( rs.getInt("id"));
    				
    			items.add(new item2(String.valueOf(rs.getInt("id")),String.valueOf(rs.getInt("cid")), rs.getString("status"),String.valueOf(o.getAmount())                             )
    					);
    			
    	      
    		}
    		con.close();  
    		
    		
    		}catch(Exception e){ System.out.println(e);} 
		
	}
	
	
	void insertDataFB(int id, int oid, String comm) {
		
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO OrderFeedback(id,oid, comment)VALUES(?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,id);
    		        statement.setInt(2,oid);
    		   
    		        statement.setString(3, comm);
    		       
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT * from OrderFeedback"); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("oid") + "  " + rs.getString("comment"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		} 
	
	
       void insertDataPayment(int id, int oid, String p, String t) {
		
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    	
    		
    		String sql = "INSERT INTO  OrderPayment(id,oid, T_price, type)VALUES(?,?,?,?)";
    		        PreparedStatement statement = con.prepareStatement(sql);
    		        statement.setInt(1,id);
    		        statement.setInt(2,oid);
    		   
    		        statement.setString(3, p);
    		        statement.setString(4, t);
    		       
    		        statement.executeUpdate();
    		System.out.println("Data inserted..");
    		ResultSet rs=st.executeQuery("SELECT *  from OrderPayment"); 
    		
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("oid") + "  " + rs.getString("T_price"));
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		} 
		
		

}
