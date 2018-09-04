package pr3.logica;

import pr3.control.factorias.FactoriaJuego;
import pr3.juego.ReglasJuego;
import pr3.jugadores.CaracterInvalido;
import pr3.jugadores.CasillaOcupada;
import pr3.jugadores.ColumnaIncorrecta;
import pr3.jugadores.FilaIncorrecta;
import pr3.jugadores.Jugador;
import pr3.movimientos.ColumnaLlena;
import pr3.movimientos.Movimiento;

public class Partida {
	private Tablero tablero;
	private Ficha turno;
	private Ficha ganador;
	private ReglasJuego reglas;
	private Pila pila;
		
	// ----------------------------- CONSTRUCTORA -------------------------
	public Partida(ReglasJuego reglas){
		this.reglas = reglas;
		this.tablero = reglas.iniciarTablero();
		this.turno = Ficha.BLANCA;
		this.pila = new Pila();
	}
	
	// ------------------------------- RESET -------------------------------------
	// Reinicia la partida por completo
	public void reset(ReglasJuego reglas){
		this.reglas = reglas;
		this.tablero = reglas.iniciarTablero();
		this.turno = Ficha.BLANCA;
		this.pila = new Pila();
	}
	
	// ------------------------------- DIBUJAR TABLERO ---------------------------
	// Dibuja el tablero colocando las fichas correspondientes
	public String dibujarTablero(){
		return this.reglas.dibujarTablero(this.tablero);
	}
		
	// ---------------------------------- GET TURNO ------------------------------
	// Devuelve el turno del jugador
	public Ficha getTurno(){
		return this.turno;
	}
	
	// ---------------------------------- GET GANADOR ------------------------------
	// Devuelve el turno del jugador
	public Ficha getGanador(){
		return this.ganador;
	}
	
	// ------------------------------- GET MOVIMIENTO ----------------------------
	// Devuelve un movimiento
	public Movimiento getMovimiento(FactoriaJuego factoria, Jugador jugador) throws CaracterInvalido, CasillaOcupada, FilaIncorrecta, ColumnaIncorrecta {
		return jugador.getMovimiento(factoria, this.tablero, this.turno);
	}
		     
	// ------------------------------------- EJECUTAR MOVIMIENTO ----------------------------------------
	// Coloca una ficha en el tablero y comprueba si se acaba la partida o no.
	public void ejecutaMovimiento(Movimiento mov) throws ColumnaLlena{
		mov.ejecutaMovimiento(this.tablero);
		this.ganador = this.reglas.comprobarGanador(this.tablero, mov);
		if(this.ganador == null){
			this.pila.poner(mov);
			this.turno = cambiaTurno();
		}
	}
	
	// ------------------------------------------ TABLAS --------------------------------------------
	// Comprueba si se han formado tablas en el tablero
	public boolean tablas(Movimiento mov){
		return this.reglas.hayTablas(this.tablero, mov);
	}
	
	// ----------------------------------------- CAMBIA TURNO ---------------------------------------
	// Cambia el turno para que juegue el siguiente jugador
	private Ficha cambiaTurno(){
		return reglas.siguienteTurno(this.tablero, this.turno);
	}
	
	// ------------------------------------------ DESHACER -----------------------------------------
	// Deshace el último movimiento del jugador
	public void deshacer() throws ImposibleDeshacer{
		Movimiento anterior = this.pila.sacar();
		if (anterior != null) {
			anterior.undo(this.tablero);
			this.turno = cambiaTurno();
		}
		else throw new ImposibleDeshacer("Imposible deshacer");
	}
}	


