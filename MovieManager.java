package sait.mms.manager;
import sait.mms.problemdomain.Movie;
import java.util.*;
import java.io.*;

public class MovieManager 
{
	//attributes
	private ArrayList<Movie> movies = new ArrayList<>();
	private Scanner keyboard = new Scanner(System.in);
	
	public MovieManager() 
	{
		loadMovieList();
		displayMenu();
	}

	//loadMovieList
	private void loadMovieList()
	{
		File file = new File("res/movies.txt");
		try 
		{
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) 
			{
				String line = in.nextLine();
				String[] tokens = line.split(",");
				Movie m = new Movie(
						Integer.parseInt(tokens[0].trim()),
						tokens[1].trim(),
						Integer.parseInt(tokens[2].trim()));
				movies.add(m);
			}
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	//displayMenu
	public void displayMenu() 
	{
		System.out.println("Movie Management System");
		System.out.println("1	Add New Movie and Save");
		System.out.println("2	Generate List of Random Movies Released in a Year");
		System.out.println("3	Generate List of Random Movies");
		System.out.println("4	Exit");
		System.out.println();
		System.out.print("Enter an option: ");

		String option = keyboard.nextLine();
		if(option.equals("1"))
		{
			addMovie();
		}
		
		else if(option.equals("2")) 
		{
			generateMovieListInYear();
		}
		
		else if(option.equals("3"))
		{
			generateRandomMovieList();
		}
		
		else if(option.equals("4"))
		{
			saveMovieListToFile();
		}
	
		else 
		{
			System.out.println("Invalid Option!");
		}

	}
	
	//addMovie
	public void addMovie() 
	{
		System.out.println("Enter duration: ");
		int duration = Integer.parseInt(keyboard.nextLine());
		System.out.println("Enter movie title: ");
		String title = keyboard.nextLine();
		System.out.println("Enter year: ");
		int year = Integer.parseInt(keyboard.nextLine());
		System.out.println("Saving movies...");
		movies.add(new Movie(duration, title, year));
		System.out.println("Added movie to data file!");
	}
 
	//generateMovieListInYear
	public void generateMovieListInYear() 
	{
		System.out.println("Enter in year: ");
		int inyear = Integer.parseInt(keyboard.nextLine());
			
		int totalDuration = 0; 
		System.out.println("\nMovie List");
		System.out.println("Duration\tYear\tTitle");
			
		for(Movie m : movies) 
		{				
			if(m.getYear() == inyear) 
			{
				System.out.println(m.getDuration() + "\t\t" + m.getYear() + "\t" + m.getTitle());
				totalDuration += m.getDuration();
			}
		}
		
		System.out.println("\nTotal duration: " + totalDuration + " minutes");
	} 
		
	//generateRandomMovieList
	public void generateRandomMovieList()
	{
		System.out.println("Enter number of movies: ");
		int numofmovies = Integer.parseInt(keyboard.nextLine());
		Random rand = new Random();
		int totalDuration = 0;
			
		for(int i = 0; i < numofmovies; i++) 
		{
			Movie m = movies.get(rand.nextInt(movies.size()));
			System.out.println(m.getDuration() + "\t\t" + m.getYear() + "\t" + m.getTitle());
			totalDuration += m.getDuration();
		}
			
		System.out.println("\nTotal duration: " + totalDuration + " minutes");
	}
		
	//saveMovieListToFile
	private void saveMovieListToFile() 
	{
		File file = new File("res/movies.txt");
		ArrayList<Movie> movies = new ArrayList<>();
		try 
		{
			Scanner in = new Scanner(file);
			while(in.hasNext()) 
			{
				String line = in.nextLine();
				String[] tokens = line.split(",");
				Movie m = new Movie(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]));
				movies.add(m);
				System.out.println(m);
			}
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			PrintWriter out = new PrintWriter(file);
			for(Movie m : movies)
			{
				out.println(m.formatToFile());
			}
			out.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}

 