
/*
лаб 3.2   5.Задан текстовый файл input.txt. Требуется определить строки этого файла, содержащие максимальную по длине подстроку-палиндром. 
Если таких строк несколько, найти первые 10. Результат вывести на консоль в форме, удобной для чтения.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxPalindromeSubstring {
    public static void main(String[] args) {
        String filename = "input.txt"; // Имя вашего файла
        List<String> maxPalindromeStrings = findMaxPalindromeSubstrings(filename);

        if (maxPalindromeStrings.isEmpty()) {
            System.out.println("В файле нет строк с подстроками-палиндромами.");
        } else {
            System.out.println("Строки с максимальной по длине подстрокой-палиндромом:");
            for (String str : maxPalindromeStrings) {
                System.out.println(str);
            }
        }
    }

    public static List<String> findMaxPalindromeSubstrings(String filename) {
        List<String> result = new ArrayList<>();
        List<String> linesWithPalindrome = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String longestPalindrome = getLongestPalindrome(line);
                if (!longestPalindrome.isEmpty()) {
                    linesWithPalindrome.add(line);
                }
            }

            if (!linesWithPalindrome.isEmpty()) {
                Collections.sort(linesWithPalindrome, (a, b) -> Integer.compare(b.length(), a.length()));
                int count = 0;
                for (String str : linesWithPalindrome) {
                    if (count < 10) {
                        result.add(str);
                        count++;
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getLongestPalindrome(String str) {
        String longest = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String substr = str.substring(i, j);
                if (isPalindrome(substr) && substr.length() > longest.length()) {
                    longest = substr;
                }
            }
        }
        return longest;
    }

    public static boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        return str.equals(sb.reverse().toString());
    }
}

