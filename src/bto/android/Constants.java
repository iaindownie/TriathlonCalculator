package bto.android;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Constants {
	public static double toKmConversion = 1.609344;
	public static double toYardConversion = 1.093613;
	public static DecimalFormat twoDecPoints = new DecimalFormat("00.00");

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static double convertSeconds2DecimalHours(Double dist,
			Double totalSeconds) {
		double myMins = totalSeconds / 60;
		int finalMins = (int) myMins;
		int mySecs = (int) (totalSeconds - ((int) myMins * 60));
		double decMins = (finalMins + new Double(mySecs / 60.0)) / 60;
		return decMins;
	}
}
