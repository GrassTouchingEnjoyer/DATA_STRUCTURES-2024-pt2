package main;
  
import avl.AVLtree;
import bst.BSTree;
import btree.Btree;
import linearhashing.LinearHashing;
import utils.audio;

public class main {

    public static long  TIMERRUN;
    public static long  TIMERSTOP;
    public static float RUNTIMES[][] = new float[19][6];
    public static float SEARCHTIMES[][] = new float[19][6];
    public static long  COUNTERS[][] = new long[19][6];
    public static int   N[] = {20, 50, 100, 200, 1000, 2500, 5000, 10000, 20000, 40000, 60000, 80000, 100000, 200000, 1000000, 1500000, 2000000, 2500000, 3000000};
    public static String NAMES[]={"AVL","BST","BTREE1","BTREE2","LHASH1","LHASH2"};

     public static void main(String[] args) {
        
        AVLtree       avl    = new  AVLtree();
        BSTree        bst    = new  BSTree();
        Btree         btree1 = new  Btree(100);
        Btree         btree2 = new  Btree(600);
        LinearHashing lhash1 = new  LinearHashing(40, 50);
        LinearHashing lhash2 = new  LinearHashing(1000, 500);




//-------------------------|START|-----------------------------  

        utils.slash.slasher();
        System.out.println("\t   STARTING POINT");
        System.out.println("\tTHIS WILL TAKE A WHILE");
        utils.slash.slasher();
        //audio.playSound();

//-------------------------------------------------------------


//___________________________________________________________________________________________

    	for(int index = 0 ; index <= 18 ; index++) //-18
        {       

        System.out.println(N[index]);
            
        int keysAdd[] = utils.Utils.getKeysFromFile(N[index]);

        int length = keysAdd.length;    



        //=================================================================================================
           // avl = new  AVLtree();

            System.out.println("avl");
            TIMERRUN = System.nanoTime(); 

            for (int jndex=0; jndex < length; jndex++) {avl.insert(keysAdd[jndex]);}


            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][0] = ((float)(TIMERSTOP - TIMERRUN) / length); 

            //--------------------------------------------------------------------------------------------|
                TIMERRUN = System.nanoTime();     
            
                    avl.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][0] = ((float)(TIMERSTOP - TIMERRUN)); 

                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(1));
                COUNTERS[index][0] = utils.MultiCounter.getCount(1);
                utils.MultiCounter.resetCounter(1);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|

        //=================================================================================================


        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);
        TIMERRUN  = 0;      
        TIMERSTOP = 0;
    

        //=================================================================================================
           // bst = new BSTree();

            System.out.println("bst");
            TIMERRUN = System.nanoTime();

            for (int jndex=0; jndex<length; jndex++) {bst.insert(keysAdd[jndex]);}

            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][1] = ((float)(TIMERSTOP - TIMERRUN) / length); 

            //--------------------------------------------------------------------------------------------|
                
                TIMERRUN = System.nanoTime();
            
                    bst.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][1] = ((float)(TIMERSTOP - TIMERRUN)); 


                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(2));
                COUNTERS[index][1] = utils.MultiCounter.getCount(2);
                utils.MultiCounter.resetCounter(2);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|

        //==================================================================================================


        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);
        TIMERRUN  = 0;      
        TIMERSTOP = 0;


        //==================================================================================================
            
           // btree1 = new  Btree(100);

            System.out.println("btree1");
            TIMERRUN = System.nanoTime();

            for (int jndex=0; jndex<length; jndex++) {btree1.insert(keysAdd[jndex]);}

            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][2] = ((float)(TIMERSTOP - TIMERRUN) / length); 

            //--------------------------------------------------------------------------------------------|
                TIMERRUN = System.nanoTime();      
            
                    btree1.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][2] = ((float)(TIMERSTOP - TIMERRUN)); 


                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(3));
                COUNTERS[index][2] = utils.MultiCounter.getCount(3);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|

        //=================================================================================================

        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);
        TIMERRUN  = 0;      
        TIMERSTOP = 0;


        //================================================================================================= 

            //btree2 = new  Btree(600);

            System.out.println("btree2");   
            TIMERRUN = System.nanoTime();

            for (int jndex=0; jndex<length; jndex++) {btree2.insert(keysAdd[jndex]);}

            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][3] = ((float)(TIMERSTOP - TIMERRUN) / length);
     
            //--------------------------------------------------------------------------------------------|
                TIMERRUN = System.nanoTime(); 
            
                    btree2.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][3] = ((float)(TIMERSTOP - TIMERRUN));          

                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(3));
                COUNTERS[index][3] = utils.MultiCounter.getCount(3);
                utils.MultiCounter.resetCounter(3);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|           
            
        //=================================================================================================


        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);
        TIMERRUN  = 0;      
        TIMERSTOP = 0;


        //=================================================================================================

            //lhash1 = new  LinearHashing(40, 50);

            System.out.println("lhash1");
            TIMERRUN = System.nanoTime();

            for (int jndex=0; jndex<length; jndex++) {lhash1.insert(keysAdd[jndex]);}

            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][4] = ((float)(TIMERSTOP - TIMERRUN) / length); 

                TIMERRUN  = 0;      
                TIMERSTOP = 0;
            //--------------------------------------------------------------------------------------------|
                TIMERRUN = System.nanoTime();
            
                    lhash1.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][4] = ((float)(TIMERSTOP - TIMERRUN)); 

                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(4));
                COUNTERS[index][4] = utils.MultiCounter.getCount(4);
                utils.MultiCounter.resetCounter(4);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|   

        //=================================================================================================


        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);
        TIMERRUN  = 0;      
        TIMERSTOP = 0;


        //=================================================================================================

           // lhash2 = new  LinearHashing(1000, 500);

            System.out.println("lhash2");
            TIMERRUN = System.nanoTime();

            for (int jndex=0; jndex<length; jndex++) {lhash2.insert(keysAdd[jndex]);}

            TIMERSTOP = System.nanoTime();
            RUNTIMES[index][5] = ((float)(TIMERSTOP - TIMERRUN) / length); 
            

                TIMERRUN  = 0;      
                TIMERSTOP = 0;
            //--------------------------------------------------------------------------------------------|
                TIMERRUN = System.nanoTime();
            
                    lhash2.searchKey(43);

                TIMERSTOP = System.nanoTime();
                SEARCHTIMES[index][5] = ((float)(TIMERSTOP - TIMERRUN)); 

                System.out.println("\nTHE NUMBER OF OPS IS:"+utils.MultiCounter.getCount(4));
                COUNTERS[index][5] = utils.MultiCounter.getCount(4);
                utils.MultiCounter.resetCounter(4);
                //audio.playSound();
            //--------------------------------------------------------------------------------------------|  

        //=================================================================================================
        
        
        utils.MultiCounter.resetCounter(1);
        utils.MultiCounter.resetCounter(2);
        utils.MultiCounter.resetCounter(3);
        utils.MultiCounter.resetCounter(4);

        TIMERRUN  = 0;      
        TIMERSTOP = 0;

}
//___________________________________________________________________________________________




