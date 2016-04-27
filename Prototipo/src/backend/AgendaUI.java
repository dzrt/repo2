package backend;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AgendaUI{
    private boolean combobox;
    private boolean array;
    private boolean listeners; //Textarea
    private boolean allowlisteners; 
    private boolean listeners2;  //RadioButtons
    private boolean allowlisteners2;
    public AgendaUI(){
        this.combobox = false;
        this.array = false;
        this.listeners = false;
        this.allowlisteners = false;
        this.listeners2 = false;
        this.allowlisteners2 = false;
    }
    
    public boolean getListeners2(){
        return this.listeners2;
    }
    
    public boolean getComboboxStatus(){
        return this.combobox;
    }
    
    public boolean getArrayStatus(){
        return this.array;
    }
    
    public boolean getListeners(){
        return this.listeners;
    }
    
    public boolean getallowlisteners(){
        return this.allowlisteners;
    }
    
    public void setListeners2(boolean bit){
        this.listeners2 = bit;
    }
    
    public void setComboboxStatus(boolean flag){
        this.combobox = flag;
    }
    
    public void setArrayStatus(boolean flag){
        this.array = flag;
    }
    
    public void setListeners(boolean flag){
        this.listeners = flag;
    }

    public void setAllowListeners(boolean bit){
        this.allowlisteners = bit;
    }
    
}
