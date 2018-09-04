package pr3.control;

import java.util.Scanner;

import pr3.control.factorias.*;
import pr3.juego.*;
import pr3.jugadores.*;
import pr3.logica.*;
import pr3.movimientos.*;

public class Controlador {
	private Partida partida;
	private Movimiento mov;
	private ReglasJuego reglas;
	private Scanner in;
	private FactoriaJuego factoria;
	private Jugador jugador1, jugador2;
	
	// -------------------------------------- CONSTRUCTORA ---------------------------------------
	public Controlador(Partida partida, FactoriaJuego fj){
		this.partida = partida;
		this.in = new java.util.Scanner(System.in);
		// Como el juego por defecto es Conecta 4, se cargan las reglas correspondientes,
		// la factoria y dos jugadores humanos.
		this.factoria = fj;
		this.reglas =this.factoria.creaReglas();
		this.jugador1 = this.factoria.creaJugadorHumano(in);
		this.jugador2 = this.factoria.creaJugadorHumano(in);
	}
	
	// ----------------------------- EJECUCIÓN DEL PROGRAMA ---------------------------------------
	public void run(){
		// Variables locales																
		Ficha color = Ficha.BLANCA;
		boolean finDelJuego = false, j1EsHumano = true, j2EsHumano = true;
		String aux;
		
		// Presenta la información del principio de la partida.
		System.out.println(this.partida.dibujarTablero());
		System.out.println("Juegan Blancas");
		System.out.println("salir // jugar c4|co|gr tamX|tamY // poner // jugador negras|blancas humano|aleatorio // deshacer // reiniciar // ayuda");
		System.out.println("Que quieres hacer?: ");
		// Se solicita al usuario que introduzca la acción a realizar.
		String orden = this.in.nextLine();
		String[] arrayS = orden.split(" +");
		
		while(!finDelJuego){
			// Si el usuario elige "jugar" (y ha introducido más información)...
			if (arrayS[0].equalsIgnoreCase("jugar") && arrayS.length > 1) {
				// ...a Conecta4.
				if(arrayS[1].equalsIgnoreCase("c4")){
					// ...se cambia el tipo de juego, se cargan las reglas y se reinicia la partida.
					this.factoria = new FactoriaJuegoConecta4();
					this.reglas = this.factoria.creaReglas();
					this.jugador1 = this.factoria.creaJugadorHumano(in);
					j1EsHumano = true;
					this.jugador2 = this.factoria.creaJugadorHumano(in);
					j2EsHumano = true;
					this.partida.reset(this.reglas);
					color = this.partida.getTurno();
				}
				// ..a Complica.
				else if (arrayS[1].equalsIgnoreCase("co")) {
					// ...se cambia el tipo de juego, se cargan las reglas y se reinicia la partida.
					this.factoria = new FactoriaJuegoComplica();
					this.reglas = this.factoria.creaReglas();
					this.jugador1 = this.factoria.creaJugadorHumano(in);
					j1EsHumano = true;
					this.jugador2 = this.factoria.creaJugadorHumano(in);
					j2EsHumano = true;
					this.partida.reset(this.reglas);
					color = this.partida.getTurno();
				}
				// ..a Gravity.
				else if (arrayS[1].equalsIgnoreCase("gr")) {
					// ...se cambia el tipo de juego, se cargan las reglas y se reinicia la partida.
					// Si no ha introducido nuevo valores para las dimensiones del tablero, se crea el tablero por defecto.
					if (arrayS.length == 2) {
						this.factoria = new FactoriaJuegoGravity(0, 0);
						this.reglas = this.factoria.creaReglas();
						this.jugador1 = this.factoria.creaJugadorHumano(in);
						j1EsHumano = true;
						this.jugador2 = this.factoria.creaJugadorHumano(in);
						j2EsHumano = true;
						this.partida.reset(this.reglas);
						color = this.partida.getTurno();
					}
					// Si ha introducido datos para el ancho y alto, se crea un tablero con los nuevos valores
					else if(arrayS.length == 4){
						try {
							int ancho = Integer.parseInt(arrayS[2]);
							int alto = Integer.parseInt(arrayS[3]);
							this.factoria = new FactoriaJuegoGravity(alto, ancho);
							this.reglas = this.factoria.creaReglas();
							this.jugador1 = this.factoria.creaJugadorHumano(in);
							j1EsHumano = true;
							this.jugador2 = this.factoria.creaJugadorHumano(in);
							j2EsHumano = true;
							this.partida.reset(this.reglas);
							color = this.partida.getTurno();
						}
						catch (NumberFormatException nfe) {
							System.out.println("Debes introducir un valor numerico.");
						}
					}
					// Si falta algún dato en la instrucción se le comunica al usuario.
					else System.out.println("Datos insuficientes para crear las reglas de juego");
				}
				else System.out.println("La orden introducida no es válida");
			}
			// Si el usuario elige "poner"...
			else if (arrayS[0].equalsIgnoreCase("poner")) {
				try{
					//Intentamos conseguir un movimiento...
					if(color == Ficha.BLANCA){
						//Si la ficha es de color blanca, mueve el jugador 1.
						this.mov = this.partida.getMovimiento(this.factoria, this.jugador1);
					}
					//Si es negra, el 2.
					else this.mov = this.partida.getMovimiento(this.factoria, this.jugador2);
					//Una vez tenemos el movimiento, intentamos ejecutarlo....
					this.partida.ejecutaMovimiento(this.mov);
					color = this.partida.getTurno();
					// Si no ha habido ninguna excepción al ejecutar el movimiento, se comprueba si alguno de los jugadores ha ganado.
					finDelJuego = establecerGanador(this.partida, this.mov);
					// El movimiento puede no haberse podido ejecutar porque la columna introducida estaba llena(solo en el caso del Conecta4).
					}catch(ColumnaLlena cl){aux = cl.getMessage(); System.out.println(aux);}
					// Si no se puede conseguir un movimiento, es porque no se ha introducido un número...
					catch(CaracterInvalido ci){aux = ci.getMessage(); System.out.println(aux);}
					// Porque la posición elegida estaba ocupada(solo en el caso de Gravity).
					catch(CasillaOcupada co){aux = co.getMessage(); System.out.println(aux);}
					//Porque la fila introducida no era válida(solo en el caso de Gravity).
					catch(FilaIncorrecta fi){aux = fi.getMessage(); System.out.println(aux);} 
					//Porque la columna introducida no era válida.
					catch (ColumnaIncorrecta ci) {aux = ci.getMessage(); System.out.println(aux);}
			}
			// Si el usuario elige "jugador <B o N> <H o A>"...
			else if (arrayS[0].equalsIgnoreCase("jugador")) {
				// ...y quería fichas negras...
				if(arrayS[1].equalsIgnoreCase("negras")){
	            	 //...dependiendo de si quería un jugador humano o aleatorio, se crea una factoría u otra.
					 if(arrayS[2].equalsIgnoreCase("humano")){
						j2EsHumano = true;
	            		this.jugador2 =this.factoria.creaJugadorHumano(in);
	            	 }
	            	 else if(arrayS[2].equalsIgnoreCase("aleatorio")){
	            		j2EsHumano = false;
	            		this.jugador2 =this.factoria.creaJugadorAleatorio();
	            	 }
					 // Si el tipo de jugador no coincide con los disponibles, se muestra un mensaje de error.
	            	 else System.out.println("La orden introducida no es válida");
	             }
				 // ...y quería fichas blancas...
	             else if(arrayS[1].equalsIgnoreCase("blancas")){
	            	 //...dependiendo de si quería un jugador humano o aleatorio, se crea una factoría u otra.
	            	 if(arrayS[2].equalsIgnoreCase("humano")){
	            		j1EsHumano = true;
	            		this.jugador1 = this.factoria.creaJugadorHumano(in);
	            	 }
	            	 else if(arrayS[2].equalsIgnoreCase("aleatorio")){
	            		j1EsHumano = false;
	            		this.jugador1 = this.factoria.creaJugadorAleatorio();
	            	 }
	            	 //Si el tipo de jugador no coincide con los disponibles, se muestra un mensaje de error.
	            	 else System.out.println("La orden introducida no es válida");
	             }
				 // Si el tipo de ficha no coincide con las disponibles, se muestra un mmensaje de error.
	             else{
	            	 System.out.println("La orden introducida no es válida");
	             }
			}
			// Si el usuario elige "deshacer"...
			else if (arrayS[0].equalsIgnoreCase("deshacer")) {
				// ...se deshace el último movimiento y se cambia al turno del jugador anterior.
				try {
					this.partida.deshacer();
					color = this.partida.getTurno();
				} catch (ImposibleDeshacer id) {aux = id.getMessage(); System.out.println(aux);}
			}
			// Si el usuario elige "reiniciar"...
			else if (arrayS[0].equalsIgnoreCase("reiniciar")) {
				// ...se resetea por completo el tablero.
				this.partida.reset(reglas);
				color = Ficha.BLANCA;
				System.out.println("Tablero reiniciado");
			}
			//Si el usuario elige "ayuda"...
			else if (arrayS[0].equalsIgnoreCase("ayuda")) {
				//...se le muestran los comandos disponibles
				System.out.println("Los comandos disponibles son:");
				System.out.println("poner: utilizalo para poner la siguiente ficha");
				System.out.println("deshacer: deshace el último movimiento de la partida");			
				System.out.println("reiniciar: reinicia la partida");			
				System.out.println("jugar [c4|co|gr] [tamX|tamY]: cambia el tipo de juego");
				System.out.println("jugador [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador");
				System.out.println("salir: termina la aplicación");
				System.out.println("ayuda: muestra esta ayuda");
			}
			//Si el usuario elige salir, finDelJuego se pone a true para salir al final del bucle
			else if(arrayS[0].equalsIgnoreCase("salir")){
				finDelJuego = true;
			}
			// Si se introduce cualquier otra instrucción, se lanza un mensaje informando del error.
			else {
				System.out.println("La orden introducida no existe");
			}
			// Si nadie ha ganado y no hay tablas, se sigue con la partida.
			if (!finDelJuego) {
				System.out.println(this.partida.dibujarTablero());
				// Si era el jugador1...
				if (color == Ficha.BLANCA && j1EsHumano) {
					System.out.println("Juegan Blancas");
					System.out.println("salir // jugar c4|co|gr tamX|tamY // poner // jugador negras|blancas humano|aleatorio // deshacer // reiniciar // ayuda");
					System.out.println("Que quieres hacer?: ");
					// Se introduce la instruccion y se repite todo el proceso.
					orden = in.nextLine();
					arrayS = orden.split(" +");
				}
				//Si era el jugador2...
				else if(color == Ficha.NEGRA && j2EsHumano){
					System.out.println("Juegan Negras");
					System.out.println("salir // jugar c4|co|gr tamX|tamY // poner // jugador negras|blancas humano|aleatorio // deshacer // reiniciar // ayuda");
					System.out.println("Que quieres hacer?: ");
					// Se introduce la instruccion y se repite todo el proceso.
					orden = in.nextLine();
					arrayS = orden.split(" +");
				}
				//Si era el jugador aleatorio...
				else{
					arrayS[0] = "poner"; // Dejamos la orden como "poner", porque la máquina no sabe elegir.
					System.out.println(" ");
					System.out.println("La máquina acaba de mover");
					System.out.println(" ");
				}
			}
			// Si se ha acabado la partida, se muestra el tablero por última vez y se cierra el programa.
			else {
				System.out.println(this.partida.dibujarTablero());
			}
		}
	}
	
	private boolean establecerGanador(Partida partida, Movimiento mov){
		boolean finDelJuego = false;
		if (partida.getGanador() == Ficha.BLANCA) {
			System.out.println("Ganan las Blancas");
			finDelJuego = true;
		}
		else if (partida.getGanador() == Ficha.NEGRA) {
			System.out.println("Ganan las Negras");
			finDelJuego = true;
		}
		// Si no ha ganado nadie, se comprueba si se han producido tablas.
		else {
			if (partida.tablas(mov)) {
				finDelJuego = true;
				System.out.println("La partida ha acabado en tablas");
			}
		}	
		return finDelJuego;
	}
}