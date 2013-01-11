package log.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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
				appender.logMessage(message, msgType);
			}
		}
	}

	private void logMessage(Throwable throwable, String msgType) {
		Collection<ILogAppender> aps = msgAppenders.get(msgType);
		if (aps != null) {
			for (ILogAppender appender : aps) {
				appender.logMessage(throwable, msgType);
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
			} 
			ap.add(appender);
		}
	}

	@Override
	public void error(Throwable throwable) {
		logMessage(throwable, MessageType.ERROR.name());
	}

	@Override
	public void debug(Throwable throwable) {
		logMessage(throwable, MessageType.DEBUG.name());
	}

}
