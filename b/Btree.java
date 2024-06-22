package b;

import java.util.Stack;
import utils.MultiCounter;

import interfaces.SearchInsert;
import utils.Utils;

public class Btree implements SearchInsert {

	private int T;
	private Bnode Root;
	protected int counterId;
	public int steps;

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public Btree(int t) {
		T = t;
		Root = new Bnode(T);
		Root.n = 0;
		Root.leaf = true;
		steps=0;
	}

	// Search the key
	private Bnode Search(Bnode x, int key) {
		int i = 0;
		if (x == null)
			return x;
		for (i = 0; i < x.n; i++) {
			if (key < x.key[i]) {
				break;
			}
			if (key == x.key[i]) {
				return x;
			}
		}
		
		if (x.leaf) {
			return null;
		} else {
			return Search(x.child[i], key);
		}
	}
	/*
	 * 
	 */
	public boolean keySearching(Bnode node, int key) {
		int i=0;
		while(i<node.n && key > node.key[i]) {
			i++;
			MultiCounter.increaseCounter(2);
		}
		if(i<node.n && key == node.key[i]) {
			MultiCounter.increaseCounter(2);
			return true;
		}
		if (node.leaf) {
			MultiCounter.increaseCounter(2);
			return false;
		} else {
			MultiCounter.increaseCounter(2);
			return keySearching(node.child[i],key);
		}
		
	}
	
	@Override
	public boolean searchKey(int key) {
		// TODO Auto-generated method stub
		return keySearching(Root,key);
	}
	
/*
 * 	
 */

	// Split function
	private void Split(Bnode x, int pos, Bnode y) {
		Bnode z = new Bnode(T);
		z.leaf = y.leaf;
		z.n = T - 1;
		for (int j = 0; j < T - 1; j++) {
			z.key[j] = y.key[j + T];
		}
		if (!y.leaf) {
			for (int j = 0; j < T; j++) {
				z.child[j] = y.child[j + T];
			}
		}
		y.n = T - 1;
		for (int j = x.n; j >= pos + 1; j--) {
			x.child[j + 1] = x.child[j];
		}
		x.child[pos + 1] = z;

		for (int j = x.n - 1; j >= pos; j--) {
			x.key[j + 1] = x.key[j];
		}
		x.key[pos] = y.key[T - 1];
		x.n = x.n + 1;
	}

	// Insert the key
	public void insertKey(final int key) {
		Bnode r = Root;
		if (r.n == 2 * T - 1) {
			Bnode s = new Bnode(T);
			Root = s;
			s.leaf = false;
			s.n = 0;
			s.child[0] = r;
			Split(s, 0, r);
			insertNode(s, key);
		} else {
			insertNode(r, key);
		}
	}
	@Override
	public void insert(int key) {
		insertKey(key);
	}

