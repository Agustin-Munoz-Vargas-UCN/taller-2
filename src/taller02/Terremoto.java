package taller02;

public class Terremoto extends Comida {

	private float litros;
	
	public Terremoto(Persona persona, String fecha, float litros)
	{
		super(persona, fecha);
		this.litros = litros;
	}
	
	public float getLitros() {return litros;}
	
	@Override
	public String toString() {return "Terremoto [persona = " + persona.getNombre() + "  fecha = " + fecha + "  litros = " + litros + "]";}
}
