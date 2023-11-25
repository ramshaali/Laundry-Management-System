package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import application.classes.item2;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;

public class classes {
	

static class LaundryShop
{
	public LaundryShop(String location) {
		super();
		this.location = location;
		current_order=-1;
		loggedin_id=-1;
	}
	public int getCurrent_order() {
		return current_order;
	}
	public void setCurrent_order(int current_order) {
		this.current_order = current_order;
	}
	private
		String location;
	    LaundryAdmin[] Admin;
	    Customer[] cust;
		Order[]  or;
		
		
		int tcust;
		int torder;
		int tadmin;
		int loggedin_id;
		String  loggedin_type;
		int current_order;
		String name;
		dbhandler db;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public
	    void loadData() {
	
		 Admin=new LaundryAdmin[6];
		 cust= new Customer[6];
		 or= new Order[10];
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		OrderCatalog oc= OrderCatalog.getInstance(101);
		 tcust=0;
		 torder=0;
		 tadmin=0;
		
		 db= new dbhandler();
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
    		System.out.println("Connected Successfully ");
    		Statement st=con.createStatement();
    		/*String query1="INSERT INTO `userdb`.`info` (`id`, `firstname`, `lastname`)"
    				+ " VALUES ('3', 'Rafay', 'Ali')";
    		st.executeUpdate(query1);
    		System.out.println("Data inserted.."); */
    		ResultSet rs=st.executeQuery("SELECT * FROM Customer"); 
    		int i=0;
    		while(rs.next()) { 
    		System.out.println(rs.getInt(1)+" "+rs.getString("name") + "  " + rs.getString("pass") +  "  " + rs.getString("address") +  "  " + rs.getString("cnum"));
    		cust[i]=new Customer(rs.getInt(1),rs.getString("name"), rs.getString("pass"), rs.getString("address"),rs.getString("cnum"));
    		 tcust=  tcust+1;
    		i++;
    		
    		}
    		
    		ResultSet rss=st.executeQuery("SELECT * FROM  cust_Order"); 
    		 i=0;
    		while(rss.next()) { 
    		System.out.println(rss.getInt(1)+" "+rss.getInt("cid") + "  " + rss.getString("status") );
    		or[i]=new Order(rss.getInt(1),rss.getInt("cid"), rss.getString("status"));
    		 torder= torder+1;
    		i++;
    		
    		}
    		
    		ResultSet rs1=st.executeQuery("SELECT * FROM  Item"); 
		   		 i=0;
		   		while(rs1.next()) { 
		   		for(int o=0; o<torder; o++) {
		   			int id=rs1.getInt("oid");
		   			int oid=or[o].getToken();
		   			if (oid == id ) {
		   				Item item= new Item(rs1.getInt(1),rs1.getString("type"),Double.parseDouble(rs1.getString("price")) );
		   				or[o].insertItem(item);
		   			}
		   		}
   		
   		}
		   		
		   		ResultSet rs2=st.executeQuery("SELECT * FROM  OrderFeedback"); 
		   		 i=0;
		   		while(rs2.next()) { 
		   		for(int o=0; o<torder; o++) {
		   			int id=rs2.getInt("oid");
		   			int oid=or[o].getToken();
		   			if (oid == id ) {
		   				Feedback F= new Feedback(rs2.getInt(1),rs2.getString("comment"));
		   				or[o].setFb(F);
		   			}
		   		}
		   		}
		   		
		   		
		   		ResultSet rs3=st.executeQuery("SELECT * FROM  OrderPayment"); 
		   		 i=0;
		   		while(rs3.next()) { 
		   		for(int o=0; o<torder; o++) {
		   			int id=rs3.getInt("oid");
		   			int oid=or[o].getToken();
		   			if (oid == id ) {
		   				OrderPayment op= new OrderPayment(rs3.getInt(1),Double.parseDouble(rs3.getString("T_price")),rs3.getString("type"));
		   				or[o].setPayment(op);
		   				or[o].setAmount(Double.parseDouble(rs3.getString("T_price")));
		   			}
		   		}
		   		
		   		
  		
  		}
		   		
		   		for(int o=0; o<torder; o++) { // for every order
		   			for(int c=0; c<tcust; c++) {
		   			int id=cust[c].getId();
		   			int oid=or[o].getCust_id();
		   			if (oid == id ) {
		   				cust[c].insertOrder(or[o]);
		   				break;
		   			}
		   			}
		   			oc.insertOrder(or[o]);
		   			}
		   			
			   			for(int c=0; c<tcust; c++) { //for every customer
			   			cc.insertCust(cust[c]);
			   			}
			   			
			   			
			   		
			   			ResultSet rs4=st.executeQuery("SELECT * FROM laundryAdmin"); 
			    		 i=0;
			    		while(rs4.next()) { 
			    		System.out.println(rs4.getInt(1)+" "+rs4.getString("name") + "  " + rs4.getString("pass") );
			    		Admin[i]=new LaundryAdmin(rs4.getInt(1),rs4.getString("name"), rs4.getString("pass"), cc, oc);
			    	tadmin= tadmin+1;
			    	
			    		i++;
			    		
			    		}
    		
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
	}
	
