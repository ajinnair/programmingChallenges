package com.test;

/**
 * @author Ajin
 * 
 * Java code to find unique countries from a rectangular map such that if adjacent squares have the same value they belong to same country
 * 
 * TODO: Put in detail question.
 *
 */
public class UniqueCountriesProblem {

	
	public int getUniqueCountries(int[][] matrix){
		// worst case no values match, the number of unique countries would be number of squares in the rectangle.
		int rowCount= matrix.length;
		int columnCount = matrix[0].length;
		boolean[][] visited = new boolean[rowCount][columnCount]; 
		int uniqueCountries = 0;
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			int value =	matrix[rowIndex][columnIndex];
			if(!visited[rowIndex][columnIndex]){
				dfs(matrix,value,rowIndex,columnIndex,visited,rowCount,columnCount);
				uniqueCountries++;
			}
				
			}
		}
		
		return uniqueCountries;
	}

	// Find orthogonal elements and check if they match the value passed, keep it going till no match found or array ends.
	private void dfs(int[][] matrix, int value, int rowIndex, int columnIndex, boolean[][] visited,int rowCount, int columnCount) {

		
		int[] neighbouringRowIndices = new int[]{-1,0,1,0};
		int[] neighbouringcolumnIndices = new int[]{0,1,0,-1};
		visited[rowIndex][columnIndex] = true;
		for(int i =0;i<4;i++){
			if(isafe(matrix,neighbouringcolumnIndices[i]+columnIndex,neighbouringRowIndices[i]+rowIndex,visited,value,rowCount,columnCount)){
				dfs(matrix, value, neighbouringRowIndices[i]+rowIndex, neighbouringcolumnIndices[i]+columnIndex, visited, rowCount, columnCount);
			}
		}
		
		

	}

	private boolean isafe(int[][] matrix, int neighbouringColumnIndex, int neighbouringRowIndex, boolean[][] visited, int value, int rowCount,int columnCount) {

		if(neighbouringRowIndex>=0&& neighbouringRowIndex < rowCount && neighbouringColumnIndex>=0 && neighbouringColumnIndex <columnCount){
			if(value == matrix[neighbouringRowIndex][neighbouringColumnIndex]&& !visited[neighbouringRowIndex][neighbouringColumnIndex]){
				return true;
			}
		}
		return false;
	}
	
}
