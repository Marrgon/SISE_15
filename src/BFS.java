import exceptions.SolvedException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {

    FifteenPuzzle fp;
    String strategies;
    HashMap<String, Object> visited;
    Queue<FifteenPuzzle> que;

    //Character direction;
    //FifteenPuzzle move;
    //Character choice;

    BFS(FifteenPuzzle fp, String strategies) throws SolvedException {
        this.fp = fp;
        this.strategies = strategies;

        visited = new HashMap<>();
        que = new LinkedList<>();

        moving();
    }



    void moving() throws SolvedException {
        que.add(this.fp);
        while (!que.isEmpty())
        {
            FifteenPuzzle node = que.poll();
            FifteenPuzzle.visited++;

            if (node.issolved()) {
                throw new SolvedException(node.node);
            }

            String direction = node.options(strategies);


            for (int i = 0; i < direction.length(); i++) {
                que.add(new FifteenPuzzle(node, node.node, direction.charAt(i)));
            }

        }

    }

    /*public static FifteenPuzzle search(Node root, String param) {
        // Node root = new Node(puzzle);
        // Queue<Node> queue = new ArrayDeque<>();
        // queue.add(root);
        // while(!queue.isEmpty()) {
        //     for(Character move : param.toCharArray()) {
        //        
        //     }
        // }
        FifteenPuzzle result = null;
        for (Character move : param.toCharArray()) {
            FifteenPuzzle state = root.getState().clone();
            state.move(move);
            if (state.checkPuzzle()) {
                result = state;
                break;
            }
            
            root.addChild(state, move.toString());
        }
        return result;
    }*/
}
