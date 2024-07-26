package com.structures.sort;

public class SortTest {
	public static void main(String[] args) {
		int[] arr = {2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
		ISort[] sorts = new ISort[6];
		
		// 선택 정렬
		sorts[0] = new SelectionSort(arr.clone());
		sorts[0].run();
		System.out.println(sorts[0].count());
		sorts[0].print();
		
		// 버블 정렬
		sorts[1] = new BubbleSort(arr.clone());
		sorts[1].run();
		System.out.println(sorts[1].count());
		sorts[1].print();
		
		// 삽입 정렬
		sorts[2] = new InsertionSort(arr.clone());
		sorts[2].run();
		System.out.println(sorts[2].count());
		sorts[2].print();
	}
}
