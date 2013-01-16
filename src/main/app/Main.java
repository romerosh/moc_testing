package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTML;

import log.ILogger;
import log.ILoggerProvider;
import log.MessageType;
import log.SimpleLoggerFactory;
import log.appenders.ConsoleAppender;
import log.appenders.FileAppender;
import log.appenders.file.FileManager;
import log.impl.SimpleLoggerProvider;

import data.contracts.IDataBaseService;
import data.db.DataBaseService;
import data.db.IDBConnectionFactory;
import data.db.impl.DBConnectionFactory;
import data.db.impl.PGDataSource;
import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.Student;
import data.orm.Subject;
import helpers.*;

public class Main {

	public static ILoggerProvider prepareLogProvider() {

		FileAppender fileAppender = new FileAppender();
		fileAppender.setFileManager(new FileManager("lab5.log"));
		ConsoleAppender consoleAppender = new ConsoleAppender();
		
		SimpleLoggerProvider logProvider = new SimpleLoggerProvider();
		logProvider.addAppenderForMsgTypes(consoleAppender, Arrays.asList(MessageType.ERROR));
		logProvider.addAppenderForMsgTypes(fileAppender, Arrays.asList(MessageType.INFO,
				MessageType.DEBUG, MessageType.WARN, MessageType.ERROR));
		return logProvider;
	}

	public static void main(String[] args) throws ORMObjectException {
		try {
			SimpleLoggerFactory.Initialize(prepareLogProvider());
			App app = new App();
			app.Run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SimpleLoggerFactory.Dispose();
		}
		return;

	}

}
