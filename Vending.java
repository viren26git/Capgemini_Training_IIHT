package capg.demos;

import java.util.HashMap;
import java.util.Map;

/**
 * Vending Machine
 *
 * Design the classes. This skeleton is only a starting suggestion  you may
 * restructure it. What matters: separated responsibilities, explicit state
 * transitions, correct money/stock logic, and safe failure handling.
 */
public class VendingMachine {

    enum Coin {
        ONE(1), 
        FIVE(5), 
        TEN(10), 
        QUARTER(25), 
        DOLLAR(100);
        
    	final int cents;
        
    	Coin(int c) { this.cents = c; }
    	
    	public int getCents() {
    		return cents;
    	}
    }

    // TODO: model a Product (code, name, priceCents,  quantity).
    //--------------------- Product  class -------------------//
    
    class Product{
    	 private String code;
    	 private String name;
    	 private int priceCents;
    	 private int quantity;
    	 
    	 public Product(String code, String name, int priceCents, int quantity) {
    		 
    		 if(priceCents <=0)
    		 {
    			 throw new IllegalArgumentException("Price cannot be negative");
    		 }
    		 
    		 if(quantity < 0)
    		 {
    			 throw new IllegalArgumentException("Quantity cannot be negative");
    		 }
    		 
    		 this.name = name;
    		 this.code = code;
    		 this.priceCents = priceCents;
    		 this.quantity = quantity;
    	 }

		 public String getCode() {
			 return code;
		 }

		 public String getName() {
			 return name;
		 }

		 public int getPriceCents() {
			 return priceCents;
		 }

		 public int getQuantity() {
			 return quantity;
		 }
    	 
    	 public boolean isInStock() {
    		 return quantity > 0;
    	 }
    	 
    	 public void descreaseQuantity() {     // dispense
    		 if(quantity <= 0)
    		 {
    			 throw new IllegalStateException("Product is out of stock");
    		 }
    		 
    		 quantity--;
    	 }
    	 
    	 public void increaseQuantity(int quantity) {  // refill
    		 if(quantity <= 0)
    		 {
    			 throw new IllegalArgumentException("Quantity cannot be negative or zero");
    		 }
    		 
    		 this.quantity = this.quantity + quantity;
    	 }
    }
    
    //-------------------- Inevtnroy --------------------//
    
    class Inventory{
    	
    	private Map<String, Product> products = new HashMap<>();
    	
    	public void addProduct(Product product) {
    		
    		if(product == null) {
    			throw new IllegalArgumentException("Product cannpt be null");
    		}
    		
    		if(products.containsKey(product.getCode())) {
    			throw new IllegalArgumentException("Product you are adding is already present");
    		}
    		
    		products.put(product.getCode(), product);
    		
    	}
    	
    	public Product getProduct(String code) {
    		return products.get(code);
    	}
    	
    }
    
    class CashPaymentStrategy{
    	
    	public int calculateChnage(int balance, int price) {
    		if(balance < price) {
    			throw new IllegalArgumentException("Balance is not sufficent ");
    		}
    		
    		return balance - price;
    	}
    }
    
    // TODO: model the result of a selection (success?, dispensed code, change, reason).
    class Result{
    	private boolean success;
    	private String dispensedCode;
    	private int change;
    	private String reason;
    	
    	Result(boolean success, String dispensedCode, int change, String reason)
    	{
    		this.success = success;
    		this.dispensedCode = dispensedCode;
    		this.change= change;
    		this.reason = reason;
    	}
    	
    	public static Result success(String code, int change) {
    		return new Result(true,code,change,null);
    	}
    	
    	public static Result failure(String reason) {
    		return new Result(false,null,0,reason);
    	}
    	
    	public boolean isSuccess() {
    		return success;
    	}

		public String getDispensedCode() {
			return dispensedCode;
		}

		public int getChange() {
			return change;
		}

		public String getReason() {
			return reason;
		}
    	
		@Override
    	public String toString() {
    		
    		if(success) {
    			return "SUCCESS| Product details with code : " + dispensedCode + " | and change is : " + change + "cents";
    		}
    		return "FAILURE with the reason code" + reason;
    	}
    	
    }

    class Balance {
    	
    	private int cents = 0;
    	
    	public void insert(Coin coin) {
    		if(coin == null) {
    			throw new IllegalArgumentException("Coin cannot be null");
    		}
    		
    		cents = cents + coin.cents;
    	}
    	
    	public int getCents() {
    		return cents;
    	}
    	
    	public int refund() {
    		int refundAmount = cents;
    		cents = 0;
    		return refundAmount;
    	}
    	
    	public void reset() {
    		cents = 0;
    	}
    }
    
    interface MachineState{
    	
    	void insertCoin(VendingMachine machine, Coin coin);
    	int cancel(VendingMachine machine);
    	Result selectProduct(VendingMachine machine, String code);
    }
    
    class IdleState implements MachineState{

		@Override
		public void insertCoin(VendingMachine machine, Coin coin) {
			
			
		}

		@Override
		public int cancel(VendingMachine machine) {
			
			return 0;
		}

		@Override
		public Result selectProduct(VendingMachine machine, String code) {
			
			return null;
		}
    	
    }
    
	class CollectingState implements MachineState{
	    	
	    }
	
	class DispensingState implements MachineState{
		
	}

    static class VendingMachine {
        // TODO: fields for inventory, current balance, and current state.
        private Inventory inventory;
        private Balance balance;
        private MachineState state;
        
        VendingMachine(Map<String, Object> initialInventory) {
          this.inventory = new Inventory();
          //write code here
        }

        void insertCoin(Coin coin) {
            state.insertCoint(this,coin);
        }

        int cancel() {
            return state.cancel(this);
        }

        Object selectProduct(String code) {
            // TODO: validate existence, stock, and balance; dispense + change or fail cleanly
            return state.selectProduct(this,code);
        }
    }
}

