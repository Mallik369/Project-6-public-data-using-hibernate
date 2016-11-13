package com.worldbank.controller;

import com.worldbank.dao.CountryDao;
import com.worldbank.dao.CountryDaoImpl;
import com.worldbank.model.Country;

import java.util.List;

public class WorldCountries {
    private CountryDao countryDao;
    private List<Country> worldCountries;

    public WorldCountries() {
        countryDao = new CountryDaoImpl();
        worldCountries = countryDao.listAllCountry();

    }

    private void header() {

        String header1 = "Code";
        String header2 = "Country Name";
        String header3 = "Internet Users";
        String header4 = "Literacy Rate";
        String ruler = "------------------------------------------------------------------------------------------------";
        System.out.format("%-6s %-40s %-20s %-20s %n%s%n",
                header1, header2,
                header3, header4, ruler);
    }

    public void listAllCountries() {
        header();
        worldCountries.stream().forEach(System.out::println);
    }
}
