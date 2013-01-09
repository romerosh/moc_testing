package log.appenders.file;

import java.util.Date;

public interface IFileManager {

	void setFile(String fileName);

	boolean isExist();

	boolean open();

	void close();

	void writeln(String message);

	Date getFileCreationTime();

	String getFileName();

	String getFileNameWithoutExp();
	boolean createIfNotExist();
}
