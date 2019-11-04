package mk.ukim.finki.natashastojanova.vp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class VpApplication {

    public static void main(String[] args) {
        SpringApplication.run(VpApplication.class, args);
    }

}