	String checkLogin(int id, String pass) {
		int found=0;
		String n="no";
		
		System.out.println( id );
		System.out.println( pass );
		for(int i=0; i<tadmin;i++) {
			System.out.println( Admin[i].getId() );
			System.out.println( Admin[i].getPass() );
			if(Admin[i].getId()==id && Admin[i].getPass().equals(pass)) {
				found=1;
				loggedin_id=i;
				System.out.println( "yes" );
				n= Admin[i].getName();
				loggedin_type="Admin";
				break;
			}
		}
		
		
		System.out.println( n );
		if(found==0) {
			for(int i=0; i<tcust;i++) {
			
				if(cust[i].getId()==id && cust[i].getPass().equals(pass)) {
					found=1;
					loggedin_id=id;
					System.out.println( "yes" );
					n= cust[i].getName();
					loggedin_type="cust";
					break;
				}
			}
		}
		name=n;
		return n;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	String page() {
		return loggedin_type ;
	}
	
	double getamountOrder() {
		OrderCatalog oc= OrderCatalog.getInstance(101);
		Order o=oc.findOrder(current_order);
		return o.getAmount();
		
		
	}
	
     double getamOrder(int oid) {
    	  OrderCatalog oc= OrderCatalog.getInstance(101);
  		Order o=oc.findOrder(oid);
  		return o.getAmount();
		
  		
	}
     int getCurrId() {
    	 return loggedin_id;
     }
     
     String getstatusOrder(int oid) {
   	  OrderCatalog oc= OrderCatalog.getInstance(101);
 		Order o=oc.findOrder(oid);
 		return o.getOrderStatus();
		
 		
	}
	
	void insertDataAdmin(int idd, String passs, String namee) {
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		OrderCatalog oc= OrderCatalog.getInstance(101);
		Admin[tadmin]=new LaundryAdmin(idd,namee, passs, cc, oc);
		tadmin++;
		
       db.insertDataAdmin(idd, passs, namee); 
       
    		} 
	
	void insertDataCust(int idd, String passs, String namee, String add, String  num) {
		cust[tcust]=new Customer(idd,namee, passs, add, num);
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		cc.insertCust(cust[tcust]);
		tcust++;
		
		
       db.insertDataCust(idd, passs, namee, add, num); 
       
    		} 
	
	  
	 void  getcustids( ObservableList<String> ids) {
		 System.out.println("in send");
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		 for(int i=0; i<tcust;i++) {
			 
			 ids.add(String.valueOf(cc.getCustomerIndex(i).getId()));
			 
		 }
		 
		 System.out.println(ids);
		 
		}
	 
	 void  getorderids( ObservableList<String> ids) {
		 System.out.println("in send");
			OrderCatalog oc= OrderCatalog.getInstance(101);
		 for(int i=0; i<torder;i++) {
			 
			 ids.add(String.valueOf(oc.getOrderIndex(i).getToken()));
			 
		 }
		 
		 System.out.println(ids);
		 
		}
	 
	 void  viewOrders( ObservableList<String> ids) {
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		 Customer cust= cc.findCust(loggedin_id);
		 System.out.println("found customer");
		 cust.viewOrders(ids);
		 
		}
	 
	 void  viewOrdersall( ObservableList<String> ids) {
		 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
		 Customer cust= cc.findCust(loggedin_id);
		 System.out.println("found customer");
		 cust.viewOrdersall(ids);
		 
		}
	 
	 void  placeNewOrder(int oid, int cid) {
		 db.insertDataOrder(oid, "Not Confirmed", cid);
		 System.out.println(loggedin_id);
		 Admin[loggedin_id].placeNewOrder(oid, cid);
		 current_order=oid;
		 torder= torder + 1;
		 }
	 
	 void enterItem(int id,String type, double price ){
		 db.insertDataItem(current_order, String.valueOf(price), id, type);
		 Admin[loggedin_id].enterItem(id, type, price, current_order);
		 
		 
	 }
	 void endOrder() {
		 System.out.println("YES ORDER END");
		 System.out.println(loggedin_id);
		 System.out.println(current_order);
		 Admin[loggedin_id].endOrder(current_order);
	 };	
	 
	 
	 void changeStatus(int orderID, String st) {
		 db.UpdateStatus(orderID, st);
		 Admin[loggedin_id].changeStatus(orderID, st);
	 };
		
        void logout() {
        	loggedin_id=-1;
        }
		double makePayment( double cashTendered, String type){
			String am= String.valueOf(getamountOrder());
			Random random = new Random();
	        random.setSeed(System.currentTimeMillis());
	       int r= random.nextInt();
	       db.insertDataPayment(r, current_order, am, type);
			
			return  Admin[loggedin_id].makePayment(r,cashTendered, current_order, type);
		};
		String viewFeedback(int orderId) {
			return Admin[loggedin_id].viewFeedback(orderId);
		};
		void generatePin() {
			 Admin[loggedin_id].generatePin();
		};
		boolean confirmPin(int pin) {
			return  Admin[loggedin_id].confirmPin(pin);
		};
		
		
		void  generateReport(String format, ObservableList<item2> items){
		
		 Admin[loggedin_id].generateReport(format, items);
			
		}
		
		void  saveReport(){
			
			 Admin[loggedin_id].saveReport();
				
			}
	
		void searchOrder(int orderID) {};
		void enterComplain(String comm) {
			
			Random random = new Random();
			   random.setSeed(System.currentTimeMillis());
	       int r= random.nextInt();
			CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(loggedin_id);
			 cust.enterComplain(comm,r);
			 int oid= cust.getCurrOrder();
			 db.insertDataFB(r, oid, comm);
		};
		void selectOrder(int orderID) {
			CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(loggedin_id);
			 cust.selectOrder(orderID);
		};
		
		Customer enterId(int custID) {
			current_order=custID;
			CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(custID);
			 return cust;
		};
		void selectOption(int opt,int custID, String name, String add, String num,  ObservableList<item2> items) {
			 Admin[loggedin_id].applyChanges(current_order, opt, name, add, num,items);
		
				 
			
		};
		ObservableList<item2> viewOrder() {
			 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(loggedin_id);
			
			 return  cust.viewOrder();
			 
		};
		
		
		
};

static class Person {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Person(int id, String name, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected
	int id;
	String name;
	String pass;
	
};
static class LaundryAdmin extends Person
{
	private

