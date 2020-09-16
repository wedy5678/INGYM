package com.product_photo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AddAllTestProductPhoto { // �u������@���A��21�Ӵ��հӫ~�s�W���ۤv���Ϥ��A�Ϥ���Ƨ����|�����OC:\\test-product-photo
	public void addOneTestProductPhoto(String fileName, String productNo) {

		PhotoService photoService = new PhotoService();
		byte[] pPhoto = null;

		try {
			String filePath = "C:\\test-product-photo\\" + fileName + ".jpg";
//				String filePath = AddAllTestProductPhoto.class.getResourceAsStream() + fileName + ".jpg";
			File file = new File(filePath);
			@SuppressWarnings("resource")
			InputStream fis = new FileInputStream(file);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			pPhoto = buf;
			photoService.addPhoto(productNo, pPhoto);
			System.out.println(productNo + "�W�[�F�@�i�Ϥ�:" + fileName);

		} catch (Exception e) {
			System.out.println("**"); //�]�����ǹϤ��u��1�i�ҥH�|�o�ͨҥ~�A���O�����z�|�~�����
		}

	}

	public static void main(String[] args) {
		AddAllTestProductPhoto addAllTestProductPhoto = new AddAllTestProductPhoto();
		int x = 1;
		int y = 1;
		String fileName = null;

		while (x <= 21) {
			while (y <= 2) {
				fileName = x + "-" + y;
				String productNo = "P00000" + x;
				if (x >= 10) {
					productNo = "P0000" + x;
				}
				addAllTestProductPhoto.addOneTestProductPhoto(fileName, productNo);
				y++;
			}
			x++;
			y = 1;
		}
	}

}
