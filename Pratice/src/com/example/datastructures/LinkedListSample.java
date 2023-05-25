package com.example.datastructures;

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}
public class LinkedListSample {
	static void printLinkedList(ListNode head) {
		ListNode p = head;//指針p
		while(p != null) {
			System.out.println(p.val);
			p = p.next;//指針往後走
		}
	}
	
	//把鏈表當成二元數在遍歷，二元數有left/right，鏈表有next
	static void printLinkedListR(ListNode head) {
		if(head == null) //遍歷到鏈表的尾部，就要結束，跟上面的for徝環離開的條件是一樣的p == null就離開while
			return;
		System.out.println(head.val);
		printLinkedListR(head.next);//指向下一個節點
		
	}
	
	static void printArray(int[] nums) {
		for(int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}
	static void printArrayR(int[] nums) {
		
		printArrayR(nums, 0);
		
	}
	
	static void printArrayR(int[] nums, int i) {
		if(i == nums.length) return;
		System.out.println(nums[i]);
		printArrayR(nums, i + 1);//i+1就視為下一個元素
		
	}
	
	static ListNode addLast(ListNode head, int val) {
		if(head == null) return new ListNode(val);
		ListNode p = head;//指針p
		while(p.next != null) {
			p = p.next;//指針p不斷往後走，走到鏈表的最後一個節點
		}
		p.next = new ListNode(val);
		return head;
	}
	
	/**
	 * 原本:1->2->3->4->null
	 * 遞歸流程
	 * 
	 * 先呼叫(1)addLast(1,5)
	 * 再呼叫(2)addLast(2,5)
	 * 再呼叫(3)addLast(3,5)
	 * 再呼叫(4)addLast(4,5)
	 * (4的next為空已到鏈結的尾巴，觸發base case)
	 * 把新節點5返回給前面接收的人(4.next)
	 * 4.next 連結到 5，再返回4
	 * 1->2->3->4->5->null
	 * 4 會把自已return給3.next
	 * 3 會把自已return給2.next
	 * 2 會把自己return給1.next
	 * 1 會把自己返回
	 * @param head
	 * @param val
	 * @return
	 */
	
	
	/**
	val加到最後一個空指針
	原本:1->2->3->4->null
	添加5之後:1->2->3->4->[5->null] 5接在4後面再接上一個空指針
	先遞歸的走(1->2->3->4->null)到null這個位置，再考慮怎麼去插入5
	addLast(head.next, int val) 遞歸的head.next就是在遞歸的往後走
	當走到結尾null也就是head == null
	**/
	static ListNode addLastR(ListNode head, int val) {
		
		if(head == null) //當走到鏈節的結尾為空，就會進來這，base case
			//new 出來新的ListNode(5)丟回去，讓前面的節點4來接收1->2->3->4->5->null
			return new ListNode(val);
		
		/**前面一個元素(4)要跟new出來的新節點接起來
		前一個元素(4)的next= new出來的新節點
		4.next去接新的節點5
		遞歸的修改數據結構，每一個遞歸函數都有返回值，返回值得被數據結構自己去接收，遞歸調用head.next，就該由head.next去接收
		**/
		head.next = addLast(head.next, val);

		return head;//再把4返回給3.next， 3->4 3本來就連著4，再連一次 ，3再返回給2.next， 2再返回1.next
	}
	static ListNode removeLast(ListNode head) {
		if(head == null) return null;
		ListNode p = head;
		while(p.next.next != null) {//走到倒數倒數第二個節點
			p = p.next;
		}
		p.next = null;//倒數第二個節點的下一個節點是最後一個節點，把它置為null，就刪掉了
		return head;
	}
	
	/**
	 * 原本:1->2->3->4->null
	 * 遞歸流程
	 * 
	 * 先呼叫(1)removeLastR(1)
	 * 再呼叫(2)removeLastR(2)
	 * 再呼叫(3)removeLastR(3)
	 * 再呼叫(4)removeLastR(4)
	 * (4的next為空已到鏈結的尾巴，觸發base case)
	 * 把null返回給前面接收的人(3.next)
	 * 3.next = null，就把4刪掉了
	 * 1->2->3->null
	 * 3 會把自已return給2.next
	 * 2 會把自己return給1.next
	 * 1 會把自己返回
	 * @param head
	 * @param val
	 * @return
	 */
	static ListNode removeLastR(ListNode head) {
		if(head.next == null) return null; //走到最後一個元素，就把自已給刪掉，怎麼刪除自己，return 一個空
		//透過遞歸的方式在往後走removeLastR(head.next, int val) 遞歸的head.next就是在遞歸的往後走
		//透過接收返回值就可以刪除最後一個節點
		head.next = removeLastR(head.next);
		return head;
	}
	

	public static void main(String[] args) {
		ListNode head = null;
		head = addLastR(head,1);
		head = addLastR(head,2);
		head = addLastR(head,3);
		head = addLastR(head,4);
		printLinkedListR(head);
		
	}
	
}