    CustomerCatalog  cc ;
    OrderCatalog oc;
    int pin;
    int custid;
    ArrayList<Report> reports = new ArrayList<Report>(); 
    String format;
    

	public LaundryAdmin(int id, String name, String pass, CustomerCatalog cc, OrderCatalog oc) {
		super(id, name, pass);
		this.cc = cc;
		this.oc = oc;
		pin=0;
		custid=-1;
	}
	public LaundryAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LaundryAdmin(int id, String name, String pass) {
		super(id, name, pass);
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomerCatalog getCc() {
		return cc;
	}
	public void setCc(CustomerCatalog cc) {
		this.cc = cc;
	}
	public OrderCatalog getOc() {
		return oc;
	}
	public void setOc(OrderCatalog oc) {
		this.oc = oc;
	}
	public
		double makePayment(int r,double cashTendered, int orderId, String type){
		OrderCatalog oc= OrderCatalog.getInstance(101);
		Order o=oc.findOrder(orderId);
		double cb= o.makePayment(r,cashTendered, type);
		return cb;
		
		
	}
	
	void  generateReport(String format ,ObservableList<item2> items){
		Report r= new Report(format);
		 r.createReport(items);
		 this.format=format;
		
	}
	

	void  saveReport(){
		Report r= new Report(format);
		reports.add(r);
		
		
		
	}
		String viewFeedback(int orderId) {
			OrderCatalog oc= OrderCatalog.getInstance(101);
			return oc.getOrderFB(orderId);
		};
		void generatePin() {
			 Random rand = new Random(); //instance of random class
		      int upperbound = 125;
		        //generate random values from 0-124
		      int int_random = rand.nextInt(upperbound); 
		      pin=int_random;
		      System.out.println(int_random);
		};
		boolean confirmPin(int pinn) {
			if (pin==pinn)
				return true;
			else
				return false;
		};
		void placeNewOrder(int oid, int cid) {
			OrderCatalog oc= OrderCatalog.getInstance(101);
		    Order temp= new Order(oid, cid, "not confirmed");
		    oc.insertOrder(temp);
		    CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(cid);
			cust.insertOrder(temp);
			custid=cid;
				
			}
		void enterItem(int itemID, String des,double p, int oid) {
			OrderCatalog oc= OrderCatalog.getInstance(101);
			oc.findOrder(oid,des,p, itemID);
			Item i= new Item(itemID, des, p);
			CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 Customer cust=cc.findCust(custid);
			cust.insertItem(i,oid);
			
		}
		void searchOrder(int orderID) {};
		void enterComplain() {};
		void selectOrder(int orderID) {
			
		};
		void changeStatus(int orderID, String st) {
			   System.out.println("admin status");
				OrderCatalog oc= OrderCatalog.getInstance(101);
				
				
				Order order= oc.findOrder(orderID);
				order.changeOrderStatus(st);
		};
		void enterId(int custID) {};
		void selectOption(int opt,int custID) {};
		void viewOrders(int custID) {};
		void endOrder(int oid) {
			   System.out.println("admin end order");
			OrderCatalog oc= OrderCatalog.getInstance(101);
			   System.out.println("admin end oorder");
			   System.out.println();
			Order order= oc.findOrder(oid);
			order.becomeCompleted();
		};	
		
		 void applyChanges(int cid, int option, String name, String add, String num,  ObservableList<item2> items) {
			 CustomerCatalog  cc=  CustomerCatalog.getInstance(102);
			 if(option==2) {// update
				Customer cust= cc.findCust(cid);
				boolean eq= name.equals("");
				if(eq==false)
					cust.setName(name);
				else
					name=cust.getName();
				 eq= add.equals("");
				 if(eq==false)
				 cust.setAddress(add);
				 else
					 add= cust.getAddress();
				 
				 eq= num.equals("");
				 if(eq==false)
				 cust.setCell(num);
				 else
					 num= cust.getCell();
				 dbhandler.Updatecust(cid, name, add, num);
				 
				 
				 
		 }else if(option==3) { // search
			 
			 dbhandler.getCustOrders(cid, items);
			 
			 
		 }
		 }
}

