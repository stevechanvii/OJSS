/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 41649
 */
public class SqlExecute {
    private String sql;
    
    public SqlExecute(){
        
    }
    
    public SqlExecute(String sql){
        this.sql =sql;
    }

    public void setSql(String sql){
        this.sql = sql;
    }
    
    public ArrayList<String> doQuery(){
        Database da = new Database();
        Connection conn = da.getConn();
        PreparedStatement pstmt;
        ArrayList<String> result = new ArrayList<String>();
        
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData  metaData = rs.getMetaData();
            int col = metaData.getColumnCount();
            
            while (rs.next()) {
                String record = "";
                for (int i = 1; i <= col; i++) {
                        
                    if (i != col)
                        record += rs.getString(i) + ",";
                    else
                        record += rs.getString(i);
                }
                result.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String> doQuery(String columnName){
        Database da = new Database();
        Connection conn = da.getConn();
        PreparedStatement pstmt;
        ArrayList<String> result = new ArrayList<String>();
        
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData  metaData = rs.getMetaData();
            int col = metaData.getColumnCount();
            
            while (rs.next()) {
                String record = "";
                for (int i = 1; i <= col; i++) {                       
                   if (metaData.getColumnName(i) == columnName)
                       record += rs.getString(i);
                }
                result.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String> doJsonQuery(){
        Database da = new Database();
        Connection conn = da.getConn();
        PreparedStatement pstmt;
        ArrayList<String> result = new ArrayList<String>();
        
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData  metaData = rs.getMetaData();
            int col = metaData.getColumnCount();
            
            while (rs.next()) {
                String record = "{";
                for (int i = 1; i <= col; i++) {
                    String type = metaData.getColumnClassName(i);
                    record += "\"" + metaData.getColumnName(i);
                    
                    if (type.equalsIgnoreCase("java.lang.String") || type.equalsIgnoreCase("java.sql.Timestamp") || type.equalsIgnoreCase("java.sql.Date"))
                        record += "\":\"" + rs.getString(i) + "\"";
                    else
                        record += "\":" + rs.getString(i);
                    if (i != col)
                        record += ",";
                }
                record += "}";
                result.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return result;
    }
    
    public boolean execute(){
        boolean result = true;
        Database da = new Database();
        Connection conn = da.getConn();
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.execute();
            conn.close();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
       return result;
    }
}
