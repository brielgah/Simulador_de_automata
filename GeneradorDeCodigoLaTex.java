/**
 * @author Esteban Olmedo Ramírez
 */
public class GeneradorDeCodigoLaTex
{
	private ArchivoTex archivo;
	private AutomataFinitoAPila automataAPila;
	private AutomataFinitoDeterminista afd;
	private AutomataFinitoNoDeterminista afn;
	private AutomataFinitoNoDeterministaEpsilon afn_epsilon;

	public GeneradorDeCodigoLaTex()
	{
		this(null, null, null, null, null);
	}
	public GeneradorDeCodigoLaTex(ArchivoTex archivo,
			AutomataFinitoAPila automataAPila,
			AutomataFinitoDeterminista afd, 
			AutomataFinitoNoDeterminista afn,
			AutomataFinitoNoDeterministaEpsilon afn_epsilon)
	{
		this.archivo = archivo;
		this.automataAPila = automataAPila;
		this.afd = afd;
		this.afn = afn;
		this.afn_epsilon = afn_epsilon;
	}
	public GeneradorDeCodigoLaTex(GeneradorDeCodigoLaTex generador)
	{
		this(generador.archivo, generador.automataAPila, 
				generador.afd, generador.afn, generador.afn_epsilon);
	}
	public void destruir()
	{
		if(archivo != null)
		{
			archivo.destruir();
			archivo = null;
		}
		if(automataAPila != null)
		{
			automataAPila.destruir();
			automataAPila = null;
		}
		if(afd != null)
		{
			afd.destruir();
			afd = null;
		}
		if(afn != null)
		{
			afn.destruir();
			afn = null;
		}
		if(afn_epsilon != null)
		{
			afn_epsilon.destruir();
			afn_epsilon = null;
		}
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof GeneradorDeCodigoLaTex)) {return false;}
		GeneradorDeCodigoLaTex generador = (GeneradorDeCodigoLaTex)obj;
		return archivo.equals(generador.archivo) && 
			automataAPila.equals(generador.automataAPila) &&
			afd.equals(generador.afd) &&
			afn.equals(generador.afn) &&
			afn_epsilon.equals(generador.afn_epsilon);
	}
	@Override
	public String toString()
	{
		return "Archivo:\n" + archivo.toString() + "\n" +
			"Automata a pila:\n" + automataAPila.toString() + "\n" +
			"Automata finito determinista:\n" + afd.toString() + "\n" +
			"Automata finito no determinista:\n" + afn.toString() + "\n" +
			"Automata finito no determinista epsilon:\n" + afn_epsilon.toString() + "\n";
	}
	public boolean verificar(AutomataFinito automata)
	{
		if(automata != null && automata.getNumeroDeEstados() > 0)
			return true;
		return false;
	}
	public boolean verificar(AutomataFinitoDeterminista afd)
	{
		return verificar((AutomataFinito)afd) &&
			afd.getTablaDeTransiciones().size()==afd.getNumeroDeEstados();
	}
	public boolean verificar(AutomataFinitoNoDeterminista afn)
	{
		return verificar((AutomataFinito)afn) &&
			afn.getTablaDeTransiciones().size()==afn.getNumeroDeEstados();
	}
	public boolean verificar(AutomataFinitoNoDeterministaEpsilon afn_e)
	{
		return verificar((AutomataFinitoNoDeterminista)afn_e);
	}
	public boolean verificar(AutomataFinitoAPila ap)
	{
		return verificar((AutomataFinito)ap);
	}
}
