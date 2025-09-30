package tp1.ejercicio9;
/*
9.	Considere un string de caracteres S, el cual comprende únicamente los caracteres: (,),[,],{,}.
Decimos que S está balanceado si tiene alguna de las siguientes formas:
S = ""	S es el string de longitud cero.
S = "(T)"
S = "[T]"
S = "{T}"
S = "TU"
Donde ambos T y U son strings balanceados. Por ejemplo, "{( ) [ ( ) ] }" está balanceado, pero "( [ ) ]" no lo está.
a.	Indique qué estructura de datos utilizará para resolver este problema y cómo la utilizará.
b.	Implemente una clase llamada tp1.ejercicio9.TestBalanceo, cuyo objetivo es determinar si un String dado 
está balanceado. El String a verificar es un parámetro de entrada (no es un dato predefinido).
 */
import tp1.ejercicio8.Stack;
public class TestBalanceo {
	/*
	public static void agregarACola(String s,Queue<Character> cola) {
		char elem;
		for (int i=0;i<s.length();i++) {
			elem=s.charAt(i);
			cola.enqueue(elem);
		}
	}
	le erre de estructura y por eso no me salio :) */
	
	public static boolean estaBalanceado(String expresion) {
		Stack<Character> pila=new Stack<Character>();
		for(int i=0;i<expresion.length();i++) {
			Character elem=expresion.charAt(i);
			//Comparo con los elementos de apertura y los meto a la pila
			if( elem == '(' || elem == '[' || elem == '{' ) {
				pila.push(elem);
			}
			//si esta vacia la pila quiere decir que esta balanceado
			else if(pila.isEmpty()) {
				return true;
			}
			//si no esta vacia la cola 
			else {
				char c=pila.pop();
				//comparo el elemento que me llega de cierre y no es el que esta en la pila que hago el pop returno false
				if( (elem==')' && c!='(') || ( elem==']' && c!='[' ) || ( elem=='}' && c!='{')) {
					return false;
				}
			}
		}
		return pila.isEmpty();
	}
	public static void main(String[] args) {
		String prueba1="{()[()]}";
		String prueba2="([))";
		//agregarACola(prueba1,pila);
		//System.out.println(cola.toString());
		if(estaBalanceado(prueba2)) {
			System.out.println("El String esta balanceado.");
		}
		else {
			System.out.println("El String no esta balanceado.");
		}
	}

}
