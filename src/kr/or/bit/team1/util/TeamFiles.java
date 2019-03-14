package kr.or.bit.team1.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

			System.out.println("데이터가 저장되었습니다.");
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
			}
		}
	}
}
