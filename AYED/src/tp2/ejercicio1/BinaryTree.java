package tp2.ejercicio1;

public class BinaryTree <T>{
	private T data;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;
	
	//●	El constructor BinaryTree(T data) inicializa un árbol con el dato pasado como parámetro y ambos hijos nulos.
	public BinaryTree(T dato) {
		this.data=dato;
		this.leftChild=null;
		this.rightChild=null;
	}
	
	//Constructor BinaryTree nulo.
	public BinaryTree() {
		this.leftChild=null;
		this.rightChild=null;
		this.data=null;
	}
	
	//Retorna el dato del arbol binario.
	public T getData() {
		return data;
	}
	
	//Setea un dato del arbol binario.
	public void setData(T data) {
		this.data = data;
	}

	//Retorna el arbol binario del hijo izquierdo.
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}

	//Retorna el arbol binario del hijo derecho.
	public BinaryTree<T> getRightChild() {
		return rightChild;
	}

	//El método addLeftChild(BinaryTree<T> child) agrega un hijo como hijo izquierdo del árbol.
	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild=child;
	}
	
	//El método addRightChild(BinaryTree<T> child) agrega un hijo como hijo derecho del árbol.
	public void addRightChild(BinaryTree<T> child) {
		this.rightChild=child;
	}
	
	//El método removeLeftChild() eliminan el hijo correspondiente.
	public void removeLeftChild() {
		this.leftChild=null;
	}
	
	
	//El métodoremoveRightChild(), eliminan el hijo correspondiente.
	public void removeRightChild() {
		this.rightChild=null;
	}
	
	//El método isEmpty() indica si el árbol está vacío.
	public boolean isEmpty() {
		return this.isLeaf() && this.getData().equals(null);
	}
	
	//El método isLeaf() indica si no tiene hijos.
	public boolean isLeaf() {
		return !this.hasLeftChild() && !this.hasRitghChild();
	}
	
	//El método hasLeftChild() devuelve un booleano indicando si tiene dicho hijo el árbol receptor del mensaje.
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}
	
	//El método hasRightChild() devuelve un booleano indicando si tiene dicho hijo el árbol receptor del mensaje.
	public boolean hasRitghChild() {
		return this.rightChild!=null;
	}
	
	//Imprime el dato
	@Override
	public String toString() {
		return "Dato:"+this.getData().toString();
	}
	
	//PreOrden: primero raiz ,despues hijos
	public void printPreOrden() {
		System.out.println("Dato:"+this.toString());
		if(this.hasLeftChild()) {
			this.leftChild.printPreOrden();
		}
		if(this.hasRitghChild()) {
			this.rightChild.printPreOrden();
		}
	}
	
	//InOrden: primero hijo izquierdo , despues raiz y ultimo hijo derecho
	public void printInOrden() {
		if(this.hasLeftChild()) {
			this.leftChild.printInOrden();
		}
		System.out.println(this.toString());
		if(this.hasRitghChild()) {
			this.rightChild.printInOrden();
		}
	}
	
	//PostOrden: primeros hijos , despues raiz
	public void printPostOrden() {
		if(this.hasLeftChild()) {
			this.leftChild.printPostOrden();
		}
		if(this.hasRitghChild()) {
			this.rightChild.printPostOrden();
		}
		System.out.println(this.toString());
	}
	
	public static void main(String[]args) {
		BinaryTree<Integer>ab=new BinaryTree<Integer>(10);//raiz
		ab.addLeftChild(new BinaryTree<Integer>(20));//hijo izq
		ab.addRightChild(new BinaryTree<Integer>(30));//hijo der
		ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(40));//hijo izq hijo izq
		ab.getLeftChild().addRightChild(new BinaryTree<Integer>(50));//hijo izq hijo der
		//ab.printInOrden();// 40 20 50 10 30
		//ab.printPostOrden(); //40 50 20 30 10
		ab.printPreOrden();//10 20 40 50 30
	}
}
