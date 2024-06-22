package b;
import utils.Utils;

public class Bmain {
	 public static void main(String[] args) {
		    Btree b = new Btree(3);
		    
		    System.out.print("\n");
		    b.insert(7);
		    b.insert(9);
		    b.insert(10);
		    b.insert(11);
		    b.insert(15);
		    b.insert(20);
		    b.insert(17);

		    b.Show();

		    b.delete(10);
		    System.out.println();
		    b.Show();
		   
		  }
	 
	 

}
