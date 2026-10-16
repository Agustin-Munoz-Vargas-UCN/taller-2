package taller02;

public class Choripan extends Comida {

	private float largo;
	private float ancho;
	
	public Choripan(Persona persona, String fecha, float largo, float ancho)
	{
		super(persona, fecha);
		this.largo = largo;
		this.ancho = ancho;
	}
	
	public float getLargo() {return largo;}
	public float getAncho() {return ancho;}
//	public void setLargo(float largo) {this.largo = largo;}
//	public void setAncho(float ancho) {this.ancho = ancho;}
	
	@Override
	public String toString() {return "Choripan [persona = " + getPersona().getNombre() + "  fecha = " + getFecha() + "  largo = " + largo + "  ancho = " + ancho + "]";}
}
