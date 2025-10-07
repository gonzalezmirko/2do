package ar.edu.unlp.info.oo1.ejercicio18;
import java.util.HashSet;
public class EvenNumberSet extends HashSet<Integer>{
	public EvenNumberSet() {
	}
	public boolean add(Integer data) {
		if(data % 2 == 0) {
			super.add(data);
		}
		return false;
	}
	/*
	 * Explique brevemente cómo propone utilizar las clases investigadas anteriormente para implementar su solución. Por ejemplo: 
	“Se debe subclasificar una determinada clase(en mi caso hice extends HashSet<Integer>) y
	 redefinir un método para que haga lo siguiente(redefini el metodo add())”
	 

	 */
}
