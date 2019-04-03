import java.util.*;

public class Jogo{

	public char tabuleiro[][] = new char[3][3];

	public Jogo(){

		inicializaTabuleiro();
	}

	public void inicializaTabuleiro(){

		for (int i = 0;i < 3;i++) {
			for (int j = 0;j < 3;j++) {
				
				tabuleiro[i][j] = '-';

			}
		}
	}

	public void mostraTabuleiro(){

		System.out.println("   0   1   2");

		for (int i = 0;i < 3;i++) {
			System.out.println();
			System.out.print(i + "  ");

			for (int j = 0;j < 3;j++) {

				System.out.print(tabuleiro[i][j] + "   ");

			}
			System.out.println();
		}
	}

	public String trataCoordenadas(Jogador jogador, int coordenadas[]){

		int cont = 0;
		boolean loop = true;
		boolean tabuleiroCheio = false;

		if (tabuleiro[coordenadas[0]][coordenadas[1]] == '-') {

			if (jogador.tipo == 'x') { // jogador 1

				tabuleiro[coordenadas[0]][coordenadas[1]] = 'x';
			}

			if (jogador.tipo == 'o') { // jogador 2

				tabuleiro[coordenadas[0]][coordenadas[1]] = 'o';
			}
		}else{

			return "Posicao ja marcada";
		}

		return " ";
	}

	public boolean trataVitoria(Jogador jogador, boolean vitoria){

		Scanner leitor = new Scanner(System.in);
		String resposta;

		if (vitoria == true) {
			
			for (int i = 0;i < 50;i++) {
				System.out.println();
			}

			mostraTabuleiro();

			System.out.println();
			System.out.println(jogador.nome +" Venceu!");
			System.out.println();
			
		}else{

			for (int i = 0;i < 50;i++) {
				System.out.println();
			}

			mostraTabuleiro();

			System.out.println();
			System.out.println("EMPATE!");
		}

		System.out.println("Jogar novamente? sim ou nao?");

		resposta = leitor.nextLine();

		if (resposta.equals("sim")) {

			inicializaTabuleiro();

			return false; // fim de jogo -> false
		}

		else if (resposta.equals("nao")) {

			System.out.println();
			System.out.println("FIM DE JOGO!");

			return true; // fim de jogo -> true
		}

		leitor.close();

		return true;
	}

	public boolean verificaTabuleiro(Jogador jogador){

		char tipo = jogador.tipo;
		int cont = 0;
		boolean vitoria = false;
		boolean fimDeJogo = false;

		for (int i = 0;i < 3;i++) {
			for (int j = 0;j < 3;j++) {

				if (tabuleiro[i][j] != '-') {
					cont++;
				}
			}
		}

		if ((tabuleiro[0][0] == tipo && tabuleiro[0][1] == tipo && tabuleiro[0][2] == tipo)||(tabuleiro[0][0] == tipo &&
			tabuleiro[1][0] == tipo && tabuleiro[2][0] == tipo)||(tabuleiro[0][0] == tipo && tabuleiro[1][1] == tipo &&
			tabuleiro[2][2] == tipo)){

			vitoria = true;

			fimDeJogo = trataVitoria(jogador, vitoria);

		}

		if ((tabuleiro[0][2] == tipo && tabuleiro[1][1] == tipo && tabuleiro[2][0] == tipo)||(tabuleiro[0][1] == tipo &&
			tabuleiro[1][1] == tipo && tabuleiro[2][1] == tipo)||(tabuleiro[1][0] == tipo && tabuleiro[1][1] == tipo &&
			tabuleiro[1][2] == tipo)){

			vitoria = true;

			fimDeJogo = trataVitoria(jogador, vitoria);
		}

		if ((tabuleiro[2][2] == tipo && tabuleiro[1][2] == tipo && tabuleiro[0][2] == tipo)||(tabuleiro[2][0] == tipo &&
			tabuleiro[2][1] == tipo && tabuleiro[2][2] == tipo)){

			vitoria = true;

			fimDeJogo = trataVitoria(jogador, vitoria);
		}

		if (cont >= 9) {

			vitoria = false;
			fimDeJogo = trataVitoria(jogador, vitoria);

		}

	return fimDeJogo;
}

public int[] coletaCoordenadas(Jogador jogador){

	Scanner leitor = new Scanner(System.in);

	int x;
	int y;

	int coordenadas[] = new int[2];

	System.out.println();
	System.out.println(jogador.nome + ", digite as coordenadas para marcar um '"+ jogador.tipo +"'");
	System.out.println("Digite a linha:");
	x = leitor.nextInt();
	coordenadas[0] = x;

	System.out.println();
	System.out.println("Digite a coluna:");
	y = leitor.nextInt();
	coordenadas[1] = y;

	return coordenadas;
}

public boolean processaDados(Jogador jogador, int coordenadas[]){

	String erro;

	coordenadas = coletaCoordenadas(jogador);

	erro = trataCoordenadas(jogador, coordenadas);

	while(erro.equals("Posicao ja marcada")){

		System.out.println(erro);
		coordenadas = coletaCoordenadas(jogador);
		erro = trataCoordenadas(jogador, coordenadas);
	}

	return verificaTabuleiro(jogador);
}

public void loopGame(Jogador jogador1, Jogador jogador2){

	boolean fimDeJogo = false;
	String erro;

	int coordenadas[] = new int[2];

	jogador1.tipo = 'x';
	jogador2.tipo = 'o';

	while(fimDeJogo != true){

		for (int i = 0;i < 50;i++) {
			System.out.println();
		}

		coordenadas = null;

		mostraTabuleiro();

		/*_________________jogador 1_________________*/
		
		if(fimDeJogo != true){

			fimDeJogo = processaDados(jogador1, coordenadas);
		} 
		/*_________________jogador 1_________________*/

		if (fimDeJogo != true) {
			
			for (int i = 0;i < 50;i++) {
				System.out.println();
			}

			mostraTabuleiro();

			/*_________________jogador 2_________________*/

			fimDeJogo = processaDados(jogador2, coordenadas);
			/*_________________jogador 2_________________*/;
		}
	}
}

public static void main(String[] args) {

	String nomeJog1;
	String nomeJog2;
	Jogo jogo = new Jogo();

	Scanner leitor = new Scanner(System.in);

		for (int i = 0;i < 50;i++) { // gambiarra pra limpar a tela
			System.out.println();
		}
		
		System.out.println("Digite o nome do primeiro jogador");
		nomeJog1 = leitor.nextLine();

		System.out.println();
		System.out.println("Digite o nome do segundo jogador");
		nomeJog2 = leitor.nextLine();

		Jogador jogador1 = new Jogador(nomeJog1);
		Jogador jogador2 = new Jogador(nomeJog2);

		jogo.loopGame(jogador1, jogador2);

		leitor.close();
	}
}

class Jogador{

	public String nome;
	public char tipo;

	public Jogador(String nomeJogador){
		nome = nomeJogador;
	}
}