 static class Customer extends Person
{
	
	private
		
		String address;
		String cell;
		ArrayList<Order> orders = new ArrayList<Order>(); 
		int currOrder;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, String name, String pass) {
		super(id, name, pass);
		// TODO Auto-generated constructor stub
	}
	 public Customer(int id, String name, String pass, String address, String cell) {
			super(id, name, pass);
			this.address = address;
			this.cell = cell;
			currOrder=-1;
			
		}
	 public int getCurrOrder() {
		return currOrder;
	}
	public void setCurrOrder(int currOrder) {
		this.currOrder = currOrder;
	}
	public void  insertOrder(Order o) {
			orders.add(o);
		}
	 public Order findOrder(int id) {
	   	 System.out.println("YES searcing");
	        for (Order order : orders) {
	            if (order.getToken()==id) {
	               return order;
	            }
	        }
	        return null;
	    }
	public void  insertItem(Item i, int oid) {
		Order o= findOrder(oid);
		o.insertItem(i);
	}
	public void  viewOrders( ObservableList<String> ids) {
		
		 for(int i=0; i<orders.size();i++) {
			 System.out.println("YESsss searcing");
			 if(orders.get(i).getOrderStatus().equals("completed"))
			 ids.add(String.valueOf(orders.get(i).getToken()));
			 
		 }
		 
		 System.out.println(ids);
		 
		}
	 
