package ExcelIO;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jhin
 */
public class ExcelIE {

    /* ---------- Excel Import, Export ---------- */
    public ExcelIE() {
    }

    public static boolean Export() {
        String path = "./src/ExcelFile/test.xlsx";
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(3);
            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
        }

        return true;
    }
}
