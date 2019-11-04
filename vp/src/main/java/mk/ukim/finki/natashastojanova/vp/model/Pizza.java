package mk.ukim.finki.natashastojanova.vp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */

@Setter
@Getter
@AllArgsConstructor
public class Pizza {
    private String name;
    private String description;
    public Pizza(){}
}
