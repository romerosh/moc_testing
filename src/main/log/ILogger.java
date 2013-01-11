package log;

import java.util.Collection;

public interface ILogger {
    
	void addAppender(ILogAppender appender,Collection<String> messageTypes);

	void info(String message);

	void error(String message);

	void debug(String message);

	void warn(String message);

	void debug(Throwable throwable);
	
	void error(Throwable throwable);

}
