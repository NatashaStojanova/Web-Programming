package mk.ukim.finki.natashastojanova.vp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Natasha Stojanova
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PizzaIngredient {
    @EmbeddedId
    PizzaIngredientCompositeKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("pizza_id")
    @JoinColumn(name = "pizza_id")
    Pizza pizza;

    @JsonIgnore
    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    float amount;
}
