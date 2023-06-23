package service;

import java.sql.ResultSet;


public class ExecuteSql {
    public void sqlexupdate(String sql){
        try {
            Conn connection = new Conn();
            connection.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
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
