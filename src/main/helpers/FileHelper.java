package helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class FileHelper {

	public static Date getCreationFileTime(String fileName) {
		if (fileName == null)
			return null;
		try {
			File f = new File(fileName);
			Path file = Paths.get(f.toURI());
			BasicFileAttributes fileAttributes = Files.readAttributes(file,
					BasicFileAttributes.class);
			FileTime creationTime = fileAttributes.creationTime();
			return new Date(creationTime.toMillis());

		} catch (IOException e) {
		}
		return null;
	}

	public static boolean renameFile(String oldName, String newName) {
		File file = new File(oldName);
		return file.renameTo(new File(newName));
	}

	public static boolean createNewFile(String fileName) {
		File file = new File(fileName);
		boolean result = false;

		try {
			result = file.createNewFile();
		} catch (IOException e) {
			return false;
		}
		return result;
	}
}
