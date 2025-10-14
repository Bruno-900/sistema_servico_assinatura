package com.antivirus.servicoassinatura.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static String URL = "jdbc:mysql://localhost:3306/nome_banco";
    private static String USER = "root";
    private static String SENHA  = "12345";

    public static Connection conexao(){
        Connection conexao = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(URL, USER, SENHA);
            System.out.println("Conexão bem sucedida ✅!");
        }catch (ClassNotFoundException erro){
            System.out.println("O Driver JDBC não foi encontrado ❌!");;
            erro.printStackTrace();
        }catch (SQLException erro){
                System.out.println("Erro na conexão com o Banco de dados ❌!");
            }

            return conexao;
    }

    public static void main(String[] args){
        Connection conexao = conexao();
        if (conexao != null){
            try {
                conexao.close();
                System.out.println("Conexao encerrada.");
            } catch (SQLException errro){
                System.out.println("Erro ao fechar a conexão:" + errro.getMessage());
            }
        }
    }
}
