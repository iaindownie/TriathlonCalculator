package bto.android;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Class to help define edit text fields for mins and secs (range: 0-59)
 * 
 * @author iaindownie Code courtesy of
 * http://stackoverflow.com/users/556975/pratik-sharma
 * http://stackoverflow.com/questions/14212518/is-there-any-way-to-define
 * -a-min-and-max-value-for-edittext-in-android
 *
 */

public class InputFilterMinMax implements InputFilter {
	private static final int MIN_SIG_FIG = 2;
	private BigDecimal min, max;

	public InputFilterMinMax(BigDecimal min, BigDecimal max) {
		this.min = min;
		this.max = max;
	}

	public InputFilterMinMax(String min, String max) {
		this.min = new BigDecimal(min);
		this.max = new BigDecimal(max);
	}

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {
		try {
			BigDecimal input = formatStringToBigDecimal(dest.toString()
					+ source.toString());

			if (isInRange(min, max, input)) {

				return null;
			}
		} catch (NumberFormatException nfe) {

		}
		return "";
	}

	private boolean isInRange(BigDecimal a, BigDecimal b, BigDecimal c) {
		return b.compareTo(a) > 0 ? c.compareTo(a) >= 0 && c.compareTo(b) <= 0
				: c.compareTo(b) >= 0 && c.compareTo(a) <= 0;
	}

	public static BigDecimal formatStringToBigDecimal(String n) {

		Number number = null;
		try {
			number = getDefaultNumberFormat().parse(n.replaceAll("[^\\d]", ""));

			BigDecimal parsed = new BigDecimal(number.doubleValue()).divide(
					new BigDecimal(100), 2, BigDecimal.ROUND_UNNECESSARY);
			return parsed;
		} catch (ParseException e) {
			return new BigDecimal(0);
		}
	}

	private static NumberFormat getDefaultNumberFormat() {
		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
		nf.setMinimumFractionDigits(MIN_SIG_FIG);
		return nf;
	}
}