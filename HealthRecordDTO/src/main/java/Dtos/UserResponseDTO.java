/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

/**
 *
 * @author JORGE
 */
public class UserResponseDTO {
      
  private long userId;
    private String curp;
    private String name;
    private String lastName;
    private int age;
    private String userType;
    
    // Campos específicos según el tipo de usuario
    private String professionalLicense; // Para HealthWorker
    private Long tutorId;               // Para Patient
    private String tutorCurp;           // Para Patient
    private Long expedientId;           // Para Patient
    
    public UserResponseDTO() {
    }
    
    public long getUserId() {
        return userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public String getCurp() {
        return curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getProfessionalLicense() {
        return professionalLicense;
    }
    
    public void setProfessionalLicense(String professionalLicense) {
        this.professionalLicense = professionalLicense;
    }
    
    public Long getTutorId() {
        return tutorId;
    }
    
    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }
    
    public String getTutorCurp() {
        return tutorCurp;
    }
    
    public void setTutorCurp(String tutorCurp) {
        this.tutorCurp = tutorCurp;
    }
    
    public Long getExpedientId() {
        return expedientId;
    }
    
    public void setExpedientId(Long expedientId) {
        this.expedientId = expedientId;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" + "userId=" + userId + ", curp=" + curp + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", userType=" + userType + ", professionalLicense=" + professionalLicense + ", tutorId=" + tutorId + ", tutorCurp=" + tutorCurp + ", expedientId=" + expedientId + '}';
    }
    
    
}
