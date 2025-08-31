package ar.edu.unlp.oo1.ejercicio1;
/*
 * DUDA->> 1)Los metodos no tienen ni public ni private
 */
/**
 Debemos definir una clase Wallpost con los siguientes atributos: un texto que se desea publicar, 
 cantidad de likes (“me gusta”) y una marca que indica si es destacado o no. La clase es subclase de Object.
 */
public class WallPost {
	private String text;
	private int cantLikes;
	private boolean esDestacado;
	
	/*
	 * /*
* Permite construir una instancia del WallpostImpl.
* Luego de la invocación, debe tener como texto: “Undefined post”,
* no debe estar marcado como destacado y la cantidad de “Me gusta” debe ser 0.
*/
	public WallPost() {
		this.text="Undefined post";
		this.cantLikes=0;
		this.esDestacado=false;
	}
	
	/**
	 * Retorna el texto descriptivo de la publicación
	 * 
	 * @return
	 */
	String getText() {
		return this.text;
	};

	/**
	 * Setea el texto descriptivo de la publicación
	 * @param text
	 */
	void setText(String text) {
		this.text=text;
	};

	/**
	 * Retorna la cantidad de “me gusta”
	 * 
	 * @return
	 */
	int getLikes() {
		return this.cantLikes;
	};

	/**
	 * Incrementa la cantidad de likes en uno
	 */
	void like() {
		this.cantLikes++;
	};

	/**
	 * Decrementa la cantidad de likes en uno. Si ya es 0, no hace nada
	 */
	void dislike() {
		if(this.cantLikes>0) {
			this.cantLikes--;
		}
	};

	/**
	 * Retorna true si el post está marcado como destacado, false en caso contrario
	 * 
	 * @return
	 */
	boolean isFeatured() {
		return this.esDestacado;
	};

	/**
	 * Cambia el post del estado destacado a no destacado y viceversa
	 */
	void toggleFeatured() {
		this.esDestacado=!this.isFeatured();
	};

	/*
	 * Este mensaje se utiliza para que una instancia de Wallpost se muestre de forma adecuada
	 */
	@Override
	public String toString() {
		return "WallPost {" +
			"text: " + getText() +
			", likes: '" + getLikes() + "'" +
			", featured: '" + isFeatured() + "'" +
			"}";
	}

}