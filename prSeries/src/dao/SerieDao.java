package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import prSeries.Serie;
import prSeries.Temporada;

public class SerieDao extends ObjetoDao implements InterfazDao<Serie> {

	private static Connection connection;

	public SerieDao() {

	}

	@Override
	public ArrayList<Serie> buscarTodos() {
		ArrayList<Serie> series=new ArrayList<>();
		connection=openConnection();
		String query= "SELECT * FROM series";
		PreparedStatement ps;
		Serie serie ;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				 serie=new Serie(rs.getInt("id"),rs.getString("titulo"),rs.getInt("edad"),rs.getString("plataforma"),null);
				 series.add(serie);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Serie buscarPorId(int id) {
		// TODO Auto-generated method stub
		connection = openConnection();

		Serie serie = null;

		String query = "select * from series where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				serie = new Serie(rs.getInt("id"), rs.getString("titulo"), rs.getInt("edad"),
						rs.getString("plataforma"), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();

		return serie;
	}

	@Override
	public void insertar(Serie serie) {
		// TODO Auto-generated method stub
		connection = openConnection();

		String query = "insert into series (titulo, edad, plataforma)" + " values (?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
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

	@Override
	public void modificar(Serie t) {
		connection = openConnection();

		byte id=(byte) t.getId();
		String titulo = t.getTitulo();
		byte edad=(byte) t.getEdad();
		String plataforma=t.getPlataforma();
		
		String query = "update series set titulo=?,edad=?,plataforma=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,titulo);
			ps.setByte(2, edad);
			ps.setString(3, plataforma);
			ps.setInt(4, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public  ArrayList<Temporada> obtenerTemporadas(Serie serie){
		ArrayList<Temporada> temporadas= new ArrayList<>();
		connection =openConnection();
		closeConnection();
		String query ="Select *FROM temporadas WHERE serie_id=?";
		
		try {
			PreparedStatement ps =connection.prepareStatement(query);
			ps.setInt(1,serie.getId());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Temporada temporada=new Temporada(rs.getInt("id"),rs.getInt("num_temporada"),rs.getString("titulo"),serie);
				temporadas.add(temporada);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public void borrar(Serie t) {
		connection = openConnection();
		String query = "delete from series";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}

}
