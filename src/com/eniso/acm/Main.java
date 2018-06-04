package com.eniso.acm;

import com.eniso.acm.OtherCodes.GoodMethods;
import java.io.*;
import java.util.*;

public class Main {

    //BufferedReader le plus rapide dans la lecture des données.
    //PrintWriter le plus rapide dans l'affichage des données.
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        out.println(GoodMethods.bisection(0.1, 1000, 2));
        out.close();
    }

}
