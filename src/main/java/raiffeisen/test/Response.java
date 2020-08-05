package raiffeisen.test;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private String status;
    private String message;
    private List<Person> result;
}