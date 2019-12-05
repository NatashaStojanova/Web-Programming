package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredientCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * @author Natasha Stojanova
 */
public interface IPizzaIngredientRepository extends JpaRepository<PizzaIngredient, PizzaIngredientCompositeKey> {

    @Transactional
    @Modifying
    void deletePizzaIngredientByPizza(Pizza pizza);

}
