package utils;
import java.io.IOException;
import avl.AVLtree;
import b.Btree;
import bs.BSTree;
import linearhashing.LinearHashing;
import interfaces.SearchInsert;
 public class MainClass { 
	
	  public static void main(String args[]) throws IOException, ClassNotFoundException {
	  double[][][] timeTable = new double[Utils.keysAmount.length][Utils.structureName.length][Utils.function.values().length];
	  double[][][] operationTable = new double[Utils.keysAmount.length][Utils.structureName.length][Utils.function.values().length];
			
		  
		  for(int n=0; n<Utils.keysAmount.length; n++) {
    		 //Holding the current N
    		 int N = Utils.keysAmount[n];
    		
    		 System.out.println("Inserting. Calculating time...");
    		//Getting the list Of all lists amount=6
 			SearchInsert[] structuresInsert = Utils.setupStructures(N);
 			//Making the list of Ks to Insert
   		    int kListInsert[] = Utils.makeKlist(N);
   		    for(int in=0; in<6; in++) {
   		    	
   		    // Get the current list for insert operations
   		    	SearchInsert current = structuresInsert[in];
   		    	long insertTime=0;
		    	long begin = System.nanoTime();
   		    	//long begin = System.nanoTime();
				for (int K : kListInsert) {
					//System.out.print(+K+"\n");
					current.insert(K);
					//System.out.println("Insert for" +ins+ "is" +totalInsertNanoTime);
					insertTime += System.nanoTime() - begin;
				}
				// Calculate the average time for Insert method
				double averageInsertTime = (double) insertTime /
						(double) kListInsert.length;
				//Utils.function("insert") returns 0 or 1
				//For insert Utils.function("insert") is 0
				timeTable[n][in][0] =averageInsertTime;
   		    }
   		    
   		    
   		    System.out.println("Searching. Calculating time...");
   		    SearchInsert[] structuresSearch = Utils.setupStructures(N);
   		    int kListSearch[] = Utils.makeKlist(N);
   		    for(int se=0; se<6; se++) {
   		    	SearchInsert current = structuresSearch[se];
   		    	long searchTime=0;
   		    	long begin = System.nanoTime();
   		    	for(int K : kListSearch) {
//   		    		
   		    		current.searchKey(K);
   		    		searchTime += System.nanoTime() - begin;
  		    	}
				double averageSearchTime = (double) searchTime /
						(double) kListSearch.length;
				System.out.println("Average search Time: "+averageSearchTime);
				timeTable[n][se][1] = averageSearchTime;
   		    }
   		 
//   		 System.out.println("Searching. Calculating operations...");
		    SearchInsert[] structuresSearchOperation = Utils.setupStructures(N);
		    int kListSearchOperation[] = Utils.makeKlist(N);
		    for(int s=0; s<6; s++) {
		    	SearchInsert current = structuresSearchOperation[s];
		    	MultiCounter.resetCounter(Utils.getCounterIdOfStructure(current));
		    	long searchTimeOperation=0;
		    	long begin = System.nanoTime();
		    	for(int K : kListSearchOperation) {
//		    		long begin = System.nanoTime();
		    		current.searchKey(K);
		    		searchTimeOperation += System.nanoTime() - begin;
		    	}
		    	double searchCount = (double) MultiCounter.getCount(Utils.getCounterIdOfStructure(current)) /
						(double) kListSearchOperation.length;
		    	operationTable[n][s][1] = searchCount;
		    }
    		 
    		 
    		 
    	 }
		//Printing tables using Utils.shoeTimeTable
			Utils.showTimeTable(timeTable);
			Utils.showOperationTable(operationTable);
          
	    

	    }
	
     

	

}
 
