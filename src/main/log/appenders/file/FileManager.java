package log.appenders.file;

import helpers.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class FileManager implements IFileManager {

	private PrintWriter writer = null;
	private String fileName = null;
	private Date creationFileTime = null;

	public FileManager() {

	}

	public FileManager(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean open() {
		try {
			if (fileName == null)
				return false;
			File f = new File(fileName);
			writer = new PrintWriter(f);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void close() {
		if (writer != null) {
			writer.close();
			writer = null;
		}
	}

	@Override
	public void writeln(String message) {
		if (writer != null) {
			writer.println(message);
			writer.flush();
		}
	}

	@Override
	public Date getFileCreationTime() {
		if (creationFileTime == null)
			creationFileTime = FileHelper.getCreationFileTime(this.fileName);
		return creationFileTime;
	}

	@Override
	public void setFile(String fileName) {
		this.fileName = fileName;
		this.creationFileTime = null;
	}

	@Override
	public String getFileName() {

		return this.fileName;
	}

	@Override
	public boolean isExist() {
		if (this.fileName == null)
			return false;
		File file = new File(this.fileName);

		return file.canWrite();
	}

	@Override
	public boolean createIfNotExist() {
		if (isExist() == true)
			return true;
		
		return FileHelper.createNewFile(this.fileName);
	}

	@Override
	public String getFileNameWithoutExp() {
		// TODO Auto-generated method stub
		return null;
	}

}
