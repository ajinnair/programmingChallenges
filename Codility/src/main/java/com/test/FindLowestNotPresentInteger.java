package com.test;

//you can also use imports, for example:
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");

/*
that,given an array A of N integers,returns the smallest positive integer
(greater than 0)that does not occur in A.
For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [−1, −3], the function should return 1.


*/


class FindLowestNotPresentInteger {
	public static void main(String []args){
		FindLowestNotPresentInteger test = new FindLowestNotPresentInteger();
		int array[] = {1, 3, 6, 4, 1, 2};//{1,2,10,-1};

		System.out.println(test.solution(array));
	}
	
 public  int solution(int[] A) {
	 long starTime = System.currentTimeMillis();
     // write your code in Java SE 8
	/*List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
     TreeSet<Integer> setA = new TreeSet<Integer>(list);*/
     //Collections.addAll(setA,A); 
    // Arrays.sort(A);
 	List<Integer> list = Arrays.stream(A).sorted().filter(i-> i > 0).boxed().collect(Collectors.toList());
 	//System.out.println(list.parallelStream().reduce(0, Integer::sum));
 	if(list.isEmpty()){
 		return 1;
 	}
 	 else if(list.size()==1){
         return (list.get(0).intValue()+1);
     }
    Iterator<Integer> iterator = list.listIterator();
    Integer prevnumber = null;
    do{
    	if(prevnumber == null){
    	     prevnumber = iterator.next();
    	     continue;
    	}
   
        if(iterator.hasNext()){
        	Integer nextNumber = iterator.next();
        	if(nextNumber-prevnumber >1){
        		return ++prevnumber;
        	}
            prevnumber = nextNumber;

        }

    }
    while(iterator.hasNext());
    return 0;
    
    /* if(iterator.hasNext()){
    	 
         Integer number = iterator.next();
         
         return 0;
     }
     else{
         return 0;
     }*/
 }
}