package mk.ukim.finki.natashastojanova.vp.web.controllers;

import mk.ukim.finki.natashastojanova.vp.exceptions.IngredientAlreadyExistsException;
import mk.ukim.finki.natashastojanova.vp.exceptions.IngredientNotFoundException;
import mk.ukim.finki.natashastojanova.vp.exceptions.NoMoreSpicyIngredientsException;
import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.service.IngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaIngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Natasha Stojanova
 */
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private IngredientService ingredientService;
    private PizzaIngredientService pizzaIngredientService;
    private PizzaService pizzaService;

    public IngredientController(IngredientService ingredientService, PizzaIngredientService pizzaIngredientService, PizzaService pizzaService) {
        this.ingredientService = ingredientService;
        this.pizzaIngredientService = pizzaIngredientService;
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public void addIngredient(@ModelAttribute Ingredient ingredient) {
        //add new ingredient
        ingredientService.findAll().stream().forEach(ingredient1 -> {
            if (ingredient1.getName().equals(ingredient.getName()))
                throw new IngredientAlreadyExistsException();
        });
        if (ingredientService.findAll().stream()
                .filter(Ingredient::isSpicy)
                .collect(Collectors.toList()).size() >= 4) {
            throw new NoMoreSpicyIngredientsException();
        }

        ingredientService.save(ingredient);

    }

    @PatchMapping("/{id}")
    public void editIngredient(@ModelAttribute Ingredient ingredient, @PathVariable Long id) {
        //edit Ingredient
        ingredient.setId(id);
        if (ingredientService.findById(id).isPresent()) {
            ingredientService.save(ingredient);
        } else {
            throw new IngredientNotFoundException();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        //delete Ingredient
        if (ingredientService.findById(id).isPresent()) {
            Ingredient ingredient = ingredientService.findById(id).get();
            ingredientService.delete(ingredient);
        }
        throw new IngredientNotFoundException();
    }

    @GetMapping
    public List<Ingredient> getIngredient(@RequestParam(name = "spicy", required = false) boolean spicy) {
        List<Ingredient> allIngredients = ingredientService.findAll();
        if (!spicy) {
            Collections.sort(allIngredients);
            return allIngredients;
        } else {
            return allIngredients.stream()
                    .filter(Ingredient::isSpicy)
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Long id) {
        //get Ingredient
        if (ingredientService.findById(id).isPresent()) {
            Ingredient ingredient = ingredientService.findById(id).get();
            return ingredient;
        }
        throw new IngredientNotFoundException();
    }

    @GetMapping("/{id}/pizzas")
    public List<Pizza> getAllPizzasWithId(@PathVariable Long id) {
        return pizzaIngredientService.findAll()
                .stream()
                .filter(ing -> ing.getIngredient().getId().equals(id)).map(PizzaIngredient::getPizza).collect(Collectors.toList());
    }

    @GetMapping("/addIngredient")
    public ModelAndView addIngredient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();

        ModelAndView modelAndView = new ModelAndView("add-ingredient");
        modelAndView.addObject("ingredient", new Ingredient());
        //modelAndView.addObject("bodyContent", "add-ingredient");
        return modelAndView;
    }
}

