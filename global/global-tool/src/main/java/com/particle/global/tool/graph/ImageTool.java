package com.particle.global.tool.graph;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageTool {
	/**
	 * 创建图片
	 * @param width
	 * @param height
	 * @param type BufferedImage.TYPE_INT_RGB
	 * @return
	 */
	public final static BufferedImage createImage(int width,int height ,int type){
		BufferedImage image = new BufferedImage(width, height,  type);
		return image;
	}
	/**
	 * 缓冲图片对象转像对象
	 * @param image
	 * @return
	 */
	public final static Image BufferedImageToImage(BufferedImage image){
		return image.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT);
	}
	public final static BufferedImage ImageToBufferedImage(Image image,boolean hasAlpha){
		if (image instanceof BufferedImage) {
			return (BufferedImage)image;
		}
		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent Pixels
		//boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(
					image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			//int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		// Copy image to buffered image
		Graphics g = bimage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}
	/**
	 *
	 * @param byteArrayInputStream
	 * @return
	 * @throws IOException
	 */
	public final static BufferedImage inputStreamToBufferedImage(InputStream byteArrayInputStream) throws IOException {
		return ImageIO.read(byteArrayInputStream);
	}

	/**
	 *
	 * @param bufferedImage
	 * @param formatName 如：png
	 * @return
	 * @throws IOException
	 */
	public final static InputStream bufferedImageToInputStream(BufferedImage bufferedImage,String formatName) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, formatName, os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}

	/**
	 * 计算图片翻转到正常显示需旋转角度
	 * @param inputStream
	 * @return
	 * @throws ImageProcessingException
	 * @throws IOException
	 * @throws MetadataException
	 */
	public static int getRotateAngle(InputStream inputStream) throws ImageProcessingException, IOException, MetadataException {
		int angel = 0;
		Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
		int orientation = 0;
		Iterable<Directory> iterable = metadata.getDirectories();

		for (Iterator<Directory> iter = iterable.iterator(); iter.hasNext(); ) {
			Directory dr = iter.next();
			if (dr.getString(ExifIFD0Directory.TAG_ORIENTATION) != null) {
				orientation = dr.getInt(ExifIFD0Directory.TAG_ORIENTATION);
				break;
			}
		}
		if (orientation == 0 || orientation == 1) {
			angel = 0;
		} else if (orientation == 3) {
			angel = 180;
		} else if (orientation == 6) {
			angel = 90;
		} else if (orientation == 8) {
			angel = 270;
		}
		return angel;
	}

	/**
	 * 计算旋转到的大小
	 * @param src
	 * @param angel {@link ImageTool#getRotateAngle(InputStream)}
	 * @return
	 */
	private static Rectangle getRotatedSize(Rectangle src,int angel){
		// if angel is greater than 90 degree,we need to do some conversion.
		if(angel > 90){
			if(angel / 9%2 ==1){
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}
		double r = Math.sqrt(src.height * src.height + src.width * src.width ) / 2 ;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angelAlpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angelDaltaWidth = Math.atan((double) src.height / src.width);
		double angeDaltaHeight = Math.atan((double) src.width / src.height);

		int lenDaltaWidth = (int) (len * Math.cos(Math.PI - angelAlpha
				- angelDaltaWidth));
		int lenDaltaHeight = (int) (len * Math.cos(Math.PI - angelAlpha
				- angeDaltaHeight));
		int desWidth = src.width + lenDaltaWidth * 2;
		int desHeight = src.height + lenDaltaHeight * 2;
		return new Rectangle(new Dimension(desWidth, desHeight));
	}

	/**
	 * 自动旋转修正图片
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * @throws ImageProcessingException
	 * @throws MetadataException
	 */
	public BufferedImage rotateAuto(InputStream inputStream) throws IOException, ImageProcessingException, MetadataException {
		BufferedImage src = inputStreamToBufferedImage(inputStream);
		BufferedImage bi = null;
		if(src != null){
			int angel = getRotateAngle(inputStream);//得到图片旋转角度
			if(angel == 0){
				return src;//图片正常直接返回
			}else{
				int srcWidth = src.getWidth(null);
				int srcHeight = src.getHeight(null);
				Rectangle rectangle = getRotatedSize(new Rectangle(new Dimension(srcWidth, srcHeight)), angel);
				bi = new BufferedImage(rectangle.width, rectangle.height,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = bi.createGraphics();
				g2.translate((rectangle.width - srcWidth) / 2,
						(rectangle.height - srcHeight) / 2);
				g2.rotate(Math.toRadians(angel), srcWidth / 2, srcHeight / 2);
				g2.drawImage(src, null, null);
				return bi;
			}
		}
		return src;
	}
}
