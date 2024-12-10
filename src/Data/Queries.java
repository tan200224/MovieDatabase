package Data;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Queries {
	
	Scanner respondSC;
	HashMap<String, String> queriesDisplay = new HashMap<String, String>();
	private HashMap<String, List<Runnable>> queriesActions;
	
	// Create the queries 
	public Queries() {
		
		this.respondSC = new Scanner(System.in);
		queriesDisplay = new HashMap<String, String>();
		queriesActions = new  HashMap<String, List<Runnable>>();
		
		this.queriesDisplay.put("W", "Give the total US box office earnings in the database in a single year \n");
		this.queriesDisplay.put("E", "Give a list of all the directors of movies in the database. \n");
		this.queriesDisplay.put("R", "Give a list of some number of directors who appear most in the database. \n");
		this.queriesDisplay.put("T", "Given the movie’s rating rank or money rank, get who is starred or directed the movie\n");
		this.queriesDisplay.put("Y", "Given the rating of the movie, get a list of movie that have the same or better rating\n");
		this.queriesDisplay.put("U", "Get a list of movie names");
		
	}
	
	
	public void createActions(Database data) {
	    this.queriesActions.put("W", Collections.singletonList(() -> {
	        String year = this.askYear(); // Prompt the user for a year and get the input
	        String result = data.totalEarningInYear(year); // Get the total earnings
	        System.out.println("Total earnings for year " + year + ": " + result); // Print the result
	    }));

	    this.queriesActions.put("E", Collections.singletonList(() -> {
	    	String rowNum = askNumRows();
	    	String result = data.getPopularDirectors(rowNum);
	        System.out.println(result);
	    }));
	    
	    
	    this.queriesActions.put("R", Collections.singletonList(() -> {
	    	String memberType = memberType();
	    	String result = data.getMembersName(memberType);
	        System.out.println(result);
	    }));
	    
	    this.queriesActions.put("T", Collections.singletonList(() -> {
	    	String rankType = askRankType();
	    	String rankNum = askRankNum();
	    	String memberType = memberType();
	    	String result = data.getPersonName(memberType, rankType, rankNum);
	        System.out.println(result);
	    }));
	    
	    this.queriesActions.put("Y", Collections.singletonList(() -> {
	    	String rating = askRating();
	    	String result = data.getMovieYearFromRating(rating);
	        System.out.println(result);
	    }));
	    
	    this.queriesActions.put("U", Collections.singletonList(() -> {
	    	String result = data.getMovies();
	        System.out.println(result);
	    }));
	    
	}
	
	
	
	// Return all the queries for display
	public HashMap<String, String> getQueriesDic() {
		
		return this.queriesDisplay;
	}
	
	public List<Runnable> getActions(String input) {
		
		return queriesActions.get(input);	
	}
	
	protected String askNumRows() {
		System.out.println("Enter the number of rows you want: ");
		return this.respondSC.nextLine();
	}
	protected String askRankNum() {
		System.out.println("Enter the rank number: ");
		return this.respondSC.nextLine();
	}
	protected String askYear() {
		System.out.println("Enter the year: ");
		return this.respondSC.nextLine();
	}
	protected String askRankType() {
		System.out.println("Enter the rank type (Gorss Rank, Rating Rank, Alphabetical Rank): ");
		return this.respondSC.nextLine();
	}
	protected String askDirector() {
		System.out.println("Enter the direcotr: ");
		return this.respondSC.nextLine();
	}
	protected String memberType() {
		System.out.println("Enter the member type (Director, Cast 1, Cast 2, Cast 3, Cast 4, or Cast 5): ");
		return this.respondSC.nextLine();
	}
	protected String askTitle() {
		System.out.println("Enter the title: ");
		return this.respondSC.nextLine();
	}
	protected String askRating() {
		System.out.println("Enter the rating: ");
		return this.respondSC.nextLine();
	}
	protected String askEarning() {
		System.out.println("Enter the earning: ");
		return this.respondSC.nextLine();
	}
	protected void doNothing() {
		
	}
	
	

}
