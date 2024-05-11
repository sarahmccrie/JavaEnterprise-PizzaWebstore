
package ca.sheridancollege.mccries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.mccries.beans.Pizza;
import ca.sheridancollege.mccries.database.DatabaseAccess;
import ca.sheridancollege.mccries.utilities.RandomGenerator;

/* Name: Sarah McCrie
* Assignment: Midterm
* Date: October 19, 2023
* Program: MidtermMccries
*/

@Controller
public class PizzaController {

	@Autowired
	private DatabaseAccess da; // DatabaseAccess object

	boolean successfulAdd = false; // Sets a boolean called successfulAdd to false

	// Get mapping for index.html page
	@GetMapping("/")
	public String index(Model model) {
		Long num = RandomGenerator.generateRandomId(); // Uses RandomGenerator to generate random 9 digit id & stores as
														// num
		Pizza aPizza = new Pizza();
		aPizza.setId(Long.valueOf(num));
		aPizza.setTitle("");
		aPizza.setPrice(Double.valueOf(0.0));
		aPizza.setDescription("");
		aPizza.setLink("");
		model.addAttribute("pizza", aPizza);
		model.addAttribute("pizzaList", da.getPizzaList());
		return "index";
	}

	// Get mapping for viewPizzaRecord.html
	@GetMapping("/viewPizzaRecord")
	public String viewPasswordRecord(Model model) {
		model.addAttribute("pizzaList", da.getPizzaList());
		return "viewPizzaRecord";
	}

	// Get mapping for searchPizzaRecord.html
	@GetMapping("/searchPizzaRecord")
	public String searchPasswordRecord(Model model) {
		Pizza aPizza = new Pizza();
		aPizza.setId((long) 0000000);
		model.addAttribute("pizza", aPizza);
		return "searchPizzaRecord";
	}

	// Post mapping for index.html
	@PostMapping("/")
	public String index(Model model, @ModelAttribute Pizza pizza) {
		da.insertPizza(pizza);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("pizzaList", da.getPizzaList());
		successfulAdd = true; // Sets the boolean to true
		model.addAttribute("successfulAdd", successfulAdd);
		return "index";
	}

	// Post mapping for searchPizzaRecord.html
	@PostMapping("/searchPizzaRecord")
	public String searchPizzaRecord(Model model, @ModelAttribute Pizza pizza) {
		Pizza foundPizza = da.getPizzaById(pizza.getId());
		boolean isIdMatching = (foundPizza != null);
		model.addAttribute("isIdMatching", isIdMatching);
		model.addAttribute("foundPizza", foundPizza);
		return "searchPizzaRecord";
	}

	// Get mapping for deletePizzaById
	@GetMapping("/deletePizzaById/{id}")
	public String deletePizzaById(Model model, @PathVariable Long id) {
		da.deletePizzaById(id);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("pizzaList", da.getPizzaList());
		return "viewPizzaRecord";
	}
	
	@GetMapping("/editPizzaById/{id}")
	public String editPizzaById(Model model, @PathVariable Long id) {
		Pizza pizza = da.getPizzaListById(id).get(0);
		model.addAttribute("pizza", pizza);
		da.deletePizzaById(id);
		model.addAttribute("pizzaList", da.getPizzaList());
		return "index";
	}
}
