package log;

public interface ILogAppender {
	
	void  logMessage(String message);

	void dispose();
}
