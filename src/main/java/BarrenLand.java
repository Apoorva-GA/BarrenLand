import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class BarrenLand {

    LinkedList<Integer[]> allRectangles;
    LinkedList<Integer[]> queue;
    HashMap<Integer, Integer> areasMap;
    int mColor[][];
    final static int XLIM = 400;
    final static int YLIM = 600;

    public void readInput(String input){

        String[] parts = input.split(",");
        for(String s:parts){
            s = s.replace("\"", "");
            s = s.replaceAll("“|”", "");
            s = s.replaceAll("\\{|\\}", "");
            s = s.replaceAll("^ ", "");

            if(!s.isEmpty()){
                String[] coord = s.split(" ");

                Integer[] temp = {Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        Integer.parseInt(coord[2]), Integer.parseInt(coord[3])};

                allRectangles.add(temp);
            }
        }

    }

    public void colorBarrenRectangles(){

        ListIterator<Integer[]> iterator = allRectangles.listIterator();
        while(iterator.hasNext()){

            Integer[] rectangle = iterator.next();

            for(int i = rectangle[0]; i <= rectangle[2]; i++)
                for(int j = rectangle[1]; j <= rectangle[3]; j++)
                    mColor[i][j] = 1;
        }
    }

    public void clearColoMatrix() {
        for(int i = 0; i < XLIM; i++)
            for(int j = 0; j < YLIM; j++)
                mColor[i][j] = 0;
    }

    public void addQueue(int i, int j){
        if(mColor[i][j] == 0){
            queue.add(new Integer[] {i, j});
        }
    }

    public String printOutput(){
        int[] result = new int[areasMap.values().size()];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : areasMap.entrySet()){
            result[i] = entry.getValue();
            i++;
        }

        Arrays.sort(result);
        return (Arrays.toString(result)).replaceAll("\\[|\\]|,", "");

    }

    public void readFromSTDIN() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        readInput(s);
    }

    public void getFertileLands(){
        int land = 1;
        int i = 0;
        int j = 0;

        while(i < XLIM && j < YLIM){

            if(queue.isEmpty()) {
                Integer node[] = {i, j};

                if(mColor[i][j] == 0) {
                    land++;
                    areasMap.put(land, 0);
                    queue.add(node);
                }
                if(i == (XLIM-1)){
                    i = 0;
                    j++;
                }
                else
                    i++;
            }

            if(!queue.isEmpty()) {
                Integer node[] = queue.pop();

                int x = node[0];
                int y = node[1];

                if(mColor[x][y] == 0){
                    if(x > 0)
                        addQueue(x-1, y);
                    if(x < (XLIM - 1))
                        addQueue(x+1, y);
                    if(y > 0)
                        addQueue(x, y-1);
                    if(y < (YLIM - 1))
                        addQueue(x, y+1);

                    mColor[x][y] = land;
                    areasMap.put(land, (areasMap.get(land) + 1));
                }
            }
        }

    }

    BarrenLand(){
        allRectangles = new LinkedList<Integer[]>();
        queue = new LinkedList<Integer []>();
        areasMap = new HashMap<Integer, Integer>();
        mColor = new int[XLIM][YLIM];
    }

}