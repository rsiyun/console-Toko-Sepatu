package service;

import java.sql.ResultSet;


public class ExecuteSql {
    public int sqlexupdate(String sql){
        int rowsAffected = 0;
        try {
            Conn connection = new Conn();
            rowsAffected = connection.getConnection().createStatement().executeUpdate(sql);
            return rowsAffected;
        } catch (Exception e) {
            System.out.println(e);
        }
        return rowsAffected;
    }
    public ResultSet sqlquerry(String sql){
        try {
            Conn connection = new Conn();
            return connection.getConnection().createStatement().executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
