import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CsvConverter {
    public static void main(String[] args) throws UnsupportedEncodingException {
        StringBuilder htmlStr = new StringBuilder("<table>");
        try (PrintWriter writer = new PrintWriter("myhtml.html", "Cp1251");
             Scanner scanner = new Scanner(new FileInputStream("source.csv"))) {

            int cellCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("&", "&amp");

                boolean isDoubleQuotes = false;
                char[] strArray = line.toCharArray();
                for (int i = 0; i < strArray.length; i++) {
                    if (isDoubleQuotes) {
                        isDoubleQuotes = false;
                        continue;
                    }
                    if (i == 0 && cellCount == 0) {
                        htmlStr.append("<tr><td>");
                    }
                    if (i == 0 && cellCount == 1) {
                        htmlStr.append("</br>");
                    }
                    if (i == strArray.length - 1 && strArray[i] == '"') {
                        htmlStr.append("</td></tr>");
                        cellCount = 0;
                        continue;
                    }
                    if (i == strArray.length - 1 && strArray[i] == ',') {
                        htmlStr.append("</td></tr>");
                        cellCount = 0;
                        continue;
                    }
                    if (i == strArray.length - 1 && cellCount == 0) {
                        htmlStr.append(strArray[i]).append("</td></tr>");
                        cellCount = 0;
                        continue;
                    }
                    if (strArray[i] == '"' && cellCount == 0 && i == 0) {
                        cellCount = 1;
                        continue;
                    }
                    if (strArray[i] == '"' && cellCount == 0) {
                        cellCount = 1;
                        continue;
                    }
                    if (i != strArray.length - 1 && cellCount == 1 && strArray[i] == '"' && strArray[i + 1] != '"') {
                        cellCount = 0;
                        continue;
                    }
                    if (strArray[i] == ',' && cellCount == 1) {
                        htmlStr.append(',');
                        continue;
                    }
                    if (strArray[i] == ',' && cellCount == 0) {
                        htmlStr.append("</td><td>");
                        continue;
                    }
                    if (i != strArray.length - 1 && cellCount == 1 && strArray[i] == '"' && strArray[i + 1] == '"') {
                        htmlStr.append('"');
                        isDoubleQuotes = true;
                        continue;
                    }
                    if (strArray[i] == '"') {
                        continue;
                    }
                    htmlStr.append(strArray[i]);
                }
            }
            htmlStr.append("</table>");

            writer.println(htmlStr);
            System.out.println("Конвертация произведена.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}
