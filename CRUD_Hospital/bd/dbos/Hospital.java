package bd.dbos;

public class Hospital implements Cloneable
{
    //declaracao de dados do bd
    private String idRgCpf;
    private String nome; 
    private String telefone; 
    private String nascimento; 
    private String naturalidade; 
    private String email; 
    private String cep; 
    private int numero; 
    private String complemento;

    //Getters e setters para cada atributo

    public void setIdRgCpf(String idRgCpf) throws Exception
    {
        if(idRgCpf==null || idRgCpf.equals(""))
            throw new Exception("ID inválido");

        this.idRgCpf = idRgCpf;
    }

    public void setNome(String nome) throws Exception
    {
        if(nome==null || nome.equals(""))
         throw new Exception("Nome nao fornecido"); 

         this.nome = nome; 
    }

    public void setTelefone (String telefone) throws Exception
    {
      if(telefone==null || telefone.equals(""))
       throw new Exception("Telefone nao fornecido"); 

       this.telefone = telefone; 
    }

    public void setNascimento (String nascimento) throws Exception
    {
        if(nascimento==null || nascimento.equals(""))
         throw new Exception("Nascimento nao fornecido"); 

         this.nascimento = nascimento; 
    }

    public void setNaturalidade (String naturalidade) throws Exception
    {
        if(naturalidade==null || naturalidade.equals(""))
         throw new Exception("Naturalidade nao fornecida"); 

         this.naturalidade = naturalidade; 
    }

    public void setEmail (String email) throws Exception
    {
        if(email==null || email.equals(""))
         throw new Exception("Email nao fornecido"); 

         this.email = email; 
    }

    public void setCep (String cep) throws Exception
    {
        if(cep==null || cep.equals(""))
         throw new Exception("Cep nao fornecido"); 

         this.cep = cep; 
    }

    public void setNumero (int numero) throws Exception
    {
        if(numero <= 0)
         throw new Exception("Numero nao fornecido"); 

         this.numero = numero; 
    }

    public void setComplemento (String complemento) throws Exception
    {
        if(complemento==null || complemento.equals(""))
         throw new Exception("Complemento nao fornecido"); 

         this.complemento = complemento; 
    }

    public String getIdRgCpf () {return this.idRgCpf; }

    public String getNome () {return this.nome; }

    public String getTelefone () {return this.telefone; }

    public String getNascimento () {return this.nascimento; }

    public String getNaturalidade () {return this.naturalidade; }

    public String getEmail () {return this.email; }

    public String getCep () {return this.cep; }

    public int getNumero () {return this.numero; }

    public String getComplemento () {return this.complemento; }

    //Construtor 
    public Hospital (String idRgCpf, String nome, String telefone, String nascimento, String naturalidade, String email, String cep, int numero, String complemento) throws Exception
    {
       this.setIdRgCpf(idRgCpf);
       this.setNome(nome);
       this.setTelefone(telefone);
       this.setNascimento(nascimento);
       this.setNaturalidade(naturalidade);
       this.setEmail(email);
       this.setCep(cep);
       this.setNumero(numero);
       this.setComplemento(complemento);
    }

    //metodos obrigatorios 
    public String toString()
    {
        String ret=""; 

        ret+="Rg/Cpf......: "+this.idRgCpf+"\n";
        ret+="Nome........: "+this.nome+"\n";
        ret+="Telefone....: "+this.telefone+"\n";
        ret+="Nascimento..: "+this.nascimento+"\n";
        ret+="Naturalidade: "+this.naturalidade+"\n"; 
        ret+="Email.......: "+this.email+"\n";
        ret+="Cep.........: "+this.cep+"\n";
        ret+="Numero......: "+this.numero+"\n";
        ret+="Complemento.: "+this.complemento;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if(this==obj)
        return true; 

        if(obj==null)
        return false; 

        if(!(obj instanceof Hospital))
          return false; 

        Hospital hosp = (Hospital)obj; 

        if(this.idRgCpf!=hosp.idRgCpf)
           return false; 

        if(this.nome.equals(hosp.nome))
           return false; 

        if(this.telefone.equals(hosp.telefone))
           return false; 

        if(this.nascimento.equals(hosp.nascimento))
        return false; 

        if(this.naturalidade.equals(hosp.naturalidade))
         return false; 

        if(this.email.equals(hosp.email))
          return false; 

        if(this.cep.equals(hosp.cep))
        return false; 

        if(this.numero!=hosp.numero)
         return false; 

        if(this.complemento.equals(hosp.complemento))
        return false; 

        return true; 
    }

    public int hashCode ()
    {
        int ret=666; 

        ret = 7*ret +  Integer.valueOf(this.idRgCpf).hashCode();
        ret = 7*ret +  this.nome.hashCode(); 
        ret = 7*ret +  this.telefone.hashCode(); 
        ret = 7*ret +  this.nascimento.hashCode(); 
        ret = 7*ret +  this.naturalidade.hashCode(); 
        ret = 7*ret +  this.email.hashCode(); 
        ret = 7*ret +  this.cep.hashCode(); 
        ret = 7*ret +  Integer.valueOf(this.numero).hashCode();
        ret = 7*ret + this.complemento.hashCode(); 

        return ret;
    }

    public Hospital (Hospital modelo) throws Exception
    {
        this.idRgCpf = modelo.idRgCpf; //não clono pq não é obj 
        this.nome = modelo.nome; //não clono, pq não é clonavel
        this.telefone = modelo.telefone;
        this.nascimento = modelo.nascimento; 
        this.naturalidade = modelo.naturalidade; 
        this.email = modelo.email; 
        this.cep = modelo.cep; 
        this.numero = modelo.numero; 
        this.complemento = modelo.complemento;  
    }

    public Object clone()
    {
        Hospital ret = null; 

        try
        {
          ret = new Hospital(this); 
        }
        catch(Exception erro)
        {}  // nao trato, pq this nunca é null e construtor de
        // copia da excecao qdo seu parametro for null

        return ret; 
    }

}

