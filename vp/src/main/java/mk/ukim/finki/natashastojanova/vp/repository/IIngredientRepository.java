package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasha Stojanova
 */
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {
}
