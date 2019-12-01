package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasha Stojanova
 */
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
