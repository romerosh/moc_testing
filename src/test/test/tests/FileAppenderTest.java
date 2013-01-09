package test.tests;

import helpers.FileHelper;

import java.util.Date;

import log.appenders.FileAppender;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class FileAppenderTest {

	@Test
	public void TimeTest() {
		FileAppender appender = mock(FileAppender.class);
		
		//doReturn(new Date(3424)).when(FileHelper.this).getCreationFileTime(anyString());
		//doNothing().when(FileHelper.this).getCreationFileTime("");
		//doNothing().when(appender).logMessage("test message");
		
		appender.dispose();
		
	}

}
