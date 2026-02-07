package sait.mms.problemdomain;

public class Movie 
{

	//attributes
	private int duration;
	private String title;
	private int year;
	
	//non-default constructors
	public Movie(int duration, String title, int year)
	{
		this.duration = duration;
		this.title = title;
		this.year = year;
		
	}
	
	//public getter methods (3)
	public int getDuration()
	{
		return duration;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getYear()
	{
		return year;
	}
	//public format to file method
	public String formatToFile()
	{
		return duration + "," + title + "," + year;
	}

	//Overridden to-string method
	
	@Override
	public String toString()
	{
		return duration + "\t\t" + year + "\t" + title;
	}
	
}
