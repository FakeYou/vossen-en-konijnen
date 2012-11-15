package program.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Json parser to read json and parser their data
 * 
 * @author FakeYou
 * @version 2012-11-10
 */
public class Json
{
	private JsonParser parser = new JsonParser();
	private JsonObject object;
	
	/**
	 * 
	 */
	public Json()
	{
		
	}
	
	/**
	 * @param object create a new Json object from JsonObject object
	 */
	public Json(JsonObject object)
	{
		this.object = object;
	}

	/**
	 * @param file .json file to load and parse
	 */
	public void loadFile(String file)
	{
		InputStream inputstream = this.getClass().getResourceAsStream(file);
		
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
		StringBuilder stringbuilder = new StringBuilder();
		String line;
		
		try 
		{
			while ((line = bufferedreader.readLine()) != null) 
			{
				stringbuilder.append(line);
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		object = parser.parse(stringbuilder.toString()).getAsJsonObject();
	}
	
	/**
	 * @param keys a group of keys each indicating the next level in the json object to get a value from
	 * @return a new Json object containing the data
	 */
	public Json getAsJson(String ... keys)
	{
		JsonObject json = object.getAsJsonObject();
		for(int i = 0; i < keys.length; i++)
		{
			if(!json.has(keys[i]))
			{
				return null;
			}
			
			json = json.get(keys[i]).getAsJsonObject();
		}
		
		return new Json(json);		
	}
	
	/**
	 * @return the raw JsonObject object
	 */
	public JsonObject getObject()
	{
		return object;
	}
	
	/**
	 * @param keys a group of keys each indicating the next level in the json object to get a value from
	 * @return the found value or null;
	 */
	public JsonElement get(String ... keys)
	{
		JsonObject json = object.getAsJsonObject();
		for(int i = 0; i < keys.length - 1; i++)
		{
			if(!json.has(keys[i]))
			{
				return null;
			}
			
			json = json.get(keys[i]).getAsJsonObject();
		}
		
		return json.get(keys[keys.length - 1]);
	}
	
	/**
	 * @param value a boolean to set
	 * @param keys a group of keys each indicating the next level in the json object to set a value to
	 */
	public void set(boolean value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
	/**
	 * @param value a char to set
	 * @param keys a group of keys each indicating the next level in the json object to set a value to
	 */
	public void set(char value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
	/**
	 * @param value a number to set
	 * @param keys a group of keys each indicating the next level in the json object to set a value to
	 */
	public void set(Number value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
	/**
	 * @param value a string to set
	 * @param keys a group of keys each indicating the next level in the json object to set a value to
	 */
	public void set(String value, String ... keys)
	{
		JsonObject json = object.getAsJsonObject();
		for(int i = 0; i < keys.length - 1; i++)
		{
			if(!json.has(keys[i]))
			{
				return;
			}
			
			json = json.get(keys[i]).getAsJsonObject();
		}
		
		json.add(keys[keys.length - 1], new JsonPrimitive(value));
	}
}
