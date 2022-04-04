import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        PuzzleBox pBox = new PuzzleBox();
        Scanner inputInt = new Scanner(System.in);
        Scanner inputTxt = new Scanner(System.in);

        System.out.println("PILIH TIPE MASUKAN PUZZLE");
        System.out.println("1. Dibangkitkan secara acak oleh program");
        System.out.println("2. Menggunakan file teks");
        System.out.println("Tipe masukan yang dipilih (ketik angkanya):");
        int tipeInput = inputInt.nextInt();
        if (tipeInput == 1) {
            pBox = new PuzzleBox(4, 4);
        } else if (tipeInput == 2) {
            System.out.println("Masukkan nama file:");
            String filename = inputTxt.nextLine();
            pBox = new PuzzleBox(filename);
        }
        System.out.println("\nPosisi awal puzzle sebagai berikut:");
        pBox.printPuzzleBox();
        
        long startTime = System.nanoTime();
        long endTime;

        System.out.println("\nNilai fungsi Kurang(i) setiap ubin:");
        int[] kurang = pBox.teoremaKurang(pBox);

        if (!pBox.isGoalReachable(kurang)) {
            System.out.println("\nBerdasarkan teorema Reachable Goal, puzzle ini tidak ada solusinya.");
            endTime = System.nanoTime();
        } else {    // goal dapat dicapai
            // Algo BnB
            endTime = System.nanoTime();
            System.out.println("\nBerikut urutan puzzle dari awal hingga mencapai goal:");
            // Print path
            // print jumlah simpul yg dibangkitkan
        }

        long totalTime = endTime - startTime;
        System.out.println("\nWaktu eksekusi program (dalam nanosecond): " + totalTime);
        inputInt.close();
        inputTxt.close();
    }
}