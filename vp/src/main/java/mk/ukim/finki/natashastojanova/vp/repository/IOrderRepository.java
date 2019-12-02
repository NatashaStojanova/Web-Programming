package mk.ukim.finki.natashastojanova.vp.repository;

import mk.ukim.finki.natashastojanova.vp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Natasha Stojanova
 */
@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
