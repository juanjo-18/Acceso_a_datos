package prZologicoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AnimalDAO {
	private static Connection connection;

	// borrar todos los animales
	public static void deleteAnimal() {
		connection = openConnection();
		String query = "delete from animales";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}

	// insertar un animal
	public static void insertarAnimal(Animal animal) {
		connection = openConnection();
		String query = "insert into animales(nombre,habitad,peso_aproximado) " + "values(?,?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, animal.getNombre());
			prepareStatement.setString(2, animal.getHabitat());
			prepareStatement.setDouble(3, animal.getPeso_aproximado());

			prepareStatement.executeUpdate();
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
