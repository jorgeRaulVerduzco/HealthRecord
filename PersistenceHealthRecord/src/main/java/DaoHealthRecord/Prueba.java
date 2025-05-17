/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DaoHealthRecord;

import Entities.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JORGE
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
UserDAO userDAO = new UserDAO();
        TutorDAO tutorDAO = new TutorDAO();
        PatientDAO patientDAO = new PatientDAO();
        HealthWorkerDAO hwDAO = new HealthWorkerDAO();
        ExpedientDAO expDAO = new ExpedientDAO();
        MedicalAppointmentDAO maDAO = new MedicalAppointmentDAO();

        // ------------------ UserDAO ------------------
        System.out.println("\n--- Probando UserDAO ---");
        User user = new User("CURP00030", "Juan", "Perez", "1234", "bioUser", 28);
        userDAO.crear(user);
        System.out.println("Crear User: " + user);
 // ------------------ UserDAO ------------------
        System.out.println("\n--- Probando UserDAO ---");
        User user2 = new User("CURP00040", "Juan", "Perez", "1234", "bioUser", 28);
        userDAO.crear(user2);
        System.out.println("Crear User: " + user2);
 System.out.println("\n--- Probando UserDAO ---");
        User user3 = new User("CURP00051", "Juan", "Perez", "1234", "bioUser", 28);
        userDAO.crear(user2);
        System.out.println("Crear User: " + user3);

//        User uById = userDAO.buscarPorId(user.getUserId());
//        System.out.println("Buscar por ID: " + uById);
//
//        User uByCurp = userDAO.buscarPorCurp("CURP00001");
//        System.out.println("Buscar por CURP: " + uByCurp);
//
//        List<User> allUsers = userDAO.listarTodos();
//        System.out.println("Listar todos:"); allUsers.forEach(System.out::println);
//
//        User validCreds = userDAO.validarCredenciales("CURP00001", "1234");
//        System.out.println("Validar credenciales: " + validCreds);
//
//        User validBio = userDAO.validarBiometricos("bioUser");
//        System.out.println("Validar biométricos: " + validBio);
//
//        // Actualizar usuario
//        user.setLastName("Perez-Rodriguez");
//        userDAO.actualizar(user);
//        System.out.println("Actualizar user: " + userDAO.buscarPorId(user.getUserId()));
//
//        // Actualizar por CURP
//        User modified = new User("CURP00001", "JuanUpdated", "PerezUpd", "abcd", "newBio", 29);
//        userDAO.actualizarPorCurp("CURP00001", modified);
//        System.out.println("Actualizar por CURP: " + userDAO.buscarPorCurp("CURP00001"));

        // Eliminar
