import exceptions.SolvedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class ASTR  {
    FifteenPuzzle fp;
    String strategies;
    HashMap<String, Object> visited;
    Stack<FifteenPuzzle> stack;

    //Character direction;
    //FifteenPuzzle move;
    //Character choice;

    ASTR(FifteenPuzzle fp, String strategies) throws SolvedException  {
        this.fp = fp;
        this.strategies = strategies;
        stack = new Stack();
        visited = new HashMap<>();
        //move.move(choice);
        moving();
    }

    void moving() throws SolvedException
    {
        stack.push(this.fp);
        while(!stack.empty())
        {
            FifteenPuzzle node = stack.pop();
//            while(wierzcholek.sciezkaDoWezla.length()>=main.maksymalnaDozwolonaGlebokoscRekursji)
//            {
//                wierzcholek = stos.pop();
//            }
            FifteenPuzzle.visited++;
//            main.c(wierzcholek.odleglosc);
//            main.c(wierzcholek);
//            if(!listaOdwiedzonych.containsKey(wierzcholek.toString()))
//            {
            if(node.issolved())
            {
                throw new SolvedException(node.node);
            }
//                main.c(wierzcholek.sciezkaDoWezla);
            String direction = node.options("LURD");
//                main.c(dozwoloneRuchy);
//                listaOdwiedzonych.put(wierzcholek.toString(), null);

            if(node.node.length()<=App.RecursionDepth)
            {
                ArrayList<FifteenPuzzle> adding = new ArrayList<>();
                for(int i = 0; i<direction.length(); i++)
                {
                    adding.add(new FifteenPuzzle(node, node.node, direction.charAt(i), strategies));
                }
                Collections.sort(adding);
                Collections.reverse(adding);
                for(FifteenPuzzle fp : adding)
                {
                    stack.push(fp);
                }
            }
//            }
//            else
//            {
////                main.c("wierzcholek juz odwiedzony");
//            }
        }
    }
}
