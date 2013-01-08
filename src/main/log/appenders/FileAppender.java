package log.appenders;

import java.io.File;

import log.ILogAppender;

public class FileAppender implements ILogAppender {

	private File file = null;
	private String fileName;
	private String currFileName;
	
	public void setFile(String fileName){
		this.fileName = fileName;
		
	}
	
	@Override
	public void logMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