	// Insert the node
	final private void insertNode(Bnode x, int k) {

		if (x.leaf) {
			int i = 0;
			for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
				x.key[i + 1] = x.key[i];
			}
			x.key[i + 1] = k;
			x.n = x.n + 1;
		} else {
			int i = 0;
			for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
			}
			;
			i++;
			Bnode tmp = x.child[i];
			if (tmp.n == 2 * T - 1) {
				Split(x, i, tmp);
				if (k > x.key[i]) {
					i++;
				}
			}
			insertNode(x.child[i], k);
		}

	}

	public void Show() {
		Show(Root);
	}

	private void remove(Bnode x, int key) {
		int pos = x.search(key);
		if (pos != -1) {
			if (x.leaf) {
				int i = 0;
				for (i = 0; i < x.n && x.key[i] != key; i++) {
				}
				;
				for (; i < x.n; i++) {
					if (i != 2 * T - 2) {
						x.key[i] = x.key[i + 1];
					}
				}
				x.n--;
				return;
			}
			if (!x.leaf) {

				Bnode pred = x.child[pos];
				int predKey = 0;
				if (pred.n >= T) {
					for (;;) {
						if (pred.leaf) {
							System.out.println(pred.n);
							predKey = pred.key[pred.n - 1];
							break;
						} else {
							pred = pred.child[pred.n];
						}
					}
					remove(pred, predKey);
					x.key[pos] = predKey;
					return;
				}

				Bnode nextNode = x.child[pos + 1];
				if (nextNode.n >= T) {
					int nextKey = nextNode.key[0];
					if (!nextNode.leaf) {
						nextNode = nextNode.child[0];
						for (;;) {
							if (nextNode.leaf) {
								nextKey = nextNode.key[nextNode.n - 1];
								break;
							} else {
								nextNode = nextNode.child[nextNode.n];
							}
						}
					}
					remove(nextNode, nextKey);
					x.key[pos] = nextKey;
					return;
				}

				int temp = pred.n + 1;
				pred.key[pred.n++] = x.key[pos];
				for (int i = 0, j = pred.n; i < nextNode.n; i++) {
					pred.key[j++] = nextNode.key[i];
					pred.n++;
				}
				for (int i = 0; i < nextNode.n + 1; i++) {
					pred.child[temp++] = nextNode.child[i];
				}

				x.child[pos] = pred;
				for (int i = pos; i < x.n; i++) {
					if (i != 2 * T - 2) {
						x.key[i] = x.key[i + 1];
					}
				}
				for (int i = pos + 1; i < x.n + 1; i++) {
					if (i != 2 * T - 1) {
						x.child[i] = x.child[i + 1];
					}
				}
				x.n--;
				if (x.n == 0) {
					if (x == Root) {
						Root = x.child[0];
					}
					x = x.child[0];
				}
				remove(pred, key);
				return;
			}
		} else {
			for (pos = 0; pos < x.n; pos++) {
				if (x.key[pos] > key) {
					break;
				}
			}
			Bnode tmp = x.child[pos];
			if (tmp.n >= T) {
				remove(tmp, key);
				return;
			}
			if (true) {
				Bnode nb = null;
				int devider = -1;

				if (pos != x.n && x.child[pos + 1].n >= T) {
					devider = x.key[pos];
					nb = x.child[pos + 1];
					x.key[pos] = nb.key[0];
					tmp.key[tmp.n++] = devider;
					tmp.child[tmp.n] = nb.child[0];
					for (int i = 1; i < nb.n; i++) {
						nb.key[i - 1] = nb.key[i];
					}
					for (int i = 1; i <= nb.n; i++) {
						nb.child[i - 1] = nb.child[i];
					}
					nb.n--;
					remove(tmp, key);
					return;
				} else if (pos != 0 && x.child[pos - 1].n >= T) {

					devider = x.key[pos - 1];
					nb = x.child[pos - 1];
					x.key[pos - 1] = nb.key[nb.n - 1];
					Bnode child = nb.child[nb.n];
					nb.n--;

					for (int i = tmp.n; i > 0; i--) {
						tmp.key[i] = tmp.key[i - 1];
					}
					tmp.key[0] = devider;
					for (int i = tmp.n + 1; i > 0; i--) {
						tmp.child[i] = tmp.child[i - 1];
					}
					tmp.child[0] = child;
					tmp.n++;
					remove(tmp, key);
					return;
				} else {
					Bnode lt = null;
					Bnode rt = null;
					boolean last = false;
					if (pos != x.n) {
						devider = x.key[pos];
						lt = x.child[pos];
						rt = x.child[pos + 1];
					} else {
						devider = x.key[pos - 1];
						rt = x.child[pos];
						lt = x.child[pos - 1];
						last = true;
						pos--;
					}
					for (int i = pos; i < x.n - 1; i++) {
						x.key[i] = x.key[i + 1];
					}
					for (int i = pos + 1; i < x.n; i++) {
						x.child[i] = x.child[i + 1];
					}
					x.n--;
					lt.key[lt.n++] = devider;

					for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
						if (i < rt.n) {
							lt.key[j] = rt.key[i];
						}
						lt.child[j] = rt.child[i];
					}
					lt.n += rt.n;
					if (x.n == 0) {
						if (x == Root) {
							Root = x.child[0];
						}
						x = x.child[0];
					}
					remove(lt, key);
					return;
				}
			}
		}
	}

	public void delete(int key) {
		Bnode x = Search(Root, key);
		if (x == null) {
			return;
		}
		remove(Root, key);
	}

	public void Task(int a, int b) {
		Stack<Integer> st = new Stack<>();
		FindKeys(a, b, Root, st);
		while (st.isEmpty() == false) {
			this.remove(Root, st.pop());
		}
	}

	private void FindKeys(int a, int b, Bnode x, Stack<Integer> st) {
		int i = 0;
		for (i = 0; i < x.n && x.key[i] < b; i++) {
			if (x.key[i] > a) {
				st.push(x.key[i]);
			}
		}
		if (!x.leaf) {
			for (int j = 0; j < i + 1; j++) {
				FindKeys(a, b, x.child[j], st);
			}
		}
	}

	public boolean Contain(int k) {
		if (this.Search(Root, k) != null) {
			return true;
		} else {
			return false;
		}
	}

	// Show the node
	private void Show(Bnode x) {
		assert (x == null);
		for (int i = 0; i < x.n; i++) {
			System.out.print(x.key[i] + " ");
		}
		if (!x.leaf) {
			for (int i = 0; i < x.n + 1; i++) {
				Show(x.child[i]);
			}
		}
	}

	
}
