package demo;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajinnair
 * 
 * This class does the job to call the comparator class and check if the element exists in the list or not
 *
 */
public class DoSearch implements Callable<Integer> {

	private int valueToFind;
	private CustomNumberEntity entity;
	Logger logger = LoggerFactory.getLogger(getClass());

	public DoSearch(int valueToFind, CustomNumberEntity entity) {
		super();
		this.valueToFind = valueToFind;
		this.entity = entity;
	}


	@Override
	public Integer call() throws Exception {
		if (entity != null && entity.getNumber() != null && NumberUtils.isParsable(entity.getNumber())
				&& Utils.isValidInt(entity.getNumber())) {

			FastestComparator comparator = new FastestComparator();
			logger.info("Comparing the search number {} with {} ", valueToFind, entity.getNumber());
			return comparator.compare(valueToFind, entity);
		}
		return 1;
	}


}
