import exceptions.SolvedException;
import exceptions.WrongMoveException;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class FifteenPuzzle implements Cloneable, Comparable<FifteenPuzzle> {

    int rows;
    int columns;
    int[][] puzzle;
    int[] empty = new int[2];
    int emptyY, emptyX;
    private static String solved;
    static long maxGlebokosc = 0;
    static long processed = 0;
    static long visited = 0;
    String node;
    int distance;
    ArrayList<Character> history = new ArrayList<>();
    boolean rightBlock;
    boolean leftBlock;
    boolean upperBlock;
    boolean downBlock;
    //boolean[][][] blockMask;

    public FifteenPuzzle(int r, int c) {

        this.puzzle = new int[r][c];
        this.distance = 1;
        this.node = "";
        processed++;
    }

    FifteenPuzzle(FifteenPuzzle fp, String parentNode, char c) throws SolvedException {
        this.puzzle = new int[fp.puzzle.length][fp.puzzle[0].length];
        for (int i = 0; i < this.puzzle.length; i++) {
            this.puzzle[i] = Arrays.copyOf(fp.puzzle[i], this.puzzle[i].length);
        }
        this.distance = fp.distance;
        this.emptyY = fp.emptyY;
        this.emptyX = fp.emptyX;
        this.move(c);
        node = parentNode + c;
        if (this.node.length() > maxGlebokosc) maxGlebokosc = this.node.length();
        processed++;
    }

    FifteenPuzzle(FifteenPuzzle fp, String parentNode, char c, String metric) throws SolvedException {
        this.puzzle = new int[fp.puzzle.length][fp.puzzle[0].length];
        for (int i = 0; i < this.puzzle.length; i++) {
            this.puzzle[i] = Arrays.copyOf(fp.puzzle[i], this.puzzle[i].length);
        }
        this.distance = fp.distance;
        this.emptyY = fp.emptyY;
        this.emptyX = fp.emptyX;
        this.move(c);
        node = parentNode + c;
        if (this.node.length() > maxGlebokosc) maxGlebokosc = this.node.length();
        processed++;
        if (metric.equals("manh")) this.ManhDistance();
        else if (metric.equals("hamm")) this.HammDistance();
    }

    /*public FifteenPuzzle(int rows, int columns, int[][] puzzle) {
        this.rows = rows;
        this.columns = columns;
        this.puzzle = puzzle;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (puzzle[i][j] == 0) {
                    empty[0] = i;
                    empty[1] = j;
                }
            }
        }


        /*
          0 - up
          1 - down
          2 - left
          3 - right
         */
   /*     blockMask = new boolean[rows][columns][4];
        for (int i = 0; i < rows; i++) {
            blockMask[i][0][0] = true;
            blockMask[i][rows - 1][1] = true;
        }

        for (int i = 0; i < columns - 1; i++) {
            blockMask[i][0][2] = true;
            blockMask[i][rows - 1][3] = true;
        }
        upperBlock = blockMask[empty[0]][empty[1]][0];
        downBlock = blockMask[empty[0]][empty[1]][1];
        leftBlock = blockMask[empty[0]][empty[1]][2];
        rightBlock = blockMask[empty[0]][empty[1]][3];

    } */



    /*private void moveRight() {
        try {
            if (empty[1] == columns - 1) {
                throw new WrongMoveException("You tried to move too much to the right");
            } else {
                puzzle[empty[0]][empty[1]] = puzzle[empty[0]][empty[1] + 1];
                puzzle[empty[0]][empty[1] + 1] = 0;
                empty[1] += 1;
            }
            System.out.println("moved right");
            history.add('R');
            leftBlock = blockMask[empty[0]][empty[1]][2];
            rightBlock = blockMask[empty[0]][empty[1]][3];

        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
    }

    private void moveLeft() {
        try {
            if (empty[1] == 0) {
                throw new WrongMoveException("You tried to move too much to the left");
            } else {
                puzzle[empty[0]][empty[1]] = puzzle[empty[0]][empty[1] - 1];
                puzzle[empty[0]][empty[1] - 1] = 0;
                empty[1] -= 1;
            }
            System.out.println("moved left");
            history.add('L');
            leftBlock = blockMask[empty[0]][empty[1]][2];
            rightBlock = blockMask[empty[0]][empty[1]][3];

        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
    }

    private void moveUp() {
        try {
            if (empty[0] == 0) {
                throw new WrongMoveException("You tried to move too much upwards");
            } else {
                puzzle[empty[0]][empty[1]] = puzzle[empty[0] - 1][empty[1]];
                puzzle[empty[0] - 1][empty[1]] = 0;
                empty[0] -= 1;
            }
            System.out.println("moved up");
            history.add('U');
            upperBlock = blockMask[empty[0]][empty[1]][0];
            downBlock = blockMask[empty[0]][empty[1]][1];

        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
    }

    private void moveDown() {
        try {
            if (empty[0] == rows - 1) {
                throw new WrongMoveException("You tried to move too much downwards");
            } else {
                puzzle[empty[0]][empty[1]] = puzzle[empty[0] + 1][empty[1]];
                puzzle[empty[0] + 1][empty[1]] = 0;
                empty[0] += 1;
            }
            System.out.println("moved down");
            history.add('D');
            upperBlock = blockMask[empty[0]][empty[1]][0];
            downBlock = blockMask[empty[0]][empty[1]][1];

        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
    }

    public void move(Character direction) {
        switch (direction) {
            case 'L' -> moveLeft();
            case 'R' -> moveRight();
            case 'U' -> moveUp();
            case 'D' -> moveDown();
            default -> throw new IllegalArgumentException("moveDirection must be one of [L, R, U, D] " +
                    "but passed " + direction);
        }
    }*/

    public int getNumber(int rows, int columns) {
        return this.puzzle[rows][columns];
    }

    public void setNumber(int rows, int columns, int number) {
        this.puzzle[rows][columns] = number;
    }

    public boolean move(char c) throws SolvedException {

        boolean worked = false;
        switch (c) {
            case 'U':
                if (emptyY != 0) {
                    swap(emptyY, emptyX, (int) (emptyY - 1), emptyX);
                    worked = true;
                }

                break;
            case 'D':
                if (emptyY != this.puzzle.length - 1) {
                    swap(emptyY, emptyX, (int) (emptyY + 1), emptyX);
                    worked = true;
                }
//                main.c("przesuniecie do dolu");
                break;
            case 'L':
                if (emptyX != 0) {
                    swap(emptyY, emptyX, emptyY, (int) (emptyX - 1));
                    worked = true;
                }
//                main.c("przesuniecie do lewej");
                break;
            case 'R':
                if (emptyX != this.puzzle[emptyX].length - 1) {
                    swap(emptyY, emptyX, emptyY, (int) (emptyX + 1));
                    worked = true;
                }
//                main.c("przesuniecie do prawej");
                break;
        }
//        main.c(this);
        return worked;
    }

    private void swap(int y1, int x1, int y2, int x2) {
        int temp = 0;
        temp = getNumber(y1, x1);

        setNumber(y1, x1, getNumber(y2, x2));

        setNumber(y2, x2, temp);
//        main.c(getLiczba(y2, x2));
        emptyY = y2;
        emptyX = x2;
    }

    public String printPuzzle() {
        System.out.println("\n");
        StringBuilder sb = new StringBuilder();
//        System.out.println("rows = " + rows);
//        System.out.println("columns = " + columns);
        for (int[] row : puzzle) {
            // System.out.println(Arrays.toString(row));
            for (int cell : row) {
                // System.out.print(cell + " ");
                sb.append(cell + " ");
            }
            // System.out.println("");
            sb.append("\n");
        }
        // System.out.println(sb.toString());
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public boolean checkPuzzle() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if ((puzzle[i - 1][j - 1] != i * j) || (i == rows && j == columns && puzzle[i - 1][j - 1] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void searchEmptySquare() {
        for (int i = 0; i < this.puzzle.length; i++) {
            for (int j = 0; j < this.puzzle[i].length; j++) {
                if (this.puzzle[i][j] == 0) {

                    this.emptyY = i;
                    this.emptyX = j;
                    App.print("gdzie zero" + emptyY + emptyX);

                }
            }
        }
    }

    public boolean issolved() {
        if (this.emptyX != this.puzzle[0].length - 1) return false;
        if (this.emptyY != this.puzzle.length - 1) return false;
        return this.toString().equals(solved);
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public ArrayList<Character> getHistory() {
        return history;
    }

    public void printHistory() {
        for (int i = 0; i < history.size(); i++) {
            System.out.print(history.get(i) + " ");
        }
        System.out.print("\b");
    }

    public void readNumber(int rows, int columns, int number) {
        this.puzzle[rows][columns] = number;
    }

    public boolean isBlocked(Character direction) {
        return switch (direction) {
            case 'L' -> leftBlock;
            case 'R' -> rightBlock;
            case 'U' -> upperBlock;
            case 'D' -> downBlock;
            default -> throw new IllegalArgumentException("direction must be one of [L, R, U, D] " +
                    "but passed " + direction);
        };
    }

    public String options(String str) {
        ArrayList<String> ok = new ArrayList<>();
        if (emptyY != 0) ok.add("U");
        if (emptyY != this.puzzle.length - 1) ok.add("D");
        if (emptyX != 0) ok.add("L");
        if (emptyX != this.puzzle[0].length - 1) ok.add("R");
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (ok.contains(str.substring(i, i + 1)))
                res += str.charAt(i);
        }
        return res;
    }

    @Override
    public int compareTo(FifteenPuzzle d) {
        return this.distance - d.distance;
    }

    private void ManhDistance() {
        this.distance = 0;
        for (byte i = 0; i < this.puzzle.length; i++) {
            for (int j = 0; j < this.puzzle[i].length; j++) {
                this.distance += distanceToDestination(this.puzzle[i][j], i, j);
            }
        }
    }

    private int distanceToDestination(int number, int x, int y) {
        for (int i = 0; i < App.solved.puzzle.length; i++) {
            for (int j = 0; j < App.solved.puzzle[i].length; j++) {
                if (App.solved.puzzle[i][j] == number) {

                    return abs(i - x) + abs(j - y);
                }
            }
        }
        return 0;
    }

    private void HammDistance() {
        this.distance = 0;
        for (int i = 0; i < this.puzzle.length; i++) {
            for (int j = 0; j < this.puzzle[i].length; j++) {
                if (this.puzzle[i][j] != App.solved.puzzle[i][j])
                    this.distance++;
            }
        }
    }

    void goodPuzzle() {
        for (int i = 0; i < this.puzzle.length; i++) {
            for (int j = 0; j < this.puzzle[i].length; j++) {
                this.setNumber(i, j, (int) ((i * this.puzzle[i].length) + j + 1));
            }
        }
        this.setNumber((int) (this.puzzle.length - 1), (int) (this.puzzle[0].length - 1), (int) 0);
    }

    void setSolved(String s) {
        FifteenPuzzle.solved = s;

    /*static class SolvedException extends Exception {

        private final String path;

        public SolvedException(String path) {
            this.path = path;
        }

        @Override
        public String toString()
        {
            return path;
        }
    }*/


    /*public String toString() {
        String res = "";
        for (byte i = 0; i<this.puzzle.length; i++)
        {
            for(byte j = 0; j<this.puzzle[i].length; j++)
            {
                res+=this.puzzle[i][j] + " ";
            }
            res +=System.lineSeparator();
        }
        return res;
    }*/




    /*public FifteenPuzzle clone() {
        try {
            FifteenPuzzle clone = (FifteenPuzzle) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }*/
    }
}
