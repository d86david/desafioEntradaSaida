import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Produto> produtos = new ArrayList<Produto>();

		System.out.print("Informe o Caminho da Pasta: ");

		// Declarando a String que vai guardar o Caminho da pasta
		String strCaminho = sc.nextLine();

		// Declarando e instanciando o caminho na classe File
		File caminho = new File(strCaminho);
		File arquivo = new File(strCaminho + "\\Produto.csv");

		// Lendo o arquivo
		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
			String linha = br.readLine();

			while (linha != null) {
				String[] p = linha.split(",");
				Produto prod = new Produto();
				prod.setNome(p[0]);
				prod.setPreco(Double.parseDouble(p[1]));
				prod.setQtd(Integer.parseInt(p[2]));

				produtos.add(prod);

				linha = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Criando a pasta
		boolean sucesso = new File(strCaminho + "\\out").mkdir();
		if (sucesso) {
			System.out.println("Pasta Criada com Sucesso!!");
		} else {
			System.out.println("Não foi possivel criar a pasta!");
		}
		
		//Crindo o arquivo na pasta criada
		String novoCaminho = strCaminho + "\\out\\summary.csv";
		
		
		//Escrevendo no Arquivo
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(novoCaminho))) {
			
			for(Produto prod : produtos) {
				bw.write(prod.getNome()+", "+(prod.getPreco()*prod.getQtd()));
				bw.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
