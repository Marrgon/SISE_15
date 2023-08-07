import exceptions.SolvedException;

import java.io.IOException;

public class Game {
    //final FifteenPuzzle puzzle;
    final String strategy;
    final String strategyParameter;
    final String inputFile;
    final String outputFile;
    final String outputInfoFile;

    public Game(String strategy, String strategyParameter, 
                String inputFile, String outputFile, String outputInfoFile) {
        this.strategy = strategy;
        this.strategyParameter = strategyParameter;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.outputInfoFile = outputInfoFile;
        //this.puzzle = FifteenFiles.readFile(inputFile);
    }
    
    // public void play() {
    //     FifteenPuzzle puzzle = null;
    //    
    //     try {
    //         puzzle = FifteenFiles.readFile(inputFile);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     switch(strategy){
    //         case "bfs":
    //             try {
    //                 new BFS(puzzle, strategyParameter);
    //             } catch (SolvedException e) {
    //                 e.printStackTrace();
    //             }
    //     }
    //    
    // }
}
