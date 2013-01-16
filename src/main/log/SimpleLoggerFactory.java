package log;

public class SimpleLoggerFactory {

	private static ILoggerProvider provider = null;

	public synchronized static void Initialize(ILoggerProvider provider) {
		SimpleLoggerFactory.provider = provider;
	}

	public synchronized static void Dispose() {
		if (provider != null) {
			provider.dispose();
		}
	}

	public synchronized static ILogger getLogger() {
		if (provider != null)
			return provider.getLogger();
		return null;
	}

}
