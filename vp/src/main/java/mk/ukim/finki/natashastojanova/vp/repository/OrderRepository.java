package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Repository
public class OrderRepository {

    List<Order> orders;

    public void init(){
        this.orders=new ArrayList<>();
    }

    public List<Order> getOrders(){
        return this.orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }



}
