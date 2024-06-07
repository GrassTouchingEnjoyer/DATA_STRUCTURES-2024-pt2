package avl;

public class AVLnode {
	int value;
    int height;
    AVLnode left;
    AVLnode right;

    public AVLnode(int value)
    {
        this.value = value;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public AVLnode getLeft() {
		return left;
	}

	public void setLeft(AVLnode left) {
		this.left = left;
	}

	public AVLnode getRight() {
		return right;
	}

	public void setRight(AVLnode right) {
		this.right = right;
	}
    

}
