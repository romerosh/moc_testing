package helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberHelper {
	public static double round(double value, int numbCount)
	{
		if(numbCount <0)
			return value;
		BigDecimal bigDecimal = new BigDecimal(value);
		BigDecimal result =  bigDecimal.setScale(numbCount , RoundingMode.HALF_UP);
		return result.doubleValue();
	}

}
