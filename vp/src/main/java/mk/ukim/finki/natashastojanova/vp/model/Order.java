package mk.ukim.finki.natashastojanova.vp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Natasha Stojanova
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Order {
    private String pizzaType;
    private String clientName;
    private String clientAddress;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    public Order(){}


}
