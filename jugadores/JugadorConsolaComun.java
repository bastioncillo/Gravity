package pr3.jugadores;

import java.util.InputMismatchException;
import java.util.Scanner;
import pr3.control.factorias.FactoriaJuego;
import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public class JugadorConsolaComun {
	public static Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color, Scanner in) throws CaracterInvalido, ColumnaIncorrecta {
		int columna = 0;
		try {
			System.out.println("Introduce la columna: ");
			columna = in.nextInt() - 1;
			in.nextLine(); //Para evitar el salto de linea
			if(columna < 0 || columna >= tab.getAncho()){
				throw new ColumnaIncorrecta("Columna incorrecta");
			}
		} catch (InputMismatchException e) {
			in.nextLine();
		    throw new CaracterInvalido("Caracter invalido"); 
		}
		return factoria.creaMovimiento(0, columna, color);
	}
}
