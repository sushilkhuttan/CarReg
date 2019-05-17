package OnlineCarRegTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;  //.. for xlsx files


public class ReadExcelxls 
{
	HSSFWorkbook wb;
	HSSFSheet sheet;
	
	public ReadExcelxls(String path) throws IOException 
	{
		try {
		File srcfile = new File(path);
		FileInputStream fis = new FileInputStream(srcfile);
		wb = new HSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		}catch (Exception e) {
			e.getMessage();
		}

	}
	
	public String getData(int sheetnum,int row, int col)
	{
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount() {
		int r = sheet.getLastRowNum();
		
		return r;
}
	
	
}
