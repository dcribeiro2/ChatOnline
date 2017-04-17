package br.univel.trabalho;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Server extends Remote {

	public static final String NOME_SERVICO = "JShare";

	public void registrarCliente(Cliente c) throws RemoteException;

	public void publicarListaArquivos(Cliente cliente, List<Arquivo> listaArq)
			throws RemoteException;

	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException;

	public byte[] baixarArquivo(Cliente baCliente, Arquivo baArquivo) throws RemoteException;

	public void desconectar(Cliente dCliente) throws RemoteException;

}
