/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import general_db_interface.Client_Interface;
import general_db_interface.PChat_Interface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objRmi.client.Client;
import objRmi.massage.privtechat.PrivateChat;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Hesham
 */
public class PrivateChatDAO implements PChat_Interface {

    //Class Variable's Declaration \\
    int pId;
    String sendMail = " ? ", receiveMail = " ? ";
    Connection con;
    PreparedStatement prepStat;
    ResultSet rs;
    String searchForId;
    String insertId;
    String getClientData;
    String createTable;
    String test;
    String deleteFromTable;
    byte[] imgPath;
    String createMapTable;
    String alterStat, seqStat;
    boolean addChatFlag, createTableFlag, deleteRowFlag, mappingFlag;
    ClientDao myClientDao;
    Client client;

    // SQL Statements and keywords \\
    final static String SELECT = " Select ";
    final static String FROM = " from ";
    final static String ALL = " * ";
    final static String CLEINT_PRIVTE_TABLE_NAME = " client_privte ";
    final static String P_ID = " Pid ";
    final static String SEND_MAIL = " Smail ";
    final static String REC_MAIL = " Rmail ";
    final static String WHERE = " Where ";
    final static String AND = " AND ";
    final static String EQUAL = " = ";
    final static String SEMI = " ; ";
    final static String INSERT = " insert ";
    final static String INTO = " into ";
    final static String INTEGER = " integer ";
    final static String INT = " int ";
    final static String PRIMARY_KEY = " primary key ";
    final static String INCREMENT = " INCREMENT ";
    final static String NOT_NULL = " not null ";
    final static String TEXT = " text ";
    final static String CREATE = " create ";
    final static String TABLE = " table ";
    final static String NUMBER = " NUMBER(10) ";
    final static String VARCHAR2 = " VARCHAR2(50) ";
    final static String VALUES = " values ";
    final static String ALTER = " alter ";
    final static String ADD = " add ";
    final static String CONSTRAINT = " CONSTRAINT ";
    final static String SEQUENCE = " SEQUENCE ";
    final static String START = " START ";
    final static String WITH = " WITH ";
    final static String DELETE = " delete ";
    final static String UPDATE = " update ";
    final static String SET = " set ";
    final static String FOREIGN_KEY = " FOREIGN KEY ";
    final static String REFERENCES = " REFERENCES ";
    final static String CACHE = " CACHE ";
    final static String BY = " BY ";
    final static String AUTO_INCR_KEY = "fdsfsfds";

    /**
     *
     * @param sMail sender mail
     * @param rMail receiver mail
     * @return id of the private chat of these clients
     */
    @Override
    public int searchPId(String sMail, String rMail) {

        try {
            searchForId = SELECT + P_ID + FROM + CLEINT_PRIVTE_TABLE_NAME
                    + WHERE + SEND_MAIL + EQUAL + " '" + sMail + "' " + AND + REC_MAIL + EQUAL + " '" + rMail + "' ";
            generateDB();
            executeMyQuery(searchForId);
            rs.next();
            pId = rs.getInt("PID");
            closeDB();
        } catch (Exception e) {
            System.out.println(searchForId);
            System.out.println("Catch from searchPId implementation in PrivateChatDAO");
            e.printStackTrace();
            pId = -1;
        }

        return pId;
    }

