import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Укажите путь к вашему файлу
        Path filePath = Paths.get("Война и мир_книга.txt");
        String content = null;
        try {
            // Читаем содержимое файла в строку
             content = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Удаляем все символы, кроме букв, цифр и пробелов
        String cleanedStr = content.replaceAll("[^\\p{L}\\p{N}\\s]", "");
        // Удаляем двойные пробелы и абзацы
        cleanedStr = cleanedStr.replaceAll("\\s+", " ").trim();
        cleanedStr = cleanedStr.toLowerCase();
        //System.out.println(cleanedStr);

        //ВСЕ ГОТОВО К ОБРАБОТКЕ
        // Создаем Set для хранения уникальных слов
        Set<String> uniqueWords = new HashSet<>();

        // Создаем Map для хранения слов и их количества
        Map<String, Integer> wordCountMap = new HashMap<>();

        String[] words = cleanedStr.split(" ");

        // Добавляем каждое слово в Set
        for (String word : words) {
            uniqueWords.add(word); //для полсчета колва слов
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1); //для создания Map
        }
        System.out.println("2.1 Количество слов = " + uniqueWords.size());
        //System.out.println("Частота слов: " + wordCountMap);

        // Перебираем wordCountMap с помощью foreach
        String popularWord = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();
            if (count>maxCount){
                maxCount = count;
                popularWord = word;
            }
        }
        // Создаем TreeMap для хранения количества повторений и соответствующих слов
        TreeMap<Integer, List<String>> frequencyMap = new TreeMap<>(Collections.reverseOrder());

        // Заполняем frequencyMap
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            Integer count = entry.getValue();
            String word = entry.getKey();

            // Получаем список слов для данного количества повторений
            frequencyMap.putIfAbsent(count, new ArrayList<>());
            frequencyMap.get(count).add(word);
        }
        // Выводим результат
        // топ 10
        int topCount = 10;
            for (Map.Entry<Integer, List<String>> entry : frequencyMap.entrySet()) {
                Integer count = entry.getKey();
                List<String> wordsWithCount = entry.getValue();
                System.out.println("Количество: " + count + ", Слова: " + wordsWithCount);
                topCount--;
                if (topCount == 0){
                    break;
                }
            }

        //System.out.println("Самое популярное слово = "+ popularWord+ ", количество использований = "+ maxCount);


    }
}