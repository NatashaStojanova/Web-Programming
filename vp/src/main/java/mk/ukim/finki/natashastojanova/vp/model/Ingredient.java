package mk.ukim.finki.natashastojanova.vp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Natasha Stojanova
 */
@Entity
@AllArgsConstructor
@Setter
@Getter
public class Ingredient {
    private String name;
    private boolean spicy;
    private float amount;
    private boolean vegie;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Pizza pizza;

}
