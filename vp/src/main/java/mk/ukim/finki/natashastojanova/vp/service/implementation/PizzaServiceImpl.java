package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.repository.IPizzaRepository;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasha Stojanova
 */
@Service
public class PizzaServiceImpl implements PizzaService {

    private IPizzaRepository pizzaRepository;

    public PizzaServiceImpl(IPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void save(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    @Override
    public List<Pizza> listPizzas() {
        return null;
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findByName(String name) {
        return pizzaRepository.findByName(name).get();
    }

    @Override
    public Page<Pizza> findPaginated(Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }

    @Override
    public List<Pizza> searchPizza(String term) {
        return pizzaRepository.findAllByNameContains(term);
    }

    /*@Override
    public List<Pizza> findSpicyPizzas() {
        return pizzaRepository.findAllByIngredientListContainsIngredientAndSpicy();
    }*/


}
