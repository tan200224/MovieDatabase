package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table extends ArrayList{
	
	protected List<HashMap<String, String>> dataset;
	
	
	public Table() {
		
		this.dataset = new ArrayList<HashMap<String, String>>();
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: \n");

        // Collect all column names (attributes)
        List<String> columns = new ArrayList<>();
        for (HashMap<String, String> row : dataset) {
            for (String column : row.keySet()) {
                if (!columns.contains(column)) {
                    columns.add(column); // Collect unique column names
                }
            }
        }

        // Print column names
        sb.append("Columns: ").append(columns).append("\n");

        // Print each row
        for (int i = 0; i < dataset.size(); i++) {
            HashMap<String, String> row = dataset.get(i);
            sb.append("Row ").append(i + 1).append(": ");
            for (String column : columns) {
                Object value = row.getOrDefault(column, "null");
                sb.append(column).append("=").append(value).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
            sb.append("\n");
        }
        return sb.toString();
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
