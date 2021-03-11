import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertMapStreamToPojo {

    @Data
    @Builder
    static class Person {
        private String firstname;
        private String lastname;
        private int age;
        private String homeCountry;
    }

    @Data
    static class Grouping {
        private String firstname;
        private String lastname;
        private int age;
        private String homeCountry;

        List<Person> persons;
    }

    public static void main(String[] args) {
        Person gerrit = Person.builder()
                .firstname("gerrit")
                .lastname("sta")
                .age(24)
                .homeCountry("germany")
                .build();

        Person johnDoe = Person.builder()
                .firstname("John")
                .lastname("Doe")
                .age(25)
                .homeCountry("USA")
                .build();

        Person janeDoe = Person.builder()
                .firstname("Jane")
                .lastname("Doe")
                .age(25)
                .homeCountry("USA")
                .build();

        List<Person> persons = Arrays.asList(gerrit, johnDoe, janeDoe);

        Map<String, Map<String, Map<Integer, Map<String, List<Person>>>>> grouping = persons.stream()
                .collect(
                        Collectors.groupingBy(Person::getFirstname,
                                Collectors.groupingBy(Person::getLastname,
                                        Collectors.groupingBy(Person::getAge,
                                                Collectors.groupingBy(Person::getHomeCountry)
                                        )
                                )
                        )
                );

        /**
         * Convert the nested maps into a List<Groupings> where each group
         * holds a list of all matching persons
         */
        List<Grouping> groupings;

        // example for grouping 1
        // firstname: gerrit
        // lastname: sta
        // age: 24
        // homeCountry: germany
        // persons: [{gerrit}] // the object gerrit as an entry in the list

    }
}
