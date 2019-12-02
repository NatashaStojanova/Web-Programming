package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Natasha Stojanova
 */
@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Long> {
}