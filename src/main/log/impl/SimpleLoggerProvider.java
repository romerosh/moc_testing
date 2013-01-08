package log.impl;

import java.util.Collection;
import java.util.HashMap;

import log.ILogAppender;
import log.ILogger;
import log.ILoggerProvider;

public class SimpleLoggerProvider implements ILoggerProvider {

	HashMap<ILogAppender,Collection<String>> appenders;
	
	public SimpleLoggerProvider(){
		appenders = new HashMap<ILogAppender,Collection<String>>();
	}
	
	@Override
	public void addAppender(ILogAppender appender,Collection<String> messageTypes){
		
		this.appenders.put(appender, messageTypes);
	}

	@Override
	public void dispose() {
		for(ILogAppender a : this.appenders.keySet()){
			a.dispose();
		}
	}

	@Override
	public ILogger getLogger() {
		ILogger logger = new SimpleLogger();
		for(ILogAppender a : this.appenders.keySet()){
			logger.addAppender(a, this.appenders.get(a));
		}
		return logger;
	}

}
