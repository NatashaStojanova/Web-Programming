package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByName(String name);

    List<Pizza> findAllByNameContains(String term);
}
