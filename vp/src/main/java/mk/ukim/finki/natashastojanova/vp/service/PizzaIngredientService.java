package mk.ukim.finki.natashastojanova.vp.service;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
public interface PizzaIngredientService {

    public List<PizzaIngredient> findAll();

    public void save(PizzaIngredient pizzaIngredient);

    public void deleteAllByPizza(Pizza pizza);
}
