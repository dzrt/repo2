
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Agenda {
    private Connection connection;
    private Statement statement ;
    private PreparedStatement prep ;
    private ResultSet result;
    private String username;
    private boolean onchangedescription;
    private boolean onchangeurgency;
    public Agenda(String username){
        this.username = username;
        this.onchangedescription = false;
        this.onchangeurgency = false;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd1","root","");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
/**This method returns the next 7 days including today's date 
     * 
     * @return Array
     */
    public String[] getNext7Days(){
        //Date format
        String dias[] = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        for(int i = 0;i <= 6;i++){
             cal.add(Calendar.DATE, i);            
             dias[i] = sdf.format(cal.getTime());
             cal.add(Calendar.DATE,-i);
        }
    return dias;
    }
    /**This method return the number of events a given username has 
     * 
     * @param username
     * @return int
     */
    public int getAgendaRows(){
        int i = 0;
        String query = "SELECT agenda.day,agenda.urgency,agenda.description FROM agenda WHERE agenda.username='"+username+"'";
        try{
        result = statement.executeQuery(query);
        while(result.next()){
            i++;
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return i ;
    }
    /**This method return a multidimensional array that contains the days, events and descriptions of the given username's agenda.
     * 
     * @param username 
     */
    public String[][] getAgenda(){
        int i = getAgendaRows();
        String array[][] = new String[i][3];
        String query = "SELECT agenda.day,agenda.urgency,agenda.description FROM agenda WHERE agenda.username='"+username+"'";
        try{
            result = statement.executeQuery(query);
            int ii = 0;
            while(result.next()){
                array[ii][0] = result.getString("day");
                array[ii][1] = result.getString("urgency");
                array[ii][2] = result.getString("description");
                ii++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return array;
    }
    
    public boolean getOnChangeDescription(){
        return this.onchangedescription;
    }
    
    public void setOnChangeDescription(boolean flag){
        this.onchangedescription = flag;
    }
    
    public boolean getOnChangeUrgency(){
        return this.onchangeurgency;
    }
    
    public void setOnChangeUrgency(boolean bit){
        this.onchangeurgency = bit;
    }
    
    public void CreateAgenda(String day,String description,int urgency){
        String query = "INSERT INTO agenda (agenda.username,agenda.day,agenda.description,agenda.urgency) VALUES ('"+username+"','"+day+"','"+description+"',"+urgency+")";
    try{
        statement.executeUpdate(query);
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public void setAgenda(String day,String description,int urgency){
        try{
        PreparedStatement ps = connection.prepareStatement("UPDATE agenda SET description=? , urgency=? WHERE username=? AND day=?");
            ps.setString(1,description);
            ps.setInt(2,urgency);
            ps.setString(4,day);
            ps.setString(3,username);
            ps.executeUpdate();
            ps.close();
    }catch(Exception e){
        System.out.println(e);
    }
    }public void setAgenda(String day,String description){
        try{
        PreparedStatement ps = connection.prepareStatement("UPDATE agenda SET description=? WHERE username=? AND day=?");
            ps.setString(3,day);
            ps.setString(1,description);
            ps.setString(2,username);
            ps.executeUpdate();
            ps.close();
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public void setAgenda(String day , int urgency){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE agenda SET urgency=? WHERE username=? AND day=?");
            ps.setInt(1,urgency);
            ps.setString(2, username);
            ps.setString(3,day);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public boolean IsDay(String day){
        boolean flag = false ;  
        try{
           String query = "SELECT agenda.day FROM agenda WHERE agenda.day='"+day+"' AND agenda.username='"+username+"'";
           result = statement.executeQuery(query);
           while(result.next()){
               flag = true;
           }
        }catch(Exception e){
            System.out.println(e);
        }
        return flag;
    }
    public String getUsernameAgenda(){
        return this.username;
    }
    public boolean DescriptionChange(String day,String description){
        boolean flag = false;
        try{
            String query = "SELECT agenda.description FROM agenda WHERE agenda.day='"+day+"' AND agenda.username='"+this.username+"'";
            result = statement.executeQuery(query);
            if(result.next()){
            if(result.getString("description").equals(description)){
                flag = false;
            }else{
                flag = true;
            }
        }else{
                flag = true;
            }}catch(Exception e){
            System.out.println(e);
        }
        return flag;
    }
    public boolean UrgencyChange(String day,int urgency){
        boolean bit = false;
        try{
            String query = "SELECT agenda.urgency FROM agenda WHERE agenda.day='"+day+"' AND agenda.username='"+this.username+"'" ;
            result = statement.executeQuery(query);
            result.next();
            if(result.getInt("urgency")==urgency){
                bit = false;
            }else{
                bit = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return bit;
    }
}
