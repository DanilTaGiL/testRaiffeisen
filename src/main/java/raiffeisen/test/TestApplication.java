package raiffeisen.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TestApplication {
    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

    @Autowired
    private Task1Service task1;
    @Autowired
    private Task2Service task2;


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TestApplication.class, args);
        SpringApplication.exit(ctx);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            if (args.length == 1) {
                switch (args[0]) {
                    case "task1date" :
                        task1.run(restTemplate, 10, Sort.BY_DATE);
                        break;
                    case "task1name" :
                        task1.run(restTemplate, 10, Sort.BY_NAME);
                        break;
                    case "task1both" :
                        task1.run(restTemplate, 10, Sort.BOTH);
                        break;
                    case "task2" :
                        List<String> input = Arrays.asList("Bob", "Alice", "Joe", "bob", "alice", "dEN");
                        task2.run(input);
                        break;
                    default: log.warn("\nincorrect args! available args described at README");
                }
            }
        };
    }
}