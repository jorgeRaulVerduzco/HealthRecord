/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author JORGE
 */
public class AuthenticationRequest {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "CURP no puede estar vacío")
    @Size(min = 18, max = 18, message = "CURP debe tener 18 caracteres")
    private String curp;
    
    @NotEmpty(message = "Contraseña no puede estar vacía")
    private String password;
    
    public AuthenticationRequest() {
    }
    
    public AuthenticationRequest(String curp, String password) {
        this.curp = curp;
        this.password = password;
    }
    
    public String getCurp() {
        return curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
