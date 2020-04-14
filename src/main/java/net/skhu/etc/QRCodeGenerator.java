package net.skhu.etc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

	private static void generateQRCodeImage(String courId, int width, int height)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(courId, BarcodeFormat.QR_CODE, width, height);

		Path path = FileSystems.getDefault().getPath("./src/main/webapp/image/qr/"+courId+".png");
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

	public static void main(String[] args) {
		try {
			generateQRCodeImage("4", 350, 350);
		} catch (WriterException e) {
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
	}
}