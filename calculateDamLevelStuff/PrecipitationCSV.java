import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Precipitation CSV
 */

/**
 * @author Leo
 *
 */
public class PrecipitationCSV extends ParseCSV {

	private final int YearRow=1;
	private final int MonthRow=2;
	private final int DayRow=3;
	private final int PrecipRow=19;
	
	/**
	 * 
	 */
	public PrecipitationCSV() {
		super();
		headLineNumber=26;
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getSpecifiedDate(java.util.GregorianCalendar)
	 */
	@Override
	protected String getSpecifiedDate(GregorianCalendar date) {
		int day=date.get(Calendar.DAY_OF_MONTH);
		int year=date.get(Calendar.YEAR);
		int month=date.get(Calendar.MONTH)+1;
		if(year<Integer.parseInt(beginningYEAR.replaceAll("\"", "")))
			return null;
		
		for(int i=headLineNumber; i<csv.size(); i++)
		{
			List<String> list=getList(i);
			
			int actualYear=Integer.parseInt(list.get(YearRow).replaceAll("\"", ""));
			int actualMonth=Integer.parseInt(list.get(MonthRow).replaceAll("\"", ""));
			int actualDay=Integer.parseInt(list.get(DayRow).replaceAll("\"", ""));
		//	System.out.println(actualDay+" "+ actualMonth + " "+actualYear);
			if(actualYear==year&&actualMonth==month&&actualDay==day)
			{	
				//System.out.println(list);
				return list.get(PrecipRow).replaceAll("\"", "");
			}
		}
		
//		
//		for (List<String> innerList : csv) {
//			counter=0;
//			for (String s : innerList) {
//				 
//				if(s.replaceAll("\"", "").equalsIgnoreCase(day)||s.replaceAll("\"", "").equalsIgnoreCase(month) ||s.replaceAll("\"", "").equalsIgnoreCase(year))
//					counter++;
//				System.out.println(s);
//				if(counter>=3)
//					counter++;
//				if(counter==19)
//					System.out.println("HERE IS SUPPOSED THING OF PRECIP: "+ s);
//			}
//			
//			System.out.println();
//		}
//		
//		
//		
		return null;
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getAverageSpecifiedDate(java.util.GregorianCalendar)
	 */
	@Override
	protected double getAverageSpecifiedDate(GregorianCalendar date) {
		return Double.parseDouble(getSpecifiedDate(date));
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getID() Also this is climate id
	 */
	@Override
	protected String getID() {
		if(csv==null)
			return null;
		else
			return csv.get(6).get(1).replaceAll("\"", "");
	}

	/* (non-Javadoc)
	 * @see ParseCSV#setYear()
	 */
	@Override
	protected void setYear() {
		if(csv==null)
			return;
		else
			beginningYEAR=csv.get(headLineNumber).get(YearRow).replaceAll("\"", "");
	}

	/* (non-Javadoc)
	 * @see ParseCSV#getYear(int)
	 */
	@Override
	protected int getYear(int row) {
		// TODO Auto-generated method stub
		return Integer.parseInt(beginningYEAR);
	}

}