	public void  viewOrdersall( ObservableList<String> ids) {
		
		 for(int i=0; i<orders.size();i++) {
			 System.out.println("YESsss searcing");
		
			 ids.add(String.valueOf(orders.get(i).getToken()));
			 
		 }
		 
		 System.out.println(ids);
		 
		}
	 
	
	
		 
		
	
	public
		void enterComplain(String com,int r) {
		OrderCatalog oc= OrderCatalog.getInstance(101);
		Order o=oc.findOrder(currOrder);
		o.giveFb(com,r);
		Order or= findOrder(currOrder);
		or.giveFb(com,r);
		
		
	};
		ObservableList<item2> viewOrder() {
			  ObservableList<item2> items=  FXCollections.observableArrayList();
				
					  try{  
				    		Class.forName("com.mysql.jdbc.Driver");  
				    		Connection con=DriverManager.getConnection(  
				    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
				    		System.out.println("Connected Successfully ");
				    		Statement st=con.createStatement();
				    		OrderCatalog oc= OrderCatalog.getInstance(101);
				    		
				    		String query = "SELECT * FROM cust_Order  where cid = ?";
				    	      PreparedStatement preparedStmt = con.prepareStatement(query);
				    	      preparedStmt.setInt(1,id);
				    	 
				    	      ResultSet rs = preparedStmt.executeQuery();
				 
				    		
				    		while(rs.next()) { 
				    			 System.out.println( rs.getInt("id"));
				    			 Order o=oc.findOrder(rs.getInt("id"));
				    			 String am= String.valueOf(o.getAmount());
				    			items.add(new item2(String.valueOf(rs.getInt("id")),rs.getString("status"), am
			                             )
				    					);
				    			
				    	      
				    		}
				    		con.close();  
				    		
				    		
				    		}catch(Exception e){ System.out.println(e);} 
					  return items;
			
		};
		void selectOrder(int orderID) {
			currOrder=orderID;
		};
		void applyChanges(Customer cus,int opt) {};
}
// CustCatalog contains list of customers
 static class CustomerCatalog
{
	 
	 private static CustomerCatalog cc = null;
	  
	 private CustomerCatalog(int id) {
		super();
		this.id = id;
	}

	 public static CustomerCatalog getInstance(int id)
	    {
	        if (cc == null)
	           cc = new  CustomerCatalog(id);
	  
	        return cc;
	    }

	private
	    ArrayList<Customer> Customers = new ArrayList<Customer>();
	    int id;

	public int getId() {
			return id;
		}
      
		public void setId(int id) {
			this.id = id;
		}
		
		public void  insertCust(Customer c) {
			Customers.add(c);
		}

	public ArrayList<Customer> getCustomer() {
		return Customers;
	}
	public Customer getCustomerIndex(int i) {
		return  Customers.get(i);
	}

	public void setCustomers(ArrayList<Customer> customer) {
		Customers = customer;
	}
	public Customer findCust(int id) {
	   	 System.out.println("YES searcing");
	        for (Customer cust : Customers) {
	            if (cust.getId()==id) {
	               return cust;
	            }
	        }
	        return null;
	    }
	
	public void  removeCust(int id ) {
		Customer cust =  findCust( id);
	
		Customers.remove(cust);
	}
	
	
	}
 

	


 static class OrderPayment
{
	 public OrderPayment( int id, double amount, String type) {
			super();
			this.amount = amount;
		    this.type=type;
			this.id = id;
		}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	private
		double amount;
	     String type;
		int id;
		
	
	//public
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
};
 
 
 static class Cashpayment extends OrderPayment{
public double getCashback() {
		return cashback;
	}
	public void setCashback(double cashback) {
		this.cashback = cashback;
	}
private  double cashback;
	public Cashpayment(int id, double amount) {
		super(id, amount,"cash");
		 System.out.println("amount in  pay c");
	   	 System.out.println(amount);
		cashback=0;
		// TODO Auto-generated constructor stub
	}
	
	
	 
 };
 
 
 static class CreditCard extends OrderPayment{
  
	 
	public CreditCard(int id, double amount) {
		super(id, amount,"creditcard");
		// TODO Auto-generated constructor stub
	}

	
	 
 }

