package log.appenders.file;

import helpers.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

public class FileManager implements IFileManager {

	private PrintWriter writer = null;
	private String fileName = null;
	private Date creationFileTime = null;
	private boolean isCanOpen = true;

	public FileManager() {

	}

	public FileManager(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean open() {
		try {
			if (fileName == null || isCanOpen == false)
				return false;
			if (isExist() == false){
				create();
			}
			File f = new File(fileName);
			FileWriter w = new FileWriter(f,true);
			writer = new PrintWriter(w);
			
			return true;
		} catch (IOException e) {
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
		if (writer == null)
			open();
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
		try {
			return file.canWrite();
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public String getFileNameWithoutExp() {
		if(fileName == null)
			return null;
		
		return  FilenameUtils.removeExtension(this.fileName);
	}

	private boolean create() {
		return FileHelper.createNewFile(getFileName());
	}

	@Override
	public boolean storage(String newFileName) {

		boolean isOpen = writer == null ? false : true;
		isCanOpen = false;
		try {
			close();
			boolean isOK = false;
			if (isExist())
				isOK = FileHelper.renameFile(getFileName(),
						newFileName.toString());
			else {
				isOK = FileHelper.createNewFile(getFileName());
				isCanOpen = true;
				return isOK;
			}
			if (isOK == false)
				return false;
			isOK = FileHelper.createNewFile(getFileName());
			if (isOK == false)
				return false;
			isCanOpen = true;
			if (isOpen)
				return open();

		} catch (Exception e) {

		} finally {
			isCanOpen = true;
		}
		return false;
	}

	@Override
	public void writeln(Throwable th) {
		if (this.writer != null)
			th.printStackTrace(this.writer);

	}

	@Override
	public String getFileExp() {
		if(fileName == null)
			return null;
		return  FilenameUtils.getExtension(this.fileName);
	}
}
