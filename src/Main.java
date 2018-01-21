
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    //FastScanner le plus rapide dans la lecture des données.
    //PrintWriter le plus rapide dans l'affichage des données.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        out.println(GoodMethods.bisection(0.1, 1000, 2));
        out.close();
    }

}
