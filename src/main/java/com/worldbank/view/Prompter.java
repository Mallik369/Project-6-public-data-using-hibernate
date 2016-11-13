package com.worldbank.view;

import com.worldbank.controller.WorldCountries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Prompter {
    private Map<Integer, String> menu; // menu : menu holds Action Items to display on User Interface
    private BufferedReader reader;    // reader : Standard Input Reader from User Interface
    private WorldCountries worldCountries;

    public Prompter() {
        menu = new HashMap<Integer, String>();
        reader = new BufferedReader(new InputStreamReader(System.in));
        worldCountries = new WorldCountries();
        fillMenu();
    }

    /**
     * fill Menu : Fills Menu Items to menu to display on User Interface
     */
    private void fillMenu() {
        menu.put(1,"World Countries");
        menu.put(2,"Adult literacy rate");
        menu.put(3,"Internet User Rate");
        menu.put(4,"Correlation between Literacy and Internet Users Rate");
        menu.put(5,"Edit");
        menu.put(6,"Add");
        menu.put(7,"Delete");
        menu.put(8,"Quit");
    }

    /**
     * @return choice : Returns user Choice from Available menu
     * @throws IOException
     */

    private int promptForAction() throws IOException {

        for (Map.Entry<Integer, String> option : menu.entrySet()) {
            System.out.printf("%d) %s %n",
                    option.getKey(),
                    option.getValue());
        }
        System.out.print("\nPlease, choose from above Menu: \n");
        int choice = Integer.parseInt(reader.readLine().trim());
        return choice;
    }

    /**
     * run    : Application Runnable Method
     * Runs the Application unitl user exists from Application
     * Input  : Obtains the User Input
     */

    public void run() {

        int choice = 0;
        System.out.println("\nWorld Countries Public Data Analysis of Internet User Rate and Adult Literacy Rate\n");
        do {
            try {
                switch (choice = promptForAction()) {
                    case 1:
                        worldCountries.listAllCountries();
                        break;
                    case 2:
                        System.out.println("option 2\n");
                        break;
                    case 3:
                        System.out.println("option 3\n");
                        break;
                    case 4:
                        System.out.println("option 4\n");
                        break;
                    case 5:
                        System.out.println("option 5\n");
                        break;
                    case 6:
                        System.out.println("option 6\n");
                        break;
                    case 7:
                        System.out.println("option 7\n");
                        break;
                    case 8:
                        System.out.println("Exiting Application\n");
                        break;
                    default:
                        System.out.println("Please enter a valid choice.\n");
                        break;
                }
                } catch (IOException ioe) {
                    System.out.println("Problem reading Input\n");
                    ioe.printStackTrace();
                }
        } while(!(choice == 8));

    }

}

