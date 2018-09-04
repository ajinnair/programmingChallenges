package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class NumberFinderImpl implements NumberFinder{

	@Override
	public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
		
	
		ExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		
		
		List<Callable<Integer>> searches = new ArrayList<>();
		
		for (CustomNumberEntity customNumberEntity : list) {
			DoSearch search = new DoSearch(valueToFind, customNumberEntity);
			searches.add(search);
			
		}
		try {
			List<Future<Integer>> results = executor.invokeAll(searches);
			int doneCount =1;
			do{
				for(int i=0;i<results.size();i++){
					if(results.get(i).isDone()){
						doneCount++;
						if(results.get(i).get()==0){
							return true;
						}
						
					}
				}
			}
			while(results.size()!=doneCount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		e.printStackTrace();
	}
	
		return null;
	}

}
