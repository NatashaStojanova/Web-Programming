package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Order;
import mk.ukim.finki.natashastojanova.vp.repository.OrderRepository;
import mk.ukim.finki.natashastojanova.vp.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String pizzaType, String clientName, String address) {
        Order newOrder = new Order();
        newOrder.setClientAddress(address);
        newOrder.setClientName(clientName);
        newOrder.setPizzaType(pizzaType);
        orderRepository.addOrder(newOrder);
        return newOrder;
    }
}
