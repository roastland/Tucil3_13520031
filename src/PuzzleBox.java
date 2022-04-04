import java.io.*;
import java.util.*;

public class PuzzleBox {
    int[][] M;
    int rows, cols; 

    // konstruktor default
    public PuzzleBox() {
        this.rows = 4;
        this.cols = 4;
        this.M = new int[rows][cols];
    }

    // membangkitkan urutan puzzle secara acak
    ArrayList<Integer> randomizeNum() {
        int[] initNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        ArrayList<Integer> listNum = new ArrayList<Integer>(initNum.length);
        for (int i : initNum) {
            listNum.add(i);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random rand = new Random();
        // selama list awal belum kosong, pindahkan value pada index
        // yang dibangkitkan secara acak dari list awal ke list result
        while (listNum.size() > 0) {
            int randIdx = rand.nextInt(listNum.size());
            result.add(listNum.get(randIdx));
            listNum.remove(randIdx);
        }
        return result;
    }
    
    // konstruktor puzzle yang dibangkitkan secara acak
    public PuzzleBox(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.M = new int[rows][cols];
        ArrayList<Integer> elmtList = randomizeNum();
        int iterator = 0;
        // memasukkan value ke puzzle sesuai urutan yang telah dibangkitkan
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.M[i][j] = elmtList.get(iterator);
                iterator++;
            }
        }
    }

    // konstruktor puzzle dari input file
    public PuzzleBox(String filename) {
        String text = "";
        try {
            FileReader reader = new FileReader(filename);
            int data = reader.read();
            while (data != -1) {
                text += (char) data;
                data = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // dipisah per baris dari file input
        String[] def = text.split("\\r?\\n");
        this.rows = def.length;
        this.cols = def[0].split(" ").length;
        this.M = new int[rows][cols];
        // memasukkan elemen puzzle
        for (int i = 0; i < rows; i++) {
            String[] temp = def[i].split(" ");
            for (int j = 0; j < cols; j++) {
                M[i][j] = Integer.parseInt(temp[j]);
            }
        }
    }

    // convert puzzle ke list untuk mempermudah pemeriksaan
    ArrayList<Integer> puzzleToList(PuzzleBox pBox) {
        ArrayList<Integer> pList = new ArrayList<Integer>();
        for(int i = 0; i < pBox.rows; i++) {
            for(int j = 0; j < pBox.cols; j++) {
                pList.add(pBox.M[i][j]);
            }
        }
        return pList;
    }

    // teorema untuk menghitung reachable goal
    public int[] teoremaKurang(PuzzleBox pBox) {
        int[] posisiX = {1, 3, 4, 6, 9, 11, 12, 14};    // posisi nilai X=1
        ArrayList<Integer> listX = new ArrayList<Integer>(posisiX.length);
        for (int i : posisiX) {
            listX.add(i);
        }
        ArrayList<Integer> pList = puzzleToList(pBox);  // urutan puzzle
        int[] kurang = new int[17];     // elemen terakhir adalah nilai X
        int nilaiX = 0;
        for (int i = 0; i < pList.size(); i++) {
            // jika sel adalah kotak kosong
            if (pList.get(i) == 0) {
                if (listX.contains(i)) {
                    nilaiX = 1;
                } else {
                    nilaiX = 0;
                }
                kurang[15] = pList.size() - (i+1);
                kurang[16] = nilaiX;
            } else {    // sel bukan kotak kosong 
                for (int j = i+1; j < pList.size(); j++) {
                    if (pList.get(j) < pList.get(i) && pList.get(j) != 0) {
                        kurang[(pList.get(i) - 1)]++;
                    }
                }
            }
        }
        // menampilkan nilai kurang(i) tiap ubin
        for (int i = 0; i < 16; i++) {
            System.out.println("Nilai Kurang(" + Integer.toString(i+1) +
            ") = " + Integer.toString(kurang[i]));
        }
        return kurang;
    }

    // memeriksa apakah goal dapat dicapai
    public boolean isGoalReachable(int[] kurang) {
        int total = kurang[16];     // inisialisasi dengan nilai X
        for (int i = 0; i < 16; i++) {
            total += kurang[i];     // menghitung total kurang(i)
        }
        System.out.println("Nilai dari teorema Reachable Goal: " + Integer.toString(total));
        return (total % 2 == 0);
    }

    // memeriksa apakah keadaan saat ini adalah goal state
    public boolean isGoalState(PuzzleBox pBox) {
        int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        ArrayList<Integer> goalList = new ArrayList<Integer>(goal.length);
        for (int i : goal) {
            goalList.add(i);
        }
        ArrayList<Integer> pList = puzzleToList(pBox);
        return (pList.equals(goalList));
    }
    
    // menampilkan puzzle ke terminal
    public void printPuzzleBox() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                System.out.print(Integer.toString(this.M[i][j]) + " ");
            }
            System.out.println();
        }
    }

}
