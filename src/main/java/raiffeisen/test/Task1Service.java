package raiffeisen.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task1Service {
    private static final Logger log = LoggerFactory.getLogger(Task1Service.class);
    private static final String API_KEY = "Bearer xVVUWBZS6NA9HehWe8TkMSgbdT009TybeE8lzW457ujaOJDVvOgRCDcMlFcj";
    private static final String API_URL = "https://randus.org/api/";
    private static final String TMP = "build/tmp/";

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public void run(RestTemplate restTemplate, int personsCount, Sort sortType) {
        String URL = API_URL + String.format(
                "person?lang=ru&count=%s&fields=name,gender,work,date,address,phone,color,login,password",
                personsCount
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", API_KEY);
        HttpEntity request = new HttpEntity(headers);

        // api request
        ResponseEntity<String> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                request,
                String.class
        );

        // check response
        if (response.getStatusCode() == HttpStatus.OK)
            log.info("Request Successful!");
        else log.warn("Request Failed! Error code:\t" + response.getStatusCode());

        // sorting and save
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(DEFAULT_DATE_FORMAT);

            List<Person> persons = mapper.readValue(response.getBody(), Response.class).getResult();

            switch (sortType) {
                case BY_NAME:
                    sortByName(persons, mapper);
                    break;
                case BY_DATE:
                    sortByDate(persons, mapper);
                    break;
                case BOTH:
                    sortByName(persons, mapper);
                    sortByDate(persons, mapper);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortByName(List<Person> persons, ObjectMapper mapper) throws IOException {
        List<Person> sortedByFIO = persons.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        File file = new File(TMP + "sortedByFIO.json");
        mapper.writeValue(file, sortedByFIO);
        log.info("check " + file.getPath());
    }

    private void sortByDate(List<Person> persons, ObjectMapper mapper) throws IOException {
        List<Person> sortedByDate = persons.stream().sorted(Comparator.comparing(Person::getDate)).collect(Collectors.toList());
        File file = new File(TMP + "sortedByDate.json");
        mapper.writeValue(file, sortedByDate);
        log.info("check " + file.getPath());
    }
}