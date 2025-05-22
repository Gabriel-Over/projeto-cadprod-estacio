package com.mycompany.cadprod;
import javax.swing.JOptionPane;

public class CadProd {
    private static Loja loja = new Loja();
    
    public static void main(String[] args) {
        
        String[] respostas = {
            "Cadastrar Clientes",
            "Cadastrar Produto",
            "Realizar Venda",
            "Listar Produtos", 
            "Listar Clientes",
            "Listar Vendas",
            "Sair"
        };
        /*Aparece a caixa de dialogo
         *Opcao recebe o valor do indice do array de String respostas
         */
        while (true) {
            int opcao = JOptionPane.showOptionDialog(
                    null, 
                    "Escolha uma opção",
                    "Cadastro e Controle de Vendas", 
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, 
                    respostas,
                    0
            );

            switch(opcao) {
                case 0:
                    cadastrarCliente();
                    break;
                case 1:
                    cadastrarProdutos();
                    break;
                case 2:
                    realizarVendas();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, loja.listarProdutos());
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, loja.listarClientes());
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, loja.listarVendas());
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    
    private static void cadastrarCliente() {
        boolean cadastroCompleto = false;
        int tentativas = 1;
        final int MAX_TENTATIVAS = 3;
        String nome;
        String cpf;
        while (cadastroCompleto == false || tentativas <= MAX_TENTATIVAS) {
            nome = JOptionPane.showInputDialog("Digite o nome do cliente");
            if(nome == null) return;
            
            if (nome.trim().length() < 3) {
                JOptionPane.showMessageDialog(null, "Nome inválido! Deve ter pelo menos 3 caracteres. \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                tentativas++;
                continue;
            }
            
            cpf = JOptionPane.showInputDialog("Digite o cpf do cliente");
            if (cpf == null) return;
            
            //Apenas pra atribuir as variaveis
            Cliente tempCliente = new Cliente(nome, cpf);
            
            
            if (tempCliente.getCpf() == null) {
                JOptionPane.showMessageDialog(null, 
                    "CPF inválido! Deve conter 11 dígitos. \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                tentativas++;
            } else {
                // Verifica se CPF já existe
                boolean cpfExistente = false;
                for (int i = 0; i < loja.getNumeroClientes(); i++) {
                    if (loja.clientes[i].getCpf().equals(cpf)) {
                        cpfExistente = true;
                        break;
                    }
               }
                
                if (cpfExistente) {
                    JOptionPane.showMessageDialog(null, 
                        "CPF já cadastrado! \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                    tentativas++;
                } else {
                    loja.adicionarClientes(tempCliente);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    cadastroCompleto = true;
                }
            }
        }
    }
    
    private static void cadastrarProdutos() {
        int id;
        double preco;
        
        String escolherID = JOptionPane.showInputDialog("Digite o ID do produto");
        String nome = JOptionPane.showInputDialog("Digite o nome do produto");
        String escolherPreco = JOptionPane.showInputDialog("Digite o preco do produto");
        
        //AINDA NAO CRIEI UM SISTEMA DE VALIDACAO DE STIRNG PARA NUMEROSSSSSSSSSSSSSSS
        id = Integer.parseInt(escolherID);
        preco = Double.parseDouble(escolherPreco);
        
        Produto produto = new Produto(id, nome, preco);
        loja.adicionarProdutos(produto);
    }
    
    private static void realizarVendas() {
        if (loja.listarClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
            return;
        }
        
        String clientesDisponiveis = loja.listarClientes();
        String escolhaCpf = JOptionPane.showInputDialog("Escolha um cliente:\n" + clientesDisponiveis + "Digite o Cpf do cliente:");
        
        Cliente clienteSelecionado = null;
        for (int i = 0; i < loja.getNumeroClientes(); i++) {
            if(loja.clientes[i].getCpf().equals(escolhaCpf)) {
                clienteSelecionado = loja.clientes[i];
                break;
            }
        }
        
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return;
        }
        
        
    }
}
