package mk.ukim.finki.natashastojanova.vp.web.servlet;

import mk.ukim.finki.natashastojanova.vp.model.Pizza;
import mk.ukim.finki.natashastojanova.vp.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@WebServlet(name="index", urlPatterns = "")
public class ShowPizza extends HttpServlet {

    private PizzaService pizzaService;
    private SpringTemplateEngine springTemplateEngine;

    public ShowPizza(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine) {
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method -> SHOW PIZZA");
        HttpSession session=req.getSession();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Pizza> pizzas = pizzaService.listPizzas();
        context.setVariable("pizzas", pizzas);
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        this.springTemplateEngine.process("listPizzas.html", context, resp.getWriter());
    }
}
