package com.testinium.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class Resources {
	private static final Gson gson = new Gson();

	private static JsonObject credentials;
	private static JsonObject configuration;

	/**
	 * Read the contents of the file
	 * @param fileName The filename of the file in the test_data folder
	 * @return The contents of the file
	 */
	private static String fileContents(String fileName){
		final String name = "test_data/" + fileName;
		final InputStream is = Resources.class.getClassLoader().getResourceAsStream(name);
		if(is==null)
			throw new RuntimeException("File not found");
		Scanner reader = new Scanner(is).useDelimiter("\\A");
		return reader.next();
	}

	/**
	 * Get the credentials from the file
	 * @return The credentials object
	 */
	public static JsonObject credentials(){
		if(Objects.isNull(credentials))
			credentials = getObjectOfFile("credentials");
		return credentials;
	}

	/**
	 * Get the configuration from the file
	 * @return The configuration object
	 */
	public static JsonObject getConfiguration(){
		if(Objects.isNull(configuration))
			configuration = getObjectOfFile("configuration");
		return configuration;
	}

	/**
	 * Get the object from the file
	 * @param filename The filename of the file in the test_data folder
	 * @return The object
	 */
	public static JsonObject getObjectOfFile(String filename){
		return gson.fromJson(fileContents(filename + ".json"), JsonObject.class);
	}
}