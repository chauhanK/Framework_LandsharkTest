package org.apitesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public ArrayList<String> readExcel(String filePath, String fileName,
			String sheetName, ArrayList<String> cells) throws IOException {
		File file = null;
		FileInputStream inputStream = null;
		Workbook wb = null;
		Sheet sheet = null;
		CellReference ref = null;
		ArrayList<String> values = new ArrayList<String>();

		try {
			file = new File(filePath + "\\" + fileName);
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
			sheet = wb.getSheet(sheetName);
			
			for (String cell : cells) {
				ref = new CellReference(cell);
				Row r = sheet.getRow(ref.getRow());
				if (r != null) {
					Cell c = r.getCell(ref.getCol());

					values.add(c.getStringCellValue());
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());

		} finally {
			if (inputStream != null)
				inputStream.close();

			if (wb != null)
				wb.close();
		}
		return values;

	}
}
