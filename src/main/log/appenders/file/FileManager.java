package log.appenders.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;


public class FileManager implements IFileManager {

	private PrintWriter writer = null;
	private String fileName;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFile(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getFileName() {

		return this.fileName;
	}

}
