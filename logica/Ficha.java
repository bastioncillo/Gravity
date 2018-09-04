package pr3.logica;
///////////////////////////////////////////////
public enum Ficha {
	VACIA, BLANCA, NEGRA;
	
	public String toString() {
		String simbolo = " ";
		if (this == BLANCA) {
			simbolo = "O";
		}
		else if (this == NEGRA) {
			simbolo = "x";
		}
		return simbolo;
	}
///////////////////////////////////////////////
}
