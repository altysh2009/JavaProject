/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dtopkg.UserDTO;
import general_db_interface.*;
import static general_db_interface.Client_Interface.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objRmi.GroupChat.GroupChat;
import objRmi.Reqests.RequsetsObj;
import objRmi.client.Client;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author ahmed
 */
public class ClientDao implements Client_Interface {

    Connection con;
    private static ClientDao clientDao = null;

    public ClientDao(String username, String password) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
        if (con != null) {
            this.con = con;
            clientDao = this;
            createTable(new UserDTO());
        }
        //System.out.println("not null");

    }

    public static ClientDao getDeflutClientDoa() throws SQLException {
        if (clientDao != null) {
            return clientDao;
        } else {
            throw new SQLException();
        }
    }

    /**
     *
     * @param con the data base connection
     */
    public void setCon(Connection con) {
        this.con = con;
    }
    private final String select = "SELECT ";
    private final String astrek = " * ";
    private final String form = " FROM ";
    private final String where = " where ";
    private final String update = "UPDATE ";
    private final String set = " SET ";
    //error prone
    private final String getClient = select + astrek + form + CLEINT_TABLE_NAME + where + EMAIL + " = " + " ? ";
    private final String loginS = getClient + " and " + PASSWORD + " = " + " ? " + "";
    private final String user = select + astrek + form + CLEINT_TABLE_NAME + where + EMAIL + " = " + " ? " + "";
    private final String forgetPassword = select + PASSWORD + form + CLEINT_TABLE_NAME + where + EMAIL + " = " + " ? " + " and " + ANSWER + " = " + " ? " + " and " + SECURTYQUTTION + " ? ";
    private final String getAll = select + astrek + form + CLEINT_TABLE_NAME;
    private final String getContacts = select + astrek + form + CLEINT_TABLE_NAME + where + MAIL + " in "
            + " ( " + select + C_MAIL + form + CLEINT_CONTACT_TABLE_NAME + where + EMAIL + " =  ? ) or "
            + MAIL + " in " + " ( " + select + MAIL + form + CLEINT_CONTACT_TABLE_NAME + where + C_MAIL + " =  ? ) ";

    private final String updatestatuse = update + CLEINT_TABLE_NAME + set + STATUS + " = "
            + " ? " + where + EMAIL + " = " + " ? ";
    private final String getstate = select + astrek + form + CLEINT_TABLE_NAME + where + EMAIL + " = " + " ? ";

    private final String updateOnStatuse = update + CLEINT_TABLE_NAME + set + ONSTATUS + " = "
            + " ? " + where + EMAIL + " = " + " ? ";
    private final String getOnStatus = select + ONSTATUS + form + CLEINT_TABLE_NAME + where + EMAIL + " = " + " ? ";

    private final String getGroups = select + astrek + form + CLEINT_GROUP_TABLE_NAME + where + GID
            + " IN (" + select + GID + form + CLIENT_GROUPS + where + CLIENT_MAil + " = " + " ? )";
    private final String getGroupMembers = select + astrek + form + CLEINT_CONTACT_TABLE_NAME + where + EMAIL + " IN ( " + select + GROUP_CONTEACT_ID + form
            + GROUP_MEMBERS + where + GROUP_CHAT_ID + " = " + " ? );";
    private final String changeProfile = update + CLEINT_TABLE_NAME + set + GENDER + "= ? ," + COUNTRY + "= ? ," + NAME + "= ? ," + PASSWORD
            + "= ? ," + STATUS + "= ? ," + PICTURE + "= ? ," + ANSWER + "= ? " + where + EMAIL + "= ? ";
    private final String insert = " INSERT INTO ";
    private final String values = " VALUES ";
    private String squance = "contact_sequence.nextval";
    private final String addFriend = insert + " . " + " ( " + EMAIL + " , " + C_MAIL + " ) " + values
            + "( " + " ? ," + " ? " + "  )";
    private final String delete = "DELETE FROM ";
    private final String deleteFriend = delete + " . " + where + " ( " + EMAIL + " = " + " ? " + " AND " + C_MAIL + " = " + " ? ) or " + " ( " + EMAIL + " = " + " ? " + " AND " + C_MAIL + " = " + " ? )";

    private final String insertClient = insert + CLEINT_TABLE_NAME
            + " ( " + EMAIL + " , " + STATUS + " , " + GENDER + " , " + COUNTRY + " , " + NAME + " , " + PASSWORD + " , "
            + ONSTATUS + " , " + PICTURE + " , " + SECURTYQUTTION + " , " + ANSWER + " , " + LASTSEEN + " ) "
            + values + " (  ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? )";
    private final String create = "CREATE TABLE ";
    private final String charcter = " VARCHAR(40) ";
    private final String number = " NUMBER(10) ";
    private final String notNull = " NOT NULL ";
    private final String bool = " BOOLEAN ";
    private final String getBlockedList = select + astrek + form + CLEINT_TABLE_NAME + where + EMAIL + " in "
            + " ( " + select + B_MAIL + form + CLIENT_BLOCKED_PEAPLED + where + EMAIL + " =  ? ) or "
            + EMAIL + " in " + " ( " + select + MAIL + form + CLIENT_BLOCKED_PEAPLED + where + B_MAIL + " =  ? ) ";
    private final String createClient = create + CLEINT_TABLE_NAME + " ( " + EMAIL + charcter + notNull + ", " + COUNTRY + charcter + notNull + ", "
            + STATUS + charcter + notNull + " , " + GENDER + number + notNull + " , " + NAME + charcter + notNull + " , "
            + PASSWORD + charcter + notNull + " , " + ONSTATUS + number + " , " + PICTURE + " blob " + notNull + " , "
            + SECURTYQUTTION + charcter + notNull + " , " + ANSWER + charcter + notNull + " , " + LASTSEEN + " TIMESTAMP " + " ,PRIMARY KEY( " + EMAIL + " ) )";
    private final String createClientContacts = create + CLEINT_CONTACT_TABLE_NAME + " ( " + MAIL + charcter + notNull + " , " + C_MAIL + charcter + notNull
            + ",PRIMARY KEY( " + MAIL + "," + C_MAIL + " ), "
            + " FOREIGN KEY ( " + C_MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) ," + " FOREIGN KEY ( " + EMAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) " + " )";

    private final String createClientBLOCEKUser = create + CLIENT_BLOCKED_PEAPLED + " ( " + EMAIL + charcter + notNull + " , " + B_MAIL + charcter + notNull
            + ",PRIMARY KEY( " + EMAIL + "," + B_MAIL + " ), "
            + " FOREIGN KEY ( " + B_MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) ," + " FOREIGN KEY ( " + EMAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) " + " )";

    private String squ = "Create sequence contact_sequence start with 1 increment by 1 minvalue 1 maxvalue 1000000";
    private final String createClientMointer = create + USER_LOGIN_TABLE_NAME + " ( " + MAIL + charcter + notNull + " , " + LOGIN_T + " TIMESTAMP " + notNull + " , "
            + SIGNOUT_t + " TIMESTAMP " + ",PRIMARY KEY( " + MAIL + "," + LOGIN_T + " ) , "
            + " FOREIGN KEY ( " + MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) " + " )";
    private final String createClientSendReq = create + USER_SEND_REQ_TABLE_NAME + " ( " + MAIL + charcter + notNull + " , " + RMAIL + charcter + notNull + " , " + DATE + " TIMESTAMP " + notNull + ",PRIMARY KEY( " + MAIL + " , " + RMAIL + " , " + DATE + " ) , "
            + " FOREIGN KEY ( " + RMAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) , " + " FOREIGN KEY ( " + MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) " + " )";
    private final String createClientRecivedReq = create + USER_RECIVED_REQ_TABLE_NAME + " ( " + MAIL + charcter + notNull + " , " + SEND_MAIL + charcter + notNull + " , " + DATE + " TIMESTAMP " + notNull + ",PRIMARY KEY( " + MAIL + "," + SEND_MAIL + " , " + DATE + " ) , "
            + " FOREIGN KEY ( " + SEND_MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) ," + " FOREIGN KEY ( " + MAIL + " ) " + " REFERENCES " + CLEINT_TABLE_NAME + " ( " + EMAIL + " ) " + " )";

    private final String getOnlineClients = select + NAME + form + CLEINT_TABLE_NAME + where + ONSTATUS + " = ? ";

    /**
     * Tested
     *
     * @param mail the input email
     * @param password the input password
     * @return Correct login info or not
     */
    @Override
    public boolean login(String mail, String password) {
        PreparedStatement stm = null;
        try {

            stm = con.prepareStatement(loginS);
            stm.setString(1, mail);
            stm.setString(2, password);
            if (stm.executeQuery().next()) {
                stm.close();
                return true;
            }
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    /**
     *
     *
     *
     * @return return all the available client
     */
    @Override
    public ArrayList<Client> getOnlineList() {
        ArrayList<Client> clients = new ArrayList<>();

        ResultSet resSet = null;
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(getOnlineClients);

            stm.setInt(1, 1);
            resSet = stm.executeQuery();
            while (resSet.next()) {
                clients.add(new Client(resSet.getString(NAME), null, null,
                        true, null));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return clients;
    }

    /**
     * Tested return basic info any user
     *
     * @param mail the mail of the user
     * @return return null if the user not found else it return a object of
     * objRmi.client.Client
     */
    //error prone
    @Override
    public Client getCurrentClient(String mail) {
        Client client = null;
        ResultSet resSet = null;
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(getClient);

            stm.setString(1, mail);
            resSet = stm.executeQuery();
            if (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int iterator = 0;
                while ((iterator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, iterator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return client;
    }

    /**
     *
     * @param mail the user's Email
     * @return user the user object from dto filled with the data .
     */
    public UserDTO getUserDTO(String mail) {
        UserDTO user = null;
        ResultSet resSet = null;
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(getClient);

            stm.setString(1, mail);
            resSet = stm.executeQuery();
            if (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int iterator = 0;
                while ((iterator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, iterator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                user = new UserDTO(resSet.getString(EMAIL), resSet.getInt(ONSTATUS), resSet.getInt(GENDER), resSet.getString(COUNTRY),
                        resSet.getString(NAME), resSet.getString(PASSWORD), resSet.getString(STATUS), bytes,
                        resSet.getString(SECURTYQUTTION), resSet.getString(ANSWER));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    /**
     * Tested
     *
     * @param mail user email
     * @param answer Quation Answer
     * @param question sequrity Quation
     * @return the password of the client depends on the answer of the question
     * he entered
     */
    @Override
    public String forgetPassword(String mail, String answer, String question) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        String password = "NULL";
        try {

            stm = con.prepareStatement(forgetPassword);

            stm.setString(1, mail);
            stm.setString(2, answer);
            stm.setString(3, question);
            resSet = stm.executeQuery();
            if (resSet.next()) {
                password = resSet.getString(PASSWORD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return password;
    }

    /**
     * @param mail user email
     * @param query the query statement
     * @return clients ArrayList of users match with that email
     */
    private ArrayList<Client> getAvailableUsers(String query, String mail) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<Client> clients = new ArrayList<>();
        try {
            stm = con.prepareStatement(query);
            if (mail != null) {
                stm.setString(1, mail);
                stm.setString(2, mail);

            }
            resSet = stm.executeQuery();

            while (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int terator = 0;
                while ((terator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, terator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                clients.add(new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }

    /**
     * Tested
     *
     * @return return all the available clients in array list
     */
    @Override
    public ArrayList<Client> getAvailableUsers() {
        return getAvailableUsers(getAll, null);
    }

    /**
     *
     * @param mail the email of the user
     * @return the full friend list with the block status
     */
    @Override
    public ArrayList<Client> getContactList(String mail) {
        return getAvailableUsers(getContacts, mail);
    }

    /**
     * Tested
     *
     * @param mail the user email
     * @param status the status of the user
     */
    @Override
    public void setStatus(String mail, String status) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        try {

            stm = con.prepareStatement(updatestatuse);
            stm.setString(1, status);
            stm.setString(2, mail);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Tested
     *
     * @param mail the user email
     * @return Client obj that contains the status of the
     * client(available-busy-outdoor)
     */
    @Override
    public Client getStatus(String mail) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        Client client = null;
        try {

            stm = con.prepareStatement(getstate);
            stm.setString(1, mail);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int iterator = 0;
                while ((iterator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, iterator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return client;
    }

    /**
     *
     * @param mail the user's E-mail
     * @return groups array of user's groups
     */
    @Override
    public ArrayList<objRmi.GroupChat.GroupChat> getGroupList(String mail) {

        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<GroupChat> groups = new ArrayList<>();
        try {

            stm = con.prepareStatement(getGroups);
            stm.setString(1, mail);

            resSet = stm.executeQuery();
            while (resSet.next()) {
                groups.add(new GroupChat(resSet.getString(GNAME), resSet.getString(GADMIN), resSet.getInt(GID), resSet.getString(GIMAGE), new ArrayList<Client>()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

    /**
     *
     * @param id the group id
     * @return clients array list of the group members matches this id
     */
    @Override
    public ArrayList<Client> getGroupMemeber(int id) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<Client> clients = new ArrayList<>();
        try {

            stm = con.prepareStatement(getGroups);
            stm.setInt(1, id);

            resSet = stm.executeQuery();
            while (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int iterator = 0;
                while ((iterator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, iterator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                Client client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));
                clients.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }

    /**
     * Tested
     *
     * @param client object of Dto contain all the data about the user to access
     * the user's data and set his profile picture
     */
    @Override
    public void changeProfile(UserDTO client) {
        PreparedStatement stm = null;

        try {
            Blob b = con.createBlob();
            b.setBinaryStream(0).write(client.GetUserPic());
            stm = con.prepareStatement(changeProfile);
            stm.setInt(1, client.GetUserGender());
            stm.setString(2, client.GetUserCountry());
            stm.setString(3, client.GetUserName());
            stm.setString(4, client.GetUserPassword());
            stm.setString(5, client.GetUserStatus());
            stm.setBlob(6, b);
            stm.setString(7, client.GetUserAnswer());
            stm.setString(8, client.GetUserEmail());

            int iterator = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Tested
     *
     * @param clientMail the email of the client
     * @param friendMail email of the other friend this function set the
     * friendMail into the friend request list of the clientMail
     *
     */
    @Override
    public void AddFriend(String clientMail, String friendMail) {
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(addFriend.replace(".", CLEINT_CONTACT_TABLE_NAME));
            stm.setString(1, clientMail);
            stm.setString(2, friendMail);

            stm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Tested
     *
     * @param clientMail the client email
     * @param friendMail the friend he want to delete this function remove the
     * friendMail from the friend request list at clientMail
     */
    @Override
    public void removeFriend(String clientMail, String friendMail) {

        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(deleteFriend.replace(".", CLEINT_CONTACT_TABLE_NAME));
            //stm.setString(1, CLEINT_CONTACT_TABLE_NAME);
            stm.setString(1, clientMail);
            stm.setString(2, friendMail);
            stm.setString(4, clientMail);
            stm.setString(3, friendMail);

            stm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param clientMail the user's E-mail
     * @param friendMail the friend's E-mail
     */
    @Override
    public void blockFriend(String clientMail, String friendMail) {
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(addFriend.replace(".", CLIENT_BLOCKED_PEAPLED));
            stm.setString(1, clientMail);
            stm.setString(2, friendMail);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param clientMail the user's E-mail
     * @param friendMail the friend's E-mail
     */
    @Override
    public void unblockFriend(String clientMail, String friendMail) {
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(deleteFriend.replace(".", CLIENT_BLOCKED_PEAPLED));
            stm.setString(1, clientMail);
            stm.setString(2, friendMail);
            stm.setString(4, clientMail);
            stm.setString(3, friendMail);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param table object of the dto to access the data about user
     * @return true if it created the table
     */
    @Override
    public boolean createTable(UserDTO table) {
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(createClient);
            stm.execute();

            stm = con.prepareStatement(createClientBLOCEKUser);
            stm.execute();
            stm = con.prepareStatement(createClientContacts);

            stm.execute();

            con.prepareStatement(createClientRecivedReq).execute();
            con.prepareStatement(createClientSendReq).execute();
            con.prepareStatement(createClientMointer).execute();
            return true;

        } catch (SQLException ex) {
            //Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tables already exit");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean updateTable(UserDTO table) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteRow(UserDTO table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param client object from the user dto to insert new record in the Client
     * table
     * @return if the row inserted or not
     */
    @Override
    public boolean InsertRow(UserDTO client) {

        PreparedStatement stm = null;
        ResultSet resSet = null;
        if (client == null) {
            System.out.println("nuuull");
        }
        ArrayList<Client> clients = new ArrayList<>();
        try {
            Blob b = con.createBlob();
           // System.out.println(client.GetUserPic().length);
            //b.setBinaryStream(client.GetUserPic().length).write(client.GetUserPic());
            //b.setBytes(0, client.GetUserPic());
            stm = con.prepareStatement(insertClient);
            stm.setString(1, client.GetUserEmail());
            stm.setString(2, client.GetUserStatus());
            stm.setInt(3, client.GetUserGender());
            stm.setString(4, client.GetUserCountry());
            stm.setString(5, client.GetUserName());
            stm.setString(6, client.GetUserPassword());
            stm.setInt(7, client.GetUserStatusOnOff());
            stm.setBinaryStream(8, new ByteArrayInputStream(client.GetUserPic()), client.GetUserPic().length);
            stm.setString(9, client.GetUserSequrityQues());
            stm.setString(10, client.GetUserAnswer());
            stm.setTimestamp(11, new Timestamp(client.GetUserLastSeen()));

            stm.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param clientMail the user's E-mail
     * @return array list contains the blocked users
     */
    @Override
    public ArrayList<Client> getBlockedList(String clientMail) {
        return getAvailableUsers(getBlockedList, clientMail);
    }

    /**
     *
     * @param senderMail the user's E-mail
     * @param person the friend's E-mail this function set the senderMail into
     * the friend request table of the person
     */
    @Override
    public void addSendReq(String senderMail, String person) {
        String send = insert + USER_SEND_REQ_TABLE_NAME + " ( " + MAIL + " , " + RMAIL + " , " + DATE + " ) " + values + " ( " + " ? , ? , ? )";
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(send);
            stm.setString(1, senderMail);
            stm.setString(2, person);

            stm.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param mail the user's E-mail
     * @param person the friend's E-mail this function removes the senderMail
     * from the friends list table of the person
     */
    public void removeReq(String mail, String person) {
        String s = delete + USER_RECIVED_REQ_TABLE_NAME + where + " ( " + EMAIL + " = '" + mail + "' and " + SEND_MAIL + " = '" + person + "' )" + " or " + " ( " + EMAIL + " = '" + mail + "' and " + SEND_MAIL + " = '" + person + "' )";
        String ss = delete + USER_SEND_REQ_TABLE_NAME + where + " ( " + EMAIL + " = '" + mail + "' and " + RMAIL + " = '" + person + "' )" + " or " + " ( " + EMAIL + " = '" + mail + "' and " + RMAIL + " = '" + person + "' )";
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(s);

            stm.execute();
            con.prepareStatement(ss).execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param mail the user's e-mail
     * @param person the other user's E-mail
     *
     */
    @Override
    public void addRecReq(String mail, String person) {
        String send = insert + USER_RECIVED_REQ_TABLE_NAME + " ( " + MAIL + " , " + SEND_MAIL + " , " + DATE + " ) " + values + " ( " + " ? , ? , ? )";
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(send);
            stm.setString(1, mail);
            stm.setString(2, person);

            stm.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param mail
     * @return ArrayList of Requsets sent by that mail
     */
    @Override
    public ArrayList<RequsetsObj> getSendReq(String mail) {
        String sele = select + " c." + astrek + " , u." + DATE + form + CLEINT_TABLE_NAME + " c ," + USER_SEND_REQ_TABLE_NAME + " u " + where + "c." + EMAIL + " in "
                + " ( " + select + RMAIL + form + USER_SEND_REQ_TABLE_NAME + where + "u." + this.MAIL + " =  ? )";
        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<RequsetsObj> clients = new ArrayList<>();
        try {

            stm = con.prepareStatement(sele);
            stm.setString(1, mail);

            resSet = stm.executeQuery();
            while (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while ((n = in.read(buf)) >= 0) {
                    baos.write(buf, 0, n);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                Client client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));
                clients.add(new RequsetsObj(client, new java.util.Date().getTime()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }

    /**
     *
     * @param mail
     * @return Array requests recived by that mail
     */
    @Override
    public ArrayList<RequsetsObj> getRecReq(String mail) {
        String sele = "SELECT distinct *  from CLIENTS  where MAIL in (select MAIL from CLIENT_RECIVED_REQ  where SMAIL = ? )";
        //System.out.println(sele);
        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<RequsetsObj> clients = new ArrayList<>();
        try {

            stm = con.prepareStatement(sele);
            stm.setString(1, mail);

            resSet = stm.executeQuery();
            while (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while ((n = in.read(buf)) >= 0) {
                    baos.write(buf, 0, n);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                Client client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));
                clients.add(new RequsetsObj(client, new java.util.Date().getTime()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }

    /**
     *
     * @return the number of all users
     */
    @Override
    public long getClientCount() {
        String cout = select + " COUNT(" + EMAIL + ") as count " + form + CLEINT_TABLE_NAME;
        PreparedStatement stm = null;
        ResultSet resSet = null;
        long ncout = 0;
        try {

            stm = con.prepareStatement(cout);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                ncout = resSet.getLong("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ncout;
    }

    /**
     *
     * @return the number of all online users
     */
    @Override
    public long getOnlineClient() {
        String cout = select + " COUNT(" + ONSTATUS + ") as count " + form + CLEINT_TABLE_NAME + where + ONSTATUS + " = " + " ? ";
        PreparedStatement stm = null;
        ResultSet resSet = null;
        long ncout = 0;
        try {

            stm = con.prepareStatement(cout);
            stm.setInt(1, 1);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                ncout = resSet.getLong("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ncout;
    }

    /**
     *
     * @return the number of the male users
     */
    @Override
    public long getMale() {
        String cout = select + " COUNT(" + GENDER + ") as count " + form + CLEINT_TABLE_NAME + where + ONSTATUS + " = " + " ? ";
        PreparedStatement stm = null;
        ResultSet resSet = null;
        long ncout = 0;
        try {

            stm = con.prepareStatement(cout);
            stm.setInt(1, 1);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                ncout = resSet.getLong("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ncout;
    }

    /**
     *
     * @return the number of female users
     */
    @Override
    public long getFemale() {
        String cout = select + " COUNT(" + ONSTATUS + ") as count " + form + CLEINT_TABLE_NAME + where + ONSTATUS + " = " + " ? ";
        PreparedStatement stm = null;
        ResultSet resSet = null;
        long ncout = 0;
        try {

            stm = con.prepareStatement(cout);
            stm.setInt(1, 0);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                ncout = resSet.getLong("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ncout;
    }

    /**
     *
     * @param email check if this email in the client table so it is authorized
     * to access the application
     */
    @Override
    public void signIn(String email) {
        String signIn = insert + USER_LOGIN_TABLE_NAME + " ( " + MAIL + " , " + LOGIN_T + " ) " + values + " ( ? , ?  )";
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(signIn);
            stm.setString(1, email);
            stm.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param email the email of the user to be removed from the user login
     */
    @Override
    public void signOut(String email) {
        String signIn = update + USER_LOGIN_TABLE_NAME + set + SIGNOUT_t + " = ? " + where + MAIL + " = ? " + " and " + SIGNOUT_t + " IS NULL ";
        PreparedStatement stm = null;

        try {

            stm = con.prepareStatement(signIn);
            //stm.setDate(3, null);
            stm.setString(2, email);
            stm.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            //stm.setString(1, CLIENT_BLOCKED_PEAPLED);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @return average
     */
    @Override
    public long getAvgUseageTime() {

        String s = select + " avg(" + "to-number (" + SIGNOUT_t + " - " + LOGIN_T + " )" + " ) as dd" + form + USER_LOGIN_TABLE_NAME;
        PreparedStatement stm = null;
        ResultSet res = null;
        try {

            stm = con.prepareStatement(s);
            //stm.setDate(3, null);
            //stm.setString(2, email);
            //stm.setDate(1, new Date(new java.util.Date().getTime()));
            //stm.setString(1, CLIENT_BLOCKED_PEAPLED);

            res = stm.executeQuery();
            if (res.next()) {
                return res.getTimestamp(DATE).getTime();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                    if (res != null) {
                        res.close();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    /**
     *
     * @return the customer that are using the app above the average
     */
    @Override
    public ArrayList<Client> getLoyalCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param mail the user's E-mail
     * @param status the user's status (on - off) change the user status
     */
    @Override
    public void setOnStatus(String mail, int status) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        ArrayList<Client> clients = new ArrayList<>();
        try {

            stm = con.prepareStatement(updatestatuse);
            stm.setInt(1, status);
            stm.setString(2, mail);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param mail the user's E-mail
     * @return object from client that is online
     */
    @Override
    public Client getOnStatus(String mail) {
        PreparedStatement stm = null;
        ResultSet resSet = null;
        Client client = null;
        try {

            stm = con.prepareStatement(getstate);
            stm.setString(1, mail);

            resSet = stm.executeQuery();
            if (resSet.next()) {
                Blob b = resSet.getBlob(PICTURE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int iterator = 0;
                while ((iterator = in.read(buf)) >= 0) {
                    baos.write(buf, 0, iterator);

                }

                in.close();
                byte[] bytes = baos.toByteArray();
                client = new Client(resSet.getString(NAME), resSet.getString(EMAIL), bytes,
                        resSet.getBoolean(ONSTATUS), resSet.getString(STATUS));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return client;
    }

}
