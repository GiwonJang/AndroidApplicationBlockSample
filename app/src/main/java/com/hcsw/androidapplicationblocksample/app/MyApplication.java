package com.hcsw.androidapplicationblocksample.app;

import android.app.Application;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Created by gwjang on 2014. 3. 19..
 */
public class MyApplication extends Application {

	public MyApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize the singletons so their instances
		// are bound to the application process.
		//initSingletons();

		configureLogbackDirectly();
	}

	private void configureLogbackDirectly() {
		// reset the default context (which may already have been initialized)
		// since we want to reconfigure it
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		context.reset();

		final String LOG_DIR = ContextCompat.getExternalFilesDirs(this, Environment.DIRECTORY_DOCUMENTS)[0].toString();

		// setup LogcatAppender
		PatternLayoutEncoder encoder2 = new PatternLayoutEncoder();
		encoder2.setContext(context);
		//encoder2.setPattern("[%thread] %msg%n");
		encoder2.setPattern("%-4r [%t] %-5p %c{35} - %m%n");
		encoder2.start();

		LogcatAppender logcatAppender = new LogcatAppender();
		logcatAppender.setContext(context);
		logcatAppender.setEncoder(encoder2);
		logcatAppender.start();

		// setup RollingFileAppender
		RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
		rollingFileAppender.setAppend(true);
		rollingFileAppender.setContext(context);

		// OPTIONAL: Set an active log file (separate from the rollover files).
		// If rollingPolicy.fileNamePattern already set, you don't need this.
		rollingFileAppender.setFile(LOG_DIR + "/log.txt");

		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<ILoggingEvent>();
		rollingPolicy.setFileNamePattern(LOG_DIR + "/log.%d.txt");
		rollingPolicy.setMaxHistory(7);
		rollingPolicy.setParent(rollingFileAppender);  // parent and context required!
		rollingPolicy.setContext(context);
		rollingPolicy.start();

		rollingFileAppender.setRollingPolicy(rollingPolicy);

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setPattern(">> %date{HH:mm:ss.SSS} [%t] %-5p %c{35} - %m%n");
		encoder.setContext(context);
		encoder.start();

		rollingFileAppender.setEncoder(encoder);
		rollingFileAppender.start();

		// add the newly created appenders to the root logger;
		// qualify Logger to disambiguate from org.slf4j.Logger
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ALL);
		root.addAppender(logcatAppender);
		root.addAppender(rollingFileAppender);

		// print any status messages (warnings, etc) encountered in logback config
		StatusPrinter.print(context);
	}
}
