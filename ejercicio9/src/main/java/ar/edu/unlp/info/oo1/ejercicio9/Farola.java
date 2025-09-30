package ar.edu.unlp.info.oo1.ejercicio9;

import java.util.*;

public class Farola {
	private List<Farola>vecinos;
	private boolean encendida;
	/*
	* Crear una farola. Debe inicializarla como apagada
	*/
	public Farola () {
		this.vecinos=new ArrayList<Farola>();
		this.encendida=false;
	}

	/*
	* Crea la relación de vecinos entre las farolas. 
	* La relación de vecinos entre las farolas es recíproca, 
	* es decir el receptor del mensaje será vecino de otraFarola, 
	* al igual que otraFarola también se convertirá en vecina del receptor del mensaje
	*/

	public void pairWithNeighbor( Farola otraFarola ) {
		if(!this.vecinos.contains(otraFarola)) {
			this.vecinos.add(otraFarola);
		}
		if(!otraFarola.vecinos.contains(this)) {
			otraFarola.pairWithNeighbor(this);//o directamnte hago un add? como lo trato al objeto?
		}
	}
	/*
	* Retorna sus farolas vecinas
	*/
	public List<Farola> getNeighbors (){
		if(this.vecinos!=null) {
			return this.vecinos;
		}
		return null;
	}


	/*
	* Si la farola no está encendida, la enciende y propaga la acción.
	*/
	public void turnOn() {
		if(!this.isOn()) {
			this.encendida=true;
			for(Farola vecino:this.getNeighbors()) {
				vecino.turnOn();
			}
		}
	}

	/*
	* Si la farola no está apagada, la apaga y propaga la acción.
	*/
	public void turnOff() {
		if(!this.isOff()) {
			this.encendida=false;
			for(Farola vecino:this.getNeighbors()) {
				vecino.turnOff();
			}
		}
	}

	/*
	* Retorna true si la farola está encendida.
	*/
	public boolean isOn() {
		return this.encendida;
	}

	/*
	* Retorna true si la farola está apagada.
	*/
	public boolean isOff() {
		return !this.encendida;
	}

	
}
