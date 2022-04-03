import java.io.*;
import java.util.*;

public class PuzzleBox {
    int[][] M;
    int rows, cols;

    public PuzzleBox(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.M = new int[rows][cols];
        ArrayList<Integer> elmtList = randomizeNum();
        int iterator = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.M[i][j] = elmtList.get(iterator);
                iterator++;
            }
        }
    }
/*
    public PuzzleBox(int rows, int cols) {
        this.M = new int[rows][cols];
        this.rows = rows;
        this.cols =cols;
    }

    public PuzzleBox(int[][] M) {
        this.rows = M.length;
        this.cols = M[0].length;
        this.M = new char[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.M[i][j] = M[i][j];
            }
        }
    }
*/
    ArrayList<Integer> randomizeNum() {
        int[] initNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        ArrayList<Integer> listNum = new ArrayList<Integer>(initNum.length);
        for (int i : initNum) {
            listNum.add(i);
        }
        //Collections.addAll(listNum, initNum);
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random rand = new Random();

        while (listNum.size() > 0) {
            int randIdx = rand.nextInt(listNum.size());
            result.add(listNum.get(randIdx));
            System.out.println(result);
            listNum.remove(randIdx);
            System.out.println(listNum);
        }
        
        return result;
    }

    public void printPuzzleBox() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                System.out.print(Integer.toString(this.M[i][j]) + " ");
            }
            System.out.println();
        }
    }
/*
    public boolean isGoalState(PuzzleBox M) {

    }
    */
}
