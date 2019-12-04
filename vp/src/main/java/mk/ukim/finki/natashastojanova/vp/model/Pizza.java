package mk.ukim.finki.natashastojanova.vp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Natasha Stojanova
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private String name;
    private String description;
    private Boolean veggie;
    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    private List<PizzaIngredient> ingredientList;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(name, pizza.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
