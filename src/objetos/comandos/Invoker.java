package objetos.comandos;

public class Invoker {
	
	private static int qtdComandos = 0;
	private static Comando[] comandos = new Comando[3];
	
	private Invoker(){}
	
	public static void adicionarEExecutar(Comando comando) throws Exception{
		if(qtdComandos!=3){
			comandos[qtdComandos] = comando;
			qtdComandos++;
			comando.executar();
		}else{
			comandos[0] = comandos[1];
			comandos[1] = comandos[2];
			comandos[2] = comando;
			comando.executar();
		}
	}
	
	public static void desfazer() throws Exception{
		comandos[qtdComandos-1].desfazer();
		qtdComandos--;
	}
	
	public static int getQtdComandos(){
		return qtdComandos;
	}

}
