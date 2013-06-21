package net.codeforeurope.amsterdam.model.gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.codeforeurope.amsterdam.model.Image;
import net.codeforeurope.amsterdam.util.StringUtils;
import android.util.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ImageTypeAdapter implements JsonDeserializer<Image> {

	private File contentDirectory;

	public ImageTypeAdapter(File contentFile) {
		this.contentDirectory = contentFile;
	}

	@Override
	public Image deserialize(JsonElement element, Type type,
			JsonDeserializationContext serializationContext)
			throws JsonParseException {
		// long start, end;

		File imagesDirectory = new File(contentDirectory, "images");
		Image image = new Image();
		// start = System.currentTimeMillis();
		String imageData = element.getAsString();
		// end = System.currentTimeMillis();
		// Log.d("ImageSerializer", "Time to getAsString: " + (end - start) +
		// "ms");
		try {
			if (!imagesDirectory.exists()) {
				imagesDirectory.mkdir();
			}
			// start = System.currentTimeMillis();
			String checksum = StringUtils.toHexString(MessageDigest
					.getInstance("MD5").digest(imageData.getBytes("UTF-8")));
			// end = System.currentTimeMillis();
			// Log.d("ImageSerializer", "Time to checksum: " + (end - start)
			// + "ms");

			File imageFile = new File(imagesDirectory, checksum + ".jpg");
			if (!imageFile.exists()) {
				writeOutImageFile(imageFile, imageData);
			}
			image.localPath = imageFile.getPath();
			// Log.d("Serializer", image.localPath);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("File not found", e);
		}

		return image;
	}

	private void writeOutImageFile(File imageFile, String imageData)
			throws IOException {
		OutputStream output = new BufferedOutputStream(new FileOutputStream(
				imageFile));
		byte[] decodedImage = Base64.decode(imageData, Base64.DEFAULT);
		output.write(decodedImage);
		output.close();
	}

}
