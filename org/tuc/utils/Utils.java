package utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;



public class Utils {




//__________________________________________________________________________________________________
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
          FileInputStream fis = new FileInputStream(fileName);
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
//____________________________________________________________________________________________________________








//___________________________________________________________________________________________________________
	// Method to generate the file name based on N
    public static String fileName(int N) {
        return "C:\\Users\\giorgos\\Desktop\\data_structures_2\\org\\tuc\\randomNumbers\\numbers-"+N+".bin";
    }
 //__________________________________________________________________________________________________________
 
 


 //________________________________________________________________
    /*
     * Step 1
     * Method to read keys based on N and store them into an array
     */
    public static int[] getKeysFromFile(int N) {
        String fileName = fileName(N);
        int totals[]= readInts(fileName);
        return totals;
    }
//__________________________________________________________________





//_______________________________________
    /*
     * Step 2 Part A
     * Initialize structures
     */
    public static int getK (int N) {
    	if (N<201)
    	    return 10;
    	else if (N>1000)
    		return 100;
    	else
    		return 50;
    }
//______________________________________ 








//___________________________________________________________________________________
    public static int[] randomKs(int N) {
    	// Making a list with K random integers from 1 to 3*N
        Random randomGenerator = new Random();
        int K = getK(N);
        return randomGenerator.ints(1,3*N).limit(K).toArray();
    }
//____________________________________________________________________________________
	
}








