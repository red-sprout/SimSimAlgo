package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_10090_CI_MergeSort {
	static int n;
	static int[] arr, tmpArr;
	static long mergeSort(int start, int end) {
		long result = 0;
		if(start == end) return 0;
		int mid = (start + end) / 2;
		result += mergeSort(start, mid);
		result += mergeSort(mid + 1, end);
		result += merge(start, mid, end);
		return result;
	}
	static long merge(int start, int mid, int end) {
		int i = start, j = mid + 1, k = start;
		long result = 0;
		while(i <= mid && j <= end) {
			if(arr[i] <= arr[j]) {
				tmpArr[k++] = arr[i++];
			} else {
				result += mid - i + 1;
				tmpArr[k++] = arr[j++];
			}
		}
		if(i <= mid) {
			for(int l = i; l <= mid; l++) {
				tmpArr[k++] = arr[l];
			}
		} else {
			for(int l = j; l <= end; l++) {
				tmpArr[k++] = arr[l];
			}
		}
		for(int l = start; l <= end; l++) {
			arr[l] = tmpArr[l];
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmpArr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(mergeSort(0, n - 1));
		br.close();
	}
}
