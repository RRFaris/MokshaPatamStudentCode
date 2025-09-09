import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Ryan Faris
 *
 */

public class MokshaPatam {
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        Queue<Integer> explored = new LinkedList<>();
        int currentNode = 1;

        int[] rolls = new int[boardsize + 1];
        boolean[] visited = new boolean[boardsize + 1];
        int[] skips = new int[boardsize + 1];

        // Fill array with ladders
        for (int i = 0; i < ladders.length; i++) {
            skips[ladders[i][0]] = ladders[i][1];
        }

        // Fill array with snakes
        for (int i = 0; i < snakes.length; i++) {
            skips[snakes[i][0]] = snakes[i][1];
        }

        explored.add(currentNode);

        while (!explored.isEmpty()) {
            // Take off the first node from the queue
            currentNode = explored.remove();

            // If you reach the last square, you have completed the game
            if (currentNode == boardsize)
                return rolls[currentNode];

            // iterate over every 6 squares in front of a node
            for (int i = 1; i <= 6; i++) {
                int node = currentNode + i;
                // Make sure node can't exceed the limits of the board
                if (node > boardsize)
                    break;

                // Check if node is at the beginning of a snake or ladder
                if (skips[node] != 0)
                    node = skips[node];

                if (!visited[node]) {
                    explored.add(node);
                    // Shouldn't visit the same node more than once
                    visited[node] = true;

                    // Save number of rolls it took to get to that node
                    rolls[node] = rolls[currentNode] + 1;
                }
            }
        }
        // If there are no more nodes to explore, and haven't reached the end, there is no solution
        return -1;
    }
}
