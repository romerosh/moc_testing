package log.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import log.ILogAppender;
import log.ILogger;
import log.MessageType;

public class SimpleLogger implements ILogger {

	private HashMap<String, Collection<ILogAppender>> msgAppenders;

	public SimpleLogger() {
		msgAppenders = new HashMap<String, Collection<ILogAppender>>();
	}

	private void logMessage(String message, String msgType) {
		Collection<ILogAppender> aps = msgAppenders.get(msgType);
		if (aps != null) {
			for (ILogAppender appender : aps) {
				appender.logMessage(msgType + ": " + message);
			}
		}
	}

	@Override
	public void info(String message) {
		logMessage(message, MessageType.INFO.name());
	}

	@Override
	public void error(String message) {
		logMessage(message, MessageType.ERROR.name());
	}

	@Override
	public void debug(String message) {
		logMessage(message, MessageType.DEBUG.name());
	}

	@Override
	public void warn(String message) {
		logMessage(message, MessageType.WARN.name());
	}

	@Override
	public void addAppender(ILogAppender appender,
			Collection<String> messageTypes) {
		for (String mType : messageTypes) {
			Collection<ILogAppender> ap = msgAppenders.get(mType);
			if (ap == null) {
				ap = new ArrayList<ILogAppender>();
				msgAppenders.put(mType, ap);
			} else {
				ap.add(appender);
			}
			ap.add(appender);

		}
	}

}
