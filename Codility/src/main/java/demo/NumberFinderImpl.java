package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class NumberFinderImpl implements NumberFinder{

	@Override
	public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
		FastestComparator comparator = new FastestComparator();
		for (CustomNumberEntity customNumberEntity : list) {
			if(comparator.compare(valueToFind, customNumberEntity)== 0){
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public List<CustomNumberEntity> readFromFile(String filePath) {
		
	try {
		
		ObjectMapper mapper = new ObjectMapper();
		
		 CollectionType javaType = mapper.getTypeFactory()
			      .constructCollectionType(List.class, CustomNumberEntity.class);
		 List<CustomNumberEntity> list = mapper.readValue(new FileInputStream(filePath), javaType);
		 return list;
		 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return null;
	}

}
