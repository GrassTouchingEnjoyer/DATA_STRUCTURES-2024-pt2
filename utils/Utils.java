package utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Random;
import avl.AVLtree;
import b.Btree;
import bs.BSTree;
import linearhashing.LinearHashing;
import linearhashing.HashBucket;
import interfaces.SearchInsert;



public class Utils {
  
  public static int[] keysAmount = {20,50,100,200,1000,2500,5000,10000,20000,
		40000,60000,80000,100000,200000,1000000,1500000,
		2000000,2500000,3000000};
  public static String[] structureName = {"AVLtree","Btree100","Btree600","BStree","LinearHashing40/500","LinearHashing1000/500"};//////
  public static int structuresAmount = 6;
  public enum function {
      insert,
      searchKey
  }
  public static int functionType(String f) {

      if (f.equalsIgnoreCase("insert")) {
          return 0;
      }
      return 1;
  }

  
  
  /**
	 * Reads the given file as 4 byte, little endian integers, and returns an array
	 * of the numbers
	 * @param fileName
	 * @return The read numbers
	 */
	private static int[] readInts(String fileName) {
		int[] numbers = null;
		try {
          // Create FileInputStream and DataInputStream
		  String currentDir = System.getProperty("user.dir");
		  System.out.println("Current Dir is: "+currentDir);
          FileInputStream fis = new FileInputStream(currentDir+"/src/utils/"+fileName);
          DataInputStream dis = new DataInputStream(fis);
          
          // Create byte array with enough space for the whole file
          // and read the whole file. We assume that the file has the correct
          // length (multiple of Integer.BYTES=4)
          byte[] bytes = new byte[(int) new File(fileName).length()];
          dis.readFully(bytes);

          // Create ByteBuffer with little-endian byte order
          ByteBuffer buffer = ByteBuffer.wrap(bytes);
          buffer.order(ByteOrder.LITTLE_ENDIAN);
          
          // Read integers from the ByteBuffer
          numbers = new int[bytes.length / Integer.BYTES];
          for (int i = 0; i < numbers.length; i++) {
          	numbers[i] = buffer.getInt();
          }

          // Close streams
          dis.close();
          fis.close();
          
      } catch (IOException e) {
          e.printStackTrace();
      }
		return numbers;
	}
	
	// Method to generate the file name based on N
    public static String fileName(int N) {
        return "numbers-"+N+".bin";
    }
    
    /*
     * Step 1
     * Method to read keys based on N and store them into an array
     */
    public static int[] getKeysFromFile(int N) {
        String fileName = fileName(N);
        int totals[]= readInts(fileName);
        return totals;
    }
    
    public static int getK (int N) {
    	if (N<201)
    	    return 10;
    	else if (N>1000)
    		return 100;
    	else
    		return 50;
    }
    
    public static int[] makeKlist(int N) {
    	// Making a list with K random integers from 1 to 3*N
        Random randomGenerator = new Random();
        int K = getK(N);
        return randomGenerator.ints(1,3*N).limit(K).toArray();
    }
    
    public static SearchInsert[] setupStructures(int N) {
    	SearchInsert[] structure = new SearchInsert[structuresAmount];
    	structure[0]=new AVLtree();
    	structure[1]=new Btree(100 + getK(N));
    	structure[2]=new Btree(600 + getK(N));
    	structure[3]=new BSTree();
    	structure[4]=new LinearHashing(40,500);
    	structure[5]=new LinearHashing(1000,500);
    	//
    	//
    	int NfromFile[]=getKeysFromFile(N);
    	
    	for(int i=0; i<NfromFile.length; i++) {
    		for(int j=0; j<3; j++) {
    			structure[j].insert(i);
    			//
    		}
    	}
    	return structure;
    	
    	}
    
    public static int getCounterIdOfStructure(SearchInsert s) {
        if (s instanceof AVLtree) {
            return 1;
        } else if (s instanceof Btree) {
            return 2;
        } else if(s instanceof BSTree ) {
        	return 3;
        }
        else return 4;
        
    }
    
    public static void showTimeTable(double[][][] t) {

        System.out.println("--------------------------------------------------------------------");
        System.out.println("__________AVERAGE EXCECUTION TIME IN ns__________ ");
        // Iterate over each function type
        for (function f : function.values()) {
            // Print method name for the current function
            System.out.println("----->" + f.toString() + " method <-----");
            System.out.print("\n");
            // Printing elements amount (N)for every operation
            for (int amount : keysAmount) {
                System.out.print("\t\tN=> " + amount);
            }
            System.out.print("\n");
            //Printing Types of list
            for (int listCurrent = 0; listCurrent < structuresAmount; listCurrent++) {
                System.out.print("Structure type: " + structureName[listCurrent] + " ");
                //For every type of list printing all the times
                for (int amountIndex = 0; amountIndex < keysAmount.length; amountIndex++) {
                    System.out.printf("[%1$9.2f ns] ", t[amountIndex][listCurrent][functionType(f.toString())]);
                }
                System.out.println();
            }

        }
     }
    
    
    public static void showOperationTable(double[][][] o) {
    	System.out.println("--------------------------------------------------------------------");
        System.out.println("__________AVERAGE AMOUNT OF OPERATIONS__________ ");
        // Iterate over each function type
        for (function f : function.values()) {
            // Print method name for the current function
            System.out.println("----->" + f.toString() + " method <-----");
            System.out.print("\n");
            // Printing elements amount (N)for every operation
            for (int n : Utils.keysAmount) {
                System.out.print("\t\tN= " + n);
            }
            System.out.println();
            //Printing Types of list
            for (int listCurrent = 0; listCurrent < structuresAmount; listCurrent++) {
                System.out.print("List type: " + structureName[listCurrent]);
                //For every type of list printing all the times
                for (int current_n = 0; current_n < keysAmount.length; current_n++) {
                    String formattedNumber = String.format("%1$9.2f", o[current_n][listCurrent][functionType(f.toString())]);
                    System.out.print("[" + formattedNumber + " operations]");
                }
                System.out.println();
            }
        }

    }

	
		
		
		
		
		
		
	}








