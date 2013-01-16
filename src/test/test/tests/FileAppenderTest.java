package test.tests;

import java.util.Calendar;
import log.appenders.FileAppender;
import log.appenders.file.IFileManager;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class FileAppenderTest {

	/**
	 * Проверяем вызов функции storage, в случае, когда нужно пересохранить лог-файл
	 */
	@Test
	public void StorageTest() {
		Calendar fileCreationTime = Calendar.getInstance();
		
		// Зададим время создания файла
		fileCreationTime.set(2000, 1, 10); 
		
		// Настроим mock
		IFileManager fileManager = mock(IFileManager.class);
		doReturn(fileCreationTime.getTime()).when(fileManager).getFileCreationTime();
		doReturn(true).when(fileManager).storage(anyString());
		doNothing().when(fileManager).writeln(anyString());
		
		// Тест
		FileAppender appender = new FileAppender();
		appender.setFileManager(fileManager);
		appender.logMessage("testing","INFO");
		appender.dispose();
		
		// Функция storage у объекта fileManager должна была быть вызвана 2 раза
		verify(fileManager,times(2)).storage(anyString());
	
	}

	
	@Test
	public void WithoutStorageTest() {
		Calendar fileCreationTime = Calendar.getInstance();
		// Настроим mock
		IFileManager fileManager = mock(IFileManager.class);
		doReturn(fileCreationTime.getTime()).when(fileManager).getFileCreationTime();
		doReturn(true).when(fileManager).storage(anyString());
		doNothing().when(fileManager).writeln(anyString());
		
		// Тест
		FileAppender appender = new FileAppender();
		appender.setFileManager(fileManager);
		appender.logMessage("testing","INFO");
		appender.dispose();
		
		// Функция storage у объекта fileManager не должна быть вызвана
		verify(fileManager,times(0)).storage(anyString());
		
	}
	
}
