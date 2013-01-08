package log;

import java.util.Collection;

public interface ILoggerProvider {
    
	void addAppender(ILogAppender appender,Collection<String> messageTypes);

	void dispose();

	ILogger getLogger();
}
