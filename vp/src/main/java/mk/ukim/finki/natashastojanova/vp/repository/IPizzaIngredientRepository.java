package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredientCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasha Stojanova
 */
public interface IPizzaIngredientRepository extends JpaRepository<PizzaIngredient, PizzaIngredientCompositeKey> {


}
