package com.example.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E>{
	
	//雙鏈表節點
	private static class Node<E>{
		E val;
		Node<E> next;
		Node<E> prev;
		Node(E val) {
			this.val = val;
		}
	}
	private Node<E> tail,head;
	private int size;
	public MyLinkedList() {
		//建立二個稍兵節點，傳null進去
		this.head = new Node<>(null);
		this.tail = new Node<>(null);
		head.next = tail;
		tail.prev = head;
		this.size = 0;
	}
	//------------增------------------
	public void addFirst(E e) {
		//建立新的節點
		Node<E> x = new Node<>(e);
		//將head的下一個放入temp
		Node<E> temp = head.next;
		//temp是head的下個節點
		//以下過程參考筆記的圖1
		x.next = temp;
		x.prev = head;
		
		//參考圖2
		head.next = x;
		temp.prev = x;
		
		
		
		size++;
	}
	
	public void addLast(E e) {
		//建立新的節點
		Node<E> x = new Node<>(e);
		//將tail的上一個放入temp
		Node<E> temp = tail.prev;
		//以下過程參考筆記的圖4
		
		x.next = tail;
		x.prev = temp;
		
		//圖5
		tail.prev = x;
		//圖6
		temp.next = x;
		size++;
	}
	
	public void add(int index,E e) {
		checkPositionIndex(index);
		
		//建立新的節點
		Node<E> x  = new Node<>(e);
		
		//參考圖14
		Node<E> p = getNode(index);
		Node<E> temp = p.prev;
		//原本temp <--> p
		//變成 temp <--> x <--> p
		x.next = p;
		x.prev = temp;
		
		temp.next = x;
		p.prev = x;
		size++;
	}
	//------------刪------------------
	public E removeFirst() {
		//若為空拋出異常
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		//head <-> x <-> temp
		//要把x給移除
		Node<E> x = head.next;
		Node<E> temp = x.next;
		
		//圖8
		//head <-> x <-> temp
		//變成head <-> temp
		head.next = temp;
		temp.prev = head;
		
		//把x的節點刪掉
		// null<- x- >null
		x.next = x.prev = null;
		size--;
		return x.val;
	}
	
	public E removeLast() {
		//若為空拋出異常
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		//temp -> x -> tail
		//要把x給移除
		Node<E> x = tail.prev;
		Node<E> temp = x.prev;
				
		//圖12
		//temp <-> x <-> tail
		//變成temp <-> tail
		temp.next = tail;
		tail.prev = temp;
		
		x.prev = x.next = null;
		size--;
		return x.val;
	}
	
	
	public E remove(int index) {
		//有checkElemntIndex，不用檢查是否為空，因為checkElementIndex會檢查index是否越界
		checkElementIndex(index);
		//參考圖15
		Node<E> p = getNode(index);
		Node<E> prev = p.prev;
		Node<E> next = p.next;
		//prev <--> p <--> next
		//把p刪掉
		//prev <-->  next
		prev.next = next;
		next.prev = prev;
		
		p.next = p.prev = null; //刪掉p
		size--;
		return p.val;
	}

	//------------查-------------------
	public E getFirst() {
		//若為空拋出異常
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		//head-> first
		return head.next.val;
	}
	public E getLast() {
		//若為空拋出異常
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		//last -> tail
		return tail.prev.val;
	}
	public E get(int index) {
		//有checkElemntIndex，不用檢查是否為空，因為checkElementIndex會檢查index是否越界
		checkElementIndex(index);
		Node<E> p = getNode(index);
		return p.val;
	}
	//------------改------------------
	public E set(int index, E element) {
		//有checkElemntIndex，不用檢查是否為空，因為checkElementIndex會檢查index是否越界
		checkElementIndex(index);
		Node<E> p = getNode(index);
		E oldVal = p.val;
		p.val = element;
		return oldVal;
	}

	public boolean isEmpty() {
		return size == 0;
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
	
	//返回index對映的node
	private Node<E> getNode(int index) {
		Node<E> p = head.next;//p相當於第一個元素(排除head，head跟tail是稍兵節點，不算數)
		for(int i = 0; i < index; i++) {
			p = p.next;
		}
		return p;
		
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<>() {
			Node<E> p = head.next;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return p != tail;
			}

			@Override
			public E next() {
				E val = p.val;
				p = p.next;
				return val;
			}
			
		};
	}
	
	public static void main(String[] args ) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
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
	
}
