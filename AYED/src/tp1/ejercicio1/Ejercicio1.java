package tp1.ejercicio1;
/*
1.	Escriba tres métodos de clase (static) que reciban por parámetro dos números enteros (tipo int) a y b e 
impriman todos los números enteros comprendidos entre a; b (inclusive), uno por cada línea en la salida estándar. 
Para ello, dentro de una nueva clase escriba un método por cada uno de los siguientes incisos:
a.	Que realice lo pedido con un for.
b.	Que realice lo pedido con un while.
c.	Que realice lo pedido sin utilizar estructuras de control iterativas (for, while, do while).
Por último, escriba en el método de clase main el llamado a cada uno de los métodos creados, con valores de ejemplo. 
En su computadora, ejecute el programa y verifique que se cumple con lo pedido.

 */
public class Ejercicio1 {
	public static boolean aMenorIgualB(int a,int b) {
		return a<=b;
	}
	public static void prueba_for(int a, int b) {
		if(aMenorIgualB(a,b)) {
			System.out.println("- Numeros entre a y b -");
			for(int i=a;i<=b;i++) {
				System.out.println("-- Numero:"+i+" --");
			}
		}
		else {
			System.out.println("El nuemero "+a+" es mas grande que "+b+".");
		}
	}
	public static void prueba_while(int a,int b) {
		if(aMenorIgualB(a,b)) {
			int i=a;
			System.out.println("-- Numeros entre a y b --");
			while(i<=b) {
				System.out.println("-- Numero: "+i+" --");
				i++;
			}
		}
		else {
			System.out.println("El nuemero "+a+" es mas grande que "+b+".");
		}
	}
	public static void prueba_recursion(int a,int b) {
		if(aMenorIgualB(a,b)) {
			System.out.println("-- Numero "+a+"--");
			prueba_recursion(a+1,b);
		}
		else {
			System.out.println("El nuemero "+a+" es mas grande que "+b+".");
		}
	}
	public static void main(String[] args) {
		prueba_for(10,5);
		prueba_while(7,5);
		prueba_recursion(6,5);
	}
}
