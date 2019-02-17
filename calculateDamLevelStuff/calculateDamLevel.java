package calculateDamLevelStuff;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class calculateDamLevel.
 */
public class calculateDamLevel {
	
	/** The my daily data. */
	dailyData	myDailyData;
	
	/** The my acceptable range percent. */
	double			myAcceptableReservoirPercent		= .340;
	
	/** The my acceptable flow rate percent. */
	double			myAcceptableFlowRatePercent			= .340;
	
	/** The my acceptable precipitation percent. */
	//int			myAcceptablePrecipitationPercent	= 0;
	double			myAcceptablePrecipitationAmount	= .340;
	
	/** The change value. */
	double		changeValue							= 0;
	
	/**
	 * Instantiates a new calculate dam level.
	 *
	 * @param myDailyData the my daily data
	 */
	calculateDamLevel(dailyData myDailyData) {
		this.myDailyData = myDailyData;
	}
	
	/**
	 * Calculate.
	 */
	void calculate() {
		dailyData previousTen = getPreviousNDayData(myDailyData.theDay, 10);
		dailyData averageDay = getAverage(myDailyData.theDay);
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
	double outsideAverageReservoir(dailyData actual, dailyData expected) {
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
	 * Outside average precipitation level.
	 *
	 * @param actual   the actual
	 * @param expected the expected
	 * @return the double
	 */
	double outsideAveragePrecipitationLevel(dailyData actual, dailyData expected) {
		/*if (actual.precipitationLevel > (expected.precipitationLevel
				+ (expected.precipitationLevel * myAcceptablePrecipitationPercent))) {
			double myVal = actual.precipitationLevel
					- (expected.precipitationLevel + (expected.precipitationLevel * myAcceptablePrecipitationPercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.precipitationLevel < (expected.precipitationLevel
				- (expected.precipitationLevel * myAcceptablePrecipitationPercent))) {
			double myVal = actual.precipitationLevel
					- (expected.precipitationLevel - (expected.precipitationLevel * myAcceptablePrecipitationPercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;*/
		if (actual.precipitationLevel > 
	}
	
	/**
	 * Outside average flow rate.
	 *
	 * @param actual   the actual
	 * @param expected the expected
	 * @return the double
	 */
	double outsideAverageFlowRate(dailyData actual, dailyData expected) {
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
	dailyData getPreviousNDayData(GregorianCalendar theDay, int n) {
		dailyData previousArr[] = new dailyData[n];
		dailyData toReturn;
		for (int i = 0; i < n; i++) {
			previousArr[i] = fetchDate(theDay);
			toReturn.flowRate += previousArr[i].flowRate;
			toReturn.flowRate += previousArr[i].precipitationLevel;
			toReturn.flowRate += previousArr[i].reservoirLevel;
		}
		return toReturn;
		
	}
}
