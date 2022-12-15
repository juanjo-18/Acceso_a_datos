package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Casa;
import clases.Mueble;

public class CasaDao extends ObjetoDao implements InterfazDao<Casa> {
	private static Connection connection;

	public CasaDao() {

	}

	public ArrayList<Casa> buscarTodos() {
		ArrayList<Casa> casas = new ArrayList<>();
		connection = openConnection();
		String query = "SELECT * FROM casas";
		PreparedStatement ps;
		Casa casa;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ArrayList<Mueble> muebles = new ArrayList<Mueble>();
				String query_mueble = "select * from muebles where casa_id=?";
				PreparedStatement ps_mueble = connection.prepareStatement(query_mueble);
				ps_mueble.setInt(1, rs.getInt("id"));
				ResultSet rs_mueble = ps_mueble.executeQuery();

				casa = new Casa(rs.getInt("id"), rs.getString("calle"), rs.getInt("numero"), rs.getString("municipio"),
						rs.getString("municipio"), null);

				while (rs_mueble.next()) {
					Mueble mueble = new Mueble(rs_mueble.getInt("id"), rs_mueble.getString("nombre"),
							rs_mueble.getInt("peso"), rs_mueble.getInt("altura"), rs_mueble.getInt("anchura"));
					muebles.add(mueble);
				}
				casa.setMuebles(muebles);
				casas.add(casa);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return casas;
	}

	public ArrayList<Mueble> obtenerCasas(Casa casa) {
		ArrayList<Mueble> muebles = new ArrayList<>();
		connection = openConnection();
		closeConnection();
		String query = "Select *FROM muebles WHERE casa_id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, casa.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Mueble mueble = new Mueble(rs.getInt("id"), rs.getString("nombre"), rs.getInt("peso"),
						rs.getInt("altura"), rs.getInt("anchura"), casa);
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
	public Casa buscarPorId(int id) {
		// TODO Auto-generated method stub
		connection = openConnection();

		Casa casa = null;

		String query = "select * from casas where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				casa = new Casa(rs.getInt("id"), rs.getString("calle"), rs.getInt("numero"), rs.getString("municipio"),
						rs.getString("provincia"), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();
		return casa;
	}

	@Override
	public void insertar(Casa casa) {
		// TODO Auto-generated method stub
		connection = openConnection();

		String query = "insert into casas (calle, numero, municipio,provincia)" + " values (?, ?, ?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, casa.getCalle());
			ps.setInt(2, casa.getNumero());
			ps.setString(3, casa.getMunicipio());
			ps.setString(4, casa.getProvincia());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();
	}

	@Override
	public void modificar(Casa casa) {
		// TODO Auto-generated method stub
		connection = openConnection();

		byte id = (byte) casa.getId();
		String calle = casa.getCalle();
		byte numero = (byte) casa.getNumero();
		String municipio = casa.getMunicipio();
		String provincia = casa.getProvincia();

		String query = "update casas set calle=?,numero=?,municipio=?,provincia=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, calle);
			ps.setByte(2, numero);
			ps.setString(3, municipio);
			ps.setString(4, provincia);
			ps.setInt(5, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	@Override
	public void borrarPorId(int i) {
		// TODO Auto-generated method stub

		MuebleDao muebleDao = new MuebleDao();
		muebleDao.borrarPorId(i);
		connection = openConnection();
		String query = "delete from casas where id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	public void borrarPorDireccion(String calle, int numero) {
		Casa casa = buscarPorDireccion(calle, numero);
		MuebleDao muebleDao = new MuebleDao();
		muebleDao.borrarPorId(casa.getId());

		connection = openConnection();
		String query = "delete from casas where calle=? and numero=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, calle);
			ps.setInt(2, numero);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}

	public Casa buscarPorDireccion(String calle, int numero) {
		// TODO Auto-generated method stub
		connection = openConnection();

		Casa casa = null;
		String query = "select * from casas where calle = ? and numero = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, calle);
			ps.setInt(2, numero);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				casa = new Casa(rs.getInt("id"), rs.getString("calle"), rs.getInt("numero"), rs.getString("municipio"),
						rs.getString("provincia"), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();
		return casa;
	}

	@Override
	public void borrarTodos() {
		// TODO Auto-generated method stub
		ArrayList<Casa> casas = buscarTodos();
		for (int i = 0; i < casas.size(); i++) {
			Casa casa = casas.get(i);
			MuebleDao muebleDao = new MuebleDao();
			muebleDao.borrarPorId(casa.getId());

			connection = openConnection();
			String query = "delete from casas where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, casa.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
	}
}
