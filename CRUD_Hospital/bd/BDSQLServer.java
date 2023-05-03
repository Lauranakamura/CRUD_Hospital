package bd;

import bd.core.*; 
import bd.daos.*;


public class BDSQLServer {
    public static final MeuPreparedStatement COMANDO; //a inicialização do atributo constante é feita no static  

    static 
    {
        MeuPreparedStatement comando = null; //variável 

        try 
        {
            //comando recebe as informações do bd 
            comando = 
            new MeuPreparedStatement( "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://xxxxxxxx;databasename=xxxxx",
            "xxxxx", "xxxxx");
        }
        catch (Exception erro)
        {
            System.err.println("Problemas de conexao com o BD");
            System.exit(0); //aborta o programa 
        }

        COMANDO = comando; //inicializou atributo estatico COMANDO 
    }
}
