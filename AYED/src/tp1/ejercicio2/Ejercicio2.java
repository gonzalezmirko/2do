package tp1.ejercicio2;
/*
.Escriba un método de clase que dado un número n devuelva un nuevo arreglo de tamaño n con los n primeros múltiplos 
enteros de n mayores o iguales que 1.
Ejemplo: f(5) = [5; 10; 15; 20; 25]; f(k) = {n*k donde k : 1..k}
Agregue al programa la posibilidad de probar con distintos valores de n ingresandolos por teclado,
 mediante el uso de System.in. La clase Scanner permite leer de forma sencilla valores de entrada.

 */
import java.util.Scanner;
public class Ejercicio2 {
	public static int[] multiplosDeN(int n) {
		int[] arreglo=new int[n];
		for(int i=0;i<n;i++) {
			arreglo[i]=n*(i+1);
		}
		return arreglo;
	}
	public static void imprimirArreglo(int[]arreglo) {
		System.out.println("-- Arreglo de "+arreglo.length+" posiciones --");
		for(int i=0;i<arreglo.length;i++) {
			System.out.println("Numero :"+arreglo[i]);
		}
	}
	public static void imprimirArreglo2(int[]arreglo,int n) {
		if(n>=0) {
			imprimirArreglo2(arreglo,n-1);
			System.out.println("num:"+arreglo[n]);
		}
	}
	public static void main(String[] args) {
		System.out.println("Escirbir un numero n(!= 0):");
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		while(n!=0) {
			int[] arregloDeN=multiplosDeN(n);
			imprimirArreglo2(arregloDeN,n-1);//si o si necesitas la posicion para hacerlo recursivo
			System.out.println("Escirbir un numero n(!= 0):");
			n=input.nextInt();
		}
	}
}
