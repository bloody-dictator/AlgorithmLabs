import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class PrinterWord {
    public static void printMatrix(int[][] matrix){
        for(int i=0; i< matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void writeIntoExcel(String file, String sheetName, String nameAlg, long procTime) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(sheetName);
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        name.setCellValue(nameAlg);
        Cell time = row.createCell(1);
        time.setCellValue(procTime);
        book.write(new FileOutputStream(file));
        book.close();
    }




}
