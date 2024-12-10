package Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataProcessing {
	
	protected File FOLDER;
	protected File[] files;
	protected Scanner fileSC;
	protected Table table;
	
	public DataProcessing(String path) {
		
		this.FOLDER = new File(path);
		this.files = FOLDER.listFiles();
		this.table = new Table();
	}
	
	// Process all the files in the folder
	protected void Process() {
		
		ArrayList<String> attributes;
		for (File file : this.files) {
			
		    if (file.isFile()) {
		    	attributes = readAttributes(file);
		    	readRestLines(attributes, file);	
		    }    
		}
	}
	
	
	protected ArrayList <String> readAttributes(File file) {
		
		ArrayList<String> attributes = null;
		
	    try (Scanner fileSC = new Scanner(file)) {
	        if (fileSC.hasNextLine()) {
	        	attributes = new ArrayList<>();
	            // Split the first line into attributes
	            String[] attrArray = fileSC.nextLine().split("\t");

	            // Add new attributes to the attributes list
	            for (String attribute : attrArray) {
	            	attributes.add(attribute);
	            }
	        }
	    
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    for (String attribute : attributes) {
	    	System.out.println(attribute);
	    }
	    
		return attributes;
	}
	
	
	private void readRestLines(ArrayList<String> attributes, File file) {
	    try (Scanner fileSC = new Scanner(file)) {
	    	
	    	// Skip the first Line of Attributes
	    	fileSC.nextLine();
	    
	        int titleIdx = attributes.indexOf("Title");
	        int yearIdx = attributes.indexOf("Year");

	        while (fileSC.hasNextLine()) {

	            String line = fileSC.nextLine();
	            String[] entry = line.split("\t");
	            String title = entry[titleIdx];
	            String year = entry[yearIdx];

	            // Check if the movie exists in the table
	            if (movieExistsInTable(title, year)) {
	                // Merge data if the movie exists
	                mergeRow(attributes, entry, title, year);
	            } else {
	                // Add a new movie if it does not exist
	                HashMap<String, String> newRow = createNewRow(attributes, entry);
	                this.table.dataset.add(newRow);
	            }
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	// Check if the movie exists in the table
	protected boolean movieExistsInTable(String title, String year) {
	    for (HashMap<String, String> row : this.table.getDataset()) {
	        if (title.equals(row.get("Title")) && year.equals(row.get("Year"))) {
	            return true;
	        }
	    }
	    return false;
	}

	// Merge data for an existing movie
	protected void mergeRow(ArrayList<String> attributes, String[] entry, String title, String year) {
	    for (HashMap<String, String> row : this.table.getDataset()) {
	        if (title.equals(row.get("Title")) && year.equals(row.get("Year"))) {
	            for (int i = 0; i < attributes.size(); i++) {
	                String attr = attributes.get(i);
	                if (!row.containsKey(attr) || row.get(attr) == null) {
	                    row.put(attr, entry[i]);
	                }
	            }
	        }
	    }
	}

	// Create a new row for the table
	protected HashMap<String, String> createNewRow(ArrayList<String> attributes, String[] text) {
	    HashMap<String, String> newRow = new HashMap<>();
	    for (int i = 0; i < attributes.size(); i++) {
	        newRow.put(attributes.get(i), text[i]);
	    }
	    return newRow;
	}



	






	
	
	
}

