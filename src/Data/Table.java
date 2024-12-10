package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table extends ArrayList{
	
	protected List<HashMap<String, String>> dataset;
	
	
	public Table() {
		
		this.dataset = new ArrayList<HashMap<String, String>>();
	}
	

	
    public List<HashMap<String, String>> getDataset() {
        return dataset;
    }
	
	protected List<List<String>> getTitleAndYearList() {
	    List<List<String>> titleYearList = new ArrayList<>();

	    for (HashMap<String, String> row : dataset) {
	        String title = row.getOrDefault("Title", null);
	        String year = row.getOrDefault("Year", null);
	        List<String> pair = new ArrayList<>();
	        pair.add(title);
	        pair.add(year);
	        titleYearList.add(pair);
	    }

	    return titleYearList;
	}
	
	
}
