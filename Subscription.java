package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */

import java.util.Arrays;

/*
This class is meant to hold all subscriptions. Since there are only four,
using an enum for its static characteristic was a good choice for what I wanted implement.
 */
public enum Subscription {
    /*for each enum element it holds an integer with a string value,
    corresponding to a type of subscription.*/
    ML(1, "music lover"),
    VL(2, "video lover"),
    TV(3, "tv lover"),
    PR(4, "premium");

    private int choice;
    private String subscription;

    Subscription(int choice, String subscription) {
        this.choice = choice;
        this.subscription = subscription;
    }
/*
this methods below are used to "parse" the integer values from the "
"registerCustomerSubscription" method, get from the switch "subscription" input.
 */
    public static Subscription getChoice(int choice) {
        return Arrays.stream(values()).findFirst().get();
    }
//this method prints the equivalent choice
    public String getSubscription() {
        return subscription;
    }
}