    /**
     *
     * @param sMail sender mail
     * @param rMail receiver mail
     * @return boolean to check if the row added to the database
     */
    @Override
    public boolean addPChat(String sMail, String rMail) {
        try {

            insertId = INSERT + INTO + CLEINT_PRIVTE_TABLE_NAME + " ( " + P_ID + " , "
                    + SEND_MAIL + " , " + REC_MAIL + " ) " + VALUES + " ( " + AUTO_INCR_KEY + ".nextval " + " , '" + sMail + "' , '"
                    + rMail + "' ) " + "  ";

            generateDB();
            executeMyQuery(insertId);
            addChatFlag = true;
            closeDB();

        } catch (Exception e) {
            System.out.println("Catch from addPCaht(String SMail , String RMail) implementation in PrivateChatDAO");
            e.printStackTrace();
            addChatFlag = false;
        }
        return addChatFlag;
    }

    
    @Override
    public PrivateChat getPChat(int pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param rMail receiver mail
     * @return array of bytes of user
     */
    @Override
    public byte[] viewImg(String rMail) {

        try {

            myClientDao = ClientDao.getDeflutClientDoa();
            client = myClientDao.getCurrentClient(rMail);
            imgPath = client.getImage();
        } catch (Exception e) {
            System.out.println("Catch from viewImg(String RMail) implementation in PrivateChatDAO Check ClientDAO");
            e.printStackTrace();
        }

        return imgPath;
    }

    /**
     *
     * @param t privateChat object from general interface
     * @return boolean to check if table created or not
     */
    @Override
    public boolean createTable(PrivateChat t) {

        try {
            test = SELECT + ALL + FROM + CLEINT_PRIVTE_TABLE_NAME + WHERE + P_ID + EQUAL + 1;
            generateDB();
            executeMyQuery(test);
            rs.next();
            String senderMail = rs.getString("SMAIL");
            System.out.println("Try from createTable(PrivateChat t) implementation "
                    + "in PrivateChatDAO\n*TABLE EXISTS* \n*SENDER MAIL = " + senderMail);
            createTableFlag = true;
            closeDB();

        } catch (Exception e) {
            System.out.println(test);
            System.out.println("Catch from  createTable(PrivateChat t) implementation in PrivateChatDAO");
            e.printStackTrace();
            createTableFlag = false;
        }

        if (!createTableFlag) {

//                            create  table  client_privte  
//                    (Pid  int  not null  ,  Smail  VARCHAR2(50)   
//               ,  Rmail  VARCHAR2(50) , constraint pvt_pk primary key(Pid) 
//               , constraint send_fk foreign key(Smail) references clients(mail)  
//                 , constraint rec_fk foreign key(Rmail) references clients(mail))
            createTable = CREATE + TABLE + CLEINT_PRIVTE_TABLE_NAME + " ( "
                    + P_ID + " int " + NOT_NULL + " , " + SEND_MAIL + VARCHAR2
                    + " , " + REC_MAIL + VARCHAR2 + " , " + CONSTRAINT + " pvt_pk " + PRIMARY_KEY + " ( " + P_ID + " ) "
                    + " , " + CONSTRAINT + " send_fk " + FOREIGN_KEY + " (" + SEND_MAIL + ") " + REFERENCES
                    + Client_Interface.CLEINT_TABLE_NAME + " (" + Client_Interface.MAIL + ") " + " , "
                    + CONSTRAINT + " rec_fk " + FOREIGN_KEY + " (" + REC_MAIL + ") " + REFERENCES
                    + Client_Interface.CLEINT_TABLE_NAME + " (" + Client_Interface.MAIL + ") " + ")";

            seqStat = CREATE + SEQUENCE + AUTO_INCR_KEY + START + WITH + 1 + INCREMENT + BY + 1 + CACHE + 1000;

            generateDB();
            executeMyQuery(createTable);
            closeDB();

            boolean testMap = createMappingTables();
            if (testMap) {
                System.out.println("From  createTable(PrivateChat t) "
                        + "implementation in PrivateChatDAO\n*TABLE CREATED*");
                createTableFlag = true;
            }
        }

        return createTableFlag;
    }

    /**
     *
     * @param t PrivateChat object (general interface)
     * @return boolean to check if the update done correctively
     */
    @Override
    public boolean updateTable(PrivateChat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //        UPDATE Customers
        //        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
        //        WHERE CustomerID = 1;
    }

    /**
     *
     * @param t PrivateChat object
     * @return boolean to check if the row deleted correctively
     */
    @Override
    public boolean deleteRow(PrivateChat t) {
        try {
            deleteFromTable = DELETE + FROM + CLEINT_PRIVTE_TABLE_NAME
                    + WHERE + P_ID + EQUAL + t.getId();
            generateDB();
            executeMyQuery(deleteFromTable);
            closeDB();
            deleteRowFlag = true;
            System.out.println("Try from  deleteRow(PrivateChat t) implementation in PrivateChatDAO\n"
                    + "*ROW DELETED WHERE PID = " + t.getId() + "*");
        } catch (Exception e) {
            System.out.println(deleteFromTable);
            System.out.println("Catch from  deleteRow(PrivateChat t) implementation in PrivateChatDAO");
            e.printStackTrace();
            deleteRowFlag = false;
        }
        return deleteRowFlag;
    }

    /**
     *
     * @param t PrivateChat object (from general interface implementation)
     * @return boolean
     */
    @Override
    public boolean InsertRow(PrivateChat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return boolean to check if the mapping table created correctively
     */
    public boolean createMappingTables() {
        boolean test = false;
        try {
            createMapTable = CREATE + TABLE + Client_Interface.CLEINT_PRIVTE_Contact_TABLE_NAME + " ( "
                    + P_ID + INT + " , "
                    + SEND_MAIL + VARCHAR2 + " , " + CONSTRAINT + " pvt_fk " + FOREIGN_KEY + " (" + P_ID + ") " + REFERENCES
                    + CLEINT_PRIVTE_TABLE_NAME + " (" + P_ID + ") " + " , "
                    + CONSTRAINT + " sender_fk " + FOREIGN_KEY + " (" + SEND_MAIL + ") " + REFERENCES
                    + Client_Interface.CLEINT_TABLE_NAME + " (" + Client_Interface.MAIL + ") " + " , " + CONSTRAINT
                    + " client_pvt_pk " + PRIMARY_KEY + " (" + P_ID + " , " + SEND_MAIL + ") " + "  ) ";

            generateDB();
            executeMyQuery(createMapTable);
            closeDB();
            System.out.println("FROM createMappingTables() MAPPING \n*TABLE CREATED*");
            test = true;
        } catch (Exception e) {
            System.out.println(createMapTable);
            System.out.println("Catch from  createMappingTables() in PrivateChatDAO");
            test = false;
        }
        return test;
    }

    // generate database method \\
    /**
     * this method to start connection to oracle database
     */
    public void generateDB() {

        try {
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch from generateDB() in PrivateChatDAO");
        }
    }

    /////\\\\\
    // close the connection to the database \\
    /**
     * this function is to close connection with database
     */
    public void closeDB() {
        try {
            rs.close();
            prepStat.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Catch from closeDB() in PrivateChatDAO");
            ex.printStackTrace();
        }
    }

    /////\\\\\
    // this method is to execute Query \\
    /**
     *
     * @param query String contains statement which I want to execute
     */
    public void executeMyQuery(String query) {
        try {
            prepStat = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            rs = prepStat.executeQuery();

            if (query.equals(createTable)) {
                executeMyQuery(seqStat);
            }

        } catch (SQLException ex) {
            System.out.println(query);
            System.out.println("Catch from executeMyQuery(String Query) in PrivateChatDAO");
            ex.printStackTrace();
        }

    }

    /////\\\\\
}
