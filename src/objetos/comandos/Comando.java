package objetos.comandos;

public interface Comando {
	
	public void executar() throws Exception;
	public void desfazer() throws Exception;

}
