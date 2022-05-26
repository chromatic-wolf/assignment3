/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import vsms.customExceptions.QueryInvalidException;

/**
 *
 * @author caleb
 */
//This class will wrap the database functions to make programming easier
public class DataBaseManager {

    private Connection connection;
    PreparedStatement query;

    boolean connect(String ipAddr, String user, String password, String dataBaseName) {
        try {
            //Maybe use the DriverManager.getDriver function to automatically get the driver to make this class more portable
            connection = DriverManager.getConnection("jdbc:mysql://" + ipAddr + dataBaseName, user, password);

            return true;

        } catch (SQLException ex) {
            System.out.println("Error unable to connect \n **STACK TRACE** \n \n \n");
            System.out.println(ex);
            return false;
        }

    }

    private boolean checkConnection(int timeout) {
        try {
            return connection.isValid(timeout);
        } catch (SQLException ex) {
            System.out.println("Error connection ERROR \n **STACK TRACE** \n \n \n");
            System.out.println(ex);
            return false;
        }
    }

    boolean connectionState() {
        return checkConnection(2000); //default timeout
    }

    boolean connectionState(int timeOut) {
        return checkConnection(timeOut); //custom timeout
    }
    
    //throws exception so it can be handled in main code/gui
    void SetSqlQuery(String SQL) throws SQLException
    {       
            this.query = this.connection.prepareStatement(SQL);       
    }
    
    //throws both Query invalid which indicates no query has been created or sql exception for anything else.
    ResultSet executeQuery(String SQLQuery) throws QueryInvalidException, SQLException 
    {
        if(query == null)
        {
            throw new QueryInvalidException("Please create a query first");
        }else
        {
            return query.executeQuery();
        }
    }
    
    void setQueryString(int index, String value) throws SQLException
    {
        query.setString(index, value);
    }
    
    void setQueryInt(int index, int value) throws SQLException
    {
        query.setInt(index, value);
    }
    
    
    
    
    //This will be a fun one
    void setQueryDate(int index, java.util.Date date) throws SQLException
    {
        this.query.setDate(index, DateTools.JavaDateToSqlDate(date));
    }
    
     void setQueryDate(int index, java.sql.Date date) throws SQLException
    {
        query.setDate(index, date);
    }

}