//        userDAO.eliminarPorCurp("CURP00001");
//        System.out.println("Eliminar por CURP. Resultado búsqueda: " + userDAO.buscarPorCurp("CURP00001"));
//
//        // ------------------ TutorDAO ------------------
//        System.out.println("\n--- Probando TutorDAO ---");
//        Tutor tutor = new Tutor("CURP00002", "Laura", "Martinez", "passT", "bioT", 35, new ArrayList<>());
//        tutorDAO.crear(tutor);
//        System.out.println("Crear Tutor: " + tutor);
//
//        Tutor tById = tutorDAO.buscarPorId(tutor.getTutorId());
//        System.out.println("Buscar por ID: " + tById);
//
//        Tutor tByCurp = tutorDAO.buscarPorCurp("CURP00002");
//        System.out.println("Buscar por CURP: " + tByCurp);
//
//        List<Tutor> allTutors = tutorDAO.listarTodos();
//        System.out.println("Listar todos: "); allTutors.forEach(System.out::println);
//
//        List<Tutor> adultTutors = tutorDAO.buscarTutoresMayoresDeEdad();
//        System.out.println("Tutores mayores de edad: "); adultTutors.forEach(System.out::println);
//
//        // Validaciones
//        System.out.println("Es tutor válido? " + tutorDAO.esTutorValido(tutor));
//        System.out.println("Validar biométricos: " + tutorDAO.validarBiometricos("bioT"));
//
//        // Actualizar tutor
//        tutor.setAge(36);
//        tutorDAO.actualizar(tutor);
//        System.out.println("Actualizar tutor: " + tutorDAO.buscarPorId(tutor.getTutorId()));
//
//        // Actualizar por CURP
//        Tutor modT = new Tutor("CURP00002", "LauraUp", "MartinezUp", "passTU", "bioTU", 37, null);
//        tutorDAO.actualizarPorCurp("CURP00002", modT);
//        System.out.println("Actualizar por CURP: " + tutorDAO.buscarPorCurp("CURP00002"));
//
//        // Eliminar tutor
////        tutorDAO.eliminarPorCurp("CURP00002");
////        System.out.println("Eliminar por CURP. Resultado búsqueda: " + tutorDAO.buscarPorCurp("CURP00002"));
//
//        // ------------------ PatientDAO ------------------
//        System.out.println("\n--- Probando PatientDAO ---");
//        // Re-crear tutor para paciente
//        tutor = new Tutor("CURP00003", "Carlos", "Lopez", "passC", "bioC", 40, new ArrayList<>());
//        tutorDAO.crear(tutor);
//
//        Patient patient = new Patient("CURP00004", "Ana", "Gutierrez", "passP", "bioP", 15, tutor);
//        patientDAO.crear(patient);
//        System.out.println("Crear Patient: " + patient);
//
//        Patient pById = patientDAO.buscarPorId(patient.getPatientId());
//        System.out.println("Buscar por ID: " + pById);
//
//        Patient pByCurp = patientDAO.buscarPorCurp("CURP00004");
//        System.out.println("Buscar por CURP: " + pByCurp);
//
//        List<Patient> allPatients = patientDAO.listarTodos();
//        System.out.println("Listar todos: "); allPatients.forEach(System.out::println);
//
//        List<Patient> byTutor = patientDAO.listarPorTutor(tutor);
//        System.out.println("Pacientes por tutor: "); byTutor.forEach(System.out::println);
//
//        System.out.println("Menores sin tutor: " + patientDAO.buscarMenoresSinTutor());
//        System.out.println("Validar biométricos: " + patientDAO.validarBiometricos("bioP"));
//
//        // Asignar tutor a paciente existente
//        Patient newPatient = new Patient("CURP00005", "LauraP", "Sanchez", "passNP", "bioNP", 17, null);
//        patientDAO.crear(newPatient);
//        System.out.println("Menores sin tutor antes de asignar: " + patientDAO.buscarMenoresSinTutor());
//        patientDAO.asignarTutor(newPatient, tutor);
//        System.out.println("Menores sin tutor después de asignar: " + patientDAO.buscarMenoresSinTutor());
//
//        // Actualizar paciente
//        patient.setLastName("Gutierrez-Up");
//        patientDAO.actualizar(patient);
//        System.out.println("Actualizar patient: " + patientDAO.buscarPorId(patient.getPatientId()));
//
//        // Actualizar por CURP
//        Patient modP = new Patient("CURP00004", "AnaUp", "GutUp", "passPU", "bioPU", 16, null);
//        patientDAO.actualizarPorCurp("CURP00004", modP);
//        System.out.println("Actualizar por CURP: " + patientDAO.buscarPorCurp("CURP00004"));
//
//        // Eliminar paciente
////        patientDAO.eliminarPorCurp("CURP00005");
////        System.out.println("Eliminar por CURP newPatient: " + patientDAO.buscarPorCurp("CURP00005"));
//
//        // ------------------ HealthWorkerDAO ------------------
//        System.out.println("\n--- Probando HealthWorkerDAO ---");
//        HealthWorker hw = new HealthWorker("CURP00006", "Diego", "Ramirez", "passHW", "bioHW", 50, "LIC-100");
//        hwDAO.crear(hw);
//        System.out.println("Crear HW: " + hw);
//
//        HealthWorker hwById = hwDAO.buscarPorId(hw.getHealthWorkerId());
//        System.out.println("Buscar HW por ID: " + hwById);
//
//        HealthWorker hwByCurp = hwDAO.buscarPorCurp("CURP00006");
//        System.out.println("Buscar HW por CURP: " + hwByCurp);
//
//        HealthWorker hwByLicense = hwDAO.buscarPorCedulaProfesional("LIC-100");
//        System.out.println("Buscar HW por cédula: " + hwByLicense);
//
//        List<HealthWorker> allHW = hwDAO.listarTodos();
//        System.out.println("Listar todos HW: "); allHW.forEach(System.out::println);
//
//        System.out.println("Validar biométricos HW: " + hwDAO.validarBiometricos("bioHW"));
//        System.out.println("Cédula válida? " + hwDAO.tieneCedulaProfesionalValida(hw));
//
//        // Actualizar HW
//        hw.setProfessionalLicense("LIC-101");
//        hwDAO.actualizar(hw);
//        System.out.println("Actualizar HW: " + hwDAO.buscarPorId(hw.getHealthWorkerId()));
//        // Actualizar por CURP y por cédula
//        HealthWorker modHW = new HealthWorker("CURP00006", "DiegoUp", "RamUp", "passHWU", "bioHWU", 51, "LIC-102");
//        hwDAO.actualizarPorCurp("CURP00006", modHW);
//        hwDAO.actualizarPorProfessionalLicense("LIC-101", modHW);
//        System.out.println("Actualizar por CURP y cédula: " + hwDAO.buscarPorCedulaProfesional("LIC-102"));
//
////        hwDAO.eliminarPorCurp("CURP00006");
////        System.out.println("Eliminar HW por CURP: " + hwDAO.buscarPorCurp("CURP00006"));
//
//       
//        // ------------------ ExpedientDAO ------------------
//        System.out.println("\n--- Probando ExpedientDAO ---");
//        Patient patExp = new Patient("CURP00007", "Sofia", "Rojas", "passExp", "bioExp", 25, tutor);
//        patientDAO.crear(patExp);
//        Expedient exp = new Expedient(patExp, new SimpleDateFormat("yyyy-MM-dd").parse("2025-05-01"), new ArrayList<>(), false);
//        expDAO.crear(exp);
//        System.out.println("Crear Expedient: " + exp);
//        System.out.println("Buscar por ID: " + expDAO.buscarPorId(exp.getExpedientId()));
//        System.out.println("Buscar por Paciente: " + expDAO.buscarPorPaciente(patExp));
//        System.out.println("Listar todos: "); expDAO.listarTodos().forEach(System.out::println);
//        expDAO.cambiarAutorizacion(exp, true);
//        System.out.println("Cambiar autorización: " + expDAO.buscarPorId(exp.getExpedientId()).isAuthorization());
//        Document doc = new Document("PDF", "Reporte.pdf", "base64doc");
//        expDAO.agregarDocumento(exp, doc);
//        System.out.println("Listar Documentos: " + expDAO.listarDocumentos(exp));
//        System.out.println("Verificar acceso (HW no válido): " + expDAO.verificarAcceso(exp, new HealthWorker()));
//        System.out.println("Listar por Fecha Creación: ");
//        Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse("2025-04-01");
//        Date fin = new SimpleDateFormat("yyyy-MM-dd").parse("2025-06-01");
//        expDAO.listarPorFechaCreacion(inicio, fin).forEach(System.out::println);
//        System.out.println("Buscar por CURP Paciente: " + expDAO.buscarPorCurpPaciente("CURP00007"));
//        Expedient modExp = new Expedient(null, null, null, false);
//        modExp.setAuthorization(false);
//        expDAO.actualizarPorCurpPaciente("CURP00007", modExp);
//        System.out.println("Actualizar por CURP Paciente: " + expDAO.buscarPorCurpPaciente("CURP00007"));
////        expDAO.eliminarPorCurpPaciente("CURP00007");
////        System.out.println("Eliminar por CURP Paciente: " + expDAO.buscarPorCurpPaciente("CURP00007"));
//
//        // ------------------ MedicalAppointmentDAO ------------------
//        System.out.println("\n--- Probando MedicalAppointmentDAO ---");
//        HealthWorker hwMA = new HealthWorker("CURP00008", "CarlosHW", "SuarezHW", "passMA", "bioMA", 55, "LIC-300");
//        hwDAO.crear(hwMA);
//        Patient patMA = new Patient("CURP00009", "Elena", "DiazMA", "passMA2", "bioMA2", 30, tutor);
//        patientDAO.crear(patMA);
//        Date fecha1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2025-05-20 09:00");
//        MedicalAppointment cita1 = new MedicalAppointment(hwMA, patMA, fecha1, "PENDIENTE", "Consulta general");
//        maDAO.crear(cita1);
//        System.out.println("Crear cita: " + cita1);
//        System.out.println("Buscar por ID: " + maDAO.buscarPorId(cita1.getMedicalAppointmentId()));
//        System.out.println("Listar todas: "); maDAO.listarTodas().forEach(System.out::println);
//        System.out.println("Listar por Paciente: "); maDAO.listarPorPaciente(patMA).forEach(System.out::println);
//        System.out.println("Listar por Trabajador: "); maDAO.listarPorTrabajadorSalud(hwMA).forEach(System.out::println);
//        Date fecha2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2025-05-20 11:00");
//        MedicalAppointment cita2 = new MedicalAppointment(hwMA, patMA, fecha2, "CONFIRMADA", "Revisión lab");
//        maDAO.crear(cita2);
//        System.out.println("Listar por Fechas: ");
//        maDAO.listarPorFechas(fecha1, fecha2).forEach(System.out::println);
//        System.out.println("Listar por Estado PENDIENTE: "); maDAO.listarPorEstado("PENDIENTE").forEach(System.out::println);
//        cita1.setStatus("ATENDIDA"); maDAO.actualizarEstado(cita1, "ATENDIDA");
//        System.out.println("Actualizar estado cita1: " + maDAO.buscarPorId(cita1.getMedicalAppointmentId()));
//        System.out.println("Buscar por CURP Paciente: " + maDAO.buscarPorCurpPaciente("CURP00009"));
//        System.out.println("Buscar por Licencia: "); maDAO.buscarPorProfessionalLicense("LIC-300").forEach(System.out::println);
//        System.out.println("Buscar por CURP Paciente y Licencia: "); maDAO.buscarPorCurpPacienteYProfessionalLicense("CURP00009", "LIC-300").forEach(System.out::println);
////        maDAO.eliminarPorCurpPaciente("CURP00009", cita2.getMedicalAppointmentId());
////        System.out.println("Eliminar cita2 por CURP Paciente: " + maDAO.buscarPorCurpPaciente("CURP00009"));
//
//        System.out.println("\nPrueba de DAOs completada.");  }
    }
}
