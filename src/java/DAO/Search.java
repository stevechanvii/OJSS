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
 * @author stevechanvii
 */
public class Search {
    
    String sql = "select * from job where position like '%";
    public void getAll() {
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
                    System.out.println(type);
                    record += "\"" + metaData.getColumnName(i);
                    if (type.equalsIgnoreCase("java.lang.String"))
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
        
        for(String item : result)
            System.out.println(item);
    }
    
    public void setSql(String keyword)
    {
        sql = sql + keyword + "%'";
    }
}
