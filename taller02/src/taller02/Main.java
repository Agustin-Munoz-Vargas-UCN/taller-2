//Agustín Muñoz Vargas - 22.387.587-4 - ICCI
package taller02;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class Main {
	
	public static boolean pruebaDeFechasAbsurdas(String[] arregloFechas)
	{
		try 
		{
			System.out.println(Integer.parseInt(arregloFechas[2]) + " " + Integer.parseInt(arregloFechas[1]) + " " + Integer.parseInt(arregloFechas[0]));
			if (arregloFechas.length != 3) {System.out.println("[!] Fecha escrita de forma incorrecta"); return false;}
			if (Integer.parseInt(arregloFechas[2]) <  1) {System.out.println("[!] Dia fuera de rango"); return false;}
			if (Integer.parseInt(arregloFechas[2]) > 31) {System.out.println("[!] Dia fuera de rango"); return false;}
			if (Integer.parseInt(arregloFechas[1]) <  1) {System.out.println("[!] Mes fuera de rango"); return false;}
			if (Integer.parseInt(arregloFechas[1]) > 12) {System.out.println("[!] Mes fuera de rango"); return false;}
			if ((Integer.parseInt(arregloFechas[1]) == 2 && Integer.parseInt(arregloFechas[2]) == 31)) {System.out.println("[!] Jeje... (31 de febrero)"); return false;}
			return true;
		}
		catch (Exception e)
		{
			System.out.println("[!] Texto o numero decimal presente");
			return false;
		}
	}

	private static void GuardarTXT (String filePath, Comida[] arregloComida)
	{
		try {
			FileWriter writerAlumnos = new FileWriter(filePath);
			for (int i = 0; i < 200; i++)
			{
				if (arregloComida[i] == null) {
					continue;
				} else {
					if (arregloComida[i] instanceof Choripan)
					{
						String soporteMoral = "Choripan;" + arregloComida[i].getPersona().getNombre() + ";" + ((Choripan)arregloComida[i]).getLargo() + ";" + ((Choripan)arregloComida[i]).getAncho() + ";" + arregloComida[i].getFecha() + "\n";
						writerAlumnos.write(soporteMoral);
					}
					if (arregloComida[i] instanceof Terremoto)
					{
						String soporteMoral = "Terremoto;" + arregloComida[i].getPersona().getNombre() + ";" + ((Terremoto)arregloComida[i]).getLitros() + ";" + arregloComida[i].getFecha() + "\n";
						writerAlumnos.write(soporteMoral);
					}
				}
			}
			writerAlumnos.close();
		} catch (Exception e) {
			System.out.println("[!] Ruta de escritura no encontrada");
		}
	}

	private static Integer ContarPersona(Persona[] arregloPersona)
	{
		int largo = 200;
		int auxContador = 0;
		for (int i = 0; i < largo; i++)
		{
			if (arregloPersona[i] != null)
			{
				auxContador++;
			}
		}
		return auxContador;
	}
	
	private static Integer ContarComida(Comida[] arregloComida)
	{
		int largo = 200;
		int auxContador = 0;
		for (int i = 0; i < largo; i++)
		{
			if (arregloComida[i] != null)
			{
				auxContador++;
			}
		}
		return auxContador;
	}

	private static Persona[] ReadPersona(String filePath)
	{
		Integer largo  = 200;
		File arch = new File(filePath);
		Persona[] arregloPersona = new Persona[200];
		String archLinePart;
		try
		{
			Scanner archLine = new Scanner(arch);
			for (int i = 0; i < largo; i++)
			{
				if (archLine.hasNextLine() == false) {break;}
				archLinePart = archLine.nextLine();
//				System.out.println(i + " " + archLinePart + " " + archLine.hasNext());
				if (archLinePart != null)
				{
					arregloPersona[i] = new Persona(archLinePart);
				}
			}
			archLine.close();
		}
		catch (Exception e)
		{
			System.out.println("[!] ReadPersona ERROR");
		}
		return arregloPersona;
	}

	private static Comida[] ReadComida(String filePath, Persona[] arregloPersona)
	{
		Integer contadorPersonas = ContarPersona(arregloPersona);
		String  divisor = ";";
		Integer largo  = 200;
		File    arch = new File(filePath);
		String[] archLinePart;
		Comida[] arregloComida = new Comida[200];
		try
		{
			Scanner archLine = new Scanner(arch);
			for (int i = 0; i < largo; i++)
			{
				if (archLine.hasNextLine() == false) {break;}
				archLinePart = archLine.nextLine().split(divisor);
//				System.out.println(i + " " + archLinePart[0] + " " + archLinePart[1] + " " + archLine.hasNext());
				if (archLinePart[0] != null)
				{
					Persona auxPersona = null;
					for (int j = 0; j < contadorPersonas; j++)
					{
						if (archLinePart[1].equalsIgnoreCase(arregloPersona[j].getNombre()))
						{
							auxPersona = arregloPersona[j];
							break;
						}
					}
					switch(archLinePart[0].toLowerCase())
					{
						case "choripan":
							if (auxPersona != null)
							{
//								System.out.println("choripan");
								arregloComida[i] = new Choripan(auxPersona,archLinePart[4],Float.parseFloat(archLinePart[2]),Float.parseFloat(archLinePart[3]));
							}
							break;
						case "terremoto":
							if (auxPersona != null)
							{
//								System.out.println("terremoto");
								arregloComida[i] = new Terremoto(auxPersona,archLinePart[3],Float.parseFloat(archLinePart[2]));
							}
							break;
					}
				}
			}
			archLine.close();
		}
		catch (Exception e)
		{
			System.out.println("[!] ReadComida ERROR");
		}
		return arregloComida;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Persona[] arregloPersonas = ReadPersona("src/textos/Participantes.txt");
		Comida[]  arregloComida = ReadComida("src/textos/Consumido.txt", arregloPersonas);
		String rutaDeGuardado = "src/textos/Consumido.txt";

		for (int i = 0; i < ContarPersona(arregloPersonas); i++)
		{
			arregloPersonas[i].updateNumeros(arregloComida,arregloPersonas[i]);
//			arregloPersonas[i].mostrarFechas();
		}
		while (true)
			{
			try
			{
				Scanner scan = new Scanner(System.in);
				
				int inputIntegerAA;

				System.out.println("============================================");
				System.out.println("           LA FONDA DEL PAPU");
				System.out.println("============================================");
				System.out.println("  Registros cargados: " + ContarComida(arregloComida));
				System.out.println("--------------------------------------------");
				System.out.println("  [1] Ver registros");
				System.out.println("  [2] Actualizar registros");
				System.out.println("  [3] Calcular metricas");
				System.out.println("  [4] Salir");
				System.out.println("============================================");
				System.out.print  ("Seleccione una opcion: ");
				inputIntegerAA = scan.nextInt();
				switch (inputIntegerAA)
				{
					case 1:
						System.out.println("============ [1] VER REGISTROS =============");
						System.out.println("  [1] Choripanes");
						System.out.println("  [2] Terremotos");
						System.out.println("  [3] Volver al menu principal");
						System.out.println("============================================");
						System.out.print  ("Seleccione una opcion: ");
						inputIntegerAA = scan.nextInt();
						switch (inputIntegerAA)
						{
							case 1:
								System.out.println("=============== CHORIPANES =================");
								System.out.println("  #    Consumidor         Fecha    Largo  Ancho    KCal");
								for (int i = 0; i < ContarComida(arregloComida); i++)
								{if (arregloComida[i] instanceof Choripan) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%8.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Choripan) arregloComida[i]).getLargo(),((Choripan) arregloComida[i]).getAncho(),(((Choripan) arregloComida[i]).getLargo()*((Choripan) arregloComida[i]).getAncho())/3);}}
								//
								//
								System.out.println("--------------------------------------------------");
								int auxO1O1 = 0;
								for (int i = 0; i < ContarComida(arregloComida); i++)
								{if (arregloComida[i] instanceof Choripan) {auxO1O1++;}}
								System.out.println("  Total: " + auxO1O1 + " choripanes registrados");
								break;
							case 2:
								System.out.println("=============== TERREMOTOS =================");
								System.out.println("  #    Consumidor         Fecha   Litros   KCal");
								for (int i = 0; i < ContarComida(arregloComida); i++)
								{if (arregloComida[i] instanceof Terremoto) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Terremoto) arregloComida[i]).getLitros(),(((Terremoto) arregloComida[i]).getLitros()*10)/3);}}
								//
								//
								System.out.println("--------------------------------------------------");
								int auxO1O2 = 0;
								for (int i = 0; i < ContarComida(arregloComida); i++)
								{if (arregloComida[i] instanceof Terremoto) {auxO1O2++;}}
								System.out.println("  Total: " + auxO1O2 + " terremotos registrados");
								break;
							case 3:
								System.out.println("--------------------------------------------------");
								System.out.println("[!] Retornando...");
								break;
							default:
								System.out.println("--------------------------------------------------");
								System.out.println("[!] Valor fuera de rango, retornando...");
								break;
						}
						break;
					case 2:
						System.out.println("========== [2] ACTUALIZAR REGISTROS ========");
						System.out.println("  [1] Agregar");
						System.out.println("  [2] Editar");
						System.out.println("  [3] Eliminar");
						System.out.println("  [4] Volver al menu principal");
						System.out.println("============================================");
						System.out.print  ("Seleccione una opcion: ");
						inputIntegerAA = scan.nextInt();
						if (ContarComida(arregloComida) >= 200) {System.out.println("[!] Limite de 200 entradas, ya no se puede agregar más, retornando...");break;}
						switch (inputIntegerAA)
						{
							case 1:
								System.out.println("[1] Tipo de registro a añadir:");
								System.out.println("  [1] Choripan");
								System.out.println("  [2] Terremoto");
								System.out.println("  [3] Cancelar");
								System.out.print  ("Seleccione una opcion: ");
								inputIntegerAA = scan.nextInt();
								switch (inputIntegerAA)
								{
									case 1:
										float auxLargo;
										float auxAncho;
										String[] auxfechaA;
										String   auxfechaB;
										System.out.println("Consumidores disponibles:");
										for (int i = 0; i < ContarPersona(arregloPersonas); i++) {System.out.println("[" + i + "] " + arregloPersonas[i].getNombre());}
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((inputIntegerAA >= 0) && (inputIntegerAA <= ContarPersona(arregloPersonas)))
										{
											if (arregloPersonas[inputIntegerAA] == null)
											{
												System.out.println("[!] Integrante no encontrado, retornando...");
												break;
											}
											System.out.println("Largo del choripan (cm): ");
											auxLargo = scan.nextFloat();
											if (auxLargo < 0) {auxLargo = auxLargo*(-1);}
											System.out.println("Ancho del choripan (cm): ");
											auxAncho = scan.nextFloat();
											if (auxAncho < 0) {auxAncho = auxAncho*(-1);}
											System.out.println("Fecha (YYYY-MM-DD): ");
											auxfechaB = scan.next();
											auxfechaA = auxfechaB.split("-");
											
											if (pruebaDeFechasAbsurdas(auxfechaA) == true)
											{
												System.out.printf("[1] ¿Confirma? (%.2f %.2f %s )%n",auxLargo ,auxAncho ,auxfechaB);
												System.out.println("  [1] Si");
												System.out.println("  [2] No");
												System.out.print  ("Seleccione una opcion: ");
												int inputIntegerAB = scan.nextInt();
												switch (inputIntegerAB)
												{
													case 1:
														System.out.println("[!] Guardando...");
														arregloComida[ContarComida(arregloComida)] = new Choripan(arregloPersonas[inputIntegerAA],auxfechaB,auxLargo,auxAncho);
														arregloPersonas[inputIntegerAA].updateNumeros(arregloComida, arregloPersonas[inputIntegerAA]);
														GuardarTXT(rutaDeGuardado,arregloComida);
														break;
													case 2:
														System.out.println("[!] Retornando...");
														break;
													default:
														System.out.println("[!] Valor fuera de rango, retornando...");
														break;
												}
											}
											else {System.out.println("[!] El formato de la fecha es incorrecto, retornando");}
										}
										else{System.out.println("[!] Integrante no encontrado, retornando....");}
										break;
									case 2:
										float auxLitro;
										String[] auxfechaC;
										String   auxfechaD;
										System.out.println("Consumidores disponibles:");
										for (int i = 0; i < ContarPersona(arregloPersonas); i++) {System.out.println("[" + i + "] " + arregloPersonas[i].getNombre());}
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((inputIntegerAA >= 0) && (inputIntegerAA <= ContarPersona(arregloPersonas)))
										{
											if (arregloPersonas[inputIntegerAA] == null)
											{
												System.out.println("[!] Integrante no encontrado, retornando...");
												break;
											}
											System.out.println("Litros (0.67): ");
											auxLitro = scan.nextFloat();
											if (auxLitro < 0) {auxLitro = auxLitro*(-1);}
											System.out.println("Fecha (YYYY-MM-DD): ");
											auxfechaD = scan.next();
											auxfechaC = auxfechaD.split("-");
											if (pruebaDeFechasAbsurdas(auxfechaC) == true)
											{
												System.out.printf("[2] ¿Confirma? (%.2f %s)%n",auxLitro ,auxfechaD);
												System.out.println("  [1] Si");
												System.out.println("  [2] No");
												System.out.print  ("Seleccione una opcion: ");
												int inputIntegerAB = scan.nextInt();
												switch (inputIntegerAB)
												{
													case 1:
														System.out.println("[!] Guardando...");
														arregloComida[ContarComida(arregloComida)] = new Terremoto(arregloPersonas[inputIntegerAA],auxfechaD,auxLitro);
														arregloPersonas[inputIntegerAA].updateNumeros(arregloComida, arregloPersonas[inputIntegerAA]);
														GuardarTXT(rutaDeGuardado,arregloComida);
														break;
													case 2:
														System.out.println("[!] Retornando...");
														break;
													default:
														System.out.println("[!] Valor fuera de rango, retornando...");
														break;
												}
											}
											else {System.out.println("[!] El formato de la fecha es incorrecto, retornando");}
										}
										else{System.out.println("[!] Integrante no encontrado, retornando....");}
										break;
									case 3:
										System.out.println("[!] Retornando...");
										break;
									default:
										System.out.println("[!] Valor fuera de rango, retornando...");
										break;
								}
								break;
							case 2:
								System.out.println("[2] Tipo de registro a editar:");
								System.out.println("  [1] Choripan");
								System.out.println("  [2] Terremoto");
								System.out.println("  [3] Cancelar");
								System.out.print  ("Seleccione una opcion: ");
								inputIntegerAA = scan.nextInt();
								switch (inputIntegerAA)
								{
									case 1:
										float auxLargo;
										float auxAncho;
										String[] auxfechaA;
										String   auxfechaB;
										System.out.println("=============== CHORIPANES =================");
										System.out.println("  #    Consumidor         Fecha    Largo  Ancho    KCal");
										for (int i = 0; i < ContarComida(arregloComida); i++)
										{if (arregloComida[i] instanceof Choripan) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%8.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Choripan) arregloComida[i]).getLargo(),((Choripan) arregloComida[i]).getAncho(),(((Choripan) arregloComida[i]).getLargo()*((Choripan) arregloComida[i]).getAncho())/3);}}
										//
										//
										System.out.println("--------------------------------------------------");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((arregloComida[inputIntegerAA] instanceof Choripan) == false) {System.out.println("[!] [Choripan] no encontrado, retornando..."); break;}
										System.out.println("Largo del choripan (cm): ");
										auxLargo = scan.nextFloat();
										if (auxLargo < 0) {auxLargo = auxLargo*(-1);}
										System.out.println("Ancho del choripan (cm): ");
										auxAncho = scan.nextFloat();
										if (auxAncho < 0) {auxAncho = auxAncho*(-1);}
										System.out.println("Fecha (YYYY-MM-DD): ");
										auxfechaB = scan.next();
										auxfechaA = auxfechaB.split("-");
										if (pruebaDeFechasAbsurdas(auxfechaA) == true)
										{
											System.out.printf("[1] ¿Confirma? (%.2f %.2f %s )%n",auxLargo ,auxAncho ,auxfechaB);
											System.out.println("  [1] Si");
											System.out.println("  [2] No");
											System.out.print  ("Seleccione una opcion: ");
											int inputIntegerAB = scan.nextInt();
											switch (inputIntegerAB)
											{
												case 1:
													System.out.println("[!] Guardando...");
													arregloComida[inputIntegerAA] = new Choripan (arregloComida[inputIntegerAA].getPersona(),auxfechaB,auxLargo,auxAncho);
													GuardarTXT(rutaDeGuardado,arregloComida);
													break;
												case 2:
													System.out.println("[!] Retornando...");
													break;
												default:
													System.out.println("[!] Valor fuera de rango, retornando...");
													break;
											}
										}
										else {System.out.println("[!] El formato de la fecha es incorrecto, retornando");}
										break;
									case 2:
										float auxLitro;
										String[] auxfechaC;
										String   auxfechaD;
										System.out.println("=============== TERREMOTOS =================");
										System.out.println("  #    Consumidor         Fecha   Litros   KCal");
										for (int i = 0; i < ContarComida(arregloComida); i++)
										{if (arregloComida[i] instanceof Terremoto) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Terremoto) arregloComida[i]).getLitros(),(((Terremoto) arregloComida[i]).getLitros()*10)/3);}}
										//
										//
										System.out.println("--------------------------------------------------");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((arregloComida[inputIntegerAA] instanceof Terremoto) == false) {System.out.println("[!] [Terremoto] no encontrado, retornando..."); break;}
										System.out.println("Litros (0.67): ");
										auxLitro = scan.nextFloat();
										if (auxLitro < 0) {auxLitro = auxLitro*(-1);}
										System.out.println("Fecha (YYYY-MM-DD): ");
										auxfechaD = scan.next();
										auxfechaC = auxfechaD.split("-");
										if (pruebaDeFechasAbsurdas(auxfechaC) == true)
										{
											System.out.printf("[2] ¿Confirma? (%.2f %s)%n",auxLitro ,auxfechaD);
											System.out.println("  [1] Si");
											System.out.println("  [2] No");
											System.out.print  ("Seleccione una opcion: ");
											int inputIntegerAB = scan.nextInt();
											switch (inputIntegerAB)
											{
												case 1:
													System.out.println("[!] Guardando...");
													arregloComida[inputIntegerAA] = new Terremoto (arregloComida[inputIntegerAA].getPersona(),auxfechaD,auxLitro);
													GuardarTXT(rutaDeGuardado,arregloComida);
													break;
												case 2:
													System.out.println("[!] Retornando...");
													break;
												default:
													System.out.println("[!] Valor fuera de rango, retornando...");
													break;
											}
										}
										else {System.out.println("[!] El formato de la fecha es incorrecto, retornando");}
										break;
									case 3:
										System.out.println("[!] Retornando...");
										break;
									default:
										System.out.println("[!] Valor fuera de rango, retornando...");
										break;
								}
								break;
							case 3:
								int inputIntegerAB;
								System.out.println("[3] Tipo de registro a eliminar:");
								System.out.println("  [1] Choripan");
								System.out.println("  [2] Terremoto");
								System.out.println("  [3] Cancelar");
								System.out.print  ("Seleccione una opcion: ");
								inputIntegerAA = scan.nextInt();
								switch (inputIntegerAA)
								{
									case 1:
										System.out.println("=============== CHORIPANES =================");
										System.out.println("  #    Consumidor         Fecha    Largo  Ancho    KCal");
										for (int i = 0; i < ContarComida(arregloComida); i++)
										{if (arregloComida[i] instanceof Choripan) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%8.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Choripan) arregloComida[i]).getLargo(),((Choripan) arregloComida[i]).getAncho(),(((Choripan) arregloComida[i]).getLargo()*((Choripan) arregloComida[i]).getAncho())/3);}}
										//
										//
										System.out.println("--------------------------------------------------");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((arregloComida[inputIntegerAA] instanceof Choripan) == false) {System.out.println("[!] [Choripan] no encontrado, retornando..."); break;}
										System.out.printf("[1] ¿Confirma? (%s)%n",inputIntegerAA);
										System.out.println("  [1] Si");
										System.out.println("  [2] No");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAB = scan.nextInt();
										switch (inputIntegerAB)
										{
											case 1:
												System.out.println("[!] Guardando...");
												arregloComida[inputIntegerAA] = arregloComida[ContarComida(arregloComida)-1];
												arregloComida[ContarComida(arregloComida)-1] = null;
												System.out.printf("[!] Entrada %s fue eliminada%n", inputIntegerAA);
												GuardarTXT(rutaDeGuardado,arregloComida);
												break;
											case 2:
												System.out.println("[!] Retornando...");
												break;
											default:
												System.out.println("[!] Valor fuera de rango, retornando...");
												break;
										}
										break;
									case 2:
										System.out.println("=============== TERREMOTOS =================");
										System.out.println("  #    Consumidor         Fecha   Litros   KCal");
										for (int i = 0; i < ContarComida(arregloComida); i++)
										{if (arregloComida[i] instanceof Terremoto) {System.out.printf("  %-3s  %-14s%-12s%7.2f%7.2f%n", i,arregloComida[i].getPersona().getNombre(),arregloComida[i].getFecha(),((Terremoto) arregloComida[i]).getLitros(),(((Terremoto) arregloComida[i]).getLitros()*10)/3);}}
										//
										//
										System.out.println("--------------------------------------------------");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAA = scan.nextInt();
										if ((arregloComida[inputIntegerAA] instanceof Terremoto) == false) {System.out.println("[!] [Terremoto] no encontrado, retornando..."); break;}
										System.out.printf("[2] ¿Confirma? (%s)%n",inputIntegerAA);
										System.out.println("  [1] Si");
										System.out.println("  [2] No");
										System.out.print  ("Seleccione una opcion: ");
										inputIntegerAB = scan.nextInt();
										switch (inputIntegerAB)
										{
											case 1:
												System.out.println("[!] Guardando...");
												arregloComida[inputIntegerAA] = arregloComida[ContarComida(arregloComida)-1];
												arregloComida[ContarComida(arregloComida)-1] = null;
												System.out.printf("[!] Entrada %s fue eliminada%n", inputIntegerAA);
												GuardarTXT(rutaDeGuardado,arregloComida);
												break;
											case 2:
												System.out.println("[!] Retornando...");
												break;
											default:
												System.out.println("[!] Valor fuera de rango, retornando...");
												break;
										}
										break;
								}
								break;
							case 4:
								System.out.println("[!] Retornando...");
								break;
							default:
								System.out.println("[!] Valor fuera de rango, retornando...");
								break;
						}
						break;
					case 3:
						System.out.println("============ [3] CALCULAR METRICAS =========");
						System.out.println("  [1] Choripanes comidos en total");
						System.out.println("  [2] Terremotos tomados en total");
						System.out.println("  [3] Choripanes comidos por usuario");
						System.out.println("  [4] Terremotos tomados por usuario");
						System.out.println("  [5] KCal consumidas por usuario");
						System.out.println("  [6] Dias ebrio por usuario");
						System.out.println("  [7] Volver al menu principal");
						System.out.println("============================================");
						System.out.println("Seleccione una opcion: ");
						inputIntegerAA = scan.nextInt();
						switch (inputIntegerAA)
						{
							case 1:
								int aux0301 = 0;
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {aux0301 = aux0301 + arregloPersonas[i].getNumeroChoripan();}
								System.out.println("[1] Total de choripanes comidos: " + aux0301);
								break;
							case 2:
								int aux0302A = 0;
								float aux0302B = 0;
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {aux0302A = aux0302A + arregloPersonas[i].getNumeroTerremoto(); aux0302B = aux0302B + arregloPersonas[i].getLitros();}
								System.out.println("[2] Total de terremotos tomados: " + aux0302A + " (" + aux0302B + " litros en total)");
								break;
							case 3:
								System.out.println("======== [3] CHORIPANES POR USUARIO ========");
								System.out.println("  Usuario       Cantidad");
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {System.out.printf("  %-14s%8s%n",arregloPersonas[i].getNombre(),arregloPersonas[i].getNumeroChoripan());}
								//System.out.println("============================================");
								break;
							case 4:
								System.out.println("======== [4] TERREMOTOS POR USUARIO ========");
								System.out.println("  Usuario       Cantidad    Litros");
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {System.out.printf("  %-14s%8s%10s L %n",arregloPersonas[i].getNombre(),arregloPersonas[i].getNumeroTerremoto(),arregloPersonas[i].getLitros());}
								//System.out.println("============================================");
								break;
							case 5:
								System.out.println("========== [5] KCAL POR USUARIO  ===========");
								System.out.println("  Usuario       Choripan  Terremoto     Total");
								float auxKCAL = 0;
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {System.out.printf("  %-14s%8.2f%10.2f%10.2f%n",arregloPersonas[i].getNombre(),arregloPersonas[i].getKCALChoripan(),(arregloPersonas[i].getKCAL() - arregloPersonas[i].getKCALChoripan()),arregloPersonas[i].getKCAL()); auxKCAL = auxKCAL + arregloPersonas[i].getKCAL();}
								System.out.printf("--------------------------------------------\n Consumo total de la fonda: %.2f kcal%n", auxKCAL);
								//System.out.println("============================================");
								break;
							case 6:
								System.out.println("======== [6] DIAS EBRIO POR USUARIO ========");
								System.out.println("  Usuario             Fecha  Litros");
								for (int i = 0; i < ContarPersona(arregloPersonas); i++) {arregloPersonas[i].mostrarDiasEbios();}
								//System.out.println("============================================");
								break;
							case 7:
								System.out.println("Retornando...");
								break;
							default:
								System.out.println("[!] Valor fuera de rango, retornando...");
								break;
							}
						break;
					default:
						System.out.println("[!] Valor fuera de rango, retornando...");
						break;
				}
				System.out.println("============================================");
				System.out.println("");
				System.out.println("Presione ENTER para continuar...");
				scan.nextLine();
				scan.nextLine();
				System.out.println("");
				
				//scan.close();
			}catch (Exception InputMismatchxception)
			{
				System.out.println("[!] El valor presente no tiene el formato correcto, retornando...");
			}
		}
	}
}
