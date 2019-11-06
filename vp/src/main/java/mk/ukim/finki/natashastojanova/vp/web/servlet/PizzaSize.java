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

/**
 * @author Natasha Stojanova
 */
@WebServlet (urlPatterns = "/pizzaSize")
public class PizzaSize extends HttpServlet {
    private PizzaService pizzaService;
    private SpringTemplateEngine springTemplateEngine;

    public PizzaSize(PizzaService pizzaService,SpringTemplateEngine springTemplateEngine) {

        this.pizzaService = pizzaService;
        this.springTemplateEngine=springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method -> PIZZA SIZE");
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String selectedPizza=req.getParameter("selectedPizza");
        req.getSession().setAttribute("selectedPizza", selectedPizza);
        context.setVariable("selectedPizza", selectedPizza);
        this.springTemplateEngine.process("selectPizzaSize.html", context, resp.getWriter());



    }
}
