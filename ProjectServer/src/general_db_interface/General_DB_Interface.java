/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_db_interface;

/**
 *
 * @author Hesham
 */
public interface General_DB_Interface<T> {

    public boolean createTable(T t);

    public boolean updateTable(T t);

    public boolean deleteRow(T t);

    public boolean InsertRow(T t);

}
