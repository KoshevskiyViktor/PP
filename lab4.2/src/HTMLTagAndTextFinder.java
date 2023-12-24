import java.io.*;
import java.util.*;

public class HTMLTagAndTextFinder {

    public static void main(String[] args) {
        try {
            // Чтение HTML-текста из файла input1.html
            List<String> htmlContent = readLinesFromFile("input1.html");

            // Чтение фрагментов текста из файла input2.in
            List<String> searchPhrases = readLinesFromFile("input2.in");

            // Задание для поиска фрагментов текста и тегов в HTML
            List<String> foundPhrases = findPhrasesInHTML(htmlContent, searchPhrases);
            List<String> foundTags = findTagsInHTML(htmlContent);

            // Сортировка тегов по возрастанию количества символов
            Collections.sort(foundTags, Comparator.comparingInt(String::length));

            // Запись результатов в соответствующие выходные файлы
            writeListToFile(foundTags, "output1.out");
            writeListToFile(foundPhrases, "output2.out");

            // Поиск фрагментов текста, которые НЕ были найдены в HTML
            List<String> notFoundPhrases = findNotFoundPhrases(searchPhrases, foundPhrases);
            writeListToFile(notFoundPhrases, "output3.out");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения строк из файла
    public static List<String> readLinesFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    // Метод для поиска фрагментов текста в HTML
    public static List<String> findPhrasesInHTML(List<String> htmlContent, List<String> searchPhrases) {
        List<String> foundPhrases = new ArrayList<>();
        for (String phrase : searchPhrases) {
            boolean found = false;
            for (int i = 0; i < htmlContent.size(); i++) {
                if (htmlContent.get(i).toLowerCase().contains(phrase.toLowerCase())) {
                    foundPhrases.add(String.valueOf(i));
                    found = true;
                    break;
                }
            }
            if (!found) {
                foundPhrases.add("-1");
            }
        }
        return foundPhrases;
    }

    // Метод для поиска тегов в HTML
    public static List<String> findTagsInHTML(List<String> htmlContent) {
        List<String> foundTags = new ArrayList<>();
        for (String line : htmlContent) {
            String[] tags = line.split("[<>]");
            for (String tag : tags) {
                if (!tag.isEmpty() && !tag.contains("/")) {
                    foundTags.add(tag);
                }
            }
        }
        return foundTags;
    }

    // Метод для поиска фрагментов, которые НЕ были найдены в HTML
    public static List<String> findNotFoundPhrases(List<String> searchPhrases, List<String> foundPhrases) {
        List<String> notFoundPhrases = new ArrayList<>();
        for (int i = 0; i < searchPhrases.size(); i++) {
            if (!foundPhrases.contains(String.valueOf(i))) {
                notFoundPhrases.add(searchPhrases.get(i));
            }
        }
        return notFoundPhrases;
    }

    // Метод для записи списка строк в файл
    public static void writeListToFile(List<String> lines, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}