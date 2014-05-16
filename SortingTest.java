// 2014-1 �ڱ�
import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// �Է¹��� �迭�� �����ΰ� �ƴѰ�?
			int[] value;	// �Է� ���� ���ڵ��� �迭
			String nums = br.readLine();	// ù ���� �Է� ����
			if (nums.charAt(0) == 'r')
			{
				// ������ ���
				isRandom = true;	// �������� ǥ��

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// �� ����
				int rminimum = Integer.parseInt(nums_arg[2]);	// �ּҰ�
				int rmaximum = Integer.parseInt(nums_arg[3]);	// �ִ밪

				Random rand = new Random();	// ���� �ν��Ͻ��� �����Ѵ�.

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ������ �迭�� ������ �����Ͽ� ����
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// ������ �ƴ� ���
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ���پ� �Է¹޾� �迭���ҷ� ����
					value[i] = Integer.parseInt(br.readLine());
			}

			// ���� �Է��� �� �޾����Ƿ� ���� ����� �޾� �׿� �´� ������ �����Ѵ�.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// ���� ���� ��ȣ�� ���� ���纻�� �����Ѵ�.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// ���α׷��� �����Ѵ�.
					default:
						throw new IOException("�߸��� ���� ����� �Է��߽��ϴ�.");
				}
				if (isRandom)
				{
					// TODO : Have to delete this code.
//					for (int i = 0; i < newvalue.length; i++)
//					{
//						System.out.println(newvalue[i]);
//					}					
					// ������ ��� ����ð��� ����Ѵ�.
					System.out.println((System.currentTimeMillis() - t) + " ms");
					// ������ �ƴ� ��� ���ĵ� ������� ����Ѵ�.					
				}
				else
				{
					// ������ �ƴ� ��� ���ĵ� ������� ����Ѵ�.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// copy from Introduction to Algorithms page 39 BUBBLESORT(A) ������ ��
	private static int[] DoBubbleSort(int[] A)
	{
		// TODO : Bubble Sort �� �����϶�.
		// value�� ���ľȵ� ���ڵ��� �迭�̸� value.length �� �迭�� ũ�Ⱑ �ȴ�.
		// ����� ���ĵ� �迭�� ������ �־�� �ϸ�, �ΰ��� ����� �����Ƿ� �� �����ؼ� ����Ұ�.
		// �־��� value �迭���� ���� ������ �ٲٰ� value�� �ٽ� �����ϰų�
		// ���� ũ���� ���ο� �迭�� ����� �� �迭�� ������ ���� �ִ�.
		for ( int i=0; i<A.length; i++)
			for ( int j=A.length-1; j >= i+1; j--)
				if ( A[j] < A[j-1] ){
					int tmp = A[j];
					A[j] = A[j-1];
					A[j-1] = tmp;
				}
		return (A);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// copy from Introduction to Algorithms page 24 INSERTION-SORT(A) ������ ��
	private static int[] DoInsertionSort(int[] A)
	{
		int i, j;
		for ( j=1; j < A.length; j++) {
			int key = A[j];
			i = j - 1;
			while ( i >= 0 && A[i] > key ) {
				A[i+1] = A[i];
				i--;
			}
			A[i+1] = key;
		}
		return (A);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// copy from Introduction to Algorithms page 142 HEAPSORT(A), page 139 BUILD-MAX-HEAP(A), page 137 MAX-HEAPIFY(A,i)
	private static int[] DoHeapSort(int[] value)
	{
		// TODO : Heap Sort �� �����϶�.
		HEAPSORT ( value);
		return (value);
	}
	
	private static void MAX_HEAPIFY(int[] A, int length, int i)
	{
		int largest;
		int l = 2*(i+1) - 1;
		int r = 2*(i+1);
		if ( l <= length -1 && A[l] > A[i] )
			largest = l;
		else
			largest = i;
		if ( r <= length - 1 && A[r] > A[largest] )
			largest = r;
		if ( largest != i ) {
			swap (A, i, largest);
			MAX_HEAPIFY (A, length, largest );
		}
	}
	
	private static void BUILD_MAX_HEAP(int[] A) {
		//int heap_size = A.length;
		for ( int i= (A.length+1)/2 -1 ; i >= 0; i-- )
			MAX_HEAPIFY( A, A.length-1, i);
	}
	
	private static void HEAPSORT(int[] A) {
		BUILD_MAX_HEAP(A);
		int heap_size = A.length;
		for ( int i=A.length-1; i>=0; i--) {
			swap(A, 0, i);
			heap_size --;
			MAX_HEAPIFY (A, heap_size, 0);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// copy from Introduction to Algorithms page 30 MERGE(A,p,q,r), page 33 MERGE-SORT(A,p,r)
	private static int[] DoMergeSort(int[] value)
	{
		MERGE_SORT(value, 0, value.length - 1);
		return (value);
	}

	private static void MERGE_SORT(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			MERGE_SORT(A, p, q);
			MERGE_SORT(A, q + 1, r);
			MERGE(A, p, q, r);
		}
	}

	private static void MERGE(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++)
			L[i] = A[p + i];
		for (int j = 0; j < n2; j++)
			R[j] = A[q + j + 1];
		L[n1] = 2147483647; // max
		R[n2] = 2147483647; // max

		int i = 0;
		int j = 0;

		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// copy from Introduction to Algorithms page 153 QUICKSORT(A,p,r), page 153 PARTITION(A,p,r)	
	private static int[] DoQuickSort(int[] value)
	{
		// TODO : Quick Sort �� �����϶�.
		QUICKSORT(value, 0, value.length -1 );
		return (value);
	}
	
	private static void QUICKSORT(int[] A, int p, int r)
	{
		int q;
		if ( p < r ) {
			q = PARTITION(A, p, r);
			QUICKSORT(A, p, q-1);
			QUICKSORT(A, q+1, r);
		}
	}

	private static int PARTITION(int[] A, int p, int r)
	{
        int x = A[r];
        int i = p - 1;
        for(int j = p; j <= r - 1; j++)
        { 
                if(A[j] <= x)
                {
                        i++;
                        swap(A, i, j);
                }
        }
        swap(A, i+1, r);
        return i+1;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// idea from Introduction to Algorithms page 180 RADIX-SORT(A,d)
	private static int[] DoRadixSort(int[] value)
	{
		// TODO : Radix Sort �� �����϶�.
		value = RADIX_SORT(value);
		return (value);
	}
	
	
	private static int[] RADIX_SORT(int[] A)
	{
		for(int shiftAvailable = Integer.SIZE-1; shiftAvailable > -1; shiftAvailable--) {
			int[] tmpBucket = new int[A.length];
			int j = 0;
			for(int i = 0; i < A.length; i++) {
				boolean moveAvailable = A[i] << shiftAvailable >= 0;
				if ( shiftAvailable == 0) { 
					moveAvailable = !moveAvailable;		
				}
				else {
					moveAvailable = moveAvailable;					
				}
				if ( moveAvailable ) {
					tmpBucket[j] = A[i];
					j++;					
				} else
					A[i-j] = A[i];					
			}
			for(int i = j; i < tmpBucket.length; i++)
				tmpBucket[i] = A[i-j];
			A = tmpBucket;
		}
		return A;
	}
 
    
	private static void swap(int[] A, int p, int r) {
		int q = A[p];
		A[p] = A[r];
		A[r] = q;
	}
}