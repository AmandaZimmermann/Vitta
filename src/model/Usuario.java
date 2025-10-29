package model;

public class Usuario {
    private Integer id;
    private String username;
    private String senha;
    private PermissaoTipo permissao;
    private Integer idPessoa;
    private Boolean ativo;

    public Usuario() {}

    public Usuario(Integer id, String username, String senha, PermissaoTipo permissao, Integer idPessoa, Boolean ativo) {
        this.id = id;
        this.username = username;
        this.senha = senha;
        this.permissao = permissao;
        this.idPessoa = idPessoa;
        this.ativo = ativo;
    }

    public Usuario(String username, String senha, PermissaoTipo permissao, Integer idPessoa, Boolean ativo) {
        this(null, username, senha, permissao, idPessoa, ativo);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public PermissaoTipo getPermissao() { return permissao; }
    public void setPermissao(PermissaoTipo permissao) { this.permissao = permissao; }

    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", permissao=" + permissao +
                ", idPessoa=" + idPessoa +
                ", ativo=" + ativo +
                '}';
    }
}
