package main.dungeonadventure.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TESTER {


    public static void main(String[] args) throws MalformedURLException {
        URL test = TESTER.class.getResource("main/dungeonadventure/controller/paper.txt");
        URL test2 = new File("main/dungeonadventure/controller/paper.txt").toURI().toURL();
        System.out.println(test2);
    }
}
