import java.util.*;

public class Main {

    
    public static int calculateEditDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; 
                } else if (j == 0) {
                    dp[i][j] = i; 
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                            Math.min(dp[i - 1][j], 
                                    dp[i][j - 1])); 
                }
            }
        }
        return dp[m][n];
    }

    
    public static List<String> approximateSearch(String query, List<String> strings, int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            int distanceA = calculateEditDistance(query, a);
            int distanceB = calculateEditDistance(query, b);
            if (distanceA == distanceB) {
                return a.compareTo(b); 
            }
            return Integer.compare(distanceA, distanceB);
        });

        for (String s : strings) {
            minHeap.offer(s);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            result.add(minHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of strings (N):");
        int n = sc.nextInt();
        sc.nextLine(); 

        List<String> strings = new ArrayList<>();
        System.out.println("Enter the strings:");
        for (int i = 0; i < n; i++) {
            strings.add(sc.nextLine());
        }

        System.out.println("Enter the word to search for:");
        String query = sc.nextLine();

        System.out.println("Enter the number of top similar strings to find (k):");
        int k = sc.nextInt();

        List<String> topKStrings = approximateSearch(query, strings, k);

        System.out.println("Top " + k + " similar strings:");
        for (String s : topKStrings) {
            System.out.println(s);
        }

        sc.close();
    }
}
