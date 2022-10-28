package main;

import dao.SerieDao;
import prSeries.Serie;

public class Main {

	public static void main(String[] args) {
		
		Serie serie = new Serie("Los simpsos",7,"disney plus");
		SerieDao serieDao =new SerieDao();
		serieDao.insert(serie);
	}

}
