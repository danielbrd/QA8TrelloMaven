package org.example.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataProviders {
    String stringGenerator = RandomStringUtils.randomAlphabetic(2, 10);


    @DataProvider //shablon
    public static Iterator<Object[]> loginPositive() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginPositive.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> addCard() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/addNewCard.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split("#")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> addList() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/addNewList.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split("#")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> loginNegative() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginNegative.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> loginNegative2() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginNegative2.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginEmpty() throws IOException {
        //считывание строчек из позитив.дата
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginEmpty.data")));
        //массив обьектов, где каждый массив - набор данных (логин и пароль)
        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";")); //разделитель
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"login1", "password1"});
        data.add(new Object[]{"login2", "password2"});
        data.add(new Object[]{"login3", "password3"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for(int i = 0; i < 4; ++i) { // 4 набора данных
            data.add(new Object[]{this.generateRandomName(),this.generateRandomPassword()});
        }
        return data.iterator();
    }
    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt()+"@gmail.com";
    }
    private Object generateRandomPassword() {
        return "pass" + (new Random()).nextInt();
    }

    @DataProvider
    public Iterator<Object[]> dataProviderRandomDataInsert() {
        List<Object[]> data = new ArrayList();

        for(int i = 0; i < 3; ++i) { // 4 набора данных
            data.add(new Object[]{this.generateRandomNames(),this.generateRandomPasswords()});
        }
        return data.iterator();
    }
    private Object generateRandomNames() {
        return RandomStringUtils.randomAlphanumeric(2, 10);
    }
    private Object generateRandomPasswords() {
        return RandomStringUtils.randomAlphanumeric(2,10);
    }
}

