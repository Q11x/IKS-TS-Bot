/**
 * Read config that specifies program parameters.
 * 
 * Note! This code doesn't work as intended, and is only here to be available to be continued work on, for when it would be useful for a config again. It's no longer a priority.
 */
package ioLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Jacob
 *
 */
public class ReadConfig {
	
	//TODO Retrieve configPath and configName from requesting class.
	private String configPath = ""; //Path to the folder which the config lies within. Example: "C://EJ_Service"
	private final String configName = "config.properties"; //The name of the config file.
	private HashMap<String, String> properties;

	/**
	 * @throws IOException 
	 * 
	 */
	public ReadConfig() throws IOException {
		// TODO Auto-generated constructor stub
		//configPath = System.getProperty("user.dir") + "//resources";
		loadProperties();
	}

	public void loadProperties() throws IOException {

		Properties prop = new Properties();
		InputStream inputStream = null;

		File file = new File(configPath + "/" + configName);
		try(FileInputStream InputStream = new FileInputStream(file)) {
			System.out.println("Config File loaded sucessfully!");
			prop.load(inputStream);
			properties = new HashMap<String, String>();
			for(String key : prop.stringPropertyNames()) { //iterates through the properties file and gets a key.
				properties.put(key, prop.getProperty(key)); //Put the key and it's value in the table
				System.out.println(key + prop.getProperty(key));
			}
		}
		catch(FileNotFoundException  e) {
			//System.out.println("Config file doesn't exists."); //TODO write config file to disk if it doesn't exsist.
			//inputStream = getClass().getClassLoader().getResourceAsStream(configName); //Loads built in supplied config with properties undefined.
			throw new FileNotFoundException("Config file '" + configName + "' not found in the directory" + configPath);
		}
		catch(SecurityException e) {
			System.out.println();
			throw new SecurityException("Access denied to reading the config file: '" + configName + "' In the following directoy: '" + configPath + "'");
		}

		//String user = prop.getProperty("user"); //Old example code for loading a value from a key in a config file.
	}

	public String getValueFromKey(String key) {
		return properties.get(key); //Will return the keys value if the key exists. null if not, per the javadocs.
	}

}
