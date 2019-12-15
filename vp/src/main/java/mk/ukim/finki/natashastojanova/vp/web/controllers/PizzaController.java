package mk.ukim.finki.natashastojanova.vp.web.controllers;

import mk.ukim.finki.natashastojanova.vp.exceptions.IngredientNotFoundException;
import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaAlreadyExistsException;
import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaNotFoundException;
import mk.ukim.finki.natashastojanova.vp.exceptions.PizzaNotVeggieException;
import mk.ukim.finki.natashastojanova.vp.model.Ingredient;
import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredient;
import mk.ukim.finki.natashastojanova.vp.model.PizzaIngredientCompositeKey;
import mk.ukim.finki.natashastojanova.vp.service.IngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaIngredientService;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import java.util.stream.Collectors;

/**
 * @author Natasha Stojanova
 */
@RestController
@RequestMapping("/pizzas")
@CrossOrigin("*")
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
    public ModelAndView addPizza(@ModelAttribute(name = "pizza") Pizza newPizza, @RequestParam(name = "newIngredients") ArrayList<Long> newIngredients) {
        //add new pizza
        pizzaService.findAll().forEach(pizza1 -> {
            if (pizza1.getName().equals(newPizza.getName()))
                throw new PizzaAlreadyExistsException();
        });

        if (newPizza.getVeggie()) {
            List<Ingredient> ings = new ArrayList<>();
            newIngredients.forEach(ingID -> {
                if (ingredientService.findById(ingID).isPresent()) {
                    Ingredient ingredient = ingredientService.findById(ingID).get();
                    ings.add(ingredient);
                } else
                    throw new IngredientNotFoundException();
            });
            //sega vo ings imam lista od ingredients i treba da proveram dali se veggie
            ings.forEach(ing -> {
                if (!ing.isVegie()) {
                    throw new PizzaNotVeggieException();
                }
            });
        }

        pizzaService.save(newPizza);
        Pizza pizza = pizzaService.findByName(newPizza.getName());
        List<PizzaIngredient> pizzaIngredients = new ArrayList<>();
        //for each id in newIngredients(which is List), check if the id is present,does it exist in db
        newIngredients.forEach(ingID -> {
            if (ingredientService.findById(ingID).isPresent()) {
                PizzaIngredient pizzaIngredient = new PizzaIngredient();
                pizzaIngredient.setPizza(pizza);
                pizzaIngredient.setIngredient(ingredientService.findById(ingID).get());
                PizzaIngredientCompositeKey compositeKey = new PizzaIngredientCompositeKey();
                compositeKey.setPizzaId(pizza.getId());
                compositeKey.setIngredientid(ingID);
                pizzaIngredient.setId(compositeKey);
                pizzaIngredients.add(pizzaIngredient);
                pizzaIngredientService.save(pizzaIngredient);
            } else
                throw new IngredientNotFoundException();
        });
        pizza.setIngredientList(pizzaIngredients);
        pizzaService.save(pizza);
        return new ModelAndView("redirect:/pizzas");
    }

    @PatchMapping("/{id}")
    public ModelAndView editPizza(@ModelAttribute(name = "pizza") Pizza pizza, @PathVariable(name = "id") Long id, @RequestParam(name = "newIngredients") ArrayList<Long> newIngredients) {
        //edit new pizza
        if (pizzaService.findById(id).isPresent()) {
            pizza.setId(id);
            List<PizzaIngredient> pizzaIngredients = new ArrayList<>();
            pizzaIngredientService.deleteAllByPizza(pizza);
            newIngredients.forEach(ingID -> {
                if (ingredientService.findById(ingID).isPresent()) {
                    PizzaIngredient pizzaIngredient = new PizzaIngredient();
                    pizzaIngredient.setPizza(pizza);
                    pizzaIngredient.setIngredient(ingredientService.findById(ingID).get());
                    PizzaIngredientCompositeKey compositeKey = new PizzaIngredientCompositeKey();
                    compositeKey.setPizzaId(pizza.getId());
                    compositeKey.setIngredientid(ingID);
                    pizzaIngredient.setId(compositeKey);
                    pizzaIngredients.add(pizzaIngredient);
                    pizzaIngredientService.save(pizzaIngredient);

                } else {
                    throw new IngredientNotFoundException();

                }
            });
            pizza.setIngredientList(pizzaIngredients);
            pizzaService.save(pizza);
            return new ModelAndView("redirect:/pizzas");
        } else
            throw new PizzaNotFoundException();

    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Long id) {

        if (pizzaService.findById(id).isPresent()) {
            Pizza pizza = pizzaService.findById(id).get();
            pizzaIngredientService.deleteAllByPizza(pizza);
            pizzaService.delete(pizza);
        } else {
            throw new PizzaNotFoundException();
        }

    }

    @GetMapping
    public Page<Pizza> getAllPizzas(@RequestParam(name = "totalIngredients", required = false, defaultValue = "0") Long totalIngredients, @PageableDefault(value = 5) Pageable pageable) {
        List<Pizza> finalList = new ArrayList<>();
        if (totalIngredients > 0) {
            pizzaService.findAll().forEach(pizza1 -> {
                if (pizza1.getIngredientList().size() < totalIngredients) {
                    finalList.add(pizza1);
                }

            });

            /*ModelAndView modelAndView = new ModelAndView("list-pizzas");
            modelAndView.addObject("pizzas", finalList);
            return modelAndView;*/
            return new PageImpl<>(finalList);
        } else {
            /*ModelAndView modelAndView = new ModelAndView("list-pizzas");
            modelAndView.addObject("pizzas", pizzaService.findAll());
            return modelAndView;*/
            return pizzaService.findPaginated(pageable);
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
    public ModelAndView addPizza(@ModelAttribute Pizza pizza, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();*/

        ModelAndView modelAndView = new ModelAndView("add-pizza");
        modelAndView.addObject("pizza", new Pizza());
        modelAndView.addObject("ingredients", ingredientService.findAll());
        modelAndView.addObject("newIngredients", new ArrayList<Long>());
        return modelAndView;
    }

    @GetMapping("/editPizza/{id}")
    public ModelAndView editPizza(@PathVariable(name = "id") Long pizzaID) throws UnsupportedEncodingException {


        Pizza pizza = null;
        if (pizzaService.findById(pizzaID).isPresent()) {
            pizza = pizzaService.findById(pizzaID).get();
        } else
            throw new PizzaNotFoundException();

        ModelAndView modelAndView = new ModelAndView("edit-pizza");
        modelAndView.addObject("pizza", pizza);
        modelAndView.addObject("ingredients", ingredientService.findAll());
        modelAndView.addObject("newIngredients", new ArrayList<>());
        return modelAndView;
    }

    @GetMapping("/deletePizza/{id}")
    public ModelAndView deletePizza(HttpServletRequest req, HttpServletResponse resp,
                                    @PathVariable(name = "id") Long pizzaID) throws UnsupportedEncodingException {
       /* resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = context.getSession();*/

        Pizza pizza = null;
        if (pizzaService.findById(pizzaID).isPresent()) {
            pizza = pizzaService.findById(pizzaID).get();
        } else
            throw new PizzaNotFoundException();

        ModelAndView modelAndView = new ModelAndView("delete-pizza");
        modelAndView.addObject("pizza", pizza);
        return modelAndView;
    }

    @GetMapping("/allPizzasSpicy")
    public List<Pizza> spicyPizzas() {
        List<Pizza> finalList = new ArrayList<>();
        for (Pizza pizza : pizzaService.findAll()) {
            for (PizzaIngredient ing : pizza.getIngredientList()) {
                if (ing.getIngredient().isSpicy()) {
                    finalList.add(pizza);
                    break;
                }
            }
        }
        return finalList;

        /*return pizzaService.findSpicyPizzas();*/
    }

}