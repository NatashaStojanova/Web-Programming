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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        pizzaService.findAll().stream().forEach(pizza1 -> {
            if (pizza1.getName().equals(pizza.getName()))
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
    public ModelAndView getAllPizzas(@RequestParam(name = "totalIngredients", required = false, defaultValue = "0") Long totalIngredients) {
        List<Pizza> finalList = new ArrayList<>();
        if (totalIngredients > 0) {
            pizzaService.findAll().forEach(pizza1 -> {
                if (pizza1.getIngredientList().size() < totalIngredients) {
                    finalList.add(pizza1);
                }

            });

            ModelAndView modelAndView = new ModelAndView("list-pizzas");
            modelAndView.addObject("pizzas", finalList);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("list-pizzas");
            modelAndView.addObject("pizzas", pizzaService.findAll());
            return modelAndView;
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

    @GetMapping("/addPizza")
    public ModelAndView addPizza(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();

        ModelAndView modelAndView = new ModelAndView("add-pizza");
        modelAndView.addObject("pizza", new Pizza());
        return modelAndView;
    }

    @GetMapping("/editPizza/{id}")
    public ModelAndView editPizza(HttpServletRequest req, HttpServletResponse resp,
                                  @PathVariable(name = "id") Long pizzaID) throws UnsupportedEncodingException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();
        Pizza pizza = null;
        if (pizzaService.findById(pizzaID).isPresent()) {
            pizza = pizzaService.findById(pizzaID).get();
        } else
            throw new PizzaNotFoundException();

        ModelAndView modelAndView = new ModelAndView("edit-pizza");
        modelAndView.addObject("pizza", pizza);
        return modelAndView;
    }

    @GetMapping("/deletePizza/{id}")
    public ModelAndView deletePizza(HttpServletRequest req, HttpServletResponse resp,
                                    @PathVariable(name = "id") Long pizzaID) throws UnsupportedEncodingException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();

        Pizza pizza = null;
        if (pizzaService.findById(pizzaID).isPresent()) {
            pizza = pizzaService.findById(pizzaID).get();
        } else
            throw new PizzaNotFoundException();

        ModelAndView modelAndView = new ModelAndView("delete-pizza");
        modelAndView.addObject("pizza", pizza);
        return modelAndView;
    }
}