//--------------------------|END|------------------------------

        utils.slash.slasher();
        System.out.println("\t      THE END");
        System.out.println("\tLET'S SEE THE RESULTS\n");
        utils.slash.slasher();
        audio.playSound();

//-------------------------------------------------------------



//------------------------------------------------|RESULTS|-------------------------------------------------------------

        System.out.println("\t\t\t\t\t\t\t\t|RUNTIME|\n");
        System.out.println("\n|AVL____________________BST____________________BTREE1____________________BTREE2__________________LHASH1___________________LHASH2|");

        for (int index = 0 ; index <= 18 ; index ++){ //-18

            System.out.print("\n"+N[index]+"  ");

            for (int jndex = 0 ; jndex <= 5 ; jndex ++){
                
                System.out.print(NAMES[jndex]+"=>["+RUNTIMES[index][jndex]+"]ns  ");

            }
        }

        System.out.println("\n\n\n\t\t\t\t\t\t|SEARCHTIME|\n");
        System.out.println("\n|AVL____________________BST____________________BTREE1____________________BTREE2__________________LHASH1___________________LHASH2|");

        for (int index = 0 ; index <= 18 ; index ++){ //-18

            System.out.print("\n"+N[index]+"  ");

            for (int jndex = 0 ; jndex <= 5 ; jndex ++){
                
                System.out.print(NAMES[jndex]+"=>["+SEARCHTIMES[index][jndex]+"]ns  ");

            }
        }


        System.out.println("\n\n\n\t\t\t\t\t\t|COUNTERS|\n");
        System.out.println("\n|AVL____________________BST____________________BTREE1____________________BTREE2__________________LHASH1___________________LHASH2|");

        for (int index = 0 ; index <= 18 ; index ++){ //-18

            System.out.print("\n"+N[index]+"  ");

            for (int jndex = 0 ; jndex <= 5 ; jndex ++){
                
                System.out.print(NAMES[jndex]+"=>["+COUNTERS[index][jndex]+"]  ");

            }
        }

//-------------------------------------------------------------------------------------------------------------------------

    }
}
