package org.example;


import java.util.Comparator;
import java.util.function.Predicate;

public class Main {
    public static void main(final String[] args) {
//        runUsingAnonymousObject();
        runUsingLamda();
    }
    private static void runUsingLamda(){
        final UsersRepository repository = new UsersRepository();
        banner("Listing users with age > 5 sorted by name");
        repository.select(user -> user.age > 5, Comparator.comparing( user -> user.name));
        banner("Listing users with age < 10 sorted by age");
        repository.select(user -> user.age < 10, Comparator.comparing(user ->  user.age));

        banner("Listing active users sorted by name");
        repository.select(user -> user.active == true, Comparator.comparing( user -> user.name));

        banner("Listing active users with age > 8 sorted by name");
        repository.select(user -> user.age > 8 && user.active == true, Comparator.comparing( user -> user.name));

    }
    private static void runUsingAnonymousObject(){
        final UsersRepository repository = new UsersRepository();
        banner("Listing users with age > 5 sorted by name");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age > 5 ? true : false;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        banner("Listing users with age < 10 sorted by age");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age < 10 ? true : false;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.age > o2.age)
                    return 1;
                else if (o1.age < o2.age)
                    return -1;
                else
                    return 0;
            }
        });

        banner("Listing active users sorted by name");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.active == true ? true : false;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        banner("Listing active users with age > 8 sorted by name");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age > 8 && user.active == true ? true : false;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });

    }
    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
}

