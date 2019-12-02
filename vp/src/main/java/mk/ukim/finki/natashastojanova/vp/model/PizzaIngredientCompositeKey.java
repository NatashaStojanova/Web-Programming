package mk.ukim.finki.natashastojanova.vp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Natasha Stojanova
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PizzaIngredientCompositeKey implements Serializable {
    @Column(name = "pizza_id")
    Long pizzaId;
    @Column(name = "ingredient_id")
    Long ingredientid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaIngredientCompositeKey that = (PizzaIngredientCompositeKey) o;
        return pizzaId.equals(that.pizzaId) &&
                ingredientid.equals(that.ingredientid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, ingredientid);
    }
}
