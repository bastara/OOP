import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvConverter {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Неверное количество аргуметов! Передайте программе пути к исходному и конечному файлу!");
            return;
        }

        StringBuilder htmlStr = new StringBuilder("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Конвертирование CSV файла</title></head><body><table border=\"1\">");
        try (PrintWriter writer = new PrintWriter(args[1]);
             Scanner scanner = new Scanner(new FileInputStream(args[0]))) {

            int rowTableCount = 0;
            int cellTableCount = 0;
            boolean isCellOpen = false;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                int cellInRow = 0;
                boolean isDoubleQuotes = false;
                for (int i = 0; i < str.length(); i++) {
                    if (isDoubleQuotes) {
                        isDoubleQuotes = false;
                        continue;
                    }
                    if (i == 0 && !isCellOpen) {
                        htmlStr.append("<tr><td>");
                        cellInRow++;
                        if (rowTableCount == 1) {
                            cellTableCount++;
                        }
                    }
                    if (i == 0 && isCellOpen) {
                        rowTableCount++;
                        htmlStr.append("<br/>");
                    }
                    if (i == str.length() - 1 && str.charAt(i) == '"') {
                        htmlStr.append("</td></tr>");
                        isCellOpen = false;
                        continue;
                    }
                    if (i == str.length() - 1 && str.charAt(i) == ',' && cellInRow < cellTableCount) {
                        htmlStr.append("</td><td></td></tr>");
                        isCellOpen = false;
                        continue;
                    }
                    if (i == str.length() - 1 && str.charAt(i) == ',') {
                        htmlStr.append("</td></tr>");
                        isCellOpen = false;
                        continue;
                    }
                    if (i == str.length() - 1 && !isCellOpen) {
                        htmlStr.append(str.charAt(i)).append("</td></tr>");
                        isCellOpen = false;
                        continue;
                    }
                    if (str.charAt(i) == '"' && !isCellOpen) {
                        isCellOpen = true;
                        continue;
                    }
                    if (i != str.length() - 1 && isCellOpen && str.charAt(i) == '"' && str.charAt(i + 1) != '"') {
                        isCellOpen = false;
                        continue;
                    }
                    if (str.charAt(i) == ',' && isCellOpen) {
                        htmlStr.append(',');
                        continue;
                    }
                    if (str.charAt(i) == ',' && !isCellOpen) {
                        htmlStr.append("</td><td>");
                        cellInRow++;
                        if (rowTableCount == 1) {
                            cellTableCount++;
                        }
                        continue;
                    }
                    if (i != str.length() - 1 && isCellOpen && str.charAt(i) == '"' && str.charAt(i + 1) == '"') {
                        htmlStr.append('"');
                        isDoubleQuotes = true;
                        continue;
                    }
                    if (str.charAt(i) == '"') {
                        continue;
                    }
                    if (str.charAt(i) == '<') {
                        htmlStr.append("&lt");
                        continue;
                    }
                    if (str.charAt(i) == '>') {
                        htmlStr.append("&gt");
                        continue;
                    }
                    if (str.charAt(i) == '&') {
                        htmlStr.append("&amp");
                        continue;
                    }
                    htmlStr.append(str.charAt(i));//можно было сделать через if-else но подумал что код станет менее читабельным.
                }
            }
            htmlStr.append("</table></body></html>");

//            String output=htmlStr.toString().replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("&", "&amp");

            writer.println(htmlStr.toString());
            System.out.println("Конвертация произведена.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте пути файлов.");
        }
    }
}
