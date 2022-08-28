package DB;
import DB.Customer;
import static javafx.stage.Modality.NONE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
/**
* A JavaFX ConnectDB  Application
*/ public class Table extends Application {
/**
* @param args the command line arguments
* 
* 	
*/
	private ArrayList<Customer> information;
    private ObservableList<Customer> informationList;

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "0000";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "test";
	private Connection con;
	
	public static void main(String[] args) {	
		Application.launch(args);
	}


	@Override
	public void start(Stage stage) {
		information = new ArrayList<>();
		
		try {
			
			getinformation();			
			//convert information from arraylist to observable arraylist
		    informationList = FXCollections.observableArrayList(information);

			//really bad method
		    tableView(stage);
			stage.show();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		

	}
	@SuppressWarnings("unchecked")
	
	private void tableView(Stage stage) {
		
	    TableView<Customer> DataTable = new TableView<Customer>();

		Scene scene = new Scene(new Group());
        stage.setTitle("Customer Data");
        stage.setWidth(550);
        stage.setHeight(500);
 
        Label label = new Label("Customer Data");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.BLUE);
 
        DataTable.setEditable(true);
        DataTable.setMaxHeight(200);
        DataTable.setMaxWidth(408);
        
        TableColumn<Customer, Double> CIDCol = new TableColumn<Customer,Double>("CID");
        CIDCol.setMinWidth(50);
        CIDCol.setCellValueFactory(
        		new PropertyValueFactory<Customer, Double>("CID"));
        
           
        
        
        TableColumn<Customer, String> NameCol = new TableColumn <Customer, String>("Name");
        NameCol.setMinWidth(100);
        
        NameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        
        NameCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        NameCol.setOnEditCommit(        
        		(CellEditEvent<Customer, String> t) -> {
                       ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())
        	                        ).setName(t.getNewValue()); //display only
                 updateName( t.getRowValue().getCID(),t.getNewValue());
        		});
        
        TableColumn<Customer, String> phoneCol = new TableColumn <Customer, String>("PhoneNumber");
        phoneCol.setMinWidth(100);
        
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("PhoneNumber"));
        
        phoneCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        phoneCol.setOnEditCommit(        
        		(CellEditEvent<Customer, String> t) -> {
                       ((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())
        	                        ).setPhoneNumber(t.getNewValue()); //display only
                 updatePhone( t.getRowValue().getCID(),t.getNewValue());
        		});
        
        DataTable.setItems(informationList);
        
        DataTable.getColumns().addAll(CIDCol,NameCol, phoneCol);


        
        final TextField addCID = new TextField();
        addCID.setPromptText("CID");
        addCID.setMaxWidth(CIDCol.getPrefWidth());
        
        final TextField addName = new TextField();
        addName.setMaxWidth(NameCol.getPrefWidth());
        addName.setPromptText("Name");
        
        final TextField addPhone = new TextField();
        addPhone.setMaxWidth(phoneCol.getPrefWidth());
        addPhone.setPromptText("PhoneNumber");
        
        
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            Customer rc;
        	rc = new Customer(
        			Double.valueOf(addCID.getText()),
            		addName.getText(), 
            		addPhone.getText());
        	informationList.add(rc);
        	insertinformation(rc);
            addCID.clear();
            addName.clear();
            addPhone.clear();
        });
 
        final HBox hb = new HBox();

 
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction((ActionEvent e) -> {        	 
        	 ObservableList<Customer> selectedRows = DataTable.getSelectionModel().getSelectedItems();
        	ArrayList<Customer> rows = new ArrayList<>(selectedRows);
        	rows.forEach(row -> {
        		DataTable.getItems().remove(row); 
        		deleteRow(row); 
        		DataTable.refresh();
        		});   
        });
        
 
        hb.getChildren().addAll(addCID, addName, addPhone, addButton, deleteButton);
        hb.setSpacing(3);
   
        
        final Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction((ActionEvent e) -> {        	 
        	DataTable.refresh();  
        });
        
//		Button ownedNoneButton = new Button("Owned None");
//		ownedNoneButton.setOnAction(c -> );
		
        final Button clearButton = new Button("Clear All");
        	clearButton.setOnAction((ActionEvent e) -> {
        		showDialog(stage, NONE, DataTable);
        		

        });
        

        	
        final HBox hb2 = new HBox();
        hb2.getChildren().addAll(clearButton, refreshButton);
        hb2.setAlignment(Pos.CENTER_RIGHT);
        hb2.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label,DataTable, hb,hb2);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
	}
	
	


	private void insertinformation(Customer rc) {
		
	try {
			System.out.println("Insert into Customer (CID, Name,PhoneNumber) values("+rc.getCID()+",'"+rc.getName()+"','"+ rc.getPhoneNumber() +"',"+")");
			
			connectDB();
			ExecuteStatement("Insert into Customer (CID, Name,PhoneNumber) values("+rc.getCID()+",'"+rc.getName()+"','"+ rc.getPhoneNumber() +"',"+")");
			con.close();
			System.out.println("Connection closed" + information.size());
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	private void getinformation() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		String SQL;
				
		connectDB();
		System.out.println("Connection established");

		SQL = "select CID,Name,PhoneNumber from Customer order by CID";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);


		while ( rs.next() ) 
			information.add(new Customer(
					Double.parseDouble(rs.getString(1)),
					rs.getString(2),
					rs.getString(3)));
		
		rs.close();
		stmt.close();

		con.close();
		System.out.println("Connection closed" + information.size());
		
		
	}


	private void connectDB() throws ClassNotFoundException, SQLException {
		
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");
	
		con = DriverManager.getConnection (dbURL, p);

	}


	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();		 
		}
		catch(SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");	  
		}
		
		
	}
	
	public void updateName(double CID, String Name) {
				
		try {
			System.out.println("update  student set sname = '"+Name + "' where snum = "+CID);
			connectDB();
			ExecuteStatement("update  student set sname = '"+Name + "' where snum = "+CID+";");
			con.close();
			System.out.println("Connection closed");
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public void updatePhone(double CID, String PhoneNumber) {
		
		try {
			System.out.println("update  student set age = "+PhoneNumber + " where snum = "+CID);
			connectDB();
			ExecuteStatement("update  student set age = "+PhoneNumber + " where snum = "+CID+";");
			con.close();
			System.out.println("Connection closed");
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}


	private void deleteRow(Customer row) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("delete from  Customer where snum="+row.getCID() + ";");
			connectDB();
			ExecuteStatement("delete from Customer where snum="+row.getCID() + ";");
			con.close();
			System.out.println("Connection closed");
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	private void showDialog(Window owner, Modality modality,   TableView<Customer> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
	//	Label modalityLabel = new Label(modality.toString());
		
		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Customer row:informationList) {
        			deleteRow(row);
        			table.refresh();
        		}
        		table.getItems().removeAll(informationList);
    			stage.close();

			});
		
		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
		}
}


