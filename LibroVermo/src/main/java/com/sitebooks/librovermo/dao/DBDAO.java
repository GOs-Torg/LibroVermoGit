package com.sitebooks.librovermo.dao;

import org.apache.juli.logging.Log;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Map;
import java.util.logging.Logger;

public class DBDAO {
    private static final String url = "jdbc:mysql://localhost:3306/libraDB";
    protected static final String user = "root";
    private static final String password = "";
    protected static Statement state;
    public static String rootClassPathName = "com.sitebooks.librovermo.models.plugs.";

    public Connection getConnections(){
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        }
        catch (Exception e){}
        return null;
    }
    public ResultSet runQuery(String query){
        Connection connection = getConnections();
        if(connection != null) {
            try {
                state = connection.createStatement();
                if (query.toLowerCase().indexOf("select") == 0) {
                    return  state.executeQuery(query);
                } else if (query.toLowerCase().indexOf("insert") == 0 ||
                        query.toLowerCase().indexOf("update") == 0 ||
                        query.toLowerCase().indexOf("delete") == 0) {
                    state.executeUpdate(query);
                }
            } catch (SQLException e) {
                Logger.getLogger("runQueryError").info(e.getMessage());
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger("runQueryError").info(e.getMessage());
                }
            }
        }
        return null;
    }
    public <T> Long addToTable(String tableName, T object){
        Long id = 1l;
        Class<?> newClass = object.getClass();
        try {
            try {
                    String fieldName = "";
                    String fieldValue = "";
                    Field[] field = newClass.getDeclaredFields();
                    field[0].setAccessible(true);
                    String querySel = "SELECT " + field[0].getName() + " FROM " + tableName + " ORDER BY " + field[0].getName() + " DESC";
                    ResultSet resSet = runQuery(querySel);
                    if (resSet.next()){
                        id = resSet.getLong(1) + 1l;
                    }
                    int i = 0;
                    field[0].set(object, id);
                    for (Field fields : field) {
                        fields.setAccessible(true);
                        fieldName += fields.getName() + ",";
                        if(fields.getName().toLowerCase().contains("password") ||
                                fields.getName().toLowerCase().contains("email") ||
                                fields.getName().toLowerCase().contains("login")) {
                            fieldValue += "'" + Crypt.encrypt(fields.get(object).toString()) +"'" + ",";
                        }
                        else
                            fieldValue += "'" + fields.get(object) + "'" + ",";
                        i++;
                    }
                    fieldName = fieldName.substring(0, fieldName.length() - 1);
                    fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
                    Logger.getAnonymousLogger().info(field.length + "");
                    String query = "INSERT INTO " + tableName + " (" + fieldName + ")" + " VALUES (" + fieldValue + ")";
                    query = query.replace("'null'","null");
                    Logger.getAnonymousLogger().info(query);
                    runQuery(query);
                    return id;
            }
            catch (SQLException e) {Logger.getAnonymousLogger().info(e.getMessage());}
        }
        catch (Exception e){Logger.getAnonymousLogger().info(e.getMessage());}
        return -1l;
    }

    public <T> void updateTable(String tableName, T object){
        Class<?> newClass = object.getClass();
        try {
            String fieldName = "";
            Field[] field = newClass.getDeclaredFields();
            for (Field fields : field) {
                fields.setAccessible(true);
                fieldName += fields.getName() + "='" + fields.get(object) + "',";
            }
            fieldName = fieldName.substring(0, fieldName.length() - 1);
            Logger.getAnonymousLogger().info(field.length + "");
            String query = "UPDATE " +  tableName + " SET " + fieldName + " WHERE " + field[0].getName() + " = " + field[0].get(object);
            query = query.replace("'null'","null");
            Logger.getAnonymousLogger().info(query);
            runQuery(query);
        }
        catch (Exception e){Logger.getAnonymousLogger().info(e.getMessage());}
    }

    public <T> void deleteTable(String tableName, T object){
        Class<?> newClass = object.getClass();
        Connection connection = getConnections();
        try {
            Field[] field = newClass.getDeclaredFields();
            Logger.getAnonymousLogger().info(field.length + "");
            field[0].setAccessible(true);
            String query = "DELETE FROM " + tableName + " WHERE " + field[0].getName() + " = " + field[0].get(object);
            query = query.replace("'null'","null");
            Logger.getAnonymousLogger().info(query);
            runQuery(query);
        }
        catch (Exception e){Logger.getAnonymousLogger().info(e.getMessage());}
    }

    public ResultSet selectFromTable(String tableName, Class<?> returnClass){
        Class<?> newClass = returnClass;
        try {
            String fieldName = "";
            Field[] field = newClass.getDeclaredFields();
            for (Field fields : field) {
                fields.setAccessible(true);
                fieldName += fields.getName() + ",";
            }
            fieldName = fieldName.substring(0, fieldName.length() - 1);
            String query = "SELECT "+ fieldName +" FROM " + tableName;
            Logger.getAnonymousLogger().info(query);
            ResultSet res = runQuery(query);
            return res;
        }
        catch (Exception e){Logger.getAnonymousLogger().info(e.getMessage());}
        return null;
    }
    public ResultSet selectFromTable(String tableName, Class<?> returnClass, Map<String,String> conditions){
        Class<?> newClass = returnClass;
        try {
            String fieldName = "";
            Field[] field = newClass.getDeclaredFields();
            for (Field fields : field) {
                fields.setAccessible(true);
                fieldName += fields.getName() + ",";
            }
            fieldName = fieldName.substring(0, fieldName.length() - 1);
            String conditionString = "";
            for (String key:
                    conditions.keySet()) {
                if (!key.equals("lim") && !key.equals("ord"))
                    conditionString += " "+conditions.get(key) + " and";
            }
            Logger.getLogger("Err").info(conditionString);
            if(!conditionString.isEmpty())
                conditionString = " Where "+conditionString.substring(0, conditionString.length()-4);
            if(conditions.containsKey("lim"))
                conditionString += " LIMIT 20 OFFSET "+ Long.parseLong(conditions.get("lim"))*10;
            if(conditions.containsKey("ord"))
                conditionString+= " ORDER BY "+conditions.get("ord");
            String query = "SELECT "+ fieldName +" FROM " + tableName + conditionString;
            return runQuery(query);
        }
        catch (Exception e){Logger.getAnonymousLogger().info(e.getMessage());}
        return null;
    }
}
