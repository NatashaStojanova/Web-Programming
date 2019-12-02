package mk.ukim.finki.natashastojanova.vp.service.implementation;

import mk.ukim.finki.natashastojanova.vp.model.Order;
import mk.ukim.finki.natashastojanova.vp.repository.IOrderRepository;
import mk.ukim.finki.natashastojanova.vp.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Natasha Stojanova
 */

@Service
public class OrderServiceImpl implements OrderService {

    private IOrderRepository orderRepository;

    public OrderServiceImpl(IOrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String pizzaType, String clientName, String address) {
        Order newOrder = new Order();
        newOrder.setClientAddress(address);
        newOrder.setClientName(clientName);
        newOrder.setPizzaType(pizzaType);
        orderRepository.save(newOrder);
        return newOrder;
    }
}
