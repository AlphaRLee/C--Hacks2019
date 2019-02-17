package calculateDamLevelStuff;

import java.util.GregorianCalendar;

public class calculateDamLevel {
	
	dailyData	myDailyData;
	int			myAcceptableRangePercent	= 0;
	double		changeValue					= 0;
	
	calculateDamLevel(dailyData myDailyData) {
		this.myDailyData = myDailyData;
	}
	
	void calculate() {
		dailyData previousTen = getPreviousNDayData(myDailyData.theDay, 10);
		dailyData averageDay = getAverage(myDailyData.theDay);
		outsideAverageReservoir(previousTen, averageDay);
		outsideAverageReservoir(myDailyData, averageDay);
		outsideAveragePrecipitationLevel(previousTen, averageDay);
		outsideAveragePrecipitationLevel(myDailyData, averageDay);
		outsideAverageFlowRate(previousTen, averageDay);
		outsideAverageFlowRate(myDailyData, averageDay);
		
		sendNewValues();
		
	}
	
	double outsideAverageReservoir(dailyData actual, dailyData expected) {
		if (actual.reservoirLevel > (expected.reservoirLevel + (expected.reservoirLevel * myAcceptableRangePercent))) {
			double myVal = actual.reservoirLevel
					- (expected.reservoirLevel + (expected.reservoirLevel * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.reservoirLevel < (expected.reservoirLevel
				- (expected.reservoirLevel * myAcceptableRangePercent))) {
			double myVal = actual.reservoirLevel
					- (expected.reservoirLevel - (expected.reservoirLevel * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}
	
	double outsideAveragePrecipitationLevel(dailyData actual, dailyData expected) {
		if (actual.precipitationLevel > (expected.precipitationLevel + (expected.precipitationLevel * myAcceptableRangePercent))) {
			double myVal = actual.precipitationLevel
					- (expected.precipitationLevel + (expected.precipitationLevel * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.precipitationLevel < (expected.precipitationLevel
				- (expected.precipitationLevel * myAcceptableRangePercent))) {
			double myVal = actual.precipitationLevel
					- (expected.precipitationLevel - (expected.precipitationLevel * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}
	
	double outsideAverageFlowRate(dailyData actual, dailyData expected) {
		if (actual.flowRate > (expected.flowRate + (expected.flowRate * myAcceptableRangePercent))) {
			double myVal = actual.flowRate
					- (expected.flowRate + (expected.flowRate * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		} else if (actual.flowRate < (expected.flowRate
				- (expected.flowRate * myAcceptableRangePercent))) {
			double myVal = actual.flowRate
					- (expected.flowRate - (expected.flowRate * myAcceptableRangePercent));
			changeValue += myVal;
			return myVal;
		}
		return 0;
	}
	
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
