package ZOutHandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class HandleTxtToSqlQuery {

    private static ArrayList<String> lines = new ArrayList<>();
    private static ArrayList<String> newLines = new ArrayList<>();

//    public static void main(String[] args) {
//        // Đường dẫn đến file cần đọc
//        String filePath = "./src/ZOutHandle/MSM.txt";
//
//        // Sử dụng try-with-resources để tự động đóng BufferedReader khi kết thúc khối try
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//
//            // Đọc từng dòng từ file và hiển thị nó
//            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
//                lines.add(line);
//            }
//
//        } catch (IOException e) {
//        }
//
//        // Xử lý
//        handle();
//        createText();
//    }

    private static void handle() {
        for (String line : lines) {
            String[] type = new String[]{"String", "ItemName", "String", "int", "int", "int"};
            String newLine = "(";
            int i = 0;

            for (String s : line.split(",")) {
                switch (type[i]) {
                    case "String" -> {
                        newLine += "\'" + s + "\'";
                    }
                    case "int" -> {
                        newLine += s;
                    }
                    case "ItemName" ->{
                        newLine += "N\'" + s + "\'";
                    }
                }
                if (i < type.length - 1) {
                    newLine += ", ";
                }
                i++;
            }
            newLine += "),";
            newLines.add(newLine);
        }
        newLines.remove(newLines.size() - 1);
        newLines.remove(newLines.size() - 1);
    }

    private static void createText() {
        String text = "use MSM\ninsert into ITEM values\n";

        newLines.set(0, newLines.get(0).substring(0, 2) + newLines.get(0).substring(3));
        for (String s : newLines) {
            text += s + "\n";
        }
        text = text.substring(0, text.length() - 2);

        System.out.println(text);

        // Đường dẫn đến file cần ghi
        String filePath = "./src/ZOutHandle/SQL.txt";

        // Sử dụng try-with-resources để tự động đóng BufferedWriter khi kết thúc khối try
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Ghi dữ liệu vào file
            writer.write(text);
        } catch (IOException e) {
        }
    }

}
