package org.spawny.triathlon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Constants {
	public static double toKmConversion = 1.609344;
	public static DecimalFormat twoDecPoints = new DecimalFormat("00.00");

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
