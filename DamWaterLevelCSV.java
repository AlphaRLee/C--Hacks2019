import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This is for handling the CSV for the Dam water level data
 */

/**
 * @author Leo
 *
 */
public class DamWaterLevelCSV extends ParseCSV {

	public static final int YEAR_SEPARATION=31;
	public static final int JAN= 5;
	public static final int FEB= 7;
	public static final int MAR= 9;
	public static final int APR= 11;
	public static final int MAY= 13;
	public static final int JUN= 15;
	public static final int JUL= 17;
	public static final int AUG= 19;
	public static final int SEP= 21;
	public static final int OCT= 23;
	public static final int NOV= 25;
	public static final int DEC= 27;
	public static final int DAY= 4;
	public static final int YEAR= 3;
	
	/**
	 * Simply Super() it
	 */
	public DamWaterLevelCSV() {
		super();
		headLineNumber=2;
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getSpecifiedDate(java.util.GregorianCalendar)
	 */
	@Override
	protected String getSpecifiedDate(GregorianCalendar date) {
		int day=date.get(Calendar.DAY_OF_MONTH);
		int year=date.get(Calendar.YEAR);
		int month=date.get(Calendar.MONTH);
		int monthRow=month*2+5;				
		if(year<Integer.parseInt(beginningYEAR))
		return null;
		int yearOffset=year-Integer.parseInt(beginningYEAR);
		String retVal=getString(yearOffset*YEAR_SEPARATION+headLineNumber+day-1, monthRow);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getAverageSpecifiedDate(java.util.GregorianCalendar)
	 */
	@Override
	protected double getAverageSpecifiedDate(GregorianCalendar date) {
		int day=date.get(Calendar.DAY_OF_MONTH);
		int month=date.get(Calendar.MONTH);
		int monthRow=month*2+5;				
		int genCounter=0, counter=0;
		double value=0;
		String retVal="";
		while(retVal!=null)
		{
			int line=genCounter*YEAR_SEPARATION+headLineNumber+day-1;
			retVal=getString(line, monthRow);
		//	System.out.println(getList(line) + "line and row are: "+line+" "+monthRow);
			if(retVal!=null&&!retVal.isEmpty())
			{
				if(day==29) {
					if(getYear(genCounter*YEAR_SEPARATION+headLineNumber+day-1)%100!=0)
					{
						counter++;
						value+=Double.parseDouble(retVal);
					}
				}
				else
				{
					counter++;
					value+=Double.parseDouble(retVal);
				}
				
			}
			genCounter++;
		}
		
		double ave=value/counter;
		return ave;
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getID()
	 */
	@Override
	protected String getID() {
		if(csv==null)
			return null;
		else
			return csv.get(headLineNumber).get(0);
	}

	/* (non-Javadoc)
	 * @see ParseCSV#setYear()
	 */
	@Override
	protected void setYear() {
		if(csv==null)
			return;
		else
			beginningYEAR=csv.get(headLineNumber).get(YEAR);
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getYear(int)
	 */
	@Override
	protected int getYear(int row) {
		int ret=0;
		try {
		ret= Integer.parseInt(getString(row, YEAR));
		}
		catch (Exception e)
		{
			return ret;
		}
		return ret;
	}

}
