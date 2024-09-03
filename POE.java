import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class POE {
    public static void main(String[] args) {
      long startTime = System.nanoTime();
      
      ArrayList<String> words = new ArrayList<String>();
      ArrayList<String> uniqueWords = new ArrayList<String>();
      ArrayList<Integer> wordFrequencies = new ArrayList<Integer>();

      try {
          File file = new File("src/main/java/poemodified.txt");
          Scanner scanner = new Scanner(file);

          while (scanner.hasNextLine()) {
              String line = scanner.nextLine().toLowerCase().replaceAll("[^a-zA-Z\\s]", " ");
              String[] lineWords = line.split("\\s+");

              for (String word : lineWords) {
                  if (!word.isEmpty()) {
                      words.add(word);

                      if (uniqueWords.contains(word)) {
                          int index = uniqueWords.indexOf(word);
                          wordFrequencies.set(index, wordFrequencies.get(index) + 1);
                      } else {
                          uniqueWords.add(word);
                          wordFrequencies.add(1);
                      }
                  }
              }
          }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

          scanner.close();
        
          /*
          System.out.println("\nList of unique words and their frequencies:");
          for (int i = 0; i < uniqueWords.size(); i++) {
              System.out.println(uniqueWords.get(i) + ": " + wordFrequencies.get(i));
          } 
          */

          int totalWords = words.size();
          System.out.println("Total number of words: " + totalWords);

          int uniqueWordCount = uniqueWords.size();
          System.out.println("Number of unique words: " + uniqueWordCount);

          /*
          bubble sort
          for (int i = 0; i < uniqueWordCount - 1; i++) {
              for (int j = 0; j < uniqueWordCount - i - 1; j++) {
                  if (wordFrequencies.get(j) < wordFrequencies.get(j + 1)) {
                      int tempFrequency = wordFrequencies.get(j);
                      wordFrequencies.set(j, wordFrequencies.get(j + 1));
                      wordFrequencies.set(j + 1, tempFrequency);

                      String tempWord = uniqueWords.get(j);
                      uniqueWords.set(j, uniqueWords.get(j + 1));
                      uniqueWords.set(j + 1, tempWord);
                  }
              }
          } 
        System.out.println(wordFrequencies.get(0));
        */        
          int maxFrequency = 0;
          String mostFrequentWord = "";
          for (int i = 0; i < uniqueWords.size(); i++) {
              if (wordFrequencies.get(i) > maxFrequency) {
                  maxFrequency = wordFrequencies.get(i);
                  mostFrequentWord = uniqueWords.get(i);
              }
          }
          System.out.println("Most frequent word: \"" + mostFrequentWord + "\" used " + maxFrequency + " times");
        
        System.out.println("\nStart Time: " + startTime + " nanoseconds");
        System.out.println("End Time: " + endTime + " nanoseconds");
        float totalTimeSeconds = totalTime/1000000000f;
        System.out.println("\nTotal Time Taken: " + totalTime + " nanoseconds/" + totalTimeSeconds + " seconds");

        System.out.println("\nThe most common word is " + "\"" + mostFrequentWord + "\"" + " with it appearing " + maxFrequency + " times.");

      Scanner searchWord = new Scanner(System.in);
      System.out.println("\nEnter a word to search for: ");
      String searchedWord = searchWord.nextLine();

      if (uniqueWords.contains(searchedWord)) {
          int index = uniqueWords.indexOf(searchedWord);
          int frequency = wordFrequencies.get(index);
          System.out.println("\"" + searchedWord + "\"" + " appears " + frequency + " times.");
      } else {
          System.out.println("\"" + searchedWord + "\"" + " does not appear in the text.");
      }

        
      searchWord.close();
        
      } catch (FileNotFoundException e) {
          System.out.println("File not found. Please check the file path.");
          e.printStackTrace();
      }
    }
}
