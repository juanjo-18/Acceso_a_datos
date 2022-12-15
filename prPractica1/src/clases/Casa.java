package clases;

import java.util.ArrayList;

public class Casa {
	
	private int id;
	private String calle;
	private int numero;
	private String municipio;
	private String provincia;
	private ArrayList<Mueble> muebles;
	
	
	public Casa( String calle, int numero, String municipio, String provincia) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.municipio = municipio;
		this.provincia = provincia;
	}
	public Casa(int id,String calle, int numero, String municipio, String provincia) {
		super();
		this.id=id;
		this.calle = calle;
		this.numero = numero;
		this.municipio = municipio;
		this.provincia = provincia;
	}


	public Casa(int id, String calle, int numero, String municipio, String provincia, ArrayList<Mueble> muebles) {
		super();
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.municipio = municipio;
		this.provincia = provincia;
		this.muebles = muebles;
	}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public ArrayList<Mueble> getMuebles() {
		return muebles;
	}


	public void setMuebles(ArrayList<Mueble> muebles) {
		this.muebles = muebles;
	}


	@Override
	public String toString() {
		return "Casa id=" + id + ", calle=" + calle + ", numero=" + numero + ", municipio=" + municipio
				+ ", provincia=" + provincia;
	}
	
	
	
	
	
}
