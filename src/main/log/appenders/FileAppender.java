package log.appenders;

import java.util.Calendar;
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
		Date fileCreationTime = fileManager.getFileCreationTime();
		if (fileCreationTime == null)
			return true;
		return currentTime.getTime() - fileCreationTime.getTime() > MSecsInDay;
	}

	protected void storage(Date currentTime) {
		StringBuilder newFileName = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentTime);
		newFileName.append(fileManager.getFileNameWithoutExp());
		newFileName.append("-");
		newFileName.append(Integer.toString(calendar.get(Calendar.YEAR)));
		newFileName.append("-");
		newFileName.append(Integer.toString(calendar.get(Calendar.MONTH)+1));
		newFileName.append("-");
		newFileName.append(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
		newFileName.append(".");
		newFileName.append(fileManager.getFileExp());
		fileManager.storage(newFileName.toString());
	}

	public void setFileManager(IFileManager fileManager) {
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
	synchronized public void logMessage(String message, String messageType) {
		if (fileManager == null)
			return;
		Date currentTime = getCurrentDate();
		if (this.isStorage(currentTime)) {
			this.storage(currentTime);
		}

		fileManager.writeln(messageType + " : " + message);

	}

	@Override
	public void logMessage(Throwable th, String messageType) {
		fileManager.writeln(messageType + ":");
		fileManager.writeln(th);
	}

	@Override
	public void dispose() {
		if (fileManager != null)
			fileManager.close();
	}

}
