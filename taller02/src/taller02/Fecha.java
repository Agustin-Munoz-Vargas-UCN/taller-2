package taller02;

public class Fecha {
	
	private int año;
	private int mes;
	private int dia;
	private float litros;
	
	public Fecha(int año, int mes, int dia, float litros) 
	{
		super();
		this.año = año;
		this.mes = mes;
		this.dia = dia;
		this.litros = litros;
	}
	
	public void addLitros(float extra) {litros = litros + extra;}
	public int getAño() {return año;}
	public int getMes() {return mes;}
	public int getDia() {return dia;}
	public float getLitros() {return litros;}

	@Override
	public String toString() {return "" + año + "/" + mes + "/" + dia + " litros = " + litros + "";}
	public String toStringEstamdar() {return "" + año + "/" + mes + "/" + dia;}
}
