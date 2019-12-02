package mk.ukim.finki.natashastojanova.vp.web.controllers;

import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaAlreadyExistsException;
import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaNotFoundException;
import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaNotVeggieException;
import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.service.IngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaIngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Natasha Stojanova
 */
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private IngredientService ingredientService;
    private PizzaService pizzaService;
    private PizzaIngredientService pizzaIngredientService;

    public PizzaController(IngredientService ingredientService, PizzaService pizzaService, PizzaIngredientService pizzaIngredientService) {
        this.ingredientService = ingredientService;
        this.pizzaService = pizzaService;
        this.pizzaIngredientService = pizzaIngredientService;
    }

    @PostMapping
    public void addPizza(@ModelAttribute Pizza pizza) {
        //save pizza
        if (pizza.getVeggie()) {
            pizza.getIngredientList().forEach(ing -> {
                if (!ing.getIngredient().isVegie())
                    throw new PizzaNotVeggieException();
            });
        }
        pizzaService.findAll().forEach(pizza1 -> {
            if (pizza.getName().equals(pizza.getName()))
                throw new PizzaAlreadyExistsException();
        });

        pizzaService.save(pizza);

    }

    @PatchMapping("/{id}")
    public void editPizza(@ModelAttribute Pizza pizza, @PathVariable Long id) {
        pizza.setId(id);
        if (pizzaService.findById(id).isPresent()) {
            pizzaService.save(pizza);
        } else {
            throw new PizzaNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Long id) {

        if (pizzaService.findById(id).isPresent()) {
            Pizza pizza = pizzaService.findById(id).get();
            pizzaService.delete(pizza);
        } else {
            throw new PizzaNotFoundException();
        }
    }

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(name = "totalIngredients", required = false, defaultValue = "0") Long totalIngredients) {
        List<Pizza> finalList = new ArrayList<>();
        if (totalIngredients > 0) {
            pizzaService.findAll().forEach(pizza1 -> {
                if (pizza1.getIngredientList().size() < totalIngredients) {
                    finalList.add(pizza1);
                }

            });

            return finalList;
        } else {
            return pizzaService.findAll();
        }
    }

    @GetMapping("/{id}")
    public Pizza getPizzaId(@PathVariable Long id) {
        if (pizzaService.findById(id).isPresent()) {
            Pizza pizza = pizzaService.findById(id).get();
            return pizza;
        } else {
            throw new PizzaNotFoundException();
        }
    }

    @GetMapping("/compare")
    public List<Ingredient> getMutualIngredients(@RequestParam(name = "pizza1") Long id1, @RequestParam(name = "pizza2") Long id2) {
        List<Ingredient> pizzaIngredients = new ArrayList<>();
        if (pizzaService.findById(id1).isPresent() && pizzaService.findById(id2).isPresent()) {
            Pizza p1 = pizzaService.findById(id1).get();
            Pizza p2 = pizzaService.findById(id2).get();
            p1.getIngredientList().forEach(ing1 -> {
                p2.getIngredientList().forEach(ing2 -> {
                    if (ing1.getIngredient().equals(ing2.getIngredient())) {
                        pizzaIngredients.add(ing1.getIngredient());
                    }
                });
            });
            return pizzaIngredients;
        } else {
            throw new PizzaNotFoundException();
        }
    }
}