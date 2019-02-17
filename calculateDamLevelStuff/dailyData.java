package calculateDamLevelStuff;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class dailyData.
 *
 * @author arebe
 */
public class DailyData {
	
	public GregorianCalendar getTheDay() {
		return theDay;
	}

	public void setTheDay(GregorianCalendar theDay) {
		this.theDay = theDay;
	}

	public double getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(double flowRate) {
		this.flowRate = flowRate;
	}

	public double getPrecipitationLevel() {
		return precipitationLevel;
	}

	public void setPrecipitationLevel(double precipitationLevel) {
		this.precipitationLevel = precipitationLevel;
	}

	public double getReservoirLevel() {
		return reservoirLevel;
	}

	public void setReservoirLevel(double reservoirLevel) {
		this.reservoirLevel = reservoirLevel;
	}

	/** The day. */
	GregorianCalendar	theDay;
	
	/** The flow rate. */
	double				flowRate;
	
	/** The precipitation level. */
	double				precipitationLevel;
	
	/** The reservoir level. */
	double				reservoirLevel;
}
