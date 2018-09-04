package pr3.jugadores;

import pr3.control.factorias.FactoriaJuego;
import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JugadorConsolaGravity extends Jugador{
	private Scanner in;
	public JugadorConsolaGravity(Scanner sc){
		this.in = sc;
	}
	
	public Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color) throws CaracterInvalido, CasillaOcupada, FilaIncorrecta, ColumnaIncorrecta {
		int fila = 0;
		int columna = 0;
		try{
			System.out.print("Introduce la fila: ");
			fila = in.nextInt() - 1;
			in.nextLine();
			if(fila < 0 || fila >= tab.getAlto()){
				throw new FilaIncorrecta("Fila incorrecta");
			}
			System.out.print("Introduce la columna: ");
			columna = in.nextInt() - 1;
			in.nextLine(); //Para evitar el salto de linea
			if(columna < 0 || columna >= tab.getAlto()){
				throw new ColumnaIncorrecta("Columna incorrecta");
			}
			if(tab.getFicha(columna, fila) != Ficha.VACIA){
				throw new CasillaOcupada("Casilla ocupada");
			}
		}catch(InputMismatchException e){
			in.nextLine();
			throw new CaracterInvalido("Caracter inválido");
		}
		return factoria.creaMovimiento(fila, columna, color);
	}
}