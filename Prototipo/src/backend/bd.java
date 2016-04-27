/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ //bosingwa rei
//comment
package backend;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Felipe
 */
public class bd {
    private Connection connection;
    private Statement statement ;
    private PreparedStatement prep ;
    private ResultSet result;
    public bd(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd1","root","");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /**This method verifies if a given username exists with the correct password
     * 
     * @param user String
     * @param password String
     * @return boolean
     */
    public boolean isUser(String user,String password){
        boolean flag = false ;
        try{
            String query = "SELECT user.username FROM user WHERE user.username='"+user+"' AND user.password='"+password+"'";
            result = statement.executeQuery(query);
            flag = result.next();
        }catch(Exception e){
            System.out.println(e);
        }
        return flag;
    }
    public boolean isAdmin(String username){
        boolean flag = false;
        try{
            String query = "SELECT user.type FROM user WHERE user.username='"+username+"'";
            result = statement.executeQuery(query);
            result.next();
            String type = result.getString("type");
            if(type.equals("adm")){
                flag = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return flag;
    }
    public String[] getUsers(){
        int i = 0;
        int ii = 0;
        try{
            String query = "SELECT * FROM user";
            result = statement.executeQuery(query);
            while(result.next()){
                i++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        String users[] = new String[i];
        try{
          String query2 = "SELECT user.username FROM user";
            result = statement.executeQuery(query2);
            while(result.next()){
                users[ii] = result.getString("username");
                ii++;
            }  
        }catch(Exception e){
            System.out.println(e);
        }
        return users;
    }
    public String getType(String username){
        String type = "";
        try{
            String query = "SELECT user.type FROM user WHERE user.username='"+username+"'";
            result = statement.executeQuery(query);
            result.next();
            type = result.getString("type");
        }catch(Exception e){
            System.out.println(e);
        }
        return type;
    }
}