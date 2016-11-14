package com.worldbank.controller;

import com.worldbank.dao.CountryDao;
import com.worldbank.dao.CountryDaoImpl;
import com.worldbank.model.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WorldCountries {
    private CountryDao countryDao;
    private List<Country> worldCountries;
    private List<Double> adultLiteracyRate;
    private List<Double> internetUsers;
    private CorelationCoefficient corelationCoefficient;
    private BufferedReader reader;

    public WorldCountries() {
        countryDao = new CountryDaoImpl();
        worldCountries = countryDao.listAllCountry();
        adultLiteracyRate = new ArrayList<>();
        internetUsers = new ArrayList<>();
        corelationCoefficient = new CorelationCoefficient();
        reader = new BufferedReader(new InputStreamReader(System.in));

    }

    private void header() {

        String header1 = "Code";
        String header2 = "Country Name";
        String header3 = "Internet Users";
        String header4 = "Literacy Rate";
        String ruler = "-------------------------------------------------------------------------------------";
        System.out.format("%-6s %-40s %-20s %-20s %n%s%n",
                header1, header2,
                header3, header4, ruler);
    }

    public void listAllCountries() {
        header();
        worldCountries.forEach(System.out::println);

    }

    public void adultLiteracyRate() {
        Country maxLiteracy;
        Country minLiteracy;

        maxLiteracy =    worldCountries
                        .stream()
                        .filter(country -> country.getAdultLiteracyRate() != null)
                        .max((o1, o2) -> o1.getAdultLiteracyRate().compareTo(o2.getAdultLiteracyRate()))
                        .get();
        minLiteracy =   worldCountries
                        .stream()
                        .filter(country -> country.getAdultLiteracyRate() != null)
                        .min((o1, o2) -> o1.getAdultLiteracyRate().compareTo(o2.getAdultLiteracyRate()))
                        .get();

        System.out.printf("Max Literacy Rate Country : %s%n",maxLiteracy.getName());
        System.out.printf("Max Literacy Rate : %.2f%n",maxLiteracy.getAdultLiteracyRate());
        System.out.printf("Min Literacy Rate Country : %s%n",minLiteracy.getName());
        System.out.printf("Min Literacy Rate : %.2f%n",minLiteracy.getAdultLiteracyRate());
    }

    public void internetUsers() {
        Country maxInternetUsers;
        Country minInternetUsers;

        maxInternetUsers  =  worldCountries
                            .stream()
                            .filter(country -> country.getInternetUsers() != null)
                            .max((o1, o2) -> o1.getInternetUsers().compareTo(o2.getInternetUsers()))
                            .get();

        minInternetUsers =  worldCountries
                            .stream()
                            .filter(country -> country.getInternetUsers() != null)
                            .min((o1, o2) -> o1.getInternetUsers().compareTo(o2.getInternetUsers()))
                            .get();

        System.out.printf("Max Internet Users Country : %s%n",maxInternetUsers.getName());
        System.out.printf("Max Internet Users : %.2f%n",maxInternetUsers.getInternetUsers());
        System.out.printf("Min Internet Users Country : %s%n",minInternetUsers.getName());
        System.out.printf("Min Internet Users : %.2f%n",minInternetUsers.getInternetUsers());
    }

    public void corelationCoefficient() {
        // Add all the Adult literate Rate and Internet Users in List
        worldCountries.forEach(country -> adultLiteracyRate.add(country.getAdultLiteracyRate()));
        worldCountries.forEach(country -> internetUsers.add(country.getInternetUsers()));
        // Comput Coefficient Corelation
        double coCoefficient = corelationCoefficient.computeCorelationCoefficient(adultLiteracyRate,internetUsers);
        System.out.printf("Corelation Coefficient between AdultLiteracyRate and " +
                "Internet User of Country : %.6f",coCoefficient);
    }

    public void editCountry() throws IOException {

        Double internetUser;
        Double literacyRate;
        int choice = 0;
        System.out.println("Enter the country Code:");
        String code = reader.readLine();
        Country country = countryDao.findCountryByCode(code);
        if (country == null) {
            System.out.println("Country does not exist , Enter a valid country code.");
        } else {

            do {
                System.out.printf("Enter values to change for %s:%n", country.getName());
                System.out.println("1) Internet Users");
                System.out.println("2) Adult Literacy Rate");
                System.out.println("3) Updated Data");
                choice = Integer.parseInt(reader.readLine().trim());
                switch (choice) {
                    case 1:
                        System.out.printf("Enter Number of Internet User for %s:%n", country.getName());
                        internetUser = Double.valueOf(reader.readLine());
                        country.setInternetUsers(internetUser);
                        break;
                    case 2:
                        System.out.printf("Enter Adult Literacy Rate for %s:%n", country.getName());
                        literacyRate = Double.valueOf(reader.readLine());
                        country.setAdultLiteracyRate(literacyRate);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Please enter the valid choice in Avaialabe Menu");
                }
            } while (choice != 3);
            countryDao.editCountry(country);
            System.out.printf("%s Data is Updated !%n", country.getName());
            worldCountries = countryDao.listAllCountry();
        }
    }
}
