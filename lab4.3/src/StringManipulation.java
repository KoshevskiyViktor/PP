import java.io.*;
import java.util.regex.*;

public class StringManipulation {

    public static void main(String[] args) {
        try {
            // Получение строки из файла
            String inputString = readFromFile("input.txt");

            // Выполнение заданий и запись результатов в файл output.txt
            String result1 = excludeInsideParentheses(inputString);
            writeToFile(result1, "output1.txt");

            String result2 = removeDigitsMoreThanTwo(inputString);
            writeToFile(result2, "output2.txt");

            String result3 = removeLeadingZerosFromDigits(inputString);
            writeToFile(result3, "output3.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения строки из файла
    public static String readFromFile(String filename) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    // Метод для записи строки в файл
    public static void writeToFile(String content, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }

    // Удалить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.
    public static String excludeInsideParentheses(String inputString) {
        Pattern pattern = Pattern.compile("\\([^()]*\\)");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll("");
    }

    // Удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.
    public static String removeDigitsMoreThanTwo(String inputString) {
        Pattern pattern = Pattern.compile("\\b(\\d{2})\\d+\\b");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll("$1");
    }

    // Удалить из каждой группы идущих подряд цифр все незначащие нули.
    public static String removeLeadingZerosFromDigits(String inputString) {
        Pattern pattern = Pattern.compile("\\b(0+)([1-9]+\\d*)\\b");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll("$2");
    }
}