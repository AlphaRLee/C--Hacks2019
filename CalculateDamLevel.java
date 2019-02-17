

import java.util.Calendar;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class calculateDamLevel.
 */
public class CalculateDamLevel {

	/** The selected flow rate deviation. */
	// div_
	private double		selectedFlowRateDeviation;
	/** The my daily data. */
	DailyData			myDailyData;

	/** The my acceptable range percent. */
	private double		myAcceptableReservoirPercent	= .40;

	/** The my acceptable flow rate percent. */
	private double		myAcceptableFlowRatePercent		= .340;

	/** The my acceptable precipitation percent. */
	// int myAcceptablePrecipitationPercent = 0;
	private double		myAcceptablePrecipitationAmount	= 1;

	/** The change value. */
	double				changeValue						= 0;

	/** The my precip. */
	PrecipitationCSV	myPrecip;

	/** The my flow rate. */
	FlowRateCSV			myFlowRate;

	/** The my dam water level. */
	DamWaterLevelCSV	myDamWaterLevel;

	/**
	 * Instantiates a new calculate dam level.
	 *
	 * @param myDailyData the my daily data
	 */
	CalculateDamLevel(DailyData myDailyData) {
		System.out.println("REMEMBER TO SET FILE PATHS!!!");
		this.myDailyData = myDailyData;
		myPrecip = new PrecipitationCSV();
		//System.out.println("REMEMBER TO SET FILE PATHS!!!");
		// myPrecip.readCSV("../Example_Data/eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
		myPrecip.readCSV("../C--Hacks2019/Example_Data/eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
		myFlowRate = new FlowRateCSV();
		// myFlowRate.readCSV("../Example_Data/Daily__Feb-16-2019_09_00_16PM_DailyFlowRates_Canmore.csv");
		myFlowRate.readCSV("../C--Hacks2019/Example_Data/Daily__Feb-16-2019_09_00_16PM_DailyFlowRates_Canmore.csv");

		myDamWaterLevel = new DamWaterLevelCSV();
		// myDamWaterLevel.readCSV("../Example_Data/Daily__Feb-16-2019_11_38_47PM_GlenmoreDailyLevelData.csv");
		myDamWaterLevel.readCSV("../C--Hacks2019/Example_Data/Daily__Feb-16-2019_11_38_47PM_GlenmoreDailyLevelData.csv");
	}

	/**
	 * The modified main function
	 *
	 * @param date is day.month.year (all in string decimal formats), then 2 doubles.
	 */
	public static double calculateNextState(String day, String month, String year, double flowInput, double levelInput) {
		GregorianCalendar myDay = new GregorianCalendar();	//check 2019 0 17 for jan 17
		myDay.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
		DailyData myData = new DailyData();
		myData.setFlowRate(flowInput);

	//	myData.setPrecipitationLevel(2.9);	//FIX THIS! --> Should be fixed...? I think.
		myData.setPrecipitationLevel(precipValue(myDay));
		myData.setReservoirLevel(levelInput);
		myData.setTheDay(myDay);
		CalculateDamLevel myGo = new CalculateDamLevel(myData);
		myGo.calculate();
		// System.out.println(myGo.changeValue);
	//	myGo.sendNewValues(myGo.changeValue);
		return myGo.changeValue;


	}

	public static double precipValue(GregorianCalendar date)
	{
		PrecipitationCSV temp=new PrecipitationCSV();
		temp.readCSV("../C--Hacks2019/Example_Data/eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
		double retVal=temp.getAverageSpecifiedDate(date);
		if(retVal==0||retVal<0)
			return 0;
		else
			return retVal;
	}


	public static void main (String[] args)
	{
	//	tDay.set(2019, 0, 17);
		calculateNextState("17", "1", "2019", 50, 1076);
	}

	/**
	 * Calculate.
	 */
	void calculate() {
		DailyData previousTen = getPreviousNDayData(myDailyData.getTheDay(), 10);
		// dailyData averageDay = getAverage(myDailyData.theDay);
		DailyData averageDay = new DailyData();
		averageDay.setPrecipitationLevel(myPrecip.getAverageSpecifiedDate(myDailyData.getTheDay()));
		averageDay.setFlowRate(myFlowRate.getAverageSpecifiedDate(myDailyData.getTheDay()));
		averageDay.setReservoirLevel(myDamWaterLevel.getAverageSpecifiedDate(myDailyData.getTheDay()));
		averageDay.setTheDay(myDailyData.getTheDay());

		// the following outsideAverage return doubles
		outsideAverageReservoir(previousTen, averageDay);
		outsideAverageReservoir(myDailyData, averageDay);
		outsideAveragePrecipitationLevel(previousTen, averageDay);
		outsideAveragePrecipitationLevel(myDailyData, averageDay);
		outsideAverageFlowRate(previousTen, averageDay);
		outsideAverageFlowRate(myDailyData, averageDay);

		// sendNewValues(changeValue);

	}

	/**
	 * Send new values.
	 *
	 * @param sendingValue the sending value
	 */
	void sendNewValues(double sendingValue) {
		// TODO RICHARD do this
		System.out.println("mock sending " + sendingValue + " but no implementation");
	}

	/**
	 * Outside average reservoir.
	 *
	 * @param actual   the actual
	 * @param expected the expected
	 * @return the double
	 */
	double outsideAverageReservoir(DailyData actual, DailyData expected) {
		/*System.out.println("upper: " + (expected.getReservoirLevel() - 5 + (5 * (myAcceptableReservoirPercent + 1))));
		System.out.println("lower: " + (expected.getReservoirLevel() - 5 + (5 * (1 - myAcceptableReservoirPercent))));
		if (actual.getReservoirLevel() > (expected.getReservoirLevel()
				+ (expected.getReservoirLevel() * myAcceptableReservoirPercent))) {
			double myVal = actual.getReservoirLevel()
					- (expected.getReservoirLevel() + (expected.getReservoirLevel() * myAcceptableReservoirPercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.getReservoirLevel() < (expected.getReservoirLevel()
				- (expected.getReservoirLevel() * myAcceptableReservoirPercent))) {
			double myVal = actual.getReservoirLevel()
					- (expected.getReservoirLevel() - (expected.getReservoirLevel() * myAcceptableReservoirPercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
		*/
		double upperReservoir = (expected.getReservoirLevel() - 5 + (5 * (myAcceptableReservoirPercent + 1)));
		double lowerReservoir = (expected.getReservoirLevel() - 5 + (5 * (1 - myAcceptableReservoirPercent)));
		System.out.println("upperreservoir: " + upperReservoir);
		System.out.println("lowerreservoir: " + lowerReservoir);
		if (actual.getReservoirLevel() > upperReservoir) {
			double myVal = actual.getReservoirLevel()
					- (expected.getReservoirLevel() + (expected.getReservoirLevel() * myAcceptableReservoirPercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.getReservoirLevel() < lowerReservoir) {
			double myVal = actual.getReservoirLevel()
					- (expected.getReservoirLevel() - (expected.getReservoirLevel() * myAcceptableReservoirPercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}

	/**
	 * Outside average precipitation level. average is 1.1-10
	 *
	 * @param actual   the actual
	 * @param expected the expected
	 * @return the double
	 */
	double outsideAveragePrecipitationLevel(DailyData actual, DailyData expected) {
		/*
		 * if (actual.precipitationLevel > (expected.precipitationLevel +
		 * (expected.precipitationLevel * myAcceptablePrecipitationPercent))) { double
		 * myVal = actual.precipitationLevel - (expected.precipitationLevel +
		 * (expected.precipitationLevel * myAcceptablePrecipitationPercent));
		 * changeValue += myVal; return myVal; } else if (actual.precipitationLevel <
		 * (expected.precipitationLevel - (expected.precipitationLevel *
		 * myAcceptablePrecipitationPercent))) { double myVal =
		 * actual.precipitationLevel - (expected.precipitationLevel -
		 * (expected.precipitationLevel * myAcceptablePrecipitationPercent));
		 * changeValue += myVal; return myVal; } return 0;
		 */
		if (actual.getPrecipitationLevel() > myAcceptablePrecipitationAmount) {
			changeValue -= actual.getPrecipitationLevel() * .1;
			return -actual.getPrecipitationLevel() * .1;
		}
		return 0;
	}

	/**
	 * Outside average flow rate.
	 *
	 * @param actual   the actual
	 * @param expected the expected
	 * @return the double
	 */
	double outsideAverageFlowRate(DailyData actual, DailyData expected) {
		if (actual.getTheDay().get(Calendar.MONTH) == 0) {
			selectedFlowRateDeviation = 8.001246344;

		}
		if (actual.getTheDay().get(Calendar.MONTH) == 1) {
			selectedFlowRateDeviation = 7.052122398;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 2) {
			selectedFlowRateDeviation = 6.288792531;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 3) {
			selectedFlowRateDeviation = 10.92509048;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 4) {
			selectedFlowRateDeviation = 46.37734215;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 5) {
			selectedFlowRateDeviation = 86.21925987;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 6) {
			selectedFlowRateDeviation = 61.34243697;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 7) {
			selectedFlowRateDeviation = 31.90467578;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 8) {
			selectedFlowRateDeviation = 20.85746476;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 9) {
			selectedFlowRateDeviation = 15.28115358;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 10) {
			selectedFlowRateDeviation = 11.25376836;
		}
		if (actual.getTheDay().get(Calendar.MONTH) == 11) {
			selectedFlowRateDeviation = 9.110849294;
		}
		System.out.println("upperflow: "
				+ (expected.getFlowRate() + selectedFlowRateDeviation * (1 + myAcceptableFlowRatePercent)));
		System.out.println("lowerflow: "
				+ (expected.getFlowRate() + selectedFlowRateDeviation * (1 - myAcceptableFlowRatePercent)));
		if (actual.getFlowRate() > (expected.getFlowRate() + (expected.getFlowRate() * myAcceptableFlowRatePercent))) {
			double myVal = actual.getFlowRate()
					- (expected.getFlowRate() + (expected.getFlowRate() * myAcceptableFlowRatePercent));
			changeValue += myVal;
			return myVal;
		} else if (actual
				.getFlowRate() < (expected.getFlowRate() - (expected.getFlowRate() * myAcceptableFlowRatePercent))) {
			double myVal = actual.getFlowRate()
					- (expected.getFlowRate() - (expected.getFlowRate() * myAcceptableFlowRatePercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}

//	private static GregorianCalendar tDay= new GregorianCalendar();

	/**
	 * Gets the previous N day data.
	 *
	 * @param theDay the the day
	 * @param n      the n
	 * @return the previous N day data
	 */
	DailyData getPreviousNDayData(GregorianCalendar theDay, int n) {
		GregorianCalendar old_theDay = theDay;
		DailyData previousArr[] = new DailyData[n];
		theDay.add(Calendar.DAY_OF_MONTH, -1);
		//TODO: set AverageSpecified to Specified.
		DailyData toReturn = new DailyData();
		for (int i = 0; i < n; i++) {
			previousArr[i] = new DailyData();
			previousArr[i].setPrecipitationLevel( Double.parseDouble(myPrecip.getSpecifiedDate(theDay)));
			previousArr[i].setFlowRate( myFlowRate.getAverageSpecifiedDate(theDay));
			//previousArr[i].setFlowRate( Double.parseDouble(myFlowRate.getSpecifiedDate(theDay)));
			previousArr[i].setReservoirLevel(myDamWaterLevel.getAverageSpecifiedDate(theDay));
			//previousArr[i].setReservoirLevel( Double.parseDouble(myDamWaterLevel.getAverageSpecifiedDate(theDay)));
			previousArr[i].setTheDay(theDay);
			toReturn.setFlowRate(toReturn.getFlowRate() + previousArr[i].getFlowRate());
			toReturn.setPrecipitationLevel(toReturn.getPrecipitationLevel() + previousArr[i].getPrecipitationLevel());
			toReturn.setReservoirLevel(toReturn.getReservoirLevel() + previousArr[i].getReservoirLevel());
			theDay.add(Calendar.DAY_OF_MONTH, -1);
			//theDay.
		}
		toReturn.setTheDay(old_theDay);
		toReturn.setFlowRate(toReturn.getFlowRate() / n);
		toReturn.setPrecipitationLevel(toReturn.getPrecipitationLevel() / n);
		toReturn.setReservoirLevel(toReturn.getReservoirLevel() / n);
		return toReturn;

	}
}
