package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Casa;
import clases.Mueble;

public class MuebleDao implements InterfazDao<Mueble> {

	private static Connection connection;

	public MuebleDao() {

	}

	@Override
	public ArrayList<Mueble> buscarTodos() {
		// TODO Auto-generated method stub
		ArrayList<Mueble> muebles = new ArrayList<>();
		connection = openConnection();
		String query = "SELECT * FROM muebles";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Mueble mueble = new Mueble(rs.getInt("id"), rs.getString("nombre"),
						rs.getInt("peso"), rs.getInt("altura"), rs.getInt("anchura"));
				muebles.add(mueble);
				muebles.add(mueble);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return muebles;
	}

	public ArrayList<Mueble> buscarTodos(Casa casa) {
		// TODO Auto-generated method stub
		connection = openConnection();
		ArrayList<Mueble> muebles=new ArrayList<Mueble>();
		Mueble mueble = null;
		String query = "select * from muebles where casa_id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, casa.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 mueble = new Mueble(rs.getInt("id"), rs.getString("nombre"),
						rs.getInt("peso"), rs.getInt("altura"), rs.getInt("anchura"));
				muebles.add(mueble);
				muebles.add(mueble);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return muebles;
	}

	@Override
	public Mueble buscarPorId(int id) {
		// TODO Auto-generated method stub
		connection = openConnection();
		Mueble mueble = null;
		String query = "select * from muebles where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				mueble = new Mueble(rs.getInt("id"), rs.getString("nombre"), rs.getInt("peso"), rs.getInt("altura"),
						rs.getInt("anchura"), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return mueble;
	}

	@Override
	public void insertar(Mueble mueble) {
		// TODO Auto-generated method stub
		connection = openConnection();

		String query = "insert into muebles (nombre, peso,altura,anchura,casa_id) values (?, ?, ?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, mueble.getNombre());
			ps.setFloat(2, mueble.getPeso());
			ps.setFloat(3, mueble.getAltura());
			ps.setFloat(4, mueble.getAnchura());
			ps.setInt(5, mueble.getCasa().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();
	}

	@Override
	public void modificar(Mueble mueble) {
		// TODO Auto-generated method stub
		connection = openConnection();

		byte id = (byte) mueble.getId();
		String nombre = mueble.getNombre();
		float peso = mueble.getPeso();
		float altura=mueble.getAltura();
		float anchura=mueble.getAnchura();
		byte casa_id=(byte) mueble.getCasa().getId();
		
		String query = "update muebles set nombre=?,peso=?,altura=?,anchura=?,casa_id=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nombre);
			ps.setFloat(2, peso);
			ps.setFloat(3, altura);
			ps.setFloat(4, anchura);
			ps.setByte(5,casa_id);
			ps.setInt(6, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();


	}

	public void borrarPorId(int id) {
		// TODO Auto-generated method stub
		connection = openConnection();

		String query = "Delete from muebles where casa_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	public void borrarPorMedidas(float peso, float altura, float anchura) {
		connection = openConnection();

		String query = "Delete from muebles where peso=? and altura=? and anchura=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setFloat(1, peso);
			ps.setFloat(2, altura);
			ps.setFloat(3, anchura);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}

	public Mueble buscarPorMedidas(float peso, float altura, float anchura) {
		connection = openConnection();
		Mueble mueble = null;
		String query = "select * from muebles where peso = ? and altura=? and anchura =?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setFloat(1, peso);
			ps.setFloat(2, altura);
			ps.setFloat(3, anchura);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				mueble = new Mueble(rs.getInt("id"), rs.getString("nombre"), rs.getInt("peso"), rs.getInt("altura"),
						rs.getInt("anchura"), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return mueble;
	}



	@Override
	public void borrarTodos() {
		// TODO Auto-generated method stub
		ArrayList<Mueble> muebles = buscarTodos();
		for (int i = 0; i < muebles.size(); i++) {
			Mueble mueble = muebles.get(i);

			connection = openConnection();
			String query = "delete from muebles where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, mueble.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}

	}
	
	private static Connection openConnection() {
		DatabaseConnection dbConnection = new DatabaseConnection();
		connection = dbConnection.getConnection();
		return connection;
	}

	private static void closeConnection() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
