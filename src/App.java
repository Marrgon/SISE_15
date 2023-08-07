import exceptions.SolvedException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App {

    static int RecursionDepth = 25;
    static FifteenPuzzle solved;


    public static void main(String[] args) throws IOException {
        print("-----Pietnastka-----");
        final String[] strategies = {
                "bfs", "dfs", "astr"
        };
        final String[] strategyParameters1 = {
                "LRUD", "RLUD", "ULRD", "LURD",
                "RULD", "URLD", "URDL", "RUDL",
                "DURL", "UDRL", "RDUL", "DRUL",
                "DLUR", "LDUR", "UDLR", "DULR",
                "LUDR", "ULDR", "RLDU", "LRDU",
                "DRLU", "RDLU", "LDRU", "DLRU"
        };
        final String[] strategyParameters2 = {
                "hamm", "manh"
        };
        if (args.length != 5) {
            System.out.println("Wymagana liczba argumentów to 5, a podano " + args.length);
            System.exit(1);
        }
         String strategy = args[0].trim();
         String strategyParameter = args[1].trim();
         String inputFile = args[2].trim();
         String outputFile = args[3].trim();
         String outputInfoFile = args[4].trim();




        if (Arrays.stream(strategies).noneMatch(strategy::equalsIgnoreCase)) {
            System.out.println("strategia musi być jedną z " + Arrays.toString(strategies) + " a podano " + strategy);
            System.exit(2);
        }

        if ((strategy.equals("bfs") || strategy.equals("dfs"))
                && Arrays.stream(strategyParameters1).noneMatch(strategyParameter::equalsIgnoreCase)) {
            System.out.println("parametr strategii musi być permutacją liter: [L, R, U, D] a podano " + strategyParameter);
            System.exit(3);
        } else if (strategy.equals("astr")
                && Arrays.stream(strategyParameters2).noneMatch(strategyParameter::equalsIgnoreCase)) {
            System.out.println("parametr strategii musi być jednym z " + Arrays.toString(strategyParameters2) + " a podano " + strategyParameter);
            System.exit(3);
        }

        //Game game = new Game(strategy, strategyParameter, inputFile, outputFile, outputInfoFile);

        print("Algorytm: " + strategy);
        print("Porządek przeszukiwania: " + strategyParameter);
        print("Plik wejściowy: " + inputFile);
        print("Plik z rozwiązeniem : " + outputFile);
        print("Plik ze statystykami: " + outputInfoFile);

        FifteenPuzzle p1 =  FifteenFiles.readFile(inputFile);
        print(p1.printPuzzle());

        p1.searchEmptySquare();

        String p2 = p1.printPuzzle();



        solved = new FifteenPuzzle(p1.puzzle.length, p1.puzzle.length);
        solved.goodPuzzle();
        print(solved.printPuzzle());


        String newString = solved.toString();
        p1.setSolved(newString);

        DFS dfs = null;
        BFS bfs = null;
        ASTR astr = null;
        long Start = System.nanoTime(), Stop = System.nanoTime();
        try {
            try (PrintWriter out = new PrintWriter(".\\output\\" + outputFile)) {
                out.println("-1");
            }
            try (PrintWriter out = new PrintWriter(".\\output\\" + outputInfoFile)) {
                out.println("-1");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            Start = System.nanoTime();
            switch (strategy) {
                case "dfs":
                    strategyParameter = new StringBuilder(strategyParameter).reverse().toString();
                    dfs = new DFS(p1, strategyParameter);
                    break;
                case "bfs":
                    bfs = new BFS(p1, strategyParameter);
                    break;
                case "astr":
                    astr = new ASTR(p1, strategyParameter);
                    break;
            }
            Stop = System.nanoTime();
            //testPrzesuwania(u);
        } catch (SolvedException ex) {
            Stop = System.nanoTime();
            print(ex);
            print("długość znalezionego rozwiązania: " + ex.toString().length());
            print("liczba stanów odwiedzonych: " + FifteenPuzzle.visited);
            print("liczba stanów przetworzonych: " + (FifteenPuzzle.processed - 1));
            print("maksymalna głębokość przetwarzana w drzewie: " + FifteenPuzzle.maxGlebokosc);
            print(((Stop - Start) / 1000) / 1000.0);

            try (PrintWriter out = new PrintWriter(outputFile)) {
                out.println(ex.toString().length());
                out.println(ex);
            }
            try (PrintWriter out = new PrintWriter(outputInfoFile)) {
                out.println(ex.toString().length());
            } catch (FileNotFoundException exc) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, exc);
            }
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(outputInfoFile), true))) {
            out.append(String.valueOf((FifteenPuzzle.visited)));
            out.append(System.lineSeparator() + String.valueOf((FifteenPuzzle.processed)));
            out.append(System.lineSeparator() + String.valueOf((FifteenPuzzle.maxGlebokosc)));
            out.append(System.lineSeparator() + String.valueOf((((Stop - Start) / 1000) / 1000.0)));
        } catch (FileNotFoundException exc) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, exc);
        }













    }




    public static void print(Object o) {

            System.out.println(o);

    }
}

