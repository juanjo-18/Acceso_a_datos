package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import prSeries.Temporada;

public class TemporadaDao implements Dao<Temporada> {

	@Override
	public ArrayList<Temporada> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Temporada buscarPorId(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Temporada t) {
		// TODO Auto-generated method stub
		 connection = openConnection();
		String query="insert into temporadas(num_temporada,titulo, serie_id values (?,?,?)";
		
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, t.getNum_temporada());
			ps.setString(2, t.getTitulo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		
		
	}

	@Override
	public void modificar(Temporada t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Temporada t) {
		// TODO Auto-generated method stub
		
	}

}
