package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IServer extends Remote {

	public static final String NOME_SERVICO = "JShare";

	public void registrarCliente(Cliente c) throws RemoteException;

	public void publicarListaArquivos(Cliente c, List<Arquivo> lista)
			throws RemoteException;

	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException;

	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException;

	public void desconectar(Cliente c) throws RemoteException;

//	public void entrarNoChat(String nome, IServer cliente) throws RemoteException;

//	void receberListaParticipantes(List<String> lista) throws RemoteException;
	
}
