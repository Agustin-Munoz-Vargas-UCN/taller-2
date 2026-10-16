package taller02;

public class Persona {

	private String nombre;
	private int numeroChoripan;
	private int numeroTerremoto;
	private float numeroKCAL;
	private float numeroKCALChoripan;
	private float numeroLitros;
	private Fecha[] registroDias;
	
	public Persona(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String   getNombre() 		 {return nombre;}
	public int      getNumeroChoripan()  {return numeroChoripan;}
	public int      getNumeroTerremoto() {return numeroTerremoto;}
	public float    getKCAL()   		 {return numeroKCAL;}
	public float    getKCALChoripan()    {return numeroKCALChoripan;}
	public float    getLitros() 		 {return numeroLitros;}
	public Fecha[]  getRegistroDias() 	 {return registroDias;}

	@Override
	public String toString() {return "Persona [nombre = " + nombre + "  numeroChoripan = " + numeroChoripan + "  numeroTerremoto = " + numeroTerremoto + "  numeroKCAL = " + numeroKCAL + "]";}
	
	public void mostrarFechas()
	{
		for (int i = 0; i < 200; i++) 
		{
			if (registroDias[i] != null) 
			{
				System.out.println(nombre + " " + registroDias[i].toString());
			}
		}
	}
	
	public void mostrarDiasEbios()
	{
		int auxBorracho = 0;
		for (int i = 0; i < 200; i++) 
		{
			if (registroDias[i] != null) 
			{
				if (registroDias[i].getLitros() >= 2) 
				{
					System.out.printf("  %-11s%14s%8.2f L%n",nombre,registroDias[i].getAño() + "/" + registroDias[i].getMes() + "/" + registroDias[i].getDia(),registroDias[i].getLitros());
					auxBorracho++;
				}
			}
		}
		System.out.printf("  %-16s[TOTAL = %s] %n",nombre,auxBorracho);
	}
	
	public void updateNumeros (Comida[] arregloComida, Persona persona) 
	{
		numeroChoripan  = 0;
		numeroTerremoto = 0;
		numeroKCAL = 0;
		numeroLitros = 0;
		numeroKCALChoripan = 0;
		int auxDias = 0;
		registroDias = new Fecha[200];	
		for (int i = 0; i < 200; i++) 
		{
			if (arregloComida[i] != null)
			{
				if (arregloComida[i] instanceof Choripan && arregloComida[i].getPersona() == persona)
				{
					numeroChoripan++;
					numeroKCAL = numeroKCAL + ((((Choripan) arregloComida[i]).getLargo() * ((Choripan) arregloComida[i]).getAncho())/3);
					numeroKCALChoripan = numeroKCALChoripan + ((((Choripan) arregloComida[i]).getLargo() * ((Choripan) arregloComida[i]).getAncho())/3);
//					System.out.print("[C]");
				}
				if (arregloComida[i] instanceof Terremoto && arregloComida[i].getPersona() == persona)
				{
					int auxJ = 0;
					Boolean auxBool = false;
					for (int j = 0; j < 200; j++) 
					{
						if (registroDias[j] != null)
						{
							Boolean first = false;
							Boolean second = false;
							Boolean third = false;
							String[] auxFecha = arregloComida[i].getFecha().split("-");
							if (registroDias[j].getAño() == Integer.parseInt(auxFecha[0])) {first  = true; /*System.out.print("[F]");*/}
							if (registroDias[j].getMes() == Integer.parseInt(auxFecha[1])) {second = true; /*System.out.print("[S]");*/}
							if (registroDias[j].getDia() == Integer.parseInt(auxFecha[2])) {third  = true; /*System.out.print("[T]");*/}
							if (first && second && third)
							{auxBool = true; auxJ = j; break;}
						}
					}
					if (auxBool == false) 
					{
						String[] auxFecha = arregloComida[i].getFecha().split("-");
//						System.out.println(auxFecha[0] + auxFecha[1] + auxFecha[2]);
						registroDias[auxDias] = new Fecha(Integer.parseInt(auxFecha[0]),Integer.parseInt(auxFecha[1]),Integer.parseInt(auxFecha[2]),((Terremoto) arregloComida[i]).getLitros());
						auxDias++;
//						System.out.print("[FC]");
					}
					else if (auxBool == true)
					{
						registroDias[auxJ].addLitros(((Terremoto) arregloComida[i]).getLitros());
//						System.out.print("[LA]");
					}
					numeroTerremoto++;
					numeroLitros = numeroLitros + (((Terremoto) arregloComida[i]).getLitros());
					numeroKCAL = numeroKCAL + (((((Terremoto) arregloComida[i]).getLitros())*10)/3);
//					System.out.println("[T]");
				}
			}
		}
	}
}
