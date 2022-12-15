package main;

import java.util.ArrayList;

import clases.Casa;
import clases.Mueble;
import dao.CasaDao;
import dao.MuebleDao;

public class Main {

	public static void main(String[] args) {

		CasaDao casaDao = new CasaDao();
		MuebleDao muebleDao = new MuebleDao();
		ArrayList<Mueble> muebles = new ArrayList<Mueble>();
		ArrayList<Casa> casas = new ArrayList<Casa>();
		String calle = "Teruel";
		int numero = 2;

		// Inserto una casa y varios muebles
		Casa casa = new Casa(calle, numero, "Malaga", "Malaga");
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
		System.out.println(casaDao.buscarTodos() + "" + muebleDao.buscarTodos() + "\n");

		// Modificamos una casa
		Casa casa1 = casaDao.buscarPorDireccion(calle, numero);
		casa1.setMunicipio("Estacion De Cartama");
		System.out.println(casaDao.buscarTodos());
		casaDao.modificar(casa1);
		System.out.println("Modificada: " + casaDao.buscarTodos() + "\n");

		// Buscar casa por id
		System.out.println(casaDao.buscarPorId(casaDao.buscarPorDireccion(calle, numero).getId()) + "\n");

		// Buscar casa por direccion
		System.out.println(casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()) + "\n");

		// Borrado de casa por id
		casaDao.borrarPorId(casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()).getId());
		System.out.println("Borrado con exito : " + casaDao.buscarTodos() + "" + muebleDao.buscarTodos() + "\n");

		// Borrado de casa por direccion
		casa = new Casa(calle, numero, "Malaga", "Malaga");
		casaDao.insertar(casa);
		System.out.println(casaDao.buscarTodos());
		casaDao.borrarPorDireccion(calle, numero);
		System.out.println("Borrado con exito : " + casaDao.buscarTodos() + "" + muebleDao.buscarTodos() + "\n");

		// Borrar todas las casas
		casa = new Casa(calle, numero, "Malaga", "Malaga");
		casaDao.insertar(casa);
		mueble = new Mueble("Armario", 50, 100, 200, casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
		mueble1 = new Mueble("Mesa", 70, 100, 200, casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
		muebles.add(mueble1);
		muebles.add(mueble);
		casa.setMuebles(muebles);
		muebleDao.insertar(mueble);
		muebleDao.insertar(mueble1);
		System.out.println(casaDao.buscarTodos() + "" + muebleDao.buscarTodos());
		casaDao.borrarTodos();
		System.out.println("Casas borradas: " + casaDao.buscarTodos() + "" + muebleDao.buscarTodos() + "\n");

		// Insertar mueble
		casa = new Casa(calle, numero, "Malaga", "Malaga");
		casaDao.insertar(casa);
		Mueble mueble2 = new Mueble("Cama", 70, 100, 200, casaDao.buscarPorDireccion(calle, numero));
		muebleDao.insertar(mueble2);
		System.out.println("Insertado: "+muebleDao.buscarTodos() + "\n");
		
		
		// Modificar mueble
		Mueble mueble3 =muebleDao.buscarPorMedidas(70, 100, 200);
		mueble3.setNombre("Silla");
		mueble3.setCasa(casa);
		muebleDao.modificar(mueble3);
		System.out.println("Modificado: " + muebleDao.buscarTodos() + "\n");
		
		
		// Buscar mueble por id
		Mueble mueble4 = new Mueble("Mampara", 2000, 1000, 200, casaDao.buscarPorDireccion(calle, numero));
		muebleDao.insertar(mueble4);
		mueble4.setCasa(casa);
		System.out.println("Por id: "+muebleDao.buscarPorId(muebleDao.buscarPorMedidas(2000, 1000, 200).getId())+"\n");
		
		
		// Buscar mueble por medidas
		System.out.println("Por medidas: "+muebleDao.buscarPorMedidas(70, 100, 200)+"\n");
		
		
		//Buscar todos los muebles
		System.out.println("Todos los muebles: "+muebleDao.buscarTodos()+"\n");
		
		
		//Buscar todos los muebles de una casa
		System.out.println("Muebles de la casa: "+muebleDao.buscarTodos(casaDao.buscarPorDireccion(calle, numero))+"\n");
		
		
		//Borrar mueble por id
		System.out.println(muebleDao.buscarPorMedidas(70, 100, 200));
		muebleDao.borrarPorId(muebleDao.buscarPorMedidas(70, 100, 200).getId());
		System.out.println("Borrado: "+muebleDao.buscarPorId(mueble4.getId())+"\n");
		
		
		//Borrar mueble por medidas
		System.out.println("Borrado: "+muebleDao.buscarPorMedidas(2000, 1000, 200));
		muebleDao.borrarPorMedidas(2000, 1000, 200);
		System.out.println("Borrado: "+muebleDao.buscarPorMedidas(2000, 1000, 200)+"\n");
		
		//Borrar todos los muebles
		mueble4 = new Mueble("Mampara", 2000, 1000, 200, casaDao.buscarPorDireccion(calle, numero));
		muebleDao.insertar(mueble4);
		mueble = new Mueble("Armario", 50, 100, 200, casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
		mueble1 = new Mueble("Mesa", 70, 100, 200, casaDao.buscarPorDireccion(casa.getCalle(), casa.getNumero()));
		muebleDao.insertar(mueble);
		muebleDao.insertar(mueble1);
		System.out.println(muebleDao.buscarTodos());
		muebleDao.borrarTodos();
		System.out.println("Borrar todos: "+muebleDao.buscarTodos());
		casaDao.borrarTodos();
		
	}

}
