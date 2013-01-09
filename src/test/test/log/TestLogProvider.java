package test.log;

import java.util.Arrays;
import java.util.List;

import log.ILoggerProvider;
import log.MessageType;
import log.appenders.ConsoleAppender;
import log.impl.SimpleLoggerProvider;

public class TestLogProvider {

	public static ILoggerProvider prepareProvider(){
		List<MessageType> msgTypes = Arrays.asList(MessageType.INFO,MessageType.ERROR,MessageType.WARN);
		ConsoleAppender consoleAppender = new ConsoleAppender();
		SimpleLoggerProvider logProvider = new SimpleLoggerProvider();
		logProvider.addAppenderForMsgTypes(consoleAppender, msgTypes);
		return logProvider;
	}
	
}
