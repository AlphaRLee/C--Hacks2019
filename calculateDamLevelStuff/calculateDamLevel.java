package calculateDamLevelStuff;

import java.util.Calendar;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class calculateDamLevel.
 */
public class CalculateDamLevel {
	
	
	/** The my daily data. */
	DailyData			myDailyData;
	
	/** The my acceptable range percent. */
	double				myAcceptableReservoirPercent	= .340;
	
	/** The my acceptable flow rate percent. */
	double				myAcceptableFlowRatePercent		= .340;
	
	/** The my acceptable precipitation percent. */
	// int myAcceptablePrecipitationPercent = 0;
	double				myAcceptablePrecipitationAmount	= 1;
	
	/** The change value. */
	double				changeValue						= 0;
	
	PrecipitationCSV	myPrecip;
	FlowRateCSV			myFlowRate;
	DamWaterLevelCSV	myDamWaterLevel;
	
	/**
	 * Instantiates a new calculate dam level.
	 *
	 * @param myDailyData the my daily data
	 */
	CalculateDamLevel(DailyData myDailyData) {
		this.myDailyData = myDailyData;
		myPrecip = new PrecipitationCSV();
		//myPrecip.readCSV("../Example_Data/eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
		myPrecip.readCSV("eng-daily-01012019-12312019_dailyPrecip_calgary.csv");
		myFlowRate = new FlowRateCSV();
		//myFlowRate.readCSV("../Example_Data/Daily__Feb-16-2019_09_00_16PM_DailyFlowRates_Canmore.csv");
		myFlowRate.readCSV("Daily__Feb-16-2019_09_00_16PM_DailyFlowRates_Canmore.csv");
		
		myDamWaterLevel = new DamWaterLevelCSV();
		//myDamWaterLevel.readCSV("../Example_Data/Daily__Feb-16-2019_11_38_47PM_GlenmoreDailyLevelData.csv");
		myDamWaterLevel.readCSV("Daily__Feb-16-2019_11_38_47PM_GlenmoreDailyLevelData.csv");
	}
	public static void main(String[] args) {
		GregorianCalendar myDay = new GregorianCalendar();
		myDay.set(2019, 0, 17);
		DailyData myData = new DailyData();
		myData.setFlowRate(50);
		myData.setPrecipitationLevel(2.9);
		myData.setReservoirLevel(1076);
		myData.setTheDay(myDay);
		CalculateDamLevel myGo = new CalculateDamLevel(myData);
		myGo.calculate();
		System.out.println(myGo.changeValue);
	}
	/**
	 * Calculate.
	 */
	void calculate() {
		DailyData previousTen = getPreviousNDayData(myDailyData.theDay, 10);
		//dailyData averageDay = getAverage(myDailyData.theDay);
		DailyData averageDay = new DailyData();
		averageDay.precipitationLevel = myPrecip.getAverageSpecifiedDate(myDailyData.theDay);
		averageDay.flowRate = myFlowRate.getAverageSpecifiedDate(myDailyData.theDay);
		averageDay.reservoirLevel = myDamWaterLevel.getAverageSpecifiedDate(myDailyData.theDay);
		
		// the following outsideAverage return doubles
		outsideAverageReservoir(previousTen, averageDay);
		outsideAverageReservoir(myDailyData, averageDay);
		outsideAveragePrecipitationLevel(previousTen, averageDay);
		outsideAveragePrecipitationLevel(myDailyData, averageDay);
		outsideAverageFlowRate(previousTen, averageDay);
		outsideAverageFlowRate(myDailyData, averageDay);
		
		sendNewValues(changeValue);
		
	}
	
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
		if (actual.reservoirLevel > (expected.reservoirLevel
				+ (expected.reservoirLevel * myAcceptableReservoirPercent))) {
			double myVal = actual.reservoirLevel
					- (expected.reservoirLevel + (expected.reservoirLevel * myAcceptableReservoirPercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.reservoirLevel < (expected.reservoirLevel
				- (expected.reservoirLevel * myAcceptableReservoirPercent))) {
			double myVal = actual.reservoirLevel
					- (expected.reservoirLevel - (expected.reservoirLevel * myAcceptableReservoirPercent));
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
		if (actual.precipitationLevel > myAcceptablePrecipitationAmount) {
			changeValue -= actual.precipitationLevel;
			return -actual.precipitationLevel;
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
		if (actual.flowRate > (expected.flowRate + (expected.flowRate * myAcceptableFlowRatePercent))) {
			double myVal = actual.flowRate - (expected.flowRate + (expected.flowRate * myAcceptableFlowRatePercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.flowRate < (expected.flowRate - (expected.flowRate * myAcceptableFlowRatePercent))) {
			double myVal = actual.flowRate - (expected.flowRate - (expected.flowRate * myAcceptableFlowRatePercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}
	
	/**
	 * Gets the previous N day data.
	 *
	 * @param theDay the the day
	 * @param n      the n
	 * @return the previous N day data
	 */
	DailyData getPreviousNDayData(GregorianCalendar theDay, int n) {
		DailyData previousArr[] = new DailyData[n];
		
		DailyData toReturn = new DailyData();
		for (int i = 0; i < n; i++) {
			previousArr[i] = new DailyData();
			previousArr[i].precipitationLevel = Double.parseDouble(myPrecip.getSpecifiedDate(theDay));
			previousArr[i].flowRate = myFlowRate.getAverageSpecifiedDate(theDay);
			previousArr[i].reservoirLevel = myDamWaterLevel.getAverageSpecifiedDate(myDailyData.theDay);
			toReturn.flowRate += previousArr[i].flowRate;
			toReturn.precipitationLevel += previousArr[i].precipitationLevel;
			toReturn.reservoirLevel += previousArr[i].reservoirLevel;
			theDay.add(Calendar.DAY_OF_MONTH, -1);
		}
		return toReturn;
		
	}
}
