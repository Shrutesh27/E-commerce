package rahulshettyacadamy.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException
	{
		//read json to string as readfiletostring depreciated use StandarCharsets.UTF_8
		//StandarCharsets.UTF_8 how to convert into string
		//rather than passing hardcore path get from Test
	String jsoncontent=	FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
	
	//convert string to hash map
	//need jacksondata Bind add from maven repo
	ObjectMapper mapper=new ObjectMapper();
	//mapper.readvalue read string and convert to hashmap
	//step is like:-
	//create 2 hashmap convert into json then create list and give back
	List<HashMap<String, String>>data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {});
	return data;
		
	}
}
