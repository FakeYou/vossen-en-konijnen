package program.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Json
{
	private JsonParser parser = new JsonParser();
	private JsonObject object;
	
	public Json()
	{
		
	}
	
	public Json(JsonObject object)
	{
		this.object = object;
	}

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
	
	public void set(boolean value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
	public void set(char value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
	public void set(Number value, String ... keys)
	{
		String val = String.valueOf(value);
		set(val, keys);
	}
	
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
