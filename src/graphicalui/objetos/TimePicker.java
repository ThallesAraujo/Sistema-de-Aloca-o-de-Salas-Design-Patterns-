package graphicalui.objetos;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Classe do objeto de seleção de horário, utilizado na janela de cadastro de eventos.
 * @author Thalles
 *
 */
public class TimePicker extends HBox{
	
	private ComboBox cbHoras;
	private ComboBox cbMinutos;
	
	public TimePicker(){
		NumberFormat formatter = new DecimalFormat("00");
		ObservableList<String> horas = FXCollections.observableArrayList();
		ObservableList<String> minutos = FXCollections.observableArrayList();
		for(int i = 0;i<60;i++){
			minutos.add(formatter.format(i));
		}
		for(int i = 0;i<24;i++){
			horas.add(formatter.format(i));
		}
		
		Label separador = new Label(":");
		cbHoras = new ComboBox(horas);
		cbMinutos = new ComboBox(minutos);
		this.setSpacing(5);
		this.setPadding(new Insets(5,5,5,5));
		this.getChildren().addAll(cbHoras,separador,cbMinutos);
	}
	
	/**
	 * Método de retorno do horário selecionado.
	 * @return (String) horário em formato 24 horas selecionado.
	 */
	public String getTime(){
		return cbHoras.getValue().toString()+":"+cbMinutos.getValue().toString();
	}

}
