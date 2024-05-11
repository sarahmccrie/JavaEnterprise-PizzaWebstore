package ca.sheridancollege.mccries.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mccries.beans.Pizza;

/* Name: Sarah McCrie
* Assignment: Midterm
* Date: October 19, 2023
* Program: MidtermMccries
*/

@Repository
public class DatabaseAccessImpl implements DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	// Insert password hardcoded method.
	public void insertPizzaHardCoded() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO PIZZAS(id, title, price, description, link) VALUES (123456789, 'SampleTitle', 12.99, 'SampleDesc', 'SampleLink')";

		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("Hard coded pizza inserted into database");
	}

	// Insert password method.
	public void insertPizza(Pizza pizza) {

		String query = "INSERT INTO PIZZAS(id, title, price, description, link) VALUES (:id, :title, :price, :description, :link)";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", pizza.getId());
		namedParameters.addValue("title", pizza.getTitle());
		namedParameters.addValue("price", pizza.getPrice());
		namedParameters.addValue("description", pizza.getDescription());
		namedParameters.addValue("link", pizza.getLink());

		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("A pizza was inserted into database");
	}

	// Get Password List method.
	public List<Pizza> getPizzaList() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM PIZZAS";

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Pizza>(Pizza.class));

	}

	// Get Password by Id method
	public Pizza getPizzaById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM PIZZAS WHERE id = :id";

		namedParameters.addValue("id", id);

		List<Pizza> pizza = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Pizza>(Pizza.class));

		return pizza.isEmpty() ? null : pizza.get(0);
	}

	// For delete functionality to be used in the future potentially.
	public void deletePizzaById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "DELETE FROM PIZZAS WHERE id = :id";

		namedParameters.addValue("id", id);

		if (jdbc.update(query, namedParameters) > 0)

			System.out.println("Deleted record " + id + " from database.");
	}

	public List<Pizza> getPizzaListById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM pizzas WHERE id = :id";

		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Pizza>(Pizza.class));

	}
}
