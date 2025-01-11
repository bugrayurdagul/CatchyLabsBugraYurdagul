package com.testinium.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class LogUtils {
	public static final Logger logger = LogManager.getLogger();

	public static void log(String text, Object... params){
		logger.printf(Level.INFO, text, Arrays.stream(params).toArray());
	}
}
