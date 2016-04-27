package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class faqUI {
private boolean list;
private boolean listeners;
private boolean allowlisteners;
private Connection connection;
private Statement statement;
private ResultSet result;
private int id;

public faqUI(){
    this.list = false;
    this.listeners = false;
    this.allowlisteners = false;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd1","root","");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
}

public boolean isAnswer(String answer){
    boolean bit = false;
    try{
        String query = "SELECT faq.a FROM faq WHERE faq.a='"+answer+"'";
        result = statement.executeQuery(query);
        bit = result.next();
    }catch(Exception e){
        System.out.println(e);
    }
    return bit;
}

public void createFaq(String question,String answer){
    try{
        String query = "INSERT INTO faq (q,a) VALUES ('"+question+"','"+answer+"')";
        statement.executeUpdate(query);
    }catch(Exception e){
        System.out.println(e);
    }
}

public void UpdateFaq(int key,String question,String answer){
    try{
        String query = "UPDATE faq SET faq.q=?,faq.a=? WHERE faq.id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,question);
        ps.setString(2,answer);
        ps.setInt(3,key);
        ps.executeUpdate();
        ps.close();
    }catch(Exception e){
        System.out.println(e);
    }
}

public void DeleteAnswer(int key){
    try{
        String query = "UPDATE faq SET faq.a=? WHERE faq.id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,"");
        ps.setInt(2,key);
        ps.executeUpdate();
        ps.close();
    }catch(Exception e){
        System.out.println(e);
    }
}

public void DeleteQuestion(int key){
    try{
        String query = "UPDATE faq SET faq.q=? WHERE faq.id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(key,"");
        ps.setInt(2,key);
        ps.executeUpdate();
        ps.close();
    }catch(Exception e){
        System.out.println(e);
    }
}

public boolean getList(){
    return this.list;
}

public boolean getListeners(){
    return this.listeners;
}

public boolean getAllowlisteners(){
    return this.allowlisteners;
}

public void setListeners(boolean bit){
    this.listeners = bit;
}

public void setAllowListeners(boolean bit){
    this.allowlisteners = bit;
}

public int getNumRows(){
    int number = 0;
    try{
    String query = "SELECT * FROM faq";
    result = statement.executeQuery(query);
    while(result.next()){
        number++;
    }
    }catch(Exception e){
        System.out.println(e);
    }
    return number;
}
public String[][] getFaq(){
    String faqs[][] = new String[this.getNumRows()][3];
    int i = 0;
    try{
        String query = "SELECT faq.q,faq.a,faq.id FROM faq";
        result = statement.executeQuery(query);
        while(result.next()){
            faqs[i][0] = result.getString("q");
            faqs[i][1] = result.getString("a");
            faqs[i][2] = result.getString("id");
            i++;
        }
    }catch(Exception e){
        System.out.println(e);
    }
    return faqs;
}

public void setList(boolean bit){
    this.list = bit;
}
}
