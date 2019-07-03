package com.cafebaby.cafewechat.utils;

import com.cafebaby.cafewechat.utils.bean.QRCode;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Created by feizi on 2017/11/24.
 */
public final class QRCodeUtil {


    /**
     * 创建指定格式的QRcode
     * @param msg
     * @return
     */
    public static QRCode qrCodeInstance(String msg){
        QRCode qrCode = new QRCode(200, 200, 0, "L", "jpg", msg);
        return qrCode;
    }


    /**
     * 创建二维码
     * @param filePath 二维码保存路径
     * @param qrCode 二维码对象
     * @return
     * @throws IOException
     */
    public static String createQRCode(String filePath, QRCode qrCode) throws IOException {
        QRCodeWriter writer = new QRCodeWriter();
        Hashtable hashtable = new Hashtable();
        //字符编码
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //纠错等级L,M,Q,H
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(qrCode.getLevel()));
        //边距
        hashtable.put(EncodeHintType.MARGIN, qrCode.getMargin());

        FileOutputStream out = null;
        try {
            //文件输出流
            out = new FileOutputStream(filePath);
            BitMatrix bitMatrix = writer.encode(qrCode.getContent(), BarcodeFormat.QR_CODE,qrCode.getHeight(), qrCode.getWidth(), hashtable);
            MatrixToImageWriter.writeToStream(bitMatrix,qrCode.getFormat(),out);
        } catch (FileNotFoundException e) {
            filePath = null;
            e.printStackTrace();
        } catch (WriterException e) {
            filePath = null;
            e.printStackTrace();
        }finally {
            if (out != null){
                out.flush();
                out.close();
            }
        }
        return filePath;
    }

    /**
     * 解析二维码
     * @param filePath 文件路径
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static String decodeQRCode(String filePath) throws IOException, NotFoundException {
        MultiFormatReader reader = new MultiFormatReader();
        FileInputStream input = new FileInputStream(filePath);
        BufferedImage image = ImageIO.read(input);

        Binarizer binarizer = new HybridBinarizer(new BufferedImageLuminanceSource(image));
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Result res = reader.decode(binaryBitmap);
        if(null == res){
            return null;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        /**
         * 创建二维码
         */
//        try {
//            QRCode qrCode = new QRCode(200, 200, 0, "L", "jpg", UUID.randomUUID().toString());
//            String filePath = QRCodeUtil.createQRCode("E:\\qrcode.jpg", qrCode);
//            System.out.println("二维码生成成功： " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /**
         * 解析二维码
         */
        try {
            String content = QRCodeUtil.decodeQRCode("E:\\qrcode.jpg");
            System.out.println("解析二维码成功：" + content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
