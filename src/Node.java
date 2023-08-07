import java.util.List;

public class Node {
    String tag = "root";
    FifteenPuzzle state;
    List<Node> children;
    int level = 0;

    public Node(FifteenPuzzle state) {
        this.state = state;
    }

    public Node(FifteenPuzzle state, int level, String tag) {
        this.state = state;
        this.level = level;
        this.tag = tag;
    }
    
    public void addChild(FifteenPuzzle state, String tag) {
        children.add(new Node(state, level + 1, tag));
    }
    
    // public void action(Character a) {
    //     FifteenPuzzle newState;
    //     switch (a) {
    //         case 'L':
    //             newState = state.clone();
    //             newState.moveLeft();
    //             this.addChild(newState, "L");
    //             break;
    //         case 'R':
    //             newState = state.clone();
    //             newState.moveRight();
    //             this.addChild(newState, "R");
    //             break;
    //         case 'U':
    //             newState = state.clone();
    //             newState.moveUp();
    //             this.addChild(newState, "U");
    //             break;
    //         case 'D':
    //             newState = state.clone();
    //             newState.moveDown();
    //             this.addChild(newState, "D");
    //             break;
    //     }
    // }

    public FifteenPuzzle getState() {
        return state;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }
}
