package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {

}
