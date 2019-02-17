
package calculateDamLevelStuff;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


/**
 * @author Leo
 * Theoretically this should parse the CSV files that we have
 * i.e., Flow data, dam data, precipitation.
 */
public abstract class ParseCSV {
	
	/**
	 * We are using this as a delimiter.
	 */
	protected static final String COMMA_DELIMITER = ",";
	/**
	 * The year might be different for different csv files, ie 1978 vs 1989 etc.
	 */
	protected String beginningYEAR;
	/**
	 * This is the number of lines that can be ignored before the actual data portion.
	 * It is noted that the header for the csv files can be important and we expect
	 * whoever to use this to know of it.
	 */
	protected int headLineNumber;
	//for flow rates
	
	
	/**
	 * This is the actual .csv file in List<List<String>> format.
	 */
	protected List<List<String>> csv;
	
	/**
	 * Sets headLineNumber to zero and csv to null.
	 */
	ParseCSV()
	{
		headLineNumber=0;
		csv=null;
	}
	
	/**
	 * @param date is the date that you want the data of
	 * @return the data in String format since this is a csv file.
	 */
	abstract protected String getSpecifiedDate(GregorianCalendar date);
	
	/**
	 * @param date is the date that you want the average data of. 
	 * Only day and month will be checked, not year, etc.
	 * @return The Data in double format. if it's an invalid date then -1 will be returned.
	 */
	abstract protected double getAverageSpecifiedDate(GregorianCalendar date);
	
	/**
	 * @return the id if the csv exists, else null.
	 */
	abstract protected String getID();
	
	/**
	 * Sets the year, needs to be called after the readCSV function.
	 */
	abstract protected void setYear();
	
	/**
	 * @return the year at a specified row, just to check if centennial year
	 */
	abstract protected int getYear(int row);
	
	/**
	 * @return the csv in List<List<String>> format.
	 */
	protected List<List<String>> get2DList()
	{
		return csv;
	}
	
	/**
	 * @param index is the index of the list/line
	 * @return null if invalid, else the line. Can be any valid List<String>
	 */
	protected List<String> getList(int index)
	{
		if(index>=csv.size()||index<0||csv==null)
			return null;
		else
			return csv.get(index);
	}
	
	/**
	 * @param outerIndex is selector for which list/line
	 * @param innerIndex is selector for which string inside the list/line
	 * @return null if outside of range, else a string for specified. (can be "" string)
	 */
	protected String getString(int outerIndex, int innerIndex) {
		if(outerIndex>=csv.size()||outerIndex<0||innerIndex<0||csv==null)
			return null;
		
		if (innerIndex>=csv.get(outerIndex).size())
			return null;
		else
			return csv.get(outerIndex).get(innerIndex);
	}
	
	// Java code read a CSV file line by line into List<List<String>> 2d list
	/**
	 * Source: https://www.baeldung.com/java-csv-file-array adapted.
	 * @param file is the filepath to the file
	 * @return the actual csv file in List<List<String>> format if you want, else null.
	 */
	protected List<List<String>> readCSV(String file) 
	{ 
	  
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(file));) {
		    while (scanner.hasNextLine()) {
		        records.add(getRecordFromLine(scanner.nextLine()));
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+file);
			//e.printStackTrace();
			return null;
		}
		csv=records;
		setYear();
		return records;
	}
	
	/**
	 * This is called by another function.
	 * @param line is a parameter from another function that calls this.
	 * @return List<String> that is gotten.
	 */
	protected List<String> getRecordFromLine(String line) {
	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(COMMA_DELIMITER);
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
	

	

	
	/**
	 * this is a public static etc to test the thing.
	 */
	public static void main(String[] args) {
		ParseCSV obj=new FlowRateCSV();
		List<List<String>> DailyFlowRate_Canmore=obj.readCSV("../C--Hacks2019/Example_Data/Daily__Feb-16-2019_09_00_16PM_DailyFlowRates_Canmore.csv");
	/*	
		for (List<String> innerList : DailyFlowRate_Canmore) {
			for (String s : innerList) {
				System.out.print("\t" + s);
			}
			
			System.out.println();
		}
	*/
		
		//Here we ignore the first 2 lists from DailyFlowRate_Canmore
		GregorianCalendar date=new GregorianCalendar();
	/*	date.set(1990, 0, 2);
		
		System.out.println(DailyFlowRate_Canmore.get(34).get(5));
		System.out.println(obj.getSpecifiedDate(date));
		System.out.println(obj.getAverageSpecifiedDate(date));
	*/	
		obj=new DamWaterLevelCSV();
		obj.readCSV("../C--Hacks2019/Example_Data/Daily__Feb-16-2019_11_38_47PM_GlenmoreDailyLevelData.csv");
		obj=new PrecipitationCSV();
		obj.readCSV("../C--Hacks2019/Example_Data/eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
	
		
		date.setLenient(false);
		date.set(2019, 0, 17);
		System.out.println(date);
		//System.out.println(obj.getAverageSpecifiedDate(date));
	//	System.out.println("Avg is: ");
		System.out.println(obj.getAverageSpecifiedDate(date));
	}

}
