package com.example.datastructures;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyArrayList<E> implements Iterable<E>{
	private E[] data;
	private int size;
	private static final int INIT_CAP = 1;
	public MyArrayList() {
		this(INIT_CAP);
	}
	public MyArrayList(int initCapacity) {
		System.out.println("capacity:" + initCapacity);
		data = (E[]) new Object[initCapacity];//初始多少大小
		size = 0;
	}
	public void addFirst(E e) {
		add(0,e);
	}
	public void addLast(E e) {
		int cap = data.length;
		//如果容量不夠
		if(size == cap) {
			resize(2 * cap);//變成2倍
		}
		//尾部加入新元數
		data[size] = e;
		size++;
	}
	
	public void add(int index, E element) {
		//注意，這邊是用Position == size
		checkPositionIndex(index);
		int cap = data.length;
		
		//如果容量不夠
		if(size == cap) {
			resize(2 * cap);//變成2倍
		}
		/**
		 * 將要新增的位置 後面所有原素往後移一位
		 * 0134567
		 *   ^
		 * 01234567
		 * 從index為2(包含2)後面的所有元素[3..7]全移到index為3以後的位置
		 * 將index的位置插入2
		 * 最後一個參數為7-2=5，把index後面5個元素[34567]往後移
		 *
		 */
		
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;//把新元素放入index
		size++;
	}
	
	public E removeLast() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		int cap = data.length;
		
		//當容量小於array的length的 1/4
		if(size == cap /4) {
			resize(cap / 2);//把容量縮成 1/2 才不浪費空間
		}
		
		E deletedVal = data[size - 1];//回傳刪除的元素
		data[size - 1] = null;
		size--;
		return deletedVal;
	}
	
	public E remove(int index) {
		checkElementIndex(index);//檢查index有沒有越界
		int cap = data.length;
		
		//當容量小於array的length的 1/4
		if(size == cap /4) {
			resize(cap / 2);//把容量縮成 1/2 才不浪費空間
		}
		
		E deletedVal = data[index];//回傳刪除的元素
		/**
		 * 將要刪的位置 後面所有原素往前移
		 * 01234567
		 *   ^
		 * 0124567
		 * 從index為2後面的所有元素[3..7]全移到index為2的位置
		 *最後一個參數為要複製幾個元素 size 目前為8 8-2-1(扣掉要刪除的)= 5 
		 *把[3,4,5,6,7]全copy 到index開始位置
		 */
		System.arraycopy(data, index + 1, data, index, size - index -1);
		data[size - 1] = null;//最後一個元素設為null
		size--;
		return deletedVal;
	}
	
	public E removeFirst() {
		return remove(0);
	}
	
	/**
	 * 取得元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		checkElementIndex(index);//檢查index有沒有越界
		return data[index];
	}
	/**
	 * 設定
	 * @param index
	 * @param element
	 * @return
	 */
	public E set(int index, E element) {
		checkElementIndex(index);//檢查index有沒有越界
		E oldVal = data[index];
		data[index] = element;
		return oldVal;
	}
	/**
	 * 檢查index 索引位置
	 * @param index
	 */
	private void checkElementIndex(int index) {
		if(!isElementIndex(index))
			throw new IndexOutOfBoundsException("Index:" + index+ ",Size:" + size);	
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * 要記得<= 等於size，這樣當index輸入0，才能加入
	 * 檢查index 索引位置
	 * @param index
	 */
	private void checkPositionIndex(int index) {
		if(!isPositionIndex(index))
			throw new IndexOutOfBoundsException("Index:" + index+ ",Size:" + size);
	}
	
	/**
	 * 檢查index有沒有超出範圍
	 * @param index
	 * @return
	 */
	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}
	
	/**
	 * 間縫
	 * 檢查index有沒有超出範圍
	 * @param index
	 * @return
	 */
	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}
	
	/**
	 * 
	 * @param newCap 大小
	 */
	private void resize(int newCap) {
		//建立temp的array，大小為newCap
		E[] temp = (E[]) new Object[newCap];
		//將data 所有資料copy到temp
//		for(int i = 0; i < size; i++) {
//			temp[i] = data[i];
//		}
		//將data[0..size] copy到temp[0..size] size為要copy的長度，目前是全部copy
		System.arraycopy(data, 0, temp, 0, size);
		data = temp;
	}
	public static void main(String[] args ) {
		MyArrayList<Integer> list = new MyArrayList<>(10);
		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		list.addFirst(7);
		list.addLast(3);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<E>() {
			private int p = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				//p = 5 / size = 5 bool:false
				//System.out.printf("p = %d / size = %d bool:%b\n", p, size, p != size);
				//若size為5，p走到5，也就是等於最大容量，會回傳 false
				return p != size;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return data[p++];
			}
			
		};
	}
}
