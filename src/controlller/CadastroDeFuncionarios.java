package controlller;


import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Funcionario;
import model.DAO.FuncionarioDAO;
import util.Teclado;

public class CadastroDeFuncionarios {

	private static final int CADASTRAR_UM_FUNCIONARIO = 1;
	private static final int MOSTRAR_FUNCIONARIOS = 2;
	private static final int APAGAR_REGISTRO_DE_FUNCIONARIO = 3;
	private static final int ATUALIZAR_FUNCIONARIO = 4;
	private static final int SAIR = 5;

	public void mostrarMenu() {
		
		ImageIcon icone = new ImageIcon("img/GridArt.png");
	JOptionPane.showMessageDialog(null, "Observe as Opções ","Menu:",JOptionPane.PLAIN_MESSAGE,icone);
	
	
	
	}

	public void cadastrarFuncionario() {
		System.out.println("\n---Cadastro de Funcionário---\n");
		//int id = Teclado.lerInt("Id: ");
        
		String nome = Teclado.lerTexto("Nome: ");
		double salario = Teclado.lerDouble("Salário: ");
		
		Funcionario func = FuncionarioDAO.inserir(nome, salario);
		
		if(func != null) {
			System.out.println("\nFuncionário cadastrado com sucesso.");
			System.out.println(func.toString());
		}else {
			System.out.println("Erro ao cadastrar um funcionário.");
		}
	}

	public void mostrarFuncionario() {
		System.out.println("---Relatório dos Funcionários---\n");
		List<Funcionario> func = FuncionarioDAO.buscarATodos();
		
		if(!func.isEmpty()) {
			for(Funcionario funcs : func) {
				System.out.println(funcs.toString()+"\n");
			}
		}else {
			System.out.println("Não há nenhum relatório de funcionário.");
		}
	}
	
	public void apagarFuncionarios() {
		System.out.println("\n---Apagar Funcionários---\n");
		int id = Teclado.lerInt("ID: ");
		if(FuncionarioDAO.excluir(id)) {
			System.out.println("Funcionário apagado com sucesso.");
		}else {
			System.out.println("Erro ao apagar funcionário.");
		}
	}

	public void atualizarFuncionario() {
		System.out.println("\n---Atualizar Funcionários---\n");
		
		int id = Teclado.lerInt("Id: ");
		String nome = Teclado.lerTexto("Nome a ser atualizado: ");
        double salario = Teclado.lerDouble("Salário a ser atualizado: ");
		
		if(FuncionarioDAO.atualizar(id, nome, salario)){
			System.out.println("Funcionário atualizado com sucesso.");
		}else {
			System.out.println("Erro ao atualizar o funcionario.");
		}
	}
	
	public static void main(String[] args) {
		CadastroDeFuncionarios func = new CadastroDeFuncionarios();
		int opcao = SAIR;
		do {
			func.mostrarMenu();
			opcao = Teclado.lerInt("Opção: ");
			switch (opcao) {
			case CADASTRAR_UM_FUNCIONARIO:
				func.cadastrarFuncionario();
				break;
			case MOSTRAR_FUNCIONARIOS:
				func.mostrarFuncionario();
				break;
			case APAGAR_REGISTRO_DE_FUNCIONARIO:
			func.apagarFuncionarios();
				break;
			case ATUALIZAR_FUNCIONARIO:
				func.atualizarFuncionario();
				break;
			case SAIR:
				System.out.println("Saindo do sistema.");
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
			Teclado.lerTexto("\nPressione a tecla ENTER para poder continuar...\n");
		}while(opcao != SAIR);
		System.out.println("\nSistema encerrado.");
	}

}