 static class Feedback
{
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
		comment="Not available....";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private 
	    int id;
		int ratings;
		String comment;
		public Feedback(int id, String comment) {
			super();
			this.id = id;
			this.comment = comment;
		}
		
	public
		void giveFeedback() {};
}

 static class Order
{
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public ArrayList<Item> getItems() {
		return Items;
	}
	public void setItems(ArrayList<Item> items) {
		Items = items;
	}
	public Feedback getFb() {
		return fb;
	}
	public void setFb(Feedback fb) {
		this.fb = fb;
	}
	public OrderPayment getPayment() {
		return payment;
	}
	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}
	public String findFeedBack() {
		
		return fb.getComment();
	}
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private
		int token;
	    int cust_id;
		int totalItems;
		String orderStatus;
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		double amount;
	    ArrayList<Item> Items = new ArrayList<Item>(); //Order has multiple items
	    Feedback fb;
	    OrderPayment payment;
	   

	public Order(int token, int cust_id, String orderStatus) {
			super();
			this.token = token;
			this.cust_id = cust_id;
			this.totalItems = 0;
			this.orderStatus = orderStatus;
			amount=0;
			fb= new Feedback();
		}
	public
		double makePayment(int r,double cashTendered,String type) {
		if (type=="credit") {
		   	 System.out.println("amount in ma pay");
		   	 System.out.println(amount);
			CreditCard cc= new CreditCard(r, amount);
			payment=cc;
			return 0;
			
			
		}else {
			Cashpayment c= new Cashpayment(r, amount);
			payment=c;
			double cb=cashTendered- amount;
			c.setCashback(cb);
			return c.getCashback();
					
		}
		
		
	} ;
	
	void giveFb(String comm, int r) {
	
      fb.setId(r);
      fb.setComment(comm);
		
	}
	
		//Feedback getFeedback() {} ;
		void addItem(String des, int id, double price) {
			Item temp=  new Item(id,des, price); // creation
			Items.add(temp); // add
			totalItems= totalItems + 1;
			amount= amount +price;
			
		} 
		
		void insertItem(Item i) {
			
			//Items.add(i);
			//totalItems= totalItems + 1;
			//amount= amount +i.getPrice();
		}
		boolean becomeCompleted() { return true;};
		
		void changeOrderStatus(String st) {
			orderStatus=st;
		}
}



 static class OrderCatalog {// Singleton 
   
    private static OrderCatalog oc = null;
  
  
	private
	ArrayList<Order> Orders = new ArrayList<Order>();
    int id;
  
  
    private OrderCatalog(int id)
    {
    	super();
		this.id = id;
    }
  
 
    public static OrderCatalog getInstance(int id)
    {
        if (oc == null)
            oc = new  OrderCatalog(id);
  
        return oc;
    }
    
    public void insertOrder(Order o) {
		Orders.add(o);
	}
    public Order getOrder(int i) {
		return  Orders.get(i);
	}
    
    public void findOrder(int id, String des, double p, int iid) {
        for (Order order : Orders) {
            if (order.getToken()==id) {
               order.addItem(des, iid, p);
            }
        }
    }
    
    public Order findOrder(int id) {
   	 System.out.println("YES searcing");
        for (Order order : Orders) {
            if (order.getToken()==id) {
               return order;
            }
        }
        return null;
    }
    
    public String getOrderFB(int oid) {
    	Order o= findOrder(oid);
    	return o.findFeedBack();
    	
    }
    
    public Order getOrderIndex(int i) {
		return Orders.get(i);
	}
}
  

static class Item
{
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Item(int id, String type, double price) {
		super();
		this.id = id;
		this.type = type;
		
		this.price = price;
	}
	private
		int id;
	   String type;
	   double price;
//	public
	//
}

public static class Report{
	String format;

