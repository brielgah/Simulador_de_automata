package logica;
/**
 * @author Esteban Olmedo Ramírez
 */

import java.util.TreeMap;
import java.io.Serializable;
public abstract class AutomataFinito implements Serializable
{
	private int numeroDeEstados;
	private char[] alfabeto;
	private int[] estadosAceptacion;
	private TreeMap<Character,Integer> mapa;
	private String descripcion;

	public AutomataFinito()
	{
		this(0, null, null, null, null);
	}
	public AutomataFinito(int numeroDeEstados, char[] alfabeto,
			int[] estadosAceptacion, TreeMap<Character,Integer> mapa, String descripcion)
	{
		this.numeroDeEstados = numeroDeEstados;
		this.alfabeto = alfabeto;
		this.estadosAceptacion = estadosAceptacion;
		this.mapa = mapa;
		this.descripcion = descripcion;
	}
	public AutomataFinito(AutomataFinito automata)
	{
		this(automata.numeroDeEstados, automata.alfabeto, 
				automata.estadosAceptacion, automata.mapa, automata.descripcion);
	}
	public void destruir()
	{
		if(alfabeto != null)
			alfabeto = null;
		if(estadosAceptacion != null)
			estadosAceptacion = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof AutomataFinito)) return false;
		AutomataFinito automata = (AutomataFinito)obj;
		if(alfabeto.length != automata.alfabeto.length)
			return false;
		for(int i = 0; i < alfabeto.length; i++)
		{
			if(alfabeto[i] != automata.alfabeto[i])
					return false;
		}
		if(estadosAceptacion.length != automata.estadosAceptacion.length)
			return false;
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			if(estadosAceptacion[i] != automata.estadosAceptacion[i])
				return false;
		}
		return numeroDeEstados == automata.numeroDeEstados;
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += "Numero de estados: " + numeroDeEstados + "\n";
		retorno += "Alfabeto: {";
		for(int i = 0; i < alfabeto.length; i++)
		{
			retorno += i < (alfabeto.length - 1)? 
				alfabeto[i] + ", ": alfabeto[i] + "}";
		}
		retorno += "Estados de aceptacion: {";
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			retorno += i < (estadosAceptacion.length - 1)? 
				estadosAceptacion[i] + ", ": estadosAceptacion[i] + "}";
		}
		return retorno;
	}
	public boolean isAceptacion(int q)
	{
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			if(estadosAceptacion[i] == q)
				return true;
		}
		return false;
	}
	public int getNumeroSimbolo(char a)
	{
		Integer simbolo = mapa.get(a);
		if(simbolo != null)
			return simbolo;
		return -1;
	}
	public int getNumeroDeEstados()
	{
		return numeroDeEstados;
	}
	public int[] getEstadosDeAceptacion(){
		return estadosAceptacion;
	}
	public char[] getAlfabeto(){
		return alfabeto;
	}
        
 	public void setMapa(){
 		mapa = new TreeMap<>();
		for(int i=0; i<alfabeto.length; i++)
		{
			mapa.put(alfabeto[i], i);
		}
	}
	public void setNumeroDeEstados(int numeroDeEstados){
		this.numeroDeEstados = numeroDeEstados;
	}
	public void setAlfabeto(char[] alfabeto){
		if(alfabeto == null)
			System.out.println("ACHIS ACHIS");
		this.alfabeto = alfabeto;
	}
	public void setEstadosAceptacion(int[] estadosAceptacion){
		this.estadosAceptacion = estadosAceptacion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public TreeMap<Character, Integer> getMapa(){
		return mapa;
	}
	public boolean exist(char a)
	{
		return mapa.containsKey(a);
	}
	public char getSimbolo(int indice){
		if(indice < alfabeto.length)
			return alfabeto[indice];
		return '\u0000';
	}
	public String getDescripcion(){
		return descripcion;
	}
        
        public String getLenguaje()
        {
            StringBuffer cadena = new StringBuffer("ALFABETO ACEPTADO POR EL AUTOMATA: ");
            for(int x = 0; x < alfabeto.length;x++)
            {
                cadena.append(alfabeto[x]);
                if(x != alfabeto.length-1)
                    cadena.append(',');
            }
            return cadena.toString();
        }
        
	public abstract boolean evaluar(String cadena);
	public abstract String getTransiciones();
}
