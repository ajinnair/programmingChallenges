package demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class NumberFinderImpl implements NumberFinder {

	Logger logger = LoggerFactory.getLogger(getClass());

	/* Approach : Create threadPool , do search for each element in a thread, thus minimizing  the amount of time lost in wait
	 * 
	 * */
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

			int doneCount = 1;
			boolean allDone = false;
			do {
				for (int i = 0; i < results.size(); i++) {
					if (results.get(i).isDone()) {
						doneCount++;
						if (results.size() == doneCount) {
							allDone = true;
						}
						if (results.get(i).get() == 0) {
							executor.shutdownNow();
							logger.info("Element found");
							return true;
						}

					}
				}
			} while (!allDone);
			logger.info("Search complete but element not found");
			executor.shutdown();
		} catch (InterruptedException interupptedException) {
			executor.shutdown();
			logger.error("A search was interuppted ", interupptedException);
		} catch (ExecutionException executionException) {
			executor.shutdown();
			logger.error("Problem in execution ", executionException);
		}
		return false;
	}

	@Override
	public List<CustomNumberEntity> readFromFile(String filePath) {

		try {
			if (StringUtils.isEmpty(filePath)) {
				logger.error("Empty File Name");
				return null;
			}

			ObjectMapper mapper = new ObjectMapper();

			CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, CustomNumberEntity.class);
			List<CustomNumberEntity> list = mapper.readValue(new FileInputStream(filePath), javaType);
			return list;

		} catch (FileNotFoundException fileNotFound) {

			logger.error("File not found ", fileNotFound);
		} catch (IOException ioException) {

			logger.error("Problem in execution ", ioException);
		}

		return null;
	}

}
