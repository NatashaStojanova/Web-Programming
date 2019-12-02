package mk.ukim.finki.natashastojanova.vp.service;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;

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
}
