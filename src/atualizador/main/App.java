package atualizador.main;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {

	private static String CAMINHO_APLICACAO = "C:\\Users\\adailsonacj\\aaa\\";

	public static void main(String[] args) {

		int[] array = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();

//		List<String> listaTemp = Arrays.asList(args[0]);
//		excluirArquivosNaoContidosNoServidor(listaExclusao);

//		String json = "[{\"caminhoPasta\":\"aaa/\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_adadsd.msi\"},{\"caminhoPasta\":\"aaa/\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_ecli.zip\"},{\"caminhoPasta\":\"aaa/\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_FileZilla_3.42.0_win64_sponsored-setup.exe\"},{\"caminhoPasta\":\"aaa/\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_JNLPInstaller.jar\"},{\"caminhoPasta\":\"aaa/\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_novo.txt\"},{\"caminhoPasta\":\"\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_FileZilla_3.42.0_win64_sponsored-setup.exe\"},{\"caminhoPasta\":\"\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_JNLPInstaller.jar\"},{\"caminhoPasta\":\"\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_SceneBuilder-11.0.0.msi\"},{\"caminhoPasta\":\"\",\"caminhoLiteral\":\"\",\"hashFile\":\"\",\"nome\":\"_temp_tes.txt\"}]";
//		String json = "[\"aaa/_temp_adadsd.msi\",\"_temp_FileZilla_3.42.0_win64_sponsored-setup.exe\"]";
//		
//		Type listType = new TypeToken<ArrayList<Arquivo>>() {
//		}.getType();
//
//		List<String> listaTemporaria = new Gson().fromJson(args[0], listType);
//		renomearArquivosTemp(listaTemporaria);

//
//		String[] arg = (String[]) listaTemporaria.toArray();
//
//		for (String i : args) {
//			System.out.println(i);
//		}

	}

	public static void renomearArquivosTemp(List<String> arquivosTemporários) {
		for (int i = 0; i < arquivosTemporários.size(); i++) {
			String nomeTemporario = new File(arquivosTemporários.get(i)).getName();
			String[] nomeNovo = nomeTemporario.split("_temp_");

			if (nomeNovo.length > 1) {
				File arquivoTemporario = new File(CAMINHO_APLICACAO + arquivosTemporários.get(i));
				File arquivoNovo = new File(arquivoTemporario.getParent() + File.separator + nomeNovo[1]);
				if (arquivoNovo.exists()) {
					arquivoNovo.delete();
				}
				arquivoTemporario.renameTo(arquivoNovo);
			}
		}
	}

	public static void excluirArquivosNaoContidosNoServidor(List<Arquivo> arquivosExlusao) {
		for (int i = 0; i < arquivosExlusao.size(); i++) {
			if (!arquivosExlusao.get(i).getCaminhoPasta().equals("MD5.txt")) {
				deletarArquivo(arquivosExlusao.get(i).getCaminhoPasta(), arquivosExlusao.get(i).getNome());
			}
		}
	}

	public static void deletarArquivo(String caminhoPastaArquivo, String nome) {
		File f = new File(CAMINHO_APLICACAO + caminhoPastaArquivo + nome);
		f.delete();
	}
}
