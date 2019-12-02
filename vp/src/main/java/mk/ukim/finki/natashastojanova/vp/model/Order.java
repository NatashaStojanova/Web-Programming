package mk.ukim.finki.natashastojanova.vp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Natasha Stojanova
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pizza_order")
public class Order {
    private String pizzaType;
    private String clientName;
    private String clientAddress;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(pizzaType, order.pizzaType) &&
                Objects.equals(clientName, order.clientName) &&
                Objects.equals(clientAddress, order.clientAddress) &&
                Objects.equals(Id, order.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaType, clientName, clientAddress, Id);
    }
}
