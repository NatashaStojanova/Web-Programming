package mk.ukim.finki.natashastojanova.vp.web.servlet;

import eu.bitwalker.useragentutils.UserAgent;
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
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String selectedPizza= (String) session.getAttribute("selectedPizza");
        req.getSession().setAttribute("selectedPizza", selectedPizza);
        context.setVariable("selectedPizza", selectedPizza);

        String size= (String) session.getAttribute("size");
        req.getSession().setAttribute("size", size);
        context.setVariable("size", size);

        String firstName=req.getParameter("firstName");
        req.getSession().setAttribute("firstName",firstName);
        context.setVariable("firstName",firstName);

        String address=req.getParameter("address");
        req.getSession().setAttribute("address",address);
        context.setVariable("address",address);

        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        req.setAttribute("browser", userAgent.getBrowser().getName());
        req.setAttribute("ip", req.getRemoteAddr());



        this.springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
    }
}
