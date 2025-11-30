package rahulshettyacadamy.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static List<HashMap<String, String>> getData(String filePath, String sheetName) throws IOException {

        List<HashMap<String, String>> data = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Header row = row 0
        Row headerRow = sheet.getRow(0);
        int lastRow = sheet.getPhysicalNumberOfRows();
        int lastCol = headerRow.getLastCellNum();

        // Loop all data rows (start from 1, because 0 is header)
        for (int i = 1; i < lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            HashMap<String, String> rowData = new HashMap<>();

            for (int j = 0; j < lastCol; j++) {
                Cell headerCell = headerRow.getCell(j);
                Cell cell = row.getCell(j);

                String key = headerCell.getStringCellValue().trim();
                String value = "";

                if (cell != null) {
                    cell.setCellType(CellType.STRING);   // convert any type to String
                    value = cell.getStringCellValue().trim();
                }

                rowData.put(key, value);
            }

            data.add(rowData);
        }

        workbook.close();
        fis.close();

        return data;
    }
}
