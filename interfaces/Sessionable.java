package ultravision.interfaces;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 * */


import ultravision.Database;
import ultravision.Input;

public interface Sessionable {

    void connectToDatabase(Input input, Database database);

    void getLogin(Input input, Database database);

}
