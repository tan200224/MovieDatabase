package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DataProcessing_Rank extends DataProcessing{

	private HashMap<String, String> rankings;
	
	public DataProcessing_Rank(String path) {
		super(path);
		this.rankings = new HashMap<>();
        rankings.put("USA Box Office", "Gorss Rank");
        rankings.put("Cast 1", "Alphabetical Rank");
        rankings.put("IMDb Rating", "Rating Rank");
		
	}
	
	@Override
	protected ArrayList<String> readAttributes(File file) {
		
		ArrayList<String> attributes = null;
		
	    try (Scanner fileSC = new Scanner(file)) {
	        if (fileSC.hasNextLine()) {
	            // Split the first line into attributes
	        	attributes = new ArrayList<>();
	            String[] attrArray = fileSC.nextLine().split("\t");
	           
	            // Add new attributes to the attributes list
	            for (String attribute : attrArray) {
	            	
	            	if (attribute.equals("Rank")) {
	            		attributes.add(getRankType(attrArray));
	            	}
	            	else {
	                    attributes.add(attribute);
	            	}
	            }
	        }
	    
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
		return attributes;
	}
	
	
	@Override
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

	@Override
	protected HashMap<String, String> createNewRow(ArrayList<String> attributes, String[] text) {
	    HashMap<String, String> newRow = new HashMap<>();
	    for (int i = 0; i < attributes.size(); i++) {
	        newRow.put(attributes.get(i), text[i]);
	    }
	    return newRow;
	}

	private String getRankType(String[] attributes) {
		for (String type : rankings.keySet()) {
	        // Check if the array contains the ranking type
	        if (Arrays.asList(attributes).contains(type)) {
	            return rankings.get(type); // Return the mapped ranking name
	        }
	    }
		
		return null;
	}

	


}
