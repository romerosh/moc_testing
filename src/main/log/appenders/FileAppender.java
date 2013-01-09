package log.appenders;

import helpers.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import log.ILogAppender;
import log.appenders.file.IFileManager;

public class FileAppender implements ILogAppender {

	protected static final int MSecsInDay = 1000 * 24 * 60 * 60;

	private IFileManager fileManager = null;

	protected Date getCurrentDate() {
		long curTime = System.currentTimeMillis();

		return new Date(curTime);

	}

	protected boolean isStorage(Date currentTime) {
		return currentTime.getTime()
				- fileManager.getFileCreationTime().getTime() > MSecsInDay;
	}

	protected void storage(Date currentTime) {
		fileManager.close();
		StringBuilder newFileName = new StringBuilder();
		FileHelper
				.renameFile(fileManager.getFileName(), newFileName.toString());
		FileHelper.createNewFile(fileManager.getFileName());
		fileManager.open();
	}

	public void setFile(IFileManager fileManager) {
		if (fileManager != null) {
			try {
				this.fileManager = fileManager;
				Date currentTime = getCurrentDate();
				if (this.isStorage(currentTime)) {
					this.storage(currentTime);
				} else {
					fileManager.open();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	synchronized public void logMessage(String message) {
		Date currentTime = getCurrentDate();
		if (this.isStorage(currentTime)) {
			this.storage(currentTime);
		}

	}

	@Override
	public void dispose() {
		fileManager.close();
	}

}
