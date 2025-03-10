package tp1.ejercicio5;

public class Datos {
	public void setMax(int max) {
		this.max = max;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public void setPro(double pro) {
		this.pro = pro;
	}
	private int max;
	private int min;
	private double pro;
	private int[] arreglo;
	
	@Override
	public String toString() {
		return "Datos [max=" + max + ", min=" + min + ", pro=" + pro + "]";
	}
	public int getMax() {
		return max;
	}
	public int getMin() {
		return min;
	}
	public double getPro() {
		return pro;
	}
	public Datos(int max, int min, double pro) {
		super();
		this.max = max;
		this.min = min;
		this.pro = pro;
	}
	public int[] getArreglo(){
		return arreglo;
	}
	public Datos(int []arreglo){
		this.arreglo=arreglo;
	}
	
}
