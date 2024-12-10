import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import Data.Database;
import Data.Queries;

public class Controller {
	
	DatabaseDisplay display;
	Queries queries = new Queries();
	Database data = new Database();
	
	public Controller(DatabaseDisplay display) {
		
		this.display = display;
		this.data = new Database();
		this.queries.createActions(data);
		
	}
	
	
	protected void getOutput(String input) {
		getFollowUpQuestions(input);
	}
	
	protected void getFollowUpQuestions(String input) {
		executeQuery(input);
	}
	
	protected HashMap<String, String> getDisplayQueries() {
		return this.queries.getQueriesDic();
	}
	
	
	public void executeQuery(String key) {
	    List<Runnable> actions = this.queries.getActions(key);

	    if (actions != null) {
	        for (Runnable action : actions) {
	            action.run(); // Execute each action
	        }
	    } else {
	        System.out.println("Invalid query key.");
	    }
	}
	
	
}