	public Report(String format) {
		super();
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public void createReport(ObservableList<item2>  items){
		
		if(format=="customers") {
			  try{  
		    		Class.forName("com.mysql.jdbc.Driver");  
		    		Connection con=DriverManager.getConnection(  
		    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
		    		System.out.println("Connected Successfully ");
		    		Statement st=con.createStatement();
		    	
		 
		    		ResultSet rs=st.executeQuery("SELECT * FROM Customer"); 
		    		
		    		while(rs.next()) { 
		    			 System.out.println( rs.getInt("id"));
		    			items.add(new item2(String.valueOf(rs.getInt("id")),rs.getString("name"), rs.getString("address"),  rs.getString("cnum")
	                             )
		    					);
		    			
		    	      
		    		}
		    		con.close();  
		    		
		    		
		    		}catch(Exception e){ System.out.println(e);} 
		}else if(format=="orders") {
			try{  
	    		Class.forName("com.mysql.jdbc.Driver");  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
	    		System.out.println("Connected Successfully ");
	    		Statement st=con.createStatement();
	    	
	 
	    		ResultSet rs=st.executeQuery("SELECT * FROM cust_Order"); 
	    		OrderCatalog oc= OrderCatalog.getInstance(101);
	    	
	    		while(rs.next()) { 
	    			 System.out.println( rs.getInt("id"));
	    				Order o=oc.findOrder( rs.getInt("id"));
	    				
	    			items.add(new item2(String.valueOf(rs.getInt("id")),String.valueOf(rs.getInt("cid")), rs.getString("status"),String.valueOf(o.getAmount())                             )
	    					);
	    			
	    	      
	    		}
	    		con.close();  
	    		
	    		
	    		}catch(Exception e){ System.out.println(e);} 
		}else  {
			try{  
	    		Class.forName("com.mysql.jdbc.Driver");  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/LMS","root","hammy123@");
	    		System.out.println("Connected Successfully ");
	    		Statement st=con.createStatement();
	    	
	 
	    		ResultSet rs=st.executeQuery("SELECT * FROM OrderPayment"); 
	    		OrderCatalog oc= OrderCatalog.getInstance(101);
	    	
	    		while(rs.next()) { 
	    			 System.out.println( rs.getInt("id"));
	    				Order o=oc.findOrder( rs.getInt("oid"));
	    				
	    			items.add(new item2(String.valueOf(rs.getInt("oid")), rs.getString("type"),   rs.getString("T_price") )
	    					);
	    			
	    	      
	    		}
	    		con.close();  
	    		
	    		
	    		}catch(Exception e){ System.out.println(e);} 
		}
		
	}
	
	
}

public static class item2 { // for table view
	   public SimpleStringProperty idProperty() {
		return id;
	}
	public void setId(SimpleStringProperty id) {
		this.id = id;
	}
	SimpleStringProperty id;  
	SimpleStringProperty price;
	SimpleStringProperty type;
	SimpleStringProperty extra;
	   
	   item2(String id, String price, String type) {
	      this.id = new SimpleStringProperty(id);
	      this.price = new SimpleStringProperty(price);
	      this.type= new SimpleStringProperty(type);
	      
	
	   }
	   item2(String id, String price, String type, String extra) {
		      this.id = new SimpleStringProperty(id);
		      this.price = new SimpleStringProperty(price);
		      this.type= new SimpleStringProperty(type);
		      this.extra= new SimpleStringProperty(extra);
		      
		
		   }
	public SimpleStringProperty extraProperty() {
		return extra;
	}
	public void setExtra(SimpleStringProperty extra) {
		this.extra = extra;
	}
	public SimpleStringProperty priceProperty() {
		return price;
	}
	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}
	public SimpleStringProperty typeProperty() {
		return type;
	}
	public void setType(SimpleStringProperty type) {
		this.type = type;
	}
}
class ItemDescription
{
	private
		String colour;
		String description;
	//public
}	

static class Delivery
{
	private
	int id;
}

static class DeliveryDetails
{
	private
		int id;
		String address;
		int customerNumber;
}	
static class Rider
{
	private
		int id;
		String name;
		int phoneNumber;
	    ArrayList<DeliveryDetails> Delivery = new ArrayList<DeliveryDetails>(); // Rider Manages Multiple Deliveries

}


}
