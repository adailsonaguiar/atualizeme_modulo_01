package br.com.atualizador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

	// private static String CAMINHO_APLICACAO = "C:\\Users\\adailsonacj\\aaa\\";
	private static String CAMINHO_APLICACAO = "/home/adailson/aaa/";

	public static void main(String[] args) {

		List<String> listaTemp = new ArrayList<String>();
		if (args != null) {

			String[] splitTemp = args[0].split("%30");
			for (int i = 0; i < splitTemp.length; i++) {
				listaTemp.add(splitTemp[i].replace("%20", " "));
			}
			System.out.println(listaTemp);
			renomearArquivosTemp(listaTemp);

			List<String> listaExclusao = new ArrayList<String>();
			String[] splitExclusao = args[1].split("%30");
			for (int i = 0; i < splitExclusao.length; i++) {
				listaExclusao.add(splitExclusao[i].replace("%20", " "));
			}
			System.out.println(listaExclusao);
			excluirArquivosNaoContidosNoServidor(listaExclusao);
		}

	}

	public static void renomearArquivosTemp(List<String> arquivosTemporarios) {
		for (int i = 0; i < arquivosTemporarios.size(); i++) {
			String nomeTemporario = new File(arquivosTemporarios.get(i)).getName();
			String[] nomeNovo = nomeTemporario.split("_temp_");

			if (nomeNovo.length > 1) {
				File arquivoTemporario = new File(CAMINHO_APLICACAO + arquivosTemporarios.get(i));
				File arquivoNovo = new File(arquivoTemporario.getParent() + File.separator + nomeNovo[1]);
				if (arquivoNovo.exists()) {
					arquivoNovo.delete();
				}
				arquivoTemporario.renameTo(arquivoNovo);
			}
		}
	}

	public static void excluirArquivosNaoContidosNoServidor(List<String> arquivosExlusao) {
		for (int i = 0; i < arquivosExlusao.size(); i++) {
			File fi = new File(CAMINHO_APLICACAO + arquivosExlusao.get(i));
			if (!fi.getName().equals("MD5.txt")) {
				deletarArquivo(fi);
			}
		}
	}

	public static void deletarArquivo(File file) {
		file.delete();
	}
}
