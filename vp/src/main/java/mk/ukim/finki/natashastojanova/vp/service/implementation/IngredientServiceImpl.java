package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.repository.IIngredientRepository;
import mk.ukim.finki.natashastojanova.vp.repository.IPizzaIngredientRepository;
import mk.ukim.finki.natashastojanova.vp.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private IIngredientRepository ingredientRepository;

    public IngredientServiceImpl(IIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

}