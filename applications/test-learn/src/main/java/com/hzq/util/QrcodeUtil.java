package com.hzq.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/22 15:16
 */
public class QrcodeUtil {
    private static final String QR_CODE_IMAGE_PATH = "D:\\MyQRCode.png";

    public static void generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }


    public static String decodeQRCodeImage() throws ChecksumException, NotFoundException, FormatException, IOException {
        BufferedImage read = ImageIO.read(new File(QR_CODE_IMAGE_PATH));
        LuminanceSource source = new BufferedImageLuminanceSource(read);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        QRCodeReader reader = new QRCodeReader();
        Result decode = reader.decode(binaryBitmap);
        return decode.getText();
    }
}
