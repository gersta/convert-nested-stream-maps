import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertMapStreamToPojo {

    @Data
    @Builder
    static class Person {
        private String firstname;
        private int age;
        private String homeCountry;
        private String favoriteColor;
    }

    @Data
    static class Grouping {
        private String firstname;
        private int age;
        private String homeCountry;

        List<Person> persons;
    }

    public static void main(String[] args) {
        Person gerrit = Person.builder()
                .firstname("gerrit")
                .age(24)
                .homeCountry("germany")
                .favoriteColor("blue")
                .build();

        Person anotherGerrit = Person.builder()
                .firstname("gerrit")
                .age(24)
                .homeCountry("germany")
                .favoriteColor("green")
                .build();

        Person janeDoe = Person.builder()
                .firstname("Jane")
                .age(25)
                .homeCountry("USA")
                .favoriteColor("yellow")
                .build();

        List<Person> persons = Arrays.asList(gerrit, anotherGerrit, janeDoe);

        Map<String, Map<Integer, Map<String, List<Person>>>> nestedGroupings = persons.stream()
                .collect(
                        Collectors.groupingBy(Person::getFirstname,
                                Collectors.groupingBy(Person::getAge,
                                        Collectors.groupingBy(Person::getHomeCountry)
                                )
                        )
                );

        /**
         * Convert the nested maps into a List<Groupings> where each group
         * holds a list of all matching persons
         */
        List<Grouping> groupings = new ArrayList<>();
        for (Grouping grouping: groupings) {
            String message = String.format("Grouping for firstname %s age %s and country %s", grouping.getFirstname(), grouping.getAge(), grouping.getHomeCountry());
            System.out.println(message);

            System.out.println("Number of persons inside this grouping: " + grouping.getPersons().size());
        }

        // example groupings

        /**
         *
         * [
         * 	Grouping(firstname=Jane, age=24, homeCountry=USA, persons=[Person(firstname=Jane, age=24, homeCountry=USA, favoriteColor=yellow)]),
         * 	Grouping(firstname=gerrit, age=24, homeCountry=germany, persons=[
         * 		Person(firstname=gerrit, age=24, homeCountry=germany, favoriteColor=blue), Person(firstname=gerrit, age=24, homeCountry=germany, favoriteColor=green)
         * 	])
         * ]
         *
         */
    }
}
