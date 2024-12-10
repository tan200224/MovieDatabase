/*
 * @author Archie Tan
 */
package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {
	
	protected final String PATH = "..\\MovieDatabase\\Test";
	protected DataProcessing_Rank dataProcessor;
	protected Table data;
	
	public Database() {
		
		this.dataProcessor = new DataProcessing_Rank(PATH);
		this.dataProcessor.Process();
		
		this.data = this.dataProcessor.table;

	}	
	
	// Return the total earning in a given year
	protected String totalEarningInYear(String year) {
		
		int total = 0;
		
		for (HashMap<String, String> row : this.data.getDataset()) {
			if (row.get("Year").equals(year) && row.get("USA Box Office")!=null) {
				total += Integer.parseInt(row.get("USA Box Office"));
			}	
		}
		return String.valueOf(total);
	}
	
	// Given the information looking for, return the whole list
	protected ArrayList<String> GetListFromKey(String key) {
		
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for (HashMap<String, String> row : this.data.getDataset()) {
			toReturn.add((String) row.get(key));
			}
			
		return toReturn;
	}
	
	// Return the List of consist of the key and its frequency
	protected HashMap<String, Integer> freqCounter(String key) {
		
		HashMap<String, Integer> freqCounter = new HashMap<String, Integer>();
		
		for (HashMap<String, String> row : this.data.getDataset()) {
			
			freqCounter.put(row.get(key), freqCounter.getOrDefault(row.get(key), 0) + 1);
		}
		
		return freqCounter;
	}
	
	protected HashMap<String, String> getRowFromRank(String rankType, String rankNum) {
		
		for (HashMap<String, String> row : this.data.getDataset()) {
			if (rankNum.equals(row.get(rankType))) {
				return row;
			}
		}
		return null;
	}
	
	protected String getMembersName(String memberType) {
		
	    StringBuilder Names = new StringBuilder();
	    Set<String> seenNames = new HashSet<>(); 

	    List<HashMap<String, String>> sortedDataset = this.data.getDataset().stream()
	    		.sorted(Comparator.comparing(row -> row.get(memberType), Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
	            .toList();

	    for (HashMap<String, String> row : sortedDataset) {
	        String name = row.get(memberType);
	        if (name != null && seenNames.add(name)) { 
	            if (Names.length() > 0) {
	            	Names.append("\n");
	            }
	            Names.append(name);
	        }
	    }

	    return Names.toString();
	}
	
	protected String getPopularDirectors(String numRows) {
		
		StringBuilder toReturn = new StringBuilder();
		
		HashMap<String, Integer> directors = freqCounter("Director");
		Map<String, Integer> sortedMap = directors.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, 
                        LinkedHashMap::new 
                ));
		
		int count = 0;
	    for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
	        if (count >= Integer.parseInt(numRows)) break; 
	        toReturn.append(entry.getKey())
	                .append(": ") 
	                .append(entry.getValue())
	                .append("\n"); 
	        count++;
	    }
		
		return toReturn.toString();
	}
	
	protected String getPersonName(String memberType, String rankType, String rankNum) {
		HashMap<String, String> row = getRowFromRank(rankType, rankNum);
		return row.get(memberType);
	}
	
	
	protected String getMovieYearFromRating(String rating) {
		
		StringBuilder moiveAndYear = new StringBuilder();
			    
    	for (HashMap<String, String> row : this.data.getDataset()) {
	        if (row.get("IMDb Rating") != null && Double.parseDouble(rating) <= Double.parseDouble(row.get("IMDb Rating"))) {
	            if (moiveAndYear.length() > 0) {
	            	moiveAndYear.append("\n"); 
	            }
	            moiveAndYear.append(row.get("Title") + "(" + row.get("Year") + ")"); 
	        }
    	}
    	return moiveAndYear.toString(); 
	}

	public String getMovies() {
	    StringBuilder title = new StringBuilder();

	    List<HashMap<String, String>> sortedDataset = this.data.getDataset().stream()
	            .sorted(Comparator.comparing(row -> row.get("Title"), String.CASE_INSENSITIVE_ORDER)) // Sort alphabetically by Director
	            .toList();


	    for (HashMap<String, String> row : sortedDataset) {
	        if (title.length() > 0) {
	        	title.append("\n");
	        }
	        title.append(row.get("Title")); 
	    }

	    return title.toString();
	}
	

	
	
	
	
	

	
	
	
	
	

}
