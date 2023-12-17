package assignment03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class OpenDirectory {
    public static void listFiles(File file, String searchText) {
    try (PrintWriter pw = new PrintWriter("output.txt")) {
        System.out.println("Scanning directory: " + file.getAbsolutePath());
        var iter = new FileCompositeIterator(file);
        while (iter.hasNext()) {
            File f = iter.next();
            boolean foundInFilename = f.getName().contains(searchText);
            long occurrences = countOccurrences(f, searchText);
            String message = "";

            if (foundInFilename) {
                if (occurrences > 0) {
                    // Found in both filename and inside the file
                    message = "Found in filename and text of file: " + f.getAbsolutePath() + " (" + occurrences + " times)";
                } else {
                    // Found only in filename
                    message = "Found in filename: " + f.getAbsolutePath();
                }
            } else if (occurrences > 0) {
                // Found only inside the file
                message = "Found in text of file: " + f.getAbsolutePath() + " (" + occurrences + " times)";
            }

            // If the message is not empty, that means there was at least one match
            if (!message.isEmpty()) {
                System.out.println(message);
                pw.println(message);
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}



    public static long countOccurrences(File f, String searchText) {
        long count = 0;
        if (f.isFile() && f.canRead() && f.getName().matches(".*\\.(txt|text|java|c|cpp|h|htm|html|js|hs|xml|log)$")) {
            try (Stream<String> lines = Files.lines(Paths.get(f.getAbsolutePath()))) {
                count = lines
                        .flatMap(line -> Arrays.stream(line.split("\\PL+")))
                        .filter(word -> word.equals(searchText))
                        .count();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the search text: ");
        String searchText = scanner.nextLine();

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        var chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new java.io.File("."));
                        chooser.setDialogTitle("Pick a starting directory");
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(false);

                        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            // File selectedDirectory = chooser.getSelectedFile();
                            // Path normalizedPath = Paths.get(selectedDirectory.getAbsolutePath()).normalize();
                            // System.out.println("getCurrentDirectory(): "
                            //         + chooser.getCurrentDirectory());
                            // System.out.println("getSelectedFile() : "
                            //         + chooser.getSelectedFile());
                            // listFiles(new File(normalizedPath.toString()), searchText);
                            File currentDirectory = chooser.getCurrentDirectory();
                            File selectedFile = chooser.getSelectedFile();
                            Path normalizedCurrentDirectory = Paths.get(currentDirectory.getAbsolutePath()).normalize();
                            Path normalizedSelectedFile = Paths.get(selectedFile.getAbsolutePath()).normalize();
                            
                            System.out.println("getCurrentDirectory(): " 
                                    + normalizedCurrentDirectory);
                            System.out.println("getSelectedFile() : " 
                                    + normalizedSelectedFile);
                            listFiles(new File(normalizedSelectedFile.toString()),searchText);
                        } else {
                            System.out.println("No Selection ");
                        }
                        System.out.println("DONE!");
                    }
                });
        scanner.close();
    }
}
