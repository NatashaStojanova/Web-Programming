package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasha Stojanova
 */
public interface IPizzaRepository extends JpaRepository<Pizza, Long> {
}
