package com.lhjl.yygh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;

public class SDcard {
	// 在SD卡上创建一个文件夹
	String path;
	int flag = 0,fileLen;
  boolean tag;
 
	public void createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// // 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			path = sdcardDir.getPath() + "/YYGH";
			File path1 = new File(path);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();

			} else {

			}
		}
	}

//	 /**
//	 * 在SD卡上创建文件
//	 *
//	 * @throws IOException
//	 */
//	 public File createSDFile(String fileName) throws IOException {
//	 File file = new File(path + "//" + fileName);
//	 if (!file.exists()) {
//	 file.createNewFile();
//	 }
//	 return file;
//	 }

	/**
	 * 写入内容到SD卡中的txt文本中 str为内容
	 */
	public void writeSDFile(String str, String fileName) {
		 File  f;
		try {

			f = new File(path + "//" + fileName);

			if (!f.exists()) {
				f.createNewFile();
			} 
			input(str, fileName);

		} catch (Exception e) {
		}
		
	}
	/**
	 * 删除txt文件
	 */
	public void deleSDFile( String fileName) {
		 File  f;
		try {

			f = new File(path + "//" + fileName);

			if (!f.exists()) {
				f.delete();
			} 

		} catch (Exception e) {
		}
		
	}
	public void input(String strm, String filename) {
//		String res = "";
//		if (Environment.MEDIA_MOUNTED.equals(Environment
//				.getExternalStorageState())) {
//			// // 创建一个文件夹对象，赋值为外部存储器的目录
//			File sdcardDir = Environment.getExternalStorageDirectory();
//			// 得到一个路径，内容是sdcard的文件夹路径和名字
//			path = sdcardDir.getPath() + "/YYGH";
//		}
		try {
			File file = new File(path, filename);
			FileOutputStream fos = new FileOutputStream(file);
			// String info = "I am a chinanese!";
			System.out.println(strm);
			fos.write(strm.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读在/mnt/sdcard/目录下面的文件

	public String readFileSdcard(String fileName) {

		String res = "";
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// // 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			path = sdcardDir.getPath() + "/YYGH";
		}
		try {
			File file = new File(path, fileName);
			FileInputStream fin = new FileInputStream(file);

			int length = fin.available();

			byte[] buffer = new byte[length];

			fin.read(buffer);

			res = EncodingUtils.getString(buffer, "UTF-8");

			fin.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}
public int size(String fileName){
	if (Environment.MEDIA_MOUNTED.equals(Environment
			.getExternalStorageState())) {
		// // 创建一个文件夹对象，赋值为外部存储器的目录
		File sdcardDir = Environment.getExternalStorageDirectory();
		// 得到一个路径，内容是sdcard的文件夹路径和名字
		String path = sdcardDir.getPath() + "/YYGH";
		
		File f = new File(path + "//" + fileName);
		File dF = new File(f.getPath());
		FileInputStream fis;
		try {
			fis = new FileInputStream(dF);
			int fileLen = fis.available();
			System.out.println(fileLen+f.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	return fileLen;
}
}
