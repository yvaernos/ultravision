package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */

/*
This class is meant to hold all subscriptions. Since there are only four,
using an enum for its static characteristic was a good choice for what I wanted implement.
 */
public enum Subscription {
    /*for each enum element it holds an integer with a string value,
    corresponding to a type of subscription.*/
    ML,
    VL,
    TV,
    PR;

/*this method gets the user input(int) from "Customer" and returns the equivalent integer,
so that when the user chooses an integer it will record one of the correspondents enum elements instead.
 */

static Subscription getChoice(int choice) {
        return values()[choice % 4];
    }

}


