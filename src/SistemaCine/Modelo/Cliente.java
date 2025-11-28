package SistemaCine.Modelo;

import java.io.Serializable;

public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nameUser;
    private String password;
    private String mail;

    public Cliente(String nameUser, String password, String mail) {
        this.nameUser = nameUser;
        this.password = password;
        this.mail = mail;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nameUser=" + nameUser + ", password=" + password + ", mail=" + mail + '}';
    }
    
}

