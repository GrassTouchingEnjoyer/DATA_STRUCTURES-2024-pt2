package avl;
import interfaces.SearchInsert;
import utils.Utils;
import utils.MultiCounter;
// https://www.geeksforgeeks.org/introduction-to-avl-tree/
// https://www.cs.uoi.gr/~loukas/courses/Data_Structures/index.files/9b-AVL-Trees.pdf

public class AVLtree implements SearchInsert {
	protected AVLnode Root;
	protected int counterId;
	

	public AVLtree() {
		counterId = Utils.getCounterIdOfStructure(this);
		
	}
	
	// returns the height of the node
    private int Height(AVLnode key)
    {
        if (key == null)
           return 0;

        else
            return key.height;
    }
    // Balance computes the balance factor of the node
    private int Balance(AVLnode key)
    {
        if (key == null)
           return 0;

        else
            return ( Height(key.right) - Height(key.left) );
    }
    // updateHeight updates the height of the node
    private void updateHeight(AVLnode key)
    {
        int l = Height(key.left);
        int r = Height(key.right);

        key.height = Math.max(l , r) + 1;
    }

    private AVLnode rotateLeft(AVLnode x)
    {
        AVLnode y = x.right;
        AVLnode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private AVLnode rotateRight(AVLnode y)
    {
        AVLnode x = y.left;
        AVLnode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // balanceTree balances the tree using rotations after an insertion or deletion
    private AVLnode balanceTree(AVLnode root)
    {
        updateHeight(root);

        int balance = Balance(root);

        if (balance > 1) //R
        {
            if (Balance(root.right) < 0)//RL
            {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }

            else //RR
                return rotateLeft(root);
        }

        if (balance < -1)//L
        {
            if (Balance(root.left) > 0)//LR
            {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
            else//LL
                return rotateRight(root);
        }

        return root;
    }

    public int search(int key)
    {
        if(findNode(Root, key) == null)
            return 0;
        else
            return 1;
    }
 // findNode is used to search for a particular value given the root
    private AVLnode findNode(AVLnode root, int key)
    {
    	
        if (root == null || key==root.value)
            return root;

        if (key < root.value)
            return findNode(root.left, key);

        else
            return findNode(root.right, key);
    }
    /*
     * 
     */
    //A boolean function used to search for a value given the root
      public boolean valueSearch(AVLnode root, int key) {
    	  System.out.println("Searching for "+key);
    	  MultiCounter.increaseCounter(counterId);
    	if (root==null) 
    		return false;
    	
    	if(root.value==key) 
    		return true;
    	
    	if(root.value < key) 
    		return valueSearch(root.right,key);
    	else
    	return valueSearch(root.left,key);
    	
    	}
    
    @Override
	public boolean searchKey(int key) {
    	 
    	
		boolean found=valueSearch(Root, key);
//		if(found==true) {
//			System.out.print("Key found");
//		}
    	return found;
	}
   /*
    * 
    */
    
    private AVLnode insertNode(AVLnode root, int key)
    {
        // Performs normal BST insertion
        if (root == null)
            return new AVLnode(key);

        else if (key < root.value)
            root.left = insertNode(root.left, key);

        else
            root.right = insertNode(root.right, key);

        // Balances the tree after BST Insertion
        return balanceTree(root);
    }
    
    // Utility function for insertion of node
    @Override
    public void insert(int key)
    {
        if (findNode(Root , key) == null)
        {
            Root = insertNode(Root , key);
            System.out.println("Insertion successful");
        }

        else {
           System.out.println("\nKey with the entered value already exists in the tree");
        }
    }

    // Successor returns the next largest node
    private AVLnode Successor(AVLnode root)
    {
        if (root.left != null)
            return Successor(root.left);

        else
            return root;
    }


    private AVLnode deleteNode(AVLnode root, int key)
    {
        // Performs standard BST Deletion
        if (root == null)
            return root;

        else if (key < root.value)
            root.left = deleteNode(root.left, key);

        else if (key > root.value)
            root.right = deleteNode(root.right, key);

        else
        {
            if (root.right == null)
                root = root.left;

            else if (root.left == null)
                root = root.right;

            else
            {
                AVLnode temp = Successor(root.right);
                root.value = temp.value;
                root.right = deleteNode(root.right, root.value);
            }
        }

        if (root == null)
            return root;

        else
            // Balances the tree after deletion
            return balanceTree(root);
    }
    // Utility function for deletion of node
    public void delete(int key)
    {
        if (findNode(Root , key) != null)
        {
            Root = deleteNode(Root , key);
            System.out.println("\nDeletion successful ");
        }

        else
            System.out.println("\nNo node with entered value found in tree");
    }

    public void InOrder(AVLnode root)
    {
        if(root == null)
        {
            System.out.println("\nNo nodes in the tree");
            return;
        }

        if(root.left != null)
            InOrder(root.left);
        System.out.print(root.value + " ");
        if(root.right != null)
            InOrder(root.right);

    }

    public void PreOrder(AVLnode root)
    {
        if(root == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }

        System.out.print(root.value + " ");
        if(root.left != null)
            PreOrder(root.left);
        if(root.right != null)
            PreOrder(root.right);

    }

    public void PostOrder(AVLnode key)
    {
        if(key == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }


        if(key.left != null)
            PostOrder(key.left);
        if(key.right != null)
            PostOrder(key.right);
        System.out.print(key.value + " ");

    }

    public void removeAll() {
    	Root = null;
    }
	
}
