package raiffeisen.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PersonList implements Serializable {
    private List<Person> persons;
}
