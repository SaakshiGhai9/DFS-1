// Time Complexity : O(m*n) Every cell is visited once during bfs traversal
// Space Complexity: O(m * n) The queue may store all cells in grid in the worst case
import java.util.LinkedList;
import java.util.Queue;

public class RottenOrranges {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

    // Add all rotten oranges to the queue and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0; // No fresh oranges, return 0 minutes

        int time = 0;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // BFS to rot adjacent fresh oranges
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;

                for (int i = 0; i < size; i++) {
                int[] rotten = queue.poll();
                for (int[] dir : directions) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];

                // Check if the adjacent cell can be rotted
                    if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.add(new int[] { x, y });
                        freshCount--;
                        rotted = true;
                    }
                }
            }

            if (rotted) time++;
        }

        return freshCount == 0 ? time : -1; // Return -1 if fresh oranges remain
    }

    public static void main(String[] args) {
        RottenOrranges solution = new RottenOrranges();
        int[][] grid = {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 }
        };
        System.out.println("Minimum time: " + solution.orangesRotting(grid)); // Output: 4
    }
}

