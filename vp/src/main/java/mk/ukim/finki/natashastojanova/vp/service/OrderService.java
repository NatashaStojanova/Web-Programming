package mk.ukim.finki.natashastojanova.vp.service;

import mk.ukim.finki.natashastojanova.vp.model.Order;

/**
 * @author Natasha Stojanova
 */
public interface OrderService {
    Order placeOrder(String pizzaType, String clientName, String address);
}
