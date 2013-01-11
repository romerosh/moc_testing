package log.appenders;

import log.ILogAppender;

public class ConsoleAppender implements ILogAppender {

	@Override
	public void logMessage(String message,String messageType) {
		System.out.println(messageType + ": " + message);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void logMessage(Throwable th, String messageType) {
		th.printStackTrace();
	}

}
