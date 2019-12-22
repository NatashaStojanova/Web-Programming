package mk.ukim.finki.natashastojanova.vp.service;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
public interface IngredientService {
    public void save(Ingredient ingredient);

    public void delete(Ingredient ingredient);

    public Optional<Ingredient> findById(Long id);

    public List<Ingredient> findAll();

    public Page<Ingredient> findPaginated(Pageable pageable);

    List<Ingredient> searchIngredients(String term);

}
