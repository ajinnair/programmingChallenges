package demo;

import java.util.concurrent.Callable;

public class DoSearch implements Callable<Integer> {

	private int valueToFind;
	private CustomNumberEntity entity;

	public DoSearch(int valueToFind, CustomNumberEntity entity) {
		super();
		this.valueToFind = valueToFind;
		this.entity = entity;
	}

	@Override
	public Integer call() throws Exception {
		if (entity != null && entity.getNumber()!=null && entity.getNumber().matches("//d")) {
			FastestComparator comparator = new FastestComparator();
			return comparator.compare(valueToFind, entity);
		}
		return 1;
	}

}
