import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class AlarmAppController {

    @FXML
    private ComboBox<String> dayBox;
    @FXML
    private ComboBox<String> monthBox;
    @FXML
    private Button showBtn;
    @FXML
    private Button updateBtn;    
    @FXML
    private TextField userText;
    @FXML
    private ComboBox<String> yearBox;

    private HashMap<Date,String> temp= new HashMap<Date, String>();
    private String name;
    
    
    
    public void initialize() {
    	 name = JOptionPane.showInputDialog("Enter the name of the file that will contain all of your reminders: ");
    	 setComboBox(); 	   	
    }
      
    
    /*
     * find the requested data in the hash-map. 
     */  
    @FXML
    void showReminders(ActionEvent event) {
		Date currDate= new Date(dayBox.getValue(), monthBox.getValue(), yearBox.getValue() );
		Iterator<Map.Entry<Date,String>> currIterator = temp.entrySet().iterator(); 
		while (currIterator.hasNext()) {
            Map.Entry<Date,String> item = currIterator.next();
            if (item.getKey().equals(currDate)) {
            	if (item.getValue()!= null) {
            		userText.setText(item.getValue());
            	}}
            	else userText.clear();}	
    }
   
    
    /*
     * update/create the data in the hash-map. 
     */   
    @FXML
    void updateReminders(ActionEvent event) {
    	Date currDate= new Date(dayBox.getValue(), monthBox.getValue(), yearBox.getValue() );
    	Iterator<Map.Entry<Date,String>> currIterator = temp.entrySet().iterator(); 
    	boolean keyExist= false;
		while (currIterator.hasNext()) {
            Map.Entry<Date,String> item = currIterator.next();
            if (item.getKey().equals(currDate)) {
            	keyExist=true;
            	item.setValue(userText.getText());
            	saveFile(name);
            	}
            }
		if (!keyExist) {
			temp.put(currDate,userText.getText());
			saveFile(name);
		}	
    }
    
    
    /*
     * build all the parameters for the combo boxes. 
     */
    private void setComboBox() {
    	for (int i=1; i<=31; i++) {
    		dayBox.getItems().addAll(""+i);
    	}
    	for (int i=1; i<=12; i++) {
    		monthBox.getItems().addAll(""+i);
    	}
    	for (int i=2000; i<=2030; i++) {
    		yearBox.getItems().addAll(""+i);
    	}
    }
    
    
    /*
     * saving all the reminders as a string (for the saved file).
     */
    private String allTheReminders() {
    	String file="";
    	Iterator<Map.Entry<Date,String>> currIterator = temp.entrySet().iterator(); 
		while (currIterator.hasNext()) {
			Map.Entry<Date,String> item = currIterator.next();
            if (!item.getValue().equals("")) {
             file+=("\nDate: " + item.getKey()+"\nReminders: " +item.getValue()+"\n" );
            } }
		return file;
    }
    
    
    /*
     * update the data in the saved file.
     */
    private void saveFile(String name) {
		try {
			FileWriter fw = new FileWriter(name + ".txt");
			fw.write(allTheReminders());
			fw.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
		JOptionPane.showMessageDialog(null, "UPDATE SAVED");
	}
   
	
    
}
