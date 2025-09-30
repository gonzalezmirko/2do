package ar.edu.unlp.info.oo1.ejercicio13;

public class PrismaRectangular extends Pieza{
	private int ladoMenor;
	private int ladoMayor;
	private int altura;
	/*
	 * Prisma Rectangular
	Volumen del prisma: ladoMayor * ladoMenor * altura
	Superficie del prisma: 2 * (ladoMayor * ladoMenor + ladoMayor * altura + ladoMenor * altura)
	 */
	public double volumen() {
		return this.ladoMayor*this.ladoMenor*this.altura;
	}
	public double superficie() {
		return 2*(this.ladoMayor*this.ladoMenor+this.ladoMayor*this.altura+this.ladoMenor*this.altura);
	}
}
