package log.appenders;

import log.ILogAppender;

public class ConsoleAppender implements ILogAppender {

	@Override
	public void logMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void dispose() {

	}

}
