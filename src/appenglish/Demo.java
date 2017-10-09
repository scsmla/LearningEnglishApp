/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appenglish;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 *
 * @author Bui
 */
public class Demo {
    public static void readExcel()
    {
        Workbook workbook;
        try {
            // create workbook to open file
            workbook = Workbook.getWorkbook(new File("E:\\demo.xlxs"));
            // get sheet want read
            Sheet sheet = workbook.getSheet(0);
            // get number row and col contain data
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
 
            System.out.println("Data in file:");
            // read data in each cell
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Cell cell = sheet.getCell(col, row);
                    System.out.print(cell.getContents() + "\t");
                }
                System.out.println("\n");
            }
            // close
            workbook.close();
        } catch (BiffException e) {
            System.out.println("File not found\n" + e.toString());
        } catch (IOException e) {
            System.out.println("File not found\n" + e.toString());
        }
    }
    public static void main(String  args[])
    {
        readExcel();
    }
}
