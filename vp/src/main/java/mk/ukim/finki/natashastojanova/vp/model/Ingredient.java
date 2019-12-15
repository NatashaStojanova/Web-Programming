package mk.ukim.finki.natashastojanova.vp.model;

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
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Ingredient implements Comparable<Ingredient> {
    private String name;
    private boolean spicy;
    private boolean vegie;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(mappedBy = "ingredient")
    private List<PizzaIngredient> pizzaList;

    @Override
    public int compareTo(Ingredient ingredient) {
        return this.name.compareTo(ingredient.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
