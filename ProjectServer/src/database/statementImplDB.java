package database;

import general_db_interface.Client_Interface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;




/**
 *
 * @author Aya
 */
public class statementImplDB {
    

    /**
     * @param args the command line arguments
     */
//    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String DB_USER = "system";
//    private static final String DB_PASSWORD = "aya1621995";
       static Connection con;
        static ResultSet resSet;
       static String createTableSQL =" CREATE TABLE " + Client_Interface.CLEINT_PRIVTE_TABLE_NAME +"(" 
				+ Client_Interface.PID +" NUMBER(5) NOT NULL," 
				+ Client_Interface.SenEMAIL + " VARCHAR(20) NOT NULL ,"
				+ Client_Interface.RecEMAIL + " VARCHAR(20) NOT NULL,"
				+ " PRIMARY KEY ( " + Client_Interface.PID  + ")"
                                + " FOREIGN KEY ( "+ Client_Interface.SenEMAIL + " , " + Client_Interface.RecEMAIL + " ) "
                                + " REFERENCES " + Client_Interface.CLEINT_TABLE_NAME+ "( " + Client_Interface.EMAIL+ "," + Client_Interface.EMAIL + ")"
                                + ")";
        
            
       
//       static String createTableSQL2 = "CREATE TABLE"+ Client_Interface.CLEINT_PRIVTE_Contact_TABLE_NAME +"("
//            + Client_Interface.PId +"NUMBER(5) NOT NULL,"
//            + Client_Interface.SendEMAIL +"VARCHAR(20) NOT NULL,"
//            +"FOREIGN KEY"+ "("+Client_Interface.PId+")"
//            +"REFERENCES"+Client_Interface.CLEINT_PRIVTE_TABLE_NAME+"("+Client_Interface.PID +")"
//            + "PRIMARY KEY"+"("+Client_Interface.PId +","+Client_Interface.SendEMAIL +")"   
//            + ")";
       
//       static String cc = "CREATE SCHEMA AUTHORIZATION system myschma ";
//       static String c= "create user ahmed identified by 1234";
//       static String c2 = "grant dba,resource, connect to ahmed";
//       static String c3 = "connect ahmed 1234";
    static Statement stm;

    /**
     *this do this
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        getDBConnection();
	
    }
//    private static void createDbUserTable() throws SQLException {
//
//		Connection dbConnection = null;
//		Statement statement = null;
//
//		
//
//
//	}

	private static void getDBConnection() {
            

        try {
            DriverManager.registerDriver(new OracleDriver());
            con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","aya1621995");
            stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stm.execute(createTableSQL);
//            stm.execute(createTableSQL2);
            
            //stm.execute(c);
            //stm.execute(c2);
            //stm.execute(c3);
            //resSet=stm.executeQuery("select * from ahmed");
        } catch (SQLException ex) {
            Logger.getLogger(statementImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    
}
