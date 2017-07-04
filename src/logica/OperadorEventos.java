package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Evento;
import database.EventoDAO;
import exceptions.RoomsAllocationException;

/**
 * Classe de gerenciamento de eventos
 * @author Thalles
 *
 */
public class OperadorEventos {

	private static EventoDAO EDao = new EventoDAO();
	
	/**
	 * M�todo de salvamento de um evento na database
	 * @param evento Objeto evento a ser salvo
	 * @throws Exception Uma exce��o pode ser lan�ada caso o evento n�o respeite as regras de cria��o de eventos
	 */
	public static void salvar(Evento evento) throws Exception{
		if(evento.getRepeticoes()<0){
			throw new Exception("Atributo invalido.");
		}else if(evento.getArea().equalsIgnoreCase("")){
			throw new Exception("Atributo invalido.");
		}else if(evento.getContato().equalsIgnoreCase("")){
			throw new Exception("Atributo invalido.");
		}else if(!datasValidas(evento.getInicio(),evento.getFim())){
			throw new RoomsAllocationException("Intervalo de datas invalido.");
		}else{
			EDao.salvar(evento);
		}
	}
	
	/**
	 * M�todo de exclus�o de eventos 
	 * @param idEvento Identificador �nico do evento a ser exclu�do
	 * @throws Exception Uma exce��o pode ser lan�ada caso o evento n�o exista
	 */
	public static void excluir(String idEvento) throws Exception{
		if(verificaExistencia(idEvento)){
			if(OperadorAlocacoes.isAlocado(idEvento)){
				OperadorAlocacoes.desalocar(idEvento);
				EDao.excluir(idEvento);
			}else{
				EDao.excluir(idEvento);
			}
		}else{
			throw new RoomsAllocationException("Evento nao existe.");
		}
		
	}

	/**
	 * M�todo de resgate dos eventos cadastrados na database
	 * @return Lista dos eventos cadastrados
	 * @throws Exception Uma exce��o pode ser lan�ada caso a database n�o exista ou caso esteja vazia
	 */
	public static ObservableList<Evento> getEventos() throws Exception{
		return EDao.getEventos();
	}

	/**
	 * M�todo de resgate de um evento espec�fico mediante seu identificador �nico
	 * @param idEvento Identificador �nico do evento
	 * @return Evento correspondente ao identificador
	 * @throws Exception Uma exce��o pode ser lan�ada caso o evento n�o exista
	 */
	public static Evento getEvento(String idEvento) throws Exception {
		ObservableList<Evento> eventos = getEventos();
		Evento evento = null;

		for(Evento e: eventos){
			if(e.getId().equals(idEvento)){
				evento = e;
			}
		}

		return evento;
	}
	
	/**
	 * M�todo de resgate do valor de um atributo de um evento cadastrado
	 * @param idEvento Identificador �nico do evento
	 * @param atributo Nome do atributo a ser resgatado
	 * @return valor do atributo correspondente ao evento
	 * @throws Exception Uma exce��o pode ser lan�ada caso o atributo seja inv�lido ou caso o evento n�o exista
	 */
	public static String getAtributoEvento(String idEvento, String atributo) throws Exception{

		Evento evento = getEvento(idEvento);

		if(evento!=null){
			if(atributo.equals("nome")){
				return evento.getNome();
			}else if(atributo.equals("inicio")){
				return evento.getInicio();
			}else if(atributo.equals("fim")){
				return evento.getFim();
			}else if(atributo.equals("contato")){
				return evento.getContato();
			}else if(atributo.equals("area")){
				return evento.getArea();
			}else if(atributo.equals("repeticoes")){
				return String.valueOf(evento.getRepeticoes());
			}else{
				throw new Exception("Atributo nao existe.");
			}
		}else{
			throw new Exception("Evento nao existe.");
		}
	}

