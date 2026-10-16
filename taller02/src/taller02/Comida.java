package taller02;

public class Comida {
	
	protected Persona persona;
	protected String fecha;
		
	public Comida(Persona persona, String fecha)
	{
		super();
		this.persona = persona;
		this.fecha = fecha;
	}
		
	public String toString() {return "Comida [persona = " + persona.getNombre() + "  fecha = " + fecha + "]";}
	public Persona getPersona() {return persona;}
	public String  getFecha()   {return fecha;}
}
