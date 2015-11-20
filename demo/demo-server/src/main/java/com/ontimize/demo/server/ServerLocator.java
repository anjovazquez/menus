package com.ontimize.demo.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import com.ontimize.demo.common.IBenMeSabeReferenceLocator;
import com.ontimize.demo.common.dto.NutritionalInformationCategory;
import com.ontimize.demo.server.ws.bedca.BedcaWSHelper;
import com.ontimize.locator.PermissionSecureReferenceLocator;
import com.ontimize.util.remote.BytesBlock;

public class ServerLocator extends PermissionSecureReferenceLocator implements IBenMeSabeReferenceLocator {

	private final String imagesFilePath;

	public ServerLocator(int port, Hashtable parameters) throws Exception {
		super(port, parameters);
		this.imagesFilePath = (String) parameters.get("ImagesFilePath");
	}

	public void saveImage(String imageId, BytesBlock image) throws Exception {
		File imgDir = new File(this.imagesFilePath);
		if(imgDir.exists()){
			String pathFileImage = this.imagesFilePath+File.separator+imageId;
			System.out.println(pathFileImage);
			File f = new File(pathFileImage);
			if(!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(f, false);
			out.write(image.getBytes());
			out.flush();
		}
	}

	public byte[] read(File file) throws IOException {
		//	    if (file.length() > MAX_FILE_SIZE) {
		//	        throw new FileTooBigException(file);
		//	    }
		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}
		}finally {
			try {
				if (ous != null) {
					ous.close();
				}
			} catch (IOException e) {
			}
			try {
				if (ios != null) {
					ios.close();
				}
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}

	public BytesBlock getImage(String imageId) throws Exception {
		File imgPath = new File(this.imagesFilePath+File.separator+imageId);
		if(imgPath.exists()){
			BytesBlock bytesBlock = new BytesBlock(this.read(imgPath));
			return bytesBlock;
		}
		//		throw new ImageNotFoundException(imageId);
		throw new Exception();
	}

	public List<NutritionalInformationCategory> getNutritionalInformation(
			String ingredientId) throws Exception {
		return BedcaWSHelper.getInstance().mapNutritionalInformationListToComponentList(BedcaWSHelper.getInstance().getNutritionalInformation(ingredientId));
	}





}