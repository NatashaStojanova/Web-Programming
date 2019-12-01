package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.repository.PizzaRepository;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Natasha Stojanova
 */
@Service
public class PizzaServiceImpl implements PizzaService {

    private PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return pizzaRepository.getAllPizzas();
    }
}
