package com.devlach.collectors;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class CollectorsExample {

    public static void main(String[] args) throws InterruptedException {

        List<Hero> heroes = Arrays.asList(
                new Hero("Superman", "Super strength", 35),
                new Hero("Batman", "Intelligence", 40),
                new Hero("Wonder Woman", "Lasso of Truth", 28),
                new Hero("The Flash", "Super speed", 25),
                new Hero("Aquaman", "Control of the seas", 30),
                new Hero("Green Lantern", "Power ring", 32),
                new Hero("Spider-Man", "Web-slinging", 26),
                new Hero("Iron Man", "Powered suit", 38)
        );

        // Less Complex Tasks:

        // Filter Heroes by Power: Find all heroes with a specific power, for example, "Flying".
        var heroesFilterByPower = heroes.stream()
                .filter(hero -> "Intelligence".equalsIgnoreCase(hero.getPower()))
                .toList();
        // System.out.println("heroesFilterByPower = " + heroesFilterByPower);

        // Sort Heroes by Name: Sort the list of heroes alphabetically by their names.
        var heroesOrderByName = heroes.stream()
                .sorted(Comparator.comparing(Hero::getName))
                .toList();
        // System.out.println("heroesOrderByName = " + heroesOrderByName);

        // Count Heroes by Power Category: Count how many heroes there are for each power category
        // (e.g., how many have "Flying", how many have "Super Strength", etc.).
        var heroesCountedByPower = heroes.stream()
                .collect(groupingBy(Hero::getPower, counting()));
        //System.out.println("heroesCountedByPower = " + heroesCountedByPower);

        // Find the Youngest Hero: Find the youngest hero in the list.
        var youngerHero = heroes.stream()
                .min(Comparator.comparing(Hero::getAge)).orElseThrow(() -> new RuntimeException("Empty list"));

        //System.out.println("youngerHero = " + youngerHero);

        /* Heroes with Unique Powers: Given the list of heroes, find those
        who have unique powers, i.e., powers not shared with any other hero. */
        var groupingHeroesByPower = heroes.stream().collect(groupingBy(Hero::getPower, counting()));
        var heroesUniquePowers = heroes.stream()
                .filter(hero -> groupingHeroesByPower.get(hero.getPower()) == 1)
                .toList();

        System.out.println("heroesUniquePowers = " + heroesUniquePowers);

        /* Average Length of Names: Calculate the average length of
        hero names in the list. */

        double heroesAverageByName = heroes.stream()
                .map(Hero::getName)
                .map(String::length)
                .collect(averagingDouble(Integer::intValue));

        System.out.println("heroesAverageByName = " + heroesAverageByName);

        // Three Oldest Heroes: Find the three oldest heroes in the list.
        var olderHeroes = heroes.stream()
                .sorted(Comparator.comparing(Hero::getAge).reversed())
                .limit(3).toList();

        System.out.println("olderHeroes = " + olderHeroes);

        /* Names Concatenation: Create a string containing the names of
        all heroes separated by commas. */

        var heroesName = heroes.stream().map(Hero::getName).collect(Collectors.joining(", "));
        System.out.println("heroesName = " + heroesName);

        /* Heroes by Initial of Name: Group heroes by the initial of their
        name and show how many there are for each initial. */
        var groupingByFirstLetter = heroes.stream()
                .collect(groupingBy(hero -> hero.getName().substring(0, 1), counting()));
        System.out.println("groupingByFirstLetter = " + groupingByFirstLetter);

        /* Heroes with Different Powers: Find heroes who have powers
        different from those in another set of given heroes. */

        List<Hero> heroesOthers = Arrays.asList(
                new Hero("Superman", "Super strength", 35),
                new Hero("Batman", "Intelligence", 40),
                new Hero("Wonder Woman", "Lasso of Truth", 28),
                new Hero("The Flash", "Super speed", 25)
        );
        var heroesDistinctPowers = heroes.parallelStream()
                .filter(hero -> heroesOthers.stream().noneMatch(other -> hero.getPower().equals(other.getPower())))
                .map(Hero::getName)
                .toList();

        System.out.println("heroesDistinctPowers = " + heroesDistinctPowers);

        /* Average Age by Power: Calculate the average age of heroes for
        each power and show the result as a map. */
        Map<String, Double> ageAverageByPower = heroes.stream()
                .collect(Collectors.groupingBy(
                        Hero::getPower,
                        Collectors.averagingInt(Hero::getAge)
                ));
        System.out.println("ageAverageByPower = " + ageAverageByPower);

        /* Oldest Hero by Power: Find the oldest hero for each power and
        show the result as a map. */
        var oldestHeroesByPower = heroes.stream().collect(
                Collectors.groupingBy(
                        Hero::getPower,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Hero::getAge)),
                                hero -> hero.orElseThrow(() -> new RuntimeException("Empty list"))
                        )
                )
        );

        System.out.println("oldestHeroesByPower = " + oldestHeroesByPower);


        /* Heroes with Names Containing "man":
         * Find and display all heroes whose names contain the substring "man"
         */

        var heroesNameContainsMan = heroes.stream()
                .filter(hero -> hero.getName().contains("man"))
                .toList();

        System.out.println("heroesNameContainsMan = " + heroesNameContainsMan);


       /*
         Heroes Grouped by Age:
         Group heroes into three categories based on their age: 
         young (under 30), adults (between 30 and 40), and seniors (over 40).
        */

        var young = heroes.stream()
                .filter(hero -> hero.getAge() < 30)
                .toList();

        var adults = heroes.stream()
                .filter(hero -> hero.getAge() >= 30 && hero.getAge() <= 40)
                .toList();

        var seniors = heroes.stream()
                .filter(hero -> hero.getAge() > 40)
                .toList();

        var heroesGroupedByAge = Map.of(
                "young", young,
                "adults", adults,
                "seniors", seniors
        );

        System.out.println("heroesGroupedByAge = " + heroesGroupedByAge);

        // Improved version of the previous example

        var heroesGroupedByAgeImproved = heroes.stream()
                .collect(Collectors.groupingBy(
                        hero -> {
                            if (hero.getAge() < 30) return "young";
                            else if (hero.getAge() >= 30 && hero.getAge() <= 40) return "adults";
                            else return "seniors";
                        }
                ));

        System.out.println("heroesGroupedByAgeImproved = " + heroesGroupedByAgeImproved);

        // Optimized version of the previous example

        var heroesNamesGroupedByAge = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                hero -> {
                                    var age = hero.getAge();
                                    if (age < 30) return "young";
                                    else if (age <= 40) return "adults";
                                    else return "seniors";
                                },
                                Collectors.mapping(Hero::getName, Collectors.toList())
                        )
                );

        System.out.println("heroesNamesGroupedByAge = " + heroesNamesGroupedByAge);
    }
}

class Hero {
    private final String name;
    private final String power;
    private final int age;

    public Hero(String name, String power, int age) {
        this.name = name;
        this.power = power;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPower() {
        return power;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", power='" + power + '\'' +
                ", age=" + age +
                '}';
    }
}
