package net.skhu.etc;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

	public static void generateQRCodeImage(String name, int id, int width, int height, int op)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(name + " " + id, BarcodeFormat.QR_CODE, width, height);

		Path path;
		if (op == 1)
			path = FileSystems.getDefault().getPath("./src/main/webapp/image/lateQR/" + id + ".png");
		else
			path = FileSystems.getDefault().getPath("./src/main/webapp/image/checkQR/" + id + ".png");

		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

}