package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {
     private String username;
     private String password;
     private String type;
     private Connection connection;
     private Statement statement ;
     private PreparedStatement prep ;
     private ResultSet result;
     public user(String username,String password,String type){
         this.username = username;
         this.password = password;
         this.type = type;
         try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd1","root","");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
     }
     public String getType(){
         return this.type;
     }
     public String getUsername(){
         return this.username;
     }
}