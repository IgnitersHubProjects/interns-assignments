package ignitershub.assignment;

import java.util.*;

public class Question1 {

    public static void main(String[] args) {
        String[] stringSet = {"apple", "banana", "orange", "grape", "melon", "peach"};

        try (
		Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter a word: ");
			String inputWord = scanner.next();
			System.out.print("Enter the value of k: ");
			int k = scanner.nextInt();
			List<String> result = approximateSearch(inputWord, stringSet, k);

			System.out.println("Top " + k + " similar strings:");
			for (String str : result) {
			    System.out.println(str);
			}
		}
    }

    private static List<String> approximateSearch(String inputWord, String[] stringSet, int k) {
        Map<String, Integer> distanceMap = new HashMap<>();

    
        for (String str : stringSet) {
            int distance = distanceOfWord(inputWord, str);
            distanceMap.put(str, distance);
        }

   
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(distanceMap.entrySet());
        sortedList.sort(Comparator.comparing(Map.Entry::getValue));

     
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, sortedList.size()); i++) {
            result.add(sortedList.get(i).getKey());
        }

        return result;
    }

    private static int distanceOfWord(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + compare(word1.charAt(i - 1), word2.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                    );
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    private static int compare(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
