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
		if(fileName==null)
			return null;
		try {
			File f = new File(fileName);
			Path file = Paths.get(f.toURI());
			BasicFileAttributes fileAttributes = Files.readAttributes(file,
					BasicFileAttributes.class);
			FileTime creationTime = fileAttributes.creationTime();
			return new Date(creationTime.toMillis());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void renameFile(String oldName, String newName){
		
	}

	public static void createNewFile(String fileName) {
		// TODO Auto-generated method stub
		
	}
}
