package kr.or.bit.team1.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.or.bit.team1.Sales;



public class TeamFiles {
	public static void saveExcel(List<Sales> list, String pathFile) {

        // 워크북 생성
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 워크시트 생성
        XSSFSheet sheet = workbook.createSheet();
        // 행 생성
        XSSFRow row = sheet.createRow(1);
        // 쎌 생성
        XSSFCell cell;
        
        // Date
        XSSFRow daterow = sheet.createRow(0);
        
        cell = daterow.createCell(0);
        	cell.setCellValue("DATE");
        	cell = daterow.createCell(1);
        	cell.setCellValue(list.get(0).getDate());
        // 헤더 정보 구성
        cell = row.createCell(0);
        cell.setCellValue("메뉴이름");
        
        cell = row.createCell(1);
        cell.setCellValue("단가");
        
        cell = row.createCell(2);
        cell.setCellValue("판매수량");
        
        cell = row.createCell(3);
        cell.setCellValue("판매금액");
        Sales vo;
        // 리스트의 size 만큼 row를 생성
        for(int rowIdx=0; rowIdx < list.size(); rowIdx++) {
            vo = list.get(rowIdx);
            
            // 행 생성
            row = sheet.createRow(rowIdx+2);
            
            cell = row.createCell(0);
            cell.setCellValue(vo.getMenuName());
            
            cell = row.createCell(1);
            cell.setCellValue(vo.getPrice());
            
            cell = row.createCell(2);
            cell.setCellValue(vo.getQty());
            
            cell = row.createCell(3);
            cell.setCellValue(vo.getSales());
            
        }
        
        // 입력된 내용 파일로 쓰기
        File file = new File(pathFile);
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	}

	public static void saveObject(Object contents, String pathFile) {
		TeamLogger.info("saveObject(Object contents, String pathFile)");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(pathFile);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			oos.writeObject(contents);

			TeamLogger.info(pathFile + " 데이터가 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			TeamLogger.info(e.getMessage());
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TeamLogger.info(e.getMessage());
			}
		}
	}
	
	public static Object loadObject(String pathFile) {
		TeamLogger.info("loadObject(String pathFile)");
		Object obj=null;
		File file = null; 
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			file = new File(pathFile);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			obj = ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TeamLogger.info(pathFile + "이 로드되었습니다.");
		return obj;
	}
	
	
	public static void main(String[] args) {
		
		List<Sales> list = new ArrayList<Sales>();
		String date=TeamFormat.dateFormat(new Date());
        list.add(new Sales(date, "짜장", 5000, 1, 5000));
        list.add(new Sales(date, "짬뽕", 6000, 1, 12000));
        list.add(new Sales(date, "우동", 5500, 0, 0));

        // 워크북 생성
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 워크시트 생성
        XSSFSheet sheet = workbook.createSheet();
        // 행 생성
        XSSFRow row = sheet.createRow(1);
        // 쎌 생성
        XSSFCell cell;
        
        // Date
        XSSFRow daterow = sheet.createRow(0);
        
        cell = daterow.createCell(0);
        	cell.setCellValue("DATE");
        	cell = daterow.createCell(1);
        	cell.setCellValue(list.get(0).getDate());
        // 헤더 정보 구성
        cell = row.createCell(0);
        cell.setCellValue("메뉴이름");
        
        cell = row.createCell(1);
        cell.setCellValue("단가");
        
        cell = row.createCell(2);
        cell.setCellValue("판매수량");
        
        cell = row.createCell(3);
        cell.setCellValue("판매금액");
        
        // 리스트의 size 만큼 row를 생성
        Sales vo;
        for(int rowIdx=0; rowIdx < list.size(); rowIdx++) {
            vo = list.get(rowIdx);
            
            // 행 생성
            row = sheet.createRow(rowIdx+2);
            
            cell = row.createCell(0);
            cell.setCellValue(vo.getMenuName());
            
            cell = row.createCell(1);
            cell.setCellValue(vo.getPrice());
            
            cell = row.createCell(2);
            cell.setCellValue(vo.getQty());
            
            cell = row.createCell(3);
            cell.setCellValue(vo.getSales());
            
        }
        
        // 입력된 내용 파일로 쓰기
        //File file = new File("C:\\Temp\\Sales.xlsx");
        File file = new File("Sales.xlsx");
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

	}
}
