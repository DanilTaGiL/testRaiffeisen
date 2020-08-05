package raiffeisen.test;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Person implements Serializable {
    private Name name;
    private Work work;
    private Gender gender;
    private Date date;
    private Address address;

    private String phone;
    private String color;
    private String login;
    private String password;
}

@Data
class Name implements Comparable<Name>{
    private String first;
    private String second;
    private String patronymic;

    @JsonIgnore
    public String getFIO() {
        return String.format("%s %s %s", second, first, patronymic);
    }

    @Override
    public int compareTo(Name o) {
        return getFIO().compareTo(o.getFIO());
    }
}

@Data
class Work {
    private String company;
    private String position;
}

@Data
class Gender {
    private String gender;
    private String code;
}

@Data
class Date implements Comparable<Date> {
    private String date;
    @JsonAlias("digital_time")
    private java.util.Date digitalTime;

    @Override
    public int compareTo(Date o) {
        return digitalTime.compareTo(o.getDigitalTime());
    }
}

@Data
class Address {
    private String country;
    private String postcode;
    private String city;
    private String street;
    private String home;
    private String apartment;
}