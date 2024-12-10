package Data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class AnswerTesting {
	
	 @Test
	    void testTotalEarningInYear() {
	        Database db = new Database();
	        String totalEarnings = db.totalEarningInYear("2009");
	        assertEquals("760505847", totalEarnings); 
	        assertEquals("0", db.totalEarningInYear("2024"));
	    }
	
	    @Test
	    void testGetListFromKey() {
	        Database db = new Database();
	        var list1 = db.GetListFromKey("Director");
	        assertNotNull(list1);
	        assertTrue(list1.contains("Archie"));
	        
	        var list2 = db.GetListFromKey("Cast 1");
	        assertNotNull(list2);
	        assertTrue(list2.contains("Tan"));
	    }
	
	    @Test
	    void testFreqCounter() {
	        Database db = new Database();
	        HashMap<String, Integer> frequency1 = db.freqCounter("Director");
	        assertNotNull(frequency1);
	        assertTrue(frequency1.containsKey("Archie")); 
	        assertEquals(2, frequency1.get("Archie")); 
	        
	        HashMap<String, Integer> frequency2 = db.freqCounter("Cast 1");
	        assertNotNull(frequency2);
	        assertTrue(frequency2.containsKey("Tan")); 
	        assertEquals(2, frequency2.get("Tan")); 
	        
	    }
	
	    @Test
	    void testGetRowFromRank() {
	        Database db = new Database();
	        HashMap<String, String> row = db.getRowFromRank("Rating Rank", "1");
	        assertNotNull(row);
	        assertEquals("The Shawshank Redemption", row.get("Title"));
	    }
	
	    @Test
	    void testGetMembersName() {
	        Database db = new Database();
	        String names = db.getMembersName("Director");
	        assertNotNull(names);
	        assertTrue(names.contains("Stanley Kubrick")); 
	    }
	
	    @Test
	    void testGetPopularDirectors() {
	        Database db = new Database();
	        String popularDirectors = db.getPopularDirectors("2");
	        assertNotNull(popularDirectors);
	        assertTrue(popularDirectors.contains("Archie")); 
	    }
	
	    @Test
	    void testGetPersonName() {
	        Database db = new Database();
	        String personName = db.getPersonName("Director", "Rating Rank", "1");
	        assertEquals(null, personName); 
	    }
	
	    @Test
	    void testGetMovieYearFromRating() {
	        Database db = new Database();
	        String moviesAndYears = db.getMovieYearFromRating("8.5");
	        assertNotNull(moviesAndYears);
	        assertFalse(moviesAndYears.contains("Inception(2010)"));
	        assertTrue(moviesAndYears.contains("Pulp Fiction")); 
	        assertFalse(moviesAndYears.contains("Avatar"));
	    }
	
	    @Test
	    void testGetMovies() {
	        Database db = new Database();
	        String movies = db.getMovies();
	        assertNotNull(movies);
	        assertTrue(movies.contains("The Shawshank Redemption")); 
	    }
	}
