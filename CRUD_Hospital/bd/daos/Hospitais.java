package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Hospitais {
    
    //CRUD 
    //em daos todos os metodos devem ser static 

    public static boolean cadastrado (String idRgCpf) throws Exception
    {
      //verdadeiro ou falso se o pacient esta cadastrado 

      boolean retorno = false; 

      try
      {
         String sql; 
         sql = "SELECT * "+
                "FROM HOSPITAL "+
                "WHERE IDRGCPF = ?"; 

         BDSQLServer.COMANDO.prepareStatement(sql); 
         BDSQLServer.COMANDO.setString (1, idRgCpf);

         MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery(); //quando é select usamos executeQuery()

         retorno = resultado.first(); //vai pra primeira linha do resultado  
      }
      catch(SQLException erro)
      {
        throw new Exception ("Erro ao procurar paciente"); 
      }

      return retorno; 
    }

    //INCLUIR   
    public static void incluir (Hospital paciente) throws Exception
    {
      if(paciente==null)
         throw new Exception ("Paciente nao fornecido"); 

         try
         {
           String sql; 

           sql = "INSERT INTO HOSPITAL "+
                "(IDRGCPF,NOME,TELEFONE,NASCIMENTO,NATURALIDADE,EMAIL,CEP,NUMERO,COMPLEMENTO) VALUES"+
                "(?,?,?,?,?,?,?,?,?)"; //isso vai ser trocado por novos dados depois da execucao

            BDSQLServer.COMANDO.prepareStatement(sql); //preparar execucao do comando sql

            //execucoes
            BDSQLServer.COMANDO.setString   (1, paciente.getIdRgCpf());
            BDSQLServer.COMANDO.setString(2, paciente.getNome() );
            BDSQLServer.COMANDO.setString(3, paciente.getTelefone());
            BDSQLServer.COMANDO.setString(4, paciente.getNascimento()); 
            BDSQLServer.COMANDO.setString(5, paciente.getNaturalidade()); 
            BDSQLServer.COMANDO.setString(6, paciente.getEmail());
            BDSQLServer.COMANDO.setString(7, paciente.getCep());
            BDSQLServer.COMANDO.setInt   (8, paciente.getNumero());
            BDSQLServer.COMANDO.setString(9, paciente.getComplemento());

            BDSQLServer.COMANDO.executeUpdate(); //atualiza o bd ->inserindo, neste caso 
            BDSQLServer.COMANDO.commit(); //efetiva
         }
         catch(SQLException erro)
         {
             System.err.println(erro.getMessage());
           BDSQLServer.COMANDO.rollback(); //desfaz td que foi feito na insercao mal sucedida 
           throw new Exception("Erro ao inserir paciente"); 
         }
    }

    //EXCLUIR
    public static void excluir (String idRgCpf) throws Exception
    {
       if(!cadastrado (idRgCpf))
          throw new Exception ("Nao cadastrado"); 

      try
      {
        String sql; 

        sql = "DELETE FROM HOSPITAL "+
              "WHERE IDRGCPF=?"; 

        BDSQLServer.COMANDO.prepareStatement (sql);
        
        BDSQLServer.COMANDO.setString (1, idRgCpf);
        
        BDSQLServer.COMANDO.executeUpdate(); 
        BDSQLServer.COMANDO.commit(); 
      }
      catch(SQLException erro)
      {
        BDSQLServer.COMANDO.rollback(); 
        throw new Exception ("Erro ao excluir paciente"); 
      }
    }
       
    //ALTERAR 
    public static void alterar (Hospital paciente) throws Exception
    {
      if (paciente == null)
          throw new Exception ("Paciente nao fornecido"); 

      if (!cadastrado (paciente.getIdRgCpf()))
        throw new Exception ("Nao cadastrado"); 

      try
      {
        String sql; 

        sql = "UPDATE HOSPITAL"+
              " SET NOME = ?,"+
              " TELEFONE = ?,"+
              " NASCIMENTO = ?,"+
              " NATURALIDADE = ?,"+
              " EMAIL = ?,"+
              " CEP = ?,"+
              " NUMERO = ?,"+
              " COMPLEMENTO = ?"+
              " WHERE IDRGCPF = ?";

        BDSQLServer.COMANDO.prepareStatement(sql);

        BDSQLServer.COMANDO.setString (1, paciente.getNome()); 
        BDSQLServer.COMANDO.setString(2, paciente.getTelefone());
        BDSQLServer.COMANDO.setString(3, paciente.getNascimento());
        BDSQLServer.COMANDO.setString(4, paciente.getNaturalidade());
        BDSQLServer.COMANDO.setString(5, paciente.getEmail());
        BDSQLServer.COMANDO.setString (6, paciente.getCep()); 
        BDSQLServer.COMANDO.setInt(7, paciente.getNumero());
        BDSQLServer.COMANDO.setString(8, paciente.getComplemento());
        BDSQLServer.COMANDO.setString(9, paciente.getIdRgCpf());

        BDSQLServer.COMANDO.executeUpdate();
        BDSQLServer.COMANDO.commit();
      }  
      catch(SQLException erro)
      {
        BDSQLServer.COMANDO.rollback(); 
        throw new Exception ("Erro ao atualizar dados do paciente"); 
      }    
    }

   //metodo para consultar dados 
   public static Hospital getPaciente (int idRgCpf) throws Exception
   {
     Hospital paciente = null; 

     try 
     {
       String sql; 

       sql = "SELECT * "+
             "FROM HOSPITAL "+
             "WHERE IDRGCPF = ?"; 

        BDSQLServer.COMANDO.prepareStatement(sql);

        BDSQLServer.COMANDO.setInt (1, idRgCpf); 

        MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery(); 

        if(!resultado.first())
          throw new Exception("Nao cadastrado");

        paciente = new Hospital (resultado.getString ("IDRGCPF"),
        resultado.getString("NOME"), 
        resultado.getString ("TELEFONE"), 
        resultado.getString("NASCIMENTO"), 
        resultado.getString ("NATURALIDADE"), 
        resultado.getString ("EMAIL"), 
        resultado.getString ("CEP"), 
        resultado.getInt ("NUMERO"), 
        resultado.getString ("COMPLEMENTO")); 
     }
     catch(SQLException erro)
     {
       throw new Exception("Erro ao procurar paciente"); 
     }

     return paciente; 
   }

   //metodo para recuperar todos os pacientes 
   public static MeuResultSet getPacientes () throws Exception
   {
    MeuResultSet resultado = null; 

    try
    {
      String sql; 

      sql = "SELECT * "+
            "FROM HOSPITAL"; 

      BDSQLServer.COMANDO.prepareStatement(sql);

      resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery(); 
    }
    catch(SQLException erro)
    {
       throw new Exception("Erro ao recuperar pacientes"); 
    }

    return resultado;
   }

}