	/**
	 * M�todo de resgate de um evento mediante um atributo e seu respectivo valor
	 * @param atributo Nome do atributo do evento
	 * @param valor Valor do atributo do evento
	 * @return Identificador �nico do evento ao qual pertence o atributo
	 * @throws Exception Uma exce��o pode ser lan�ada caso o atributo seja inv�lido ou o evento n�o exista.
	 */
	public static String localizarEvento(String atributo, String valor) throws Exception{
		ObservableList<Evento> eventos = getEventos();

		String id = null;
		if(valor.equals("") || atributo.equals("")){
			throw new RoomsAllocationException("Entrada Invalida");
		}

		if(atributo.equals("nome")){
			for(Evento e: eventos){
				if(e.getNome().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("inicio")){
			for(Evento e: eventos){
				if(e.getInicio().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("fim")){
			for(Evento e: eventos){
				if(e.getFim().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("contato")){
			for(Evento e: eventos){
				if(e.getContato().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("area")){
			for(Evento e: eventos){
				if(e.getArea().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("repeticoes")){
			for(Evento e: eventos){
				if(e.getRepeticoes() == Integer.parseInt(valor)){
					id = e.getId();
				}
			}
		}else{
			throw new Exception("Atributo invalido");
		}

		if(id.equals(null)){
			throw new Exception("Nenhum evento encontrado");
		}else{
			return id;
		}

	}

	/**
	 * Verifica a exist�ncia de um evento na database
	 * @param idEvento Identificador �nico do evento
	 * @return True caso o evento exista, false caso n�o.
	 * @throws Exception Uma exception pode ser lan�ada caso a database n�o exista ou esteja vazia.
	 */
	public static boolean verificaExistencia(String idEvento) throws Exception{
		ObservableList<Evento> eventos = getEventos();

		for(Evento e: eventos){
			if(e.getId().equals(idEvento)){
				return true;
			}
		}

		return false;

	}
	
	/**
	 * M�todo de valida��o das datas de um evento
	 * @param dataInicio Data de inicio do evento
	 * @param dataFim Data de t�rmino do evento
	 * @return True se as datas forem v�lidas, false caso n�o
	 */
	public static boolean datasValidas(String dataInicio, String dataFim){

		if(!dataInicio.equalsIgnoreCase("") && !dataFim.equalsIgnoreCase("")){
			Date inicio = null;
			Date fim = null;

			try{
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formater.setLenient(false);
				inicio = (Date) formater.parse(dataInicio);
				fim = (Date) formater.parse(dataFim);
			}catch (ParseException ps){
				return false;
			}

			Calendar clInicio = Calendar.getInstance();
			Calendar clFim = Calendar.getInstance();

			clInicio.setTime(inicio);
			clFim.setTime(fim);

			if(clInicio.before(clFim)){
				String diaInicio = dataInicio.split("/")[0];
				String diaFim = dataFim.split("/")[0];
				if(diaInicio.equals(diaFim)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}

		}else{
			return false;
		}

	}
	
	public static boolean databaseVazia() throws Exception{
		ObservableList<Evento> eventos = getEventos();
		boolean retorno = true;
		
		if(eventos!=null){
			for(Evento e: eventos){
				if(e.getId().equals(null) || e.getId().equals("")){
					retorno =  true;
				}else{
					retorno = false;
				}
			}
		}else{
			retorno = true;
		}
		return retorno;
	}
	
	public static String localizarEventoGUI(String atributo, String valor) throws Exception{
		
		ObservableList<Evento> eventos = getEventos();

		String id = null;
		if(valor.equals("") || atributo.equals("")){
			throw new RoomsAllocationException("Entrada Invalida");
		}

		if(atributo.equals("nome")){
			for(Evento e: eventos){
				if(e.getNome().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("inicio")){
			for(Evento e: eventos){
				if(e.getInicio().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("fim")){
			for(Evento e: eventos){
				if(e.getFim().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("contato")){
			for(Evento e: eventos){
				if(e.getContato().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("area")){
			for(Evento e: eventos){
				if(e.getArea().equals(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("repeticoes")){
			for(Evento e: eventos){
				if(e.getRepeticoes() == Integer.parseInt(valor)){
					id = e.getId();
				}
			}
		}else if(atributo.equals("id")){
			for(Evento e: eventos){
				if(e.getId().equals(valor)){
					id = e.getId();
				}
			}
		}else{
			throw new Exception("Atributo invalido");
		}

		Evento evento = null;
		
		if(id.equals(null)){
			throw new Exception("Nenhum evento encontrado");
		}else{
			if(OperadorAlocacoes.isAlocado(id)){
				evento = getEvento(id);
			}
		}
		return evento.toString();

		
	}



}
