package log;

public interface ILogAppender {

	void logMessage(String message, String messageType);

	void logMessage(Throwable th, String messageType);

	void dispose();
}
