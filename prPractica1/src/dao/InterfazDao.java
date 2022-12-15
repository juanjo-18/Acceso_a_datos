package dao;

import java.util.ArrayList;


public interface InterfazDao<T> {
	
	
	public ArrayList<T> buscarTodos();
	

	public T buscarPorId(int i);
	
	
	public void insertar(T t);
	
	
	public void modificar(T t);
	
	
	public void borrarPorId(int i);
	
	public void borrarTodos();
}
