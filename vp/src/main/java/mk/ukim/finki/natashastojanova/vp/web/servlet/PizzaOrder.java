package mk.ukim.finki.natashastojanova.vp.web.servlet;

import mk.ukim.finki.natashastojanova.vp.repository.PizzaRepository;
import mk.ukim.finki.natashastojanova.vp.service.OrderService;
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
@WebServlet(urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    private PizzaService pizzaService;
    private OrderService orderService;
    private SpringTemplateEngine springTemplateEngine;


    public PizzaOrder(PizzaService pizzaService,SpringTemplateEngine springTemplateEngine) {

        this.pizzaService = pizzaService;
        this.springTemplateEngine=springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        WebContext context=new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html ; charset=UTF-8");
        this.springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }
}
