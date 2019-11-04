package mk.ukim.finki.natashastojanova.vp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Getter
@Setter
public class Order {
    private String pizzaType;
    private String clientName;
    private String clientAddress;
    private Long orderId;
}
