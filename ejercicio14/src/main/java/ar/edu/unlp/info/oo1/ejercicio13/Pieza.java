package ar.edu.unlp.info.oo1.ejercicio13;

public abstract class Pieza {
	private String material;
	private String color;
	
	public abstract double volumen();
	public abstract double superficie();
	public String getMaterial() {
		return material;
	}
	public String getColor() {
		return color;
	}
	
	public boolean contieneColor(String unColor) {
		return this.getColor().equals(unColor);
	}
	
	public boolean contieneMaterial(String unMaterial) {
		return this.getMaterial().equals(unMaterial);
	}
}
