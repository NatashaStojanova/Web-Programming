package mk.ukim.finki.natashastojanova.vp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Arrays;
import java.util.logging.Logger;

@SpringBootApplication
@ServletComponentScan
public class VpApplication {

    @Bean
    public FilterRegistrationBean hiddenFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    public Logger Logger() {
        return Logger.getLogger(VpApplication.class.getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(VpApplication.class, args);
    }

}
