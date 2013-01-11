package helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberHelper {
	public static double round(double value, int numbCount)
	{
		BigDecimal bigDecimal = new BigDecimal(value);
		BigDecimal result =  bigDecimal.setScale(numbCount , RoundingMode.UP);
		return result.doubleValue();
	}

}
