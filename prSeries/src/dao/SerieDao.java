package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import prSeries.Serie;


public class SerieDao  implements Dao<Serie>{

	private static Connection connection;
	
	public SerieDao() {
		
	}

	@Override
	public ArrayList<Serie> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificar(Serie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Serie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serie buscarPorId(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Serie serie) {
		// TODO Auto-generated method stub
		connection=openConnection();
		
		String query= "insert into series(titulo,edad,plataforma) values (? ,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, serie.getTitulo());
			ps.setInt(2, serie.getEdad());
			ps.setString(3, serie.getPlataforma());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	

	public static Connection openConnection() {
		DataBaseConection dbConnection = new DataBaseConection();
		connection = dbConnection.getConnection();
		return connection;
	}

	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = null;
	}

}
