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
import java.util.List;


class CustomerVo {
	 
    public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getCustAge() {
		return custAge;
	}


	public void setCustAge(String custAge) {
		this.custAge = custAge;
	}


	public String getCustEmail() {
		return custEmail;
	}


	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}


	private String  custId;        //고객ID
    private String  custName;    //고객명
    private String    custAge;    //고객나이
    private String    custEmail;    //고객이메일
    
    
    // 생성자
    public CustomerVo(String custId, String custName, String custAge,
            String custEmail) {
        super();
        this.custId = custId;
        this.custName = custName;
        this.custAge = custAge;
        this.custEmail = custEmail;
    }
    
    
}

public class TeamFiles {
	public static void saveText(Object contents, String pathFile) {

	}

	public static void saveCsv(Object contents, String pathFile) {

	}

	public static void saveExcel(Object contents, String pathFile) {

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
		
		List<CustomerVo> list = new ArrayList<CustomerVo>();
		
        list.add(new CustomerVo("asdf1", "사용자1", "30", "asdf1@naver.com"));
        list.add(new CustomerVo("asdf2", "사용자2", "31", "asdf2@naver.com"));
        list.add(new CustomerVo("asdf3", "사용자3", "32", "asdf3@naver.com"));
        list.add(new CustomerVo("asdf4", "사용자4", "33", "asdf4@naver.com"));
        list.add(new CustomerVo("asdf5", "사용자5", "34", "asdf5@naver.com"));

        // 워크북 생성
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        // 워크시트 생성
//        XSSFSheet sheet = workbook.createSheet();
//        // 행 생성
//        XSSFRow row = sheet.createRow(0);
//        // 쎌 생성
//        XSSFCell cell;
//        
//        // 헤더 정보 구성
//        cell = row.createCell(0);
//        cell.setCellValue("아이디");
//        
//        cell = row.createCell(1);
//        cell.setCellValue("이름");
//        
//        cell = row.createCell(2);
//        cell.setCellValue("나이");
//        
//        cell = row.createCell(3);
//        cell.setCellValue("이메일");
//        
//        // 리스트의 size 만큼 row를 생성
//        CustomerVo vo;
//        for(int rowIdx=0; rowIdx < list.size(); rowIdx++) {
//            vo = list.get(rowIdx);
//            
//            // 행 생성
//            row = sheet.createRow(rowIdx+1);
//            
//            cell = row.createCell(0);
//            cell.setCellValue(vo.getCustId());
//            
//            cell = row.createCell(1);
//            cell.setCellValue(vo.getCustName());
//            
//            cell = row.createCell(2);
//            cell.setCellValue(vo.getCustAge());
//            
//            cell = row.createCell(3);
//            cell.setCellValue(vo.getCustEmail());
//            
//        }
//        
//        // 입력된 내용 파일로 쓰기
//        File file = new File("C:\\excel\\testWrite.xlsx");
//        FileOutputStream fos = null;
//        
//        try {
//            fos = new FileOutputStream(file);
//            workbook.write(fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(workbook!=null) workbook.close();
//                if(fos!=null) fos.close();
//                
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
	}
}
