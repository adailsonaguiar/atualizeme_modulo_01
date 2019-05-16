package br.com.atualizador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

	private static String NOME_PASTA_APLICACAO = null;
	private static String CAMINHO_APLICACAO = System.getProperty("user.dir") + File.separator;

	public static void main(String[] args) {
		File aplicacao = new File(CAMINHO_APLICACAO);

		NOME_PASTA_APLICACAO = aplicacao.getName();

		renomearArquivosTemp(listaCaminhos(aplicacao));

		excluirArquivosNaoContidosNoServidor(args);

		if (args.length > 0) {
			String[] splitExclusao = args[0].split("%30");
			for (String ar : splitExclusao) {
				System.out.println(ar);
			}
			excluirArquivosNaoContidosNoServidor(splitExclusao);
		}
		abrirSicap();
	}

	public static List<String> listaCaminhos(File dir) {
		List<String> caminhos = new ArrayList<String>();
		if (dir == null || dir.listFiles() == null) {
			return null;
		}
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				String[] dados = null;
				dados = file.getParent().split(NOME_PASTA_APLICACAO, 2);
				if (dados.length > 1) {
					caminhos.add(dados[1] + File.separator + file.getName());
				} else {
					caminhos.add(File.separator + file.getName());
				}
			} else
				caminhos.addAll(listaCaminhos(file));
		}
		return (List<String>) caminhos;
	}

	public static void renomearArquivosTemp(List<String> arquivosTemporarios) {
		for (String arq : arquivosTemporarios) {
			String nomeTemporario = new File(arq).getName();
			String[] nomeNovo = nomeTemporario.split("_temp_");

			if (nomeNovo.length > 1) {
				File arquivoTemporario = new File(CAMINHO_APLICACAO + arq);
				File arquivoNovo = new File(arquivoTemporario.getParent() + File.separator + nomeNovo[1]);
				if (arquivoNovo.exists()) {
					arquivoNovo.delete();
				}
				arquivoTemporario.renameTo(arquivoNovo);
			}
		}
	}

	public static void excluirArquivosNaoContidosNoServidor(String[] arquivosExlusao) {
		for (String dir : arquivosExlusao) {
			File fi = new File(CAMINHO_APLICACAO + dir.replace("%20", " "));
			if (!fi.getName().equals("MD5.txt")) {
				fi.delete();
			}
		}
	}

	public static void abrirSicap() {
		try {
			Runtime.getRuntime().exec("java -jar " + CAMINHO_APLICACAO + "SicapEstado0.0.6-BETA.jar ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
