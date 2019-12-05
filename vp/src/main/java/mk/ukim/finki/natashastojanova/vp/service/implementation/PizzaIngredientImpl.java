package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.repository.IPizzaIngredientRepository;
import mk.ukim.finki.natashastojanova.vp.service.PizzaIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
@Service
public class PizzaIngredientImpl implements PizzaIngredientService {

    private IPizzaIngredientRepository pizzaIngredientRepository;

    public PizzaIngredientImpl(IPizzaIngredientRepository pizzaIngredientRepository) {
        this.pizzaIngredientRepository = pizzaIngredientRepository;
    }

    @Override
    public List<PizzaIngredient> findAll() {
        return pizzaIngredientRepository.findAll();
    }

    @Override
    public void save(PizzaIngredient pizzaIngredient) {
        pizzaIngredientRepository.save(pizzaIngredient);
    }

    @Override
    public void deleteAllByPizza(Pizza pizza) {
        pizzaIngredientRepository.deletePizzaIngredientByPizza(pizza);
    }

}
