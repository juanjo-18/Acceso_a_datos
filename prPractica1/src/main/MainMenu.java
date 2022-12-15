package main;

import java.util.ArrayList;
import java.util.Scanner;

import clases.Casa;
import clases.Mueble;
import dao.CasaDao;
import dao.MuebleDao;

public class MainMenu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		CasaDao casaDao = new CasaDao();
		MuebleDao muebleDao = new MuebleDao();
		ArrayList<Mueble> muebles = new ArrayList<Mueble>();
		ArrayList<Casa> casas = new ArrayList<Casa>();
		casas = casaDao.buscarTodos();
		Casa casa = null;
		String calle = "Marco";
		int numero = 2;
		byte opcion;

		do {
			System.out.println("Elige una opcion:\n1 - Insertar casa\n2 - Borrar casa por id\n"
					+ "3 -Borrar casa por direccion\n4 - Mostrar todas las casas\n5 - Modificar casa"
					+ "\n6 - Buscar casa por id\n7 - Buscar casa por direccion\n" + "8 - Borrar todas las casas"
					+ "\n9 - Insertar mueble\n10 - Borrar mueble por id\n11 - "
					+ "Borrar mueble por medidas\n12 - Modificar mueble\n13 - Buscar mueble por id\n14 - "
					+ "Buscar mueble por medidas\n15 - Buscar todos los muebles\n16 - Buscar todos los muebles de una casa"
					+ "\n17 - Borrar todos los muebles");

			opcion = Byte.parseByte(sc.nextLine());
			switch (opcion) {
			case 1:
				casa = new Casa(calle, numero, "Malaga", "Malaga");
				casaDao.insertar(casa);
				Mueble mueble = new Mueble("Armario", 50, 100, 200,
						casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
				Mueble mueble1 = new Mueble("Mesa", 70, 100, 200,
						casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
				muebles.add(mueble1);
				muebles.add(mueble);
				casa.setMuebles(muebles);
				muebleDao.insertar(mueble);
				muebleDao.insertar(mueble1);

				break;
			case 2:
				if (!casas.isEmpty()) {
					System.out.println("Dime el id de la casa: ");
					byte id = Byte.parseByte(sc.nextLine());
					casaDao.borrarPorId(id);
					System.out.println("Borrado con exito");
				} else {
					System.out.println("No existe");
				}
				break;
			case 3:
				if (!casas.isEmpty()) {
					casaDao.borrarPorDireccion(calle, numero);
					System.out.println("Borrado con exito");
				} else {
					System.out.println("No existe");
				}
				break;
			case 4:
				System.out.println(casas.toString());
				break;
			case 5:
				Casa casa1 = new Casa("Caceres", 2, "Cartama", "Malaga");
				casaDao.insertar(casa1);
				casa1 = new Casa(casaDao.buscarPorDireccion(casa1.getCalle(), casa1.getNumero()).getId(), "Caceres", 2,
						"Estacion de cartama", "Malaga");
				casaDao.modificar(casa1);
				break;
			case 6:
				casa = casaDao.buscarPorId(28);
				System.out.println(casa);
				break;
			case 7:
				casa = casaDao.buscarPorDireccion(calle, numero);
				System.out.println(casa);
				break;
			case 8:
				casaDao.borrarTodos();
				casas = casaDao.buscarTodos();
				if (casas.isEmpty()) {
					System.out.println("Todo borrado");
				}
				break;
			case 9:
				Mueble mueble2 = new Mueble("Cama", 70, 100, 200,
						casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
				muebleDao.insertar(mueble2);
				break;
			case 10:
				muebleDao.borrarPorId(32);
				break;
			case 11:
				muebleDao.borrarPorMedidas(70, 100, 200);
				break;
			case 12:
				Mueble mueble3 = new Mueble("Silla", 100, 90, 80,
						casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
				muebleDao.insertar(mueble3);
				mueble3 = new Mueble(muebleDao.buscarPorMedidas(100, 90, 80).getId(), "Silla", 120, 90, 80,
						casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
				muebleDao.modificar(mueble3);
				break;
			case 13:
				System.out.println(muebleDao.buscarPorId(17844));
				break;
			case 14:
				System.out.println(muebleDao.buscarPorMedidas(50, 100, 200));
				break;
			case 15:
				System.out.println(muebleDao.buscarTodos());
				break;
			case 16:
				System.out.println(muebleDao.buscarTodos(casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero())));
				break;
			case 17:
				muebleDao.borrarTodos();
				muebles = muebleDao.buscarTodos();
				if (muebles.isEmpty()) {
					System.out.println("Todo borrado");
				}
				break;
			}
		} while (opcion != 0);

	}

}
