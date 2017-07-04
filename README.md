<h2>
Sistema de Alocação de Salas - Professor Msc. Mirko Barbosa Perkusich
</h2>
<p align="center">
  <img src="https://github.com/ThallesAraujo/Sistema-de-Aloca-o-de-Salas-Design-Patterns-/blob/master/Main%20Window.PNG"/>
</p>

<h3>
Objetivos do projeto: 
</h3>
- incluir o maior número de padrões de projeto GoF possíveis no sistema, de maneira justificável e com a finalidade de aumentar a expansibilidade e manutenabilidade do código

- Implementar os requisitos de sistema, conforme descritos em https://sites.google.com/site/mperkusich/disciplinas/padroes-de-projeto-2015-1/projeto-da-disciplina

<h3>
Padrões de projeto aplicados:
</h3>
- Singleton: Obtenção de conexão com o banco de dados
- Strategy: Classes de printer para interface de linha de comando e janelas de adição de salas
- Template Method: Utilizado para replicação de design nas janelas de adição de salas
- Command: Log de Eventos
- Composite: Interface gráfica

<h3>
Semipadrões aplicados:
</h3>

- Simple Factory - Classe de criação de tabelas e ícones.

<h3>
Adaptações de padrões:
</h3>

- Adapter - Utilizado na produção da classe de ExceptionCreator, porém não aplicado conforme definições oficiais.

<h3>
API's utilizadas
</h3>

- Undecorator by Arnaud Nouard (https://github.com/in-sideFX/Undecorator)
- Apache Derby - Banco de dados standalone (https://db.apache.org/derby/)
- JavaFX (http://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm#JFXST784)

<h3>
Créditos
</h3>

- Desenvolvedor: Thalles H. Araújo (https://sites.google.com/view/thallesaraujo)
- Professor - Disciplina de Padrões de Projeto: Mirko B. Perkusich (https://sites.google.com/site/mperkusich/)

- Documentação do projeto disponível (ver index.html)
