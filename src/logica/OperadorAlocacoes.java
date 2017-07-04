package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import database.AlocacoesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import exceptions.RoomsAllocationException;
import objetos.Alocacao;
import objetos.Evento;
import objetos.Sala;

/**
 * Classe de gerenciamento das alocações ou desalocações de eventos
 * @author Thalles
 *
 */
public class OperadorAlocacoes {

	private static AlocacoesDAO ADao = new AlocacoesDAO();
	/**
	 * Método de alocação de eventos
	 * @param idEvento Identificador único do evento a ser alocado
	 * @param idSala Identificador único da sala que receberá o evento
	 * @throws Exception Uma exceção pode ser lançada caso a sala ou o evento (ou ambos) não exista, caso a sala não esteja disponível para o horário em que o evento ocorre ou caso o evento não respeite as restrições de disponibilidade da sala
	 */
	public static void alocar(String idEvento, String idSala) throws Exception{

		if(OperadorEventos.verificaExistencia(idEvento)){
			if(OperadorSalas.verificaExistencia(idSala)){
				Evento evento = OperadorEventos.getEvento(idEvento);
				Sala sala = OperadorSalas.getSala(idSala);
				if(!isAlocado(idEvento)){
					if(isAlocada(idSala)){
						if(verificarChoqueDatas(evento)==null){
							if(sala.getFinalidade().equals("laboratorio")){
								if(sala.getTipo().equals("computacao") && sala.getIsAberto().equals("sim")){
									throw new Exception("laboratorios abertos de computacao nao sao escalonaveis");
								}else if(!evento.getArea().contains(sala.getTipo())){
									if(!sala.getTipo().equals("computacao")){
										throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
									}
								}
							}else if(sala.getFinalidade().equals("sala de conferencia")){
								if(sala.getTipo().equals("videoconferencia")){
									if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
										if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
											throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
										}
									}
								}else{
									if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
										throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
									}
								}	
							}else{
								ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
							}
						}else{
							if(sala.getFinalidade().equals("Escritorio")){
								throw new Exception("Escritorios nao sao escalonaveis.");
							}
							if(sala.getFinalidade().equals("Laboratorio")){
								if(sala.getIsAberto().equals("sim")){
									throw new Exception("Laboratorios abertos nao sao escalonaveis.");
								}else if(!evento.getArea().contains(sala.getTipo())){
									if(!sala.getTipo().equals("computacao")){
										throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
									}
								}
							}else if(sala.getTipo().equals("Videoconferencia")){
								if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
									if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
										throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
									}
								}
							}else if(sala.getFinalidade().equals("Sala de Conferencia")){
								if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
									throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
								}else if(sala.getTipo().equals("Normal") && evento.getRepeticoes()>0){
									throw new Exception("Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
								}
							}else{
								throw new Exception(verificarChoqueDatas(evento));
							}
						}

					}else{
						if(sala.getFinalidade().equals("Escritorio")){
							throw new Exception("Escritorios nao sao escalonaveis.");
						}
						if(sala.getFinalidade().equals("Laboratorio")){
							if(sala.getIsAberto().equals("sim")){
								throw new Exception("Laboratorios abertos nao sao escalonaveis.");
							}else if(!evento.getArea().contains(sala.getTipo())){
								if(!sala.getTipo().equals("computacao")){
									throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
								}
							}
						}else if(sala.getTipo().equals("Videoconferencia")){
							if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
								if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
									throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
								}
							}
						}else if(sala.getFinalidade().equals("Sala de Conferencia")){
							if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
								throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
							}else if(sala.getTipo().equals("Normal") && evento.getRepeticoes()>0){
								throw new Exception("Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
							}
						}else{
							ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
						}
					}
				}else{
					throw new Exception("O Evento ja foi alocado anteriormente.");
				}
			}else{
				throw new Exception("Sala/Evento nao existe.");
			}
		}else{
			throw new Exception("Sala/Evento nao existe.");
		}
		ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
	}

	public static void alocarGUI(String idEvento, String idSala) throws Exception{
		if(OperadorEventos.verificaExistencia(idEvento)){
			if(OperadorSalas.verificaExistencia(idSala)){
				Evento evento = OperadorEventos.getEvento(idEvento);
				Sala sala = OperadorSalas.getSala(idSala);
				if(!isAlocado(idEvento)){
					if(isAlocada(idSala)){
						if(verificarChoqueDatasGUI(evento)==null){
							if(sala.getFinalidade().equals("laboratorio")){
								if(sala.getTipo().equals("computacao") && sala.getIsAberto().equals("sim")){
									throw new Exception("laboratorios abertos de computacao nao sao escalonaveis");
								}else if(!evento.getArea().contains(sala.getTipo())){
									if(!sala.getTipo().equals("computacao")){
										throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
									}
								}
							}else if(sala.getFinalidade().equals("sala de conferencia")){
								if(sala.getTipo().equals("videoconferencia")){
									if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
										if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
											throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
										}
									}
								}else{
									if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
										throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
									}
								}	
							}else{
								ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
							}
						}else{
							if(sala.getFinalidade().equals("Escritorio")){
								throw new Exception("Escritorios nao sao escalonaveis.");
							}
							if(sala.getFinalidade().equals("Laboratorio")){
								if(sala.getIsAberto().equals("sim")){
									throw new Exception("Laboratorios abertos nao sao escalonaveis.");
								}else if(!evento.getArea().contains(sala.getTipo())){
									if(!sala.getTipo().equals("computacao")){
										throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
									}
								}
							}else if(sala.getTipo().equals("Videoconferencia")){
								if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
									if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
										throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
									}
								}
							}else if(sala.getFinalidade().equals("Sala de Conferencia")){
								if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
									throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
								}else if(sala.getTipo().equals("Normal") && evento.getRepeticoes()>0){
									throw new Exception("Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
								}
							}else{
								throw new Exception(verificarChoqueDatasGUI(evento));
							}
						}

					}else{
						if(sala.getFinalidade().equals("Escritorio")){
							throw new Exception("Escritorios nao sao escalonaveis.");
						}
						if(sala.getFinalidade().equals("Laboratorio")){
							if(sala.getIsAberto().equals("sim")){
								throw new Exception("Laboratorios abertos nao sao escalonaveis.");
							}else if(!evento.getArea().contains(sala.getTipo())){
								if(!sala.getTipo().equals("computacao")){
									throw new Exception("Sala exclusiva para a area de "+sala.getTipo()+".");
								}
							}
						}else if(sala.getTipo().equals("Videoconferencia")){
							if(!evento.getNome().contains("ead") || !evento.getNome().contains("EAD")){
								if(!evento.getArea().contains("ead") || !evento.getArea().contains("EAD")){
									throw new Exception("Salas de videoconferencia so podem ser utilizadas por eventos a distancia");
								}
							}
						}else if(sala.getFinalidade().equals("Sala de Conferencia")){
							if(evento.getNome().contains("Aula") || evento.getNome().contains("AULA") || evento.getNome().contains("aula")){
								throw new Exception("Salas de conferencia nao podem ser escalonadas para aulas");
							}else if(sala.getTipo().equals("Normal") && evento.getRepeticoes()>0){
								throw new Exception("Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
							}
						}else{
							ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
						}
					}
				}else{
					throw new Exception("O Evento ja foi alocado anteriormente.");
				}
			}else{
				throw new Exception("Sala/Evento nao existe.");
			}
		}else{
			throw new Exception("Sala/Evento nao existe.");
		}
		ADao.salvarAlocacao(new Alocacao(idSala,idEvento));
	}

	/**
	 * Localiza um evento alocado por meio do valor de um determinado atributo
	 * @param atributo Nome do atributo a ser pesquisado.
	 * @param valor Valor do atributo pesquisado.
	 * @return Par com o identificador único do evento e o identificador único da sala onde o mesmo está alocado
	 * @throws Exception Uma exceção pode ser lançada caso o atributo seja inválido ou não tenha sido encontrado nenhum evento que satisfaça as condições determinadas
	 */
	public static String localizarAlocacao(String atributo, String valor) throws Exception{
		ObservableList<Evento> eventos = OperadorEventos.getEventos();
		ArrayList<String> localizados = new ArrayList<String>();

		if(valor.equals("") || atributo.equals("")){
			throw new RoomsAllocationException("Entrada Invalida");
		}

		if(atributo.equalsIgnoreCase("contato")){
			for(Evento e: eventos){
				if(e.getContato().contains(valor) || e.getContato().endsWith(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("nome")){
			for(Evento e: eventos){
				if(e.getNome().contains(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("inicio")){
			for(Evento e: eventos){
				if(e.getInicio().contains(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("fim")){
			for(Evento e: eventos){
				if(e.getFim().contains(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("area")){
			for(Evento e: eventos){
				if(e.getArea().contains(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("repeticoes")){
			for(Evento e: eventos){
				if(e.getRepeticoes() == Integer.parseInt(valor)){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}
		}else if(atributo.equalsIgnoreCase("horario")){
			for(Evento e: eventos){
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

				Date inicio = null;
				Date fim = null;
				Date data = null;

				try{
					inicio = (Date) formater.parse(e.getInicio());
					fim = (Date) formater.parse(e.getFim());
					data = (Date) formater.parse(valor);
				}catch(ParseException ps){
					ps.printStackTrace();
				}

				if(data.equals(inicio) || data.equals(fim) || (data.after(inicio) && data.before(fim))){
					if(isAlocado(e.getId())){
						Alocacao alocacao = getAlocacao(e.getId());
						localizados.add(alocacao.getIdSala()+":"+alocacao.getIdEvento());
					}
				}
			}




		}else{
			throw new Exception("Atributo invalido");
		}

		if(localizados.size()>0){
			if(atributo.equals("contato")){
				ArrayList<String> invertido = new ArrayList<String>();
				for(int i= localizados.size()-1;i>=0;i--){
					invertido.add(localizados.get(i));
				}
				String temp = invertido.toString();
				String retorno = temp.substring(1, temp.length()-1);
				return retorno;

			}else{
				String temp = localizados.toString();
				String retorno = temp.substring(1, temp.length()-1);
				return retorno;
			}
		}else{
			return "Nenhum evento encontrado.";
		}

	}

	/**
	 * Método que verifica se as datas de um evento a ser alocado se chocam com as datas dos eventos alocados na sala onde o mesmo será alocado
	 * @param novo Evento a ser comparado.
	 * @return Texto de erro caso haja choque de datas.
	 * @throws Exception Uma exceção pode ser lançada em caso de não existência da database.
	 */
	public static String verificarChoqueDatas(Evento novo) throws Exception{

		ObservableList<Evento> eventos = OperadorEventos.getEventos();
		ObservableList<Evento> alocados = FXCollections.observableArrayList();

		for(Evento e: eventos){
			if(isAlocado(e.getId())){
				alocados.add(e);
			}
		}

		if(alocados.size()>0){
			Date inicioEx = null;
			Date fimEx = null;

			Date inicioNv = null;
			Date fimNv = null;

			for(Evento alocado: alocados){
				try{
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					formater.setLenient(false);
					inicioEx = (Date) formater.parse(alocado.getInicio());
					fimEx = (Date) formater.parse(alocado.getFim());
					inicioNv = (Date) formater.parse(novo.getInicio());
					fimNv = (Date) formater.parse(novo.getFim());
				}catch (ParseException ps){}

				Calendar inicio1 = Calendar.getInstance();
				Calendar fim1 = Calendar.getInstance();

				Calendar inicio2 = Calendar.getInstance();
				Calendar fim2 = Calendar.getInstance();

				inicio1.setTime(inicioEx);
				fim1.setTime(fimEx);

				inicio2.setTime(inicioNv);
				fim2.setTime(fimNv);

				if(!fim2.before(inicio1) && !inicio2.after(fim1)){
					return "A sala nao esta disponivel neste horario.";
				}else if(alocado.getRepeticoes()>1){
					if(novo.getRepeticoes()>1){
						for(int i=0;i<=alocado.getRepeticoes();i++){
							inicio1.add(Calendar.DAY_OF_MONTH,7);
							fim1.add(Calendar.DAY_OF_MONTH,7);
							if(!fim2.before(inicio1) && !inicio2.after(fim1)){
								return "A sala nao esta disponivel neste horario.";
							}
						}
					}else{
						for(int i=0;i<=alocado.getRepeticoes();i++){
							inicio1.add(Calendar.DAY_OF_MONTH,7);
							fim1.add(Calendar.DAY_OF_MONTH,7);
							if(inicio1.equals(inicio2) || inicio1.equals(fim2) || fim1.equals(fim2)){
								return "A sala nao esta disponivel neste horario.";
							}
							if(!fim2.before(inicio1) && !inicio2.after(fim1)){
								return "A sala nao esta disponivel neste horario.";
							}
						}

					}
				}else if (inicio2.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY  || fim2.get(Calendar.DAY_OF_WEEK) ==  Calendar.THURSDAY|| inicio2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || fim2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ) {
					return "As salas nao sao alocadas nos fins de semana.";
				}else{
					return null;
				}

			}
		}else{
			return null;
		}
		return null;
	}


	public static String verificarChoqueDatasGUI(Evento novo) throws Exception{

		if(novo.getTipo().equals("semanal")){
			return verificaChoqueDatasSemanal(novo);
		}else if(novo.getTipo().equals("mensal")){
			return verificaChoqueDatasMensal(novo);
		}else{
			return verificaChoqueDatasDiario(novo);
		}

	}



	public static String verificaChoqueDatasSemanal(Evento novo) throws Exception{

		ObservableList<Evento> eventos = OperadorEventos.getEventos();
		ObservableList<Evento> alocados = FXCollections.observableArrayList();

		for(Evento e: eventos){
			if(isAlocado(e.getId())){
				alocados.add(e);
			}
		}

		if(alocados.size()>0){
			Date inicioEx = null;
			Date fimEx = null;

			Date inicioNv = null;
			Date fimNv = null;
			String tipo = "semanal";
			int repeticoes = 0;
			for(Evento alocado: alocados){
				try{
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					formater.setLenient(false);
					inicioEx = (Date) formater.parse(alocado.getInicio());
					fimEx = (Date) formater.parse(alocado.getFim());
					inicioNv = (Date) formater.parse(novo.getInicio());
					fimNv = (Date) formater.parse(novo.getFim());
					if(alocado.getTipo().equals("mensal")){
						tipo = "mensal";
					}else if(alocado.getTipo().equals("diario")){
						tipo = "diario";
					}
					if(alocado.getRepeticoes()>0){
						repeticoes = alocado.getRepeticoes();
					}
				}catch (ParseException ps){}

				Calendar inicio1 = Calendar.getInstance();
				Calendar fim1 = Calendar.getInstance();

				Calendar inicio2 = Calendar.getInstance();
				Calendar fim2 = Calendar.getInstance();
				inicio1.setTime(inicioEx);
				fim1.setTime(fimEx);

				inicio2.setTime(inicioNv);
				fim2.setTime(fimNv);

				if(!fim2.before(inicio1) && !inicio2.after(fim1)){
					return "A sala nao esta disponivel neste horario.";
				}else if(alocado.getRepeticoes()>0){
					if(novo.getRepeticoes()>0){
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,7);
								fim2.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,7);
								fim2.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,7);
								fim2.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}else{
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}
				}else if (inicio2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || fim2.get(Calendar.DAY_OF_WEEK) ==  Calendar.SUNDAY) {
					return "As salas nao sao alocadas nos fins de semana.";
				}else{
					return null;
				}

			}
		}else{
			return null;
		}
		return null;
	}

	public static String verificaChoqueDatasDiario(Evento novo) throws Exception{

		ObservableList<Evento> eventos = OperadorEventos.getEventos();
		ObservableList<Evento> alocados = FXCollections.observableArrayList();

		for(Evento e: eventos){
			if(isAlocado(e.getId())){
				alocados.add(e);
			}
		}

		if(alocados.size()>0){
			Date inicioEx = null;
			Date fimEx = null;

			Date inicioNv = null;
			Date fimNv = null;
			String tipo = "semanal";
			int repeticoes = 0;
			for(Evento alocado: alocados){
				try{
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					formater.setLenient(false);
					inicioEx = (Date) formater.parse(alocado.getInicio());
					fimEx = (Date) formater.parse(alocado.getFim());
					inicioNv = (Date) formater.parse(novo.getInicio());
					fimNv = (Date) formater.parse(novo.getFim());
					if(alocado.getTipo().equals("mensal")){
						tipo = "mensal";
					}else if(alocado.getTipo().equals("diario")){
						tipo = "diario";
					}
					if(alocado.getRepeticoes()>0){
						repeticoes = alocado.getRepeticoes();
					}
				}catch (ParseException ps){}

				Calendar inicio1 = Calendar.getInstance();
				Calendar fim1 = Calendar.getInstance();

				Calendar inicio2 = Calendar.getInstance();
				Calendar fim2 = Calendar.getInstance();
				
				inicio1.setTime(inicioEx);
				fim1.setTime(fimEx);

				inicio2.setTime(inicioNv);
				fim2.setTime(fimNv);

				if(!fim2.before(inicio1) && !inicio2.after(fim1)){
					return "A sala nao esta disponivel neste horario.";
				}else if(alocado.getRepeticoes()>0){
					if(novo.getRepeticoes()>0){
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,1);
								fim2.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,1);
								fim2.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,1);
								fim2.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}else{
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}
				}else if (inicio2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || fim2.get(Calendar.DAY_OF_WEEK) ==  Calendar.SUNDAY) {
					return "As salas nao sao alocadas nos fins de semana.";
				}else{
					return null;
				}

			}
		}else{
			return null;
		}
		return null;
	}

	public static String verificaChoqueDatasMensal(Evento novo) throws Exception{

		ObservableList<Evento> eventos = OperadorEventos.getEventos();
		ObservableList<Evento> alocados = FXCollections.observableArrayList();

		for(Evento e: eventos){
			if(isAlocado(e.getId())){
				alocados.add(e);
			}
		}

		if(alocados.size()>0){
			Date inicioEx = null;
			Date fimEx = null;

			Date inicioNv = null;
			Date fimNv = null;
			String tipo = "semanal";
			int repeticoes = 0;
			for(Evento alocado: alocados){
				try{
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					formater.setLenient(false);
					inicioEx = (Date) formater.parse(alocado.getInicio());
					fimEx = (Date) formater.parse(alocado.getFim());
					inicioNv = (Date) formater.parse(novo.getInicio());
					fimNv = (Date) formater.parse(novo.getFim());
					if(alocado.getTipo().equals("mensal")){
						tipo = "mensal";
					}else if(alocado.getTipo().equals("diario")){
						tipo = "diario";
					}
					if(alocado.getRepeticoes()>0){
						repeticoes = alocado.getRepeticoes();
					}
				}catch (ParseException ps){}

				Calendar inicio1 = Calendar.getInstance();
				Calendar fim1 = Calendar.getInstance();

				Calendar inicio2 = Calendar.getInstance();
				Calendar fim2 = Calendar.getInstance();
				inicio1.setTime(inicioEx);
				fim1.setTime(fimEx);

				inicio2.setTime(inicioNv);
				fim2.setTime(fimNv);

				if(!fim2.before(inicio1) && !inicio2.after(fim1)){
					return "A sala nao esta disponivel neste horario.";
				}else if(alocado.getRepeticoes()>0){
					if(novo.getRepeticoes()>0){
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,30);
								fim2.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,30);
								fim2.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
							inicio1.setTime(inicioEx);
							fim1.setTime(fimEx);
							inicio2.setTime(inicioNv);
							fim2.setTime(fimNv);
							for(int i=0;i<=novo.getRepeticoes();i++){
								inicio2.add(Calendar.DAY_OF_MONTH,30);
								fim2.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}else{
						if(alocado.getTipo().equals("semanal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,7);
								fim1.add(Calendar.DAY_OF_MONTH,7);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("mensal")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,30);
								fim1.add(Calendar.DAY_OF_MONTH,30);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}else if(alocado.getTipo().equals("diario")){
							for(int i=0;i<=alocado.getRepeticoes();i++){
								inicio1.add(Calendar.DAY_OF_MONTH,1);
								fim1.add(Calendar.DAY_OF_MONTH,1);
								if(!fim2.before(inicio1) && !inicio2.after(fim1)){
									return "A sala nao esta disponivel neste horario.";
								}
							}
						}
					}
				}else if (inicio2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || fim2.get(Calendar.DAY_OF_WEEK) ==  Calendar.SUNDAY) {
					return "As salas nao sao alocadas nos fins de semana.";
				}else{
					return null;
				}

			}
		}else{
			return null;
		}
		return null;
	}


	/**
	 * Desaloca um evento previamente alocado
	 * @param idEvento Identificador único do evento a ser desalocado
	 * @throws Exception Uma exceção pode ser lançada caso o evento não exista ou não esteja alocado
	 */
	public static void desalocar(String idEvento) throws Exception{
		if(isAlocado(idEvento)){
			ADao.excluirAlocacao(idEvento);
		}else{
			throw new Exception("O evento nao esta alocado.");
		}

	}

	/**
	 * Desaloca uma sala previamente alocada
	 * @param idSala Identificador único da sala a ser desalocada
	 * @throws Exception Uma exceção pode ser lançada caso a sala não exista ou não tenha sido alocada.
	 */
	public static void desalocarSala(String idSala) throws Exception{
		try {
			ADao.excluirAlocacaoPorSala(idSala);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Verifica se um evento está alocado ou não.
	 * @param idEvento Identificador único do evento que será consultado.
	 * @return Verdadeiro caso o evento esteja alocado, falso caso contrário.
	 * @throws Exception Uma exceção pode ser lançada caso o evento não exista.
	 */
	public static boolean isAlocado(String idEvento) throws Exception{

		ObservableList<Alocacao> alocacoes = ADao.getAlocacoes();
		for(Alocacao a: alocacoes){
			if(a.getIdEvento().equalsIgnoreCase(idEvento)){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Verifica se uma sala está alocada ou não.
	 * @param idSala Identificador único da sala que será consultada.
	 * @return Verdadeiro caso a sala esteja alocada, falso caso contrário.
	 * @throws Exception Uma exceção pode ser lançada caso a sala não exista.
	 */
	public static boolean isAlocada(String idSala) throws Exception{

		ObservableList<Alocacao> alocacoes = ADao.getAlocacoes();
		for(Alocacao a: alocacoes){
			if(a.getIdSala().equalsIgnoreCase(idSala)){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Retorna uma alocação existente a partir do identificador único do evento.
	 * @param idEvento Identificador único do evento alocado.
	 * @return Alocação correspondente ao identificador do evento, caso exista alguma alocação associada ao evento.
	 * @throws Exception Uma exceção pode ser lançada caso o evento não possua alocações associadas a ele ou caso ele não exista.
	 */
	public static Alocacao getAlocacao(String idEvento) throws Exception{
		ObservableList<Alocacao> alocacoes = ADao.getAlocacoes();
		for(Alocacao a: alocacoes){
			if(a.getIdEvento().equalsIgnoreCase(idEvento)){
				return a;
			}
		}
		return null;	

	}

	/**
	 * Método de recuperação das alocações cadastradas na database.
	 * @return lista de alocações cadastradas.
	 * @throws Exception Uma exceção pode ser lançada caso a tabela alocações ou a database não exista.
	 */
	public static ObservableList<Alocacao> getAlocacoes() throws Exception{
		return ADao.getAlocacoes();
	}

	/**
	 * Verificador de nulidade da database de alocações
	 * @return Verdadeiro caso não existam alocações cadastradas, falso caso contrário.
	 * @throws Exception Uma exceção pode ser lançada caso a database não exista.
	 */
	public static boolean databaseVazia() throws Exception{

		ObservableList<Alocacao> alocacoes = getAlocacoes();
		boolean retorno = true;
		if(alocacoes!=null){
			for(Alocacao a: alocacoes){
				if(a.getIdSala().equals(null) || a.getIdSala().equals("")){
					retorno = true;
				}else{
					retorno = false;
				}
			}
		}else{
			retorno = true;
		}

		return retorno;

	}

}
