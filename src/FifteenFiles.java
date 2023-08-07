import java.util.Scanner;

import java.io.*;

public class FifteenFiles {

    static FifteenPuzzle solved;


    public static FifteenPuzzle readFile(String path) throws IOException {
        path = ".\\puzzles\\" + path;

        int rows, columns;


        Scanner input = new Scanner(new File(path));
        rows = input.nextInt();
        columns = input.nextInt();


        System.out.println("liczba wierszy: " + rows);
        System.out.println("liczba kolumn: " + columns);
        FifteenPuzzle fp = new FifteenPuzzle(rows, columns);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                fp.readNumber(i, j, input.nextInt());


            }
        }

        //solved = new FifteenPuzzle(rows,columns);

        // System.out.println("Wczytana ukladanka");
        // for (int i = 0; i < rows; i++) {
        //     System.out.println("");
        //     for (int j = 0; j < columns; j++) {
        //         System.out.print(fp.puzzle[i][j] + " ");
        //     }
        // }
        return fp;
        
        /*try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            int rows;
            int columns;

            int[][] puzzle;
            
            String line = reader.readLine();
            String[] temp;
            if (line == null) {
                throw new EmptyFileException();
            } else {
                temp = line.split(" ");
                rows = Integer.parseInt(temp[0]);
                columns = Integer.parseInt(temp[1]);
            }
            puzzle = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                line = reader.readLine();
                temp = line.split(" ");
                for (int j = 0; j < columns; j++) {
                    puzzle[i][j] = Integer.parseInt(temp[j]);
                }
            }
            FifteenPuzzle fp = new FifteenPuzzle(rows,columns);
            reader.close();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


    }




    
    /*public static void writeFile(String path, String content) {
        path = ".\\output\\" + path;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
