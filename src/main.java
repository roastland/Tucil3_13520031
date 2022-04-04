import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        PuzzleBox pBox;
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan nama file ");
        String filename = input.nextLine();
        pBox = new PuzzleBox(filename);
        int kureng = pBox.teoremaKurang(pBox);
        System.out.println(kureng);
        pBox.printPuzzleBox();
    }
}