package mk.ukim.finki.natashastojanova.vp.web.servlet;

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
@WebServlet (urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private PizzaService pizzaService;
    private OrderService orderService;
    private SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfo(PizzaService pizzaService,OrderService orderService,SpringTemplateEngine springTemplateEngine){
        this.pizzaService=pizzaService;
        this.orderService=orderService;
        this.springTemplateEngine=springTemplateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        WebContext context=new WebContext(req,resp,req.getServletContext());
        this.springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
    }
}
