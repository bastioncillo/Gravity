package pr3.juego;

import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public class ReglasComplica extends ReglasJuego {

	public static int ANCHO = 4;
	public static int ALTO = 7;
		
	public ReglasComplica(){
		super();
	}
	
	// --------------------------------------- INICIAR TABLERO ---------------------------------------
	// Crea un tablero según los datos introducidos.
	public Tablero iniciarTablero() {
		return new Tablero(ANCHO, ALTO);
	}
	
	// ---------------------------------------- COMPROBAR GANADOR ------------------------------------------
	// Comprueba si hay un ganador de la partida según la útima ficha introducida.
	public Ficha comprobarGanador(Tablero tab, Movimiento mov) {
		Ficha ganador;
		boolean c4N = c4Negras(tab), c4B = c4Blancas(tab);
		if(c4N && !c4B){
			ganador = Ficha.NEGRA;
		}
		else if(!c4N && c4B){
			ganador = Ficha.BLANCA;
		}
		else{
			ganador = null;
		}
		return ganador;
	}
	
	//------------------------------------- COMPROBAR GANADOR NEGRAS ------------------------------------
	//Comprueba si las fichas negras han hecho 4 en raya
	private boolean c4Negras(Tablero tab){
		return ReglasComunes4EnLinea.c4Negras(tab);
	}
	
	//------------------------------------- COMPROBAR GANADOR BLANCAS ------------------------------------
	//Comprueba si las fichas blancas han hecho 4 en raya
	private boolean c4Blancas(Tablero tab){
		return ReglasComunes4EnLinea.c4Blancas(tab);
	}
		
	//-------------------------- COMPROBAR SI HAY TABLAS ----------------------------------
	//Este método no tiene  uso aquí.
	public boolean hayTablas(Tablero tab, Movimiento mov) {
		return false;
	}
		
	// ----------------------------------- CAMBIAR TURNO ---------------------------------
	// Cambia el color de la ficha a introducir según el turno que corresponda.
	public Ficha siguienteTurno(Tablero tab, Ficha turno) {
		return ReglasComunes4EnLinea.siguienteTurno(tab, turno);
	}
		
	// ---------------------------- DIBUJAR TABLERO ----------------------------
	// Dibuja el tablero colocando las fichas correspondientes.
	public String dibujarTablero(Tablero tab) {
		return tab.dibujarTablero();
	}
}