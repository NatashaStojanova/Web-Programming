package mk.ukim.finki.natashastojanova.vp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Natasha Stojanova
 */
@ResponseStatus(value = HttpStatus.FOUND)
public class NoMoreSpicyIngredientsException extends RuntimeException {
}
