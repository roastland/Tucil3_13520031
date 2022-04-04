import java.io.*;
import java.util.*;

public class BranchNBound {
    
    class Node {
        Node parent;
        PuzzleBox pBox;
        int x, y;
        int cost, moves;
    }

    // membuat node baru
    public Node newNode(PuzzleBox pBox, int x, int y, int newX, int newY, int moves, Node parent) {
        Node node = new Node();
        node.parent = parent;
        node.pBox = pBox.copyPBox(pBox);
        // swap empty tile ke suatu arah
        int temp = node.pBox.M[x][y];
        node.pBox.M[x][y] = node.pBox.M[newX][newY];
        node.pBox.M[newX][newY] = temp;
        // menghitung cost dan jumlah simpul
        node.cost = costToGoal(node.pBox);
        node.moves = moves;
        // update index empty tile
        node.x = newX;
        node.y = newY;
        return node;
    }

    // menghitung cost untuk mencapai goal node dari node saat ini
    public int costToGoal(PuzzleBox pBox) {
        int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        ArrayList<Integer> goalList = pBox.arrayToList(goal);
        ArrayList<Integer> pList = pBox.puzzleToList(pBox);
        int cost = 0;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i) == 0)  continue;
            else if (pList.get(i) != goalList.get(i))    cost++;
        }
        return cost;
    }
    
    // down, left, up, right
    int row[] = {1, 0, -1, 0};
    int col[] = {0, -1, 0, 1};
    
    // memeriksa jika pergerakan valid
    boolean isValid(int x, int y) {
        return (x >= 0 && x < 4 && y >= 0 && y < 4);
    }
    
    // print path dari root node ke goal node
    void printPath(Node root) {
        if (root == null)    return;
        printPath(root.parent);
        root.pBox.printPuzzleBox();
        System.out.println();
    }
    
    // comparator untuk mengatur urutan priority queue
    class pqComparator implements Comparator<Node> {
        public int compare(Node node1, Node node2) {
            if (node1.cost > node2.cost) {
                return 1;
            } else if (node1.cost < node2.cost) {
                return -1;
            }
            return 0;
        }
    }
    
    // fungsi penyelesaian
    void solve(PuzzleBox pBox, int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new pqComparator());
        Node root = newNode(pBox, x, y, x, y, 0, null);
        // push root ke prio queue dan melakukan pencarian
        // selama prio queue belum kosong
        pq.add(root);
        while (!pq.isEmpty()) {
            // mengambil head dari priority queue
            Node min = pq.poll();
            // jika node adalah simpul solusi (tidak memiliki cost ke goal)
            if (min.cost == 0) {
                printPath(min);
                System.out.println("\nJumlah simpul: " + Integer.toString(min.moves));
                return;
            }
            // membangkitkan simpul dari perpindahan yang valid
            for (int i = 0; i < 4; i++) {
                if (isValid(min.x + row[i], min.y + col[i])) {
                    // membuat child node
                    Node child = newNode(min.pBox, min.x, min.y,
                                min.x + row[i], min.y + col[i],
                                min.moves + 1, min);
                    // menambah child ke prio queue
                    pq.add(child);
                }
            }
        }
    }

}
