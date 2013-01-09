package log;


public class SimpleLoggerFactory {
	private static ILoggerProvider provider;
	
	public synchronized static void Initialize(ILoggerProvider provider){
		SimpleLoggerFactory.provider = provider;
	}
	public synchronized static void Dispose(){
		if(SimpleLoggerFactory.provider!=null){
			SimpleLoggerFactory.provider.dispose();
		}
	}
	public synchronized static ILogger getLogger(){
		if(SimpleLoggerFactory.provider!=null)
			return SimpleLoggerFactory.provider.getLogger();
		return null;
	}
	


	
}
