package capg.demos;

class StackDemo{
	int[] arr;
	int top, size;
	
	StackDemo(int size){
		this.size = size;
		arr = new int[size]; // arr[5] = 0 - 4
		top = -1;
	}
	
	void push(int value) {
		if(top == size -1) {
			System.out.println("Stack is full. we cannot add more");
			return;
		}
		else {
			top++;
			arr[top] = value;
			System.out.println(" new element is pushed " + value); 
		}
	}
	
	int pop() {
		if(top == -1) {
			System.out.println("Stack is empty we cannot remove.");
			return -1;
		}
		else
		{
			int value = arr[top];
			top--;
			return value;
		}
	}
	
	int peek() {
		if(top == -1) {
			System.out.println("Stack is empty we cannot remove.");
			return -1;
		}
		else {
			int value = arr[top];
			return value;
		}
	}
	
	boolean isEmpty() {
		if(top == -1) {
			System.out.println("Stack is empty");
		  return true;
		}
		else {
			return false;
		}
	}
	
	void display() {
		if(top == -1) {
			System.out.println("Elements cannot be fetched as stakc is empty"); 
			return;
		}
		else {
			for(int i=top; i>=0; i--) {
				System.out.println(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		StackDemo stack = new StackDemo(5);
		stack.push(20);
		stack.push(45);
		stack.push(78);
		
		stack.display();
		
		stack.push(34);
		stack.push(56); 
		
		stack.push(90);
		stack.display();
		System.out.println(stack.peek());
		
		stack.pop();
		stack.pop();
		stack.display();

	}
}
