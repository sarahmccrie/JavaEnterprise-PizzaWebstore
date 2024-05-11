package ca.sheridancollege.mccries.utilities;

import java.time.LocalTime;
import java.util.Random;

/* Name: Sarah McCrie
* Assignment: Midterm
* Date: October 19, 2023
* Program: MidtermMccries
*/

public class RandomGenerator {

	public static Long generateRandomId() {
		Random i = new Random(LocalTime.now().toNanoOfDay());
		return i.nextLong(111111111, 999999999);
	}
}
