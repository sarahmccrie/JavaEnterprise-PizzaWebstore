package ca.sheridancollege.mccries.database;

import java.util.List;

import ca.sheridancollege.mccries.beans.Pizza;

/* Name: Sarah McCrie
* Assignment: Midterm
* Date: October 19, 2023
* Program: MidtermMccries
*/

public interface DatabaseAccess {

	public void insertPizza(Pizza pizza);

	public void insertPizzaHardCoded();

	public List<Pizza> getPizzaList();

	public Pizza getPizzaById(Long id);

	public void deletePizzaById(Long id);

	public List<Pizza> getPizzaListById(Long id);

}
