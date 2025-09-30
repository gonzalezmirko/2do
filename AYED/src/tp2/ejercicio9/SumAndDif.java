package tp2.ejercicio9;

public class SumAndDif {
	private int suma;
	private int dif;
	
	public SumAndDif(int suma, int dif) {
		super();
		this.suma = suma;
		this.dif = dif;
	}
	
	public int getSuma() {
		return suma;
	}
	public int getDif() {
		return dif;
	}
	
	@Override
	public String toString() {
		return "SumAndDif [suma=" + suma + ", dif=" + dif + "]";
	}
}
