package mk.ukim.finki.natashastojanova.vp.service;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
public interface PizzaService {
    public void save(Pizza pizza);

    public void delete(Pizza pizza);

    List<Pizza> listPizzas();

    public Optional<Pizza> findById(Long id);

    public List<Pizza> findAll();

    public Pizza findByName(String name);

    public Page<Pizza> findPaginated(Pageable pageable);
}
