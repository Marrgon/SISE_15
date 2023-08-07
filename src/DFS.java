import exceptions.SolvedException;

import java.util.HashMap;
import java.util.Stack;




public class DFS {
    FifteenPuzzle fp;
    String strategies;
    HashMap<String, Object> visited;
    Stack<FifteenPuzzle> stack;

    //Character direction;
    //FifteenPuzzle move;
    //Character choice;

    DFS(FifteenPuzzle fp, String strategies) throws SolvedException {
        this.fp = fp;
        this.strategies = strategies;
        stack = new Stack();
        visited = new HashMap<>();
        //move.move(choice);
        moving();

    }

    void moving() throws SolvedException {
        stack.push(this.fp);
        while(!stack.empty()){
        FifteenPuzzle node = stack.pop();
        FifteenPuzzle.visited++;
        String optionToMove = node.options(strategies);

            /*if(node.issolved())
            {
                throw
            }*/
            //App.print(fp);
            if(node.node.length()<App.RecursionDepth)
            {
                for(int i = 0; i<optionToMove.length(); i++)
                {
                    stack.push(new FifteenPuzzle(node, node.node, optionToMove.charAt(i)));
                }
            }

        }
    }
}
