package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import clases.empleado.Empleado;
import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;

public class ModeloDatos {
	
	HashMap<String, Paciente> pacientesMap;
	HashMap<String, EmpleadoPlanilla> empleadosPlanillaMap;
	HashMap<String, EmpleadoEventual> empleadosEventualMap;
	HashMap<String, Medico> medicosMap;
	ArrayList<CitaMedica> citasList;
	
	public ModeloDatos() {
		pacientesMap=new HashMap<String, Paciente>();
		empleadosPlanillaMap=new HashMap<String, EmpleadoPlanilla>();
		medicosMap=new HashMap<String, Medico>();
		empleadosEventualMap=new HashMap<String, EmpleadoEventual>();
		citasList=new ArrayList<CitaMedica>();
	}
	
	public void registrarPersona(Paciente miPaciente) {
		pacientesMap.put(miPaciente.getNumeroDeDNI(), miPaciente);
		System.out.println("Se ha registrado el paciente "+miPaciente.getNombre()+" Satisfactoriamente");
	}
	
	public void registrarPersona(EmpleadoPlanilla miEmpPlanilla) {
		empleadosPlanillaMap.put(miEmpPlanilla.getNumeroDeDNI(), miEmpPlanilla);
		System.out.println("Se ha registrado el paciente "+miEmpPlanilla.getNombre()+" Satisfactoriamente");
	}
	
	public void registrarPersona(EmpleadoEventual miEmpEventual) {
		empleadosEventualMap.put(miEmpEventual.getNumeroDeDNI(), miEmpEventual);
		System.out.println("Se ha registrado el paciente "+miEmpEventual.getNombre()+" Satisfactoriamente");
	}
	
	public void registrarPersona(Medico miMedico) {
		empleadosPlanillaMap.put(miMedico.getNumeroDeDNI(), miMedico);
		System.out.println("Se ha registrado el paciente "+miMedico.getNombre()+" Satisfactoriamente");
	}

	public void imprimirPacientes() {
		String msj="PACIENTES REGISTRADOS\n";
		Iterator<String> iterador=pacientesMap.keySet().iterator();
		
		if (pacientesMap.size()>0) {
			while (iterador.hasNext()) {
				String clave = iterador.next();
				pacientesMap.get(clave).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay pacientes!");
		}
		
		
	}

	public void imprimirEmpleadosEventuales() {
		String msj="EMPLEADOS EVENTUALES REGISTRADOS\n";
		
		if (empleadosEventualMap.size()>0) {
			for(String clave : empleadosEventualMap.keySet()) {
				empleadosEventualMap.get(clave).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No se encontro el empleado!");
		}
		
	}

	public void imprimirEmpleadosPorPlanilla() {
		String msj="EMPLEADOS POR PLANILLA REGISTRADOS\n";
		
		if (empleadosPlanillaMap.size()>0) {
			for (EmpleadoPlanilla empleadoPlanilla : empleadosPlanillaMap.values()) {
				empleadoPlanilla.imprimirDatosPersona(msj);
			}
		}
		System.out.println("\n¡MEDICOS!\n");
		if(medicosMap.size()>0) {
			for (Map.Entry<String, Medico> elemento : medicosMap.entrySet()) {
				//System.out.println("Key = " + elemento.getKey() + ", Value = " + elemento.getValue());
				//medicosMap.get(elemento.getKey()).imprimirDatosPersona(msj);
				elemento.getValue().imprimirDatosPersona(msj);
			}
		}else{
			System.out.println("No se encontro el medico!");
		}
		
	}

	public void imprimirMedicos() {
		String msj="MEDICOS REGISTRADOS\n";
		
		if(medicosMap.size()>0) {
			for (Map.Entry<String, Medico> elemento : medicosMap.entrySet()) {
				//System.out.println("Key = " + elemento.getKey() + ", Value = " + elemento.getValue());
				//medicosMap.get(elemento.getKey()).imprimirDatosPersona(msj);
				elemento.getValue().imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No se encontro el medico!");
		}
		
	}
	
	public Paciente buscarPaciente(String numeroDeDNI)
	{
		for (Paciente paciente : pacientesMap.values())
		{
			if(paciente.getNumeroDeDNI().equalsIgnoreCase(numeroDeDNI))
			{
				return paciente;
			}
		}

		return null;
	}
	
	public Empleado buscarEmpleadoPlanilla(String numeroDeDNI)
	{
		for (Empleado empleado : empleadosPlanillaMap.values())
		{
			if(empleado.getNumeroDeDNI().equalsIgnoreCase(numeroDeDNI))
			{
				return empleado;
			}
		}

		return null;
	}
	
	public Empleado buscarEmpleadoEventual(String numeroDeDNI)
	{
		for (Empleado empleado : empleadosEventualMap.values())
		{
			if(empleado.getNumeroDeDNI().equalsIgnoreCase(numeroDeDNI))
			{
				return empleado;
			}
		}

		return null;
	}
	
	public Medico consultarMedicoPorNombre(String nombreMedico) {
		
		for (Medico medico : medicosMap.values()) {
			
			 if (medico.getNombre().equalsIgnoreCase(nombreMedico)) {
				 return medico;
			 }
			  
		}
		
		//
		return null;
	}
	
	public Paciente consultarPacientePorDocumento(String documentoPaciente) {
		Paciente miPaciente=null;
		
		if (pacientesMap.containsKey(documentoPaciente)) {
			miPaciente=pacientesMap.get(documentoPaciente);
		}
		
		//si el paciente existe lo retorna, sino retorna null
		return miPaciente;
	}
	
	public void registrarCitaMedica(CitaMedica miCita) {
		citasList.add(miCita);
		System.out.println("Se ha registrado la cita exitosamente\n");
		System.out.println(miCita.informacionCitaMedica());
	}
	
	public void imprimirCitasMedicasProgramadas() {
		String msj="CITAS MEDICAS PROGRAMADAS\n";
		CitaMedica miCita=null;
		
		System.out.println(msj+"\n");
		
		if (citasList.size()>0) {
			for (int i = 0; i < citasList.size(); i++) {
				miCita=citasList.get(i);
				System.out.println(miCita.informacionCitaMedica());
			}
		}else {
			System.out.println("No existen citas programadas");
		}
	}
}