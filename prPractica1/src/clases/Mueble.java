package clases;

public class Mueble {
	private int id;
	private String nombre;
	private float peso;
	private float altura;
	private float anchura;
	private Casa casa;
	
	
	
	public Mueble(int id, String nombre, float peso, float altura, float anchura) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.anchura = anchura;
	}
	public Mueble( String nombre, float peso, float altura, float anchura) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.anchura = anchura;
	}
	public Mueble(int id,String nombre, float peso, float altura, float anchura, Casa casa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.anchura = anchura;
		this.casa = casa;
	}

	public Mueble(String nombre, float peso, float altura, float anchura, Casa casa) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.anchura = anchura;
		this.casa = casa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getPeso() {
		return peso;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}


	public float getAltura() {
		return altura;
	}


	public void setAltura(float altura) {
		this.altura = altura;
	}


	public float getAnchura() {
		return anchura;
	}


	public void setAnchura(float anchura) {
		this.anchura = anchura;
	}


	public Casa getCasa() {
		return casa;
	}


	public void setCasa(Casa casa) {
		this.casa = casa;
	}


	@Override
	public String toString() {
		return "Mueble id=" + id + ", nombre=" + nombre + ", peso=" + peso + ", altura=" + altura + ", anchura="
				+ anchura ;
	}
	
	
}
