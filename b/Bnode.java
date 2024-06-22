package b;

public class Bnode {
	 private int T;

	    int n;
	    int key[];
	    Bnode child[];
	    boolean leaf = true;

	    public Bnode(int T) {
	    	this.T = T;
	    	key = new int[2 * T - 1];
	    	child = new Bnode[2 * T];
	    }
	    public int search(int k) {
	      for (int i = 0; i < this.n; i++) {
	        if (this.key[i] == k) {
	          return i;
	        }
	      }
	      return -1;
	    };
	

}
