package pr3.control;
///////////////////////////////////////////////
public enum TipoJuego {
	C4, CO, GR;
	
	public String toString() {
		String simbolo = " ";
		if (this == C4) {
			simbolo = "Conecta 4";
		}
		else if (this == CO) {
			simbolo = "Complica";
		}
		else simbolo = "Gravity";
		return simbolo;
	}
///////////////////////////////////////////////
}
