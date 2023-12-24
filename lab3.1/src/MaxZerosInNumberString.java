/*
лаб 3.1   5.Задана строка  из слов. Слова в исходной строке разделяются некоторым множеством разделителей.
 Среди слов, состоящих только из цифр, найти слово, содержащее максимальное число нулей. Если таких слов больше одного, найти предпоследнее из них.
  Слова в новой строке должны разделяться ровно одним пробелом.
 */



import java.util.StringTokenizer;

public class MaxZerosInNumberString {

    public static void main(String[] args) {
        String inputString = "Это 00123 строка с 0000234 словами, содержащими 000012000000 много нулей 00";

        String maxZerosWord = findMaxZerosWord(inputString);
        System.out.println("Слово с максимальным числом нулей: " + maxZerosWord);
    }

    public static String findMaxZerosWord(String inputString) {
        StringTokenizer tokenizer = new StringTokenizer(inputString, " \t\n\r\f,.:;!?"); // Указываем разделители слов
        String maxZerosWord = "";
        int maxZerosCount = 0;
        int prevMaxZerosCount = 0;

        while (tokenizer.hasMoreTokens()) {
            String currentWord = tokenizer.nextToken();
            if (isNumeric(currentWord)) {
                int zerosCount = countZeros(currentWord);
                if (zerosCount >= maxZerosCount) {
                    prevMaxZerosCount = maxZerosCount;
                    maxZerosCount = zerosCount;
                    maxZerosWord = currentWord;
                }
            }
        }

        return prevMaxZerosCount > 0 ? maxZerosWord : "";
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static int countZeros(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }
}

