package ca.sheridancollege.mccries.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* Name: Sarah McCrie
* Assignment: Midterm
* Date: October 19, 2023
* Program: MidtermMccries
*/

@Data
@NoArgsConstructor
public class Pizza {
	@NonNull
	private Long id;
	private String title;
	private double price;
	private String description;
	private String link;
	
}
