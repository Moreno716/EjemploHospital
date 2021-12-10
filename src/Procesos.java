import javax.swing.JOptionPane;

import clases.CitaMedica;
import clases.ModeloDatos;
import clases.Paciente;
import clases.empleado.Empleado;
import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;

public class Procesos {
	
	ModeloDatos miModeloDatos;
	
	public Procesos() {
		miModeloDatos=new ModeloDatos();
		presentarMenuOpciones();
	}
	
	private void presentarMenuOpciones() {
		String menu="MENU HOSPITAL\n\n";
		menu+="1. Registrar paciente\n";
		menu+="2. Registrar empleado\n";
		menu+="3. Registrar cita medica\n";
		menu+="4. Imprimir información\n";
		menu+="5. Salir\n\n";
		menu+="Ingrese una opción: \n";
		
		int opcion=0;
		
		do {
			opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch(opcion) {
			case 1: registrarPaciente(); break;
			case 2: registrarEmpleado(); break;
			case 3: registrarCitaMedica(); break;
			case 4: imprimirInformacion(); break;
			case 5: System.out.println("El sistema ha terminado!"); break;
			default: System.out.println("No existe el codigo, verifique nuevamente");
			break;
			
			}
		}while (opcion!=5);
	}
	
	private void registrarPaciente() 
	{
		String numeroDeDNI = JOptionPane.showInputDialog("Consulte si el numero de DNI ya esta registrado");
		Paciente miPaciente=new Paciente();
		Paciente consultarPaciente = miModeloDatos.buscarPaciente(numeroDeDNI);
		if(consultarPaciente == null)
		{
			miPaciente.registrarDatos();
			miModeloDatos.registrarPersona(miPaciente);
		}
		else
		{
			System.out.println("Ya existe el paciente");
		}
		
		
		
	}
	
	private void registrarEmpleado() {
		String numeroDeDNI = JOptionPane.showInputDialog("Consulte si el numero de codigo ya esta registrado");
		Empleado consultarEmpleadoEventual = miModeloDatos.buscarEmpleadoEventual(numeroDeDNI);
		Empleado consultarEmpleadoPlanilla = miModeloDatos.buscarEmpleadoPlanilla(numeroDeDNI);
		if(consultarEmpleadoEventual == null && consultarEmpleadoPlanilla == null)
		{
			String menuTipoEmpleado="Registro de Empleado\n";
			menuTipoEmpleado+="1. Empleado Eventual\n";
			menuTipoEmpleado+="2. Empleado por Planilla\n";
			menuTipoEmpleado+="Seleccione el tipo de empleado a registrar\n"; 
			
			int tipoEmpleado=Integer.parseInt(JOptionPane.showInputDialog(menuTipoEmpleado));
			
			switch(tipoEmpleado) {
			case 1: //Registrar Empleado Eventual
				EmpleadoEventual miEmpleadoEventual=new EmpleadoEventual();
				miEmpleadoEventual.registrarDatos();
				miModeloDatos.registrarPersona(miEmpleadoEventual);
			  break;
			case 2:
				String resp=JOptionPane.showInputDialog("Ingrese si, si es un medico, de lo contrario es otro tipo de empleado");
				if (resp.equalsIgnoreCase("si")) {
					//Registro Medico
					Medico miMedico=new Medico();
					miMedico.registrarDatos();
					miModeloDatos.registrarPersona(miMedico);
				}else {
					//Registro Empleado Planilla
					EmpleadoPlanilla miEmpleadoPlanilla=new EmpleadoPlanilla();
					miEmpleadoPlanilla.registrarDatos();
					miModeloDatos.registrarPersona(miEmpleadoPlanilla);
				}
				
				break;
				
			default:System.out.println("El tipo de empleado no es valido, intentelo nuevamente");
			    break;
			}
		}
		else
		{
			System.out.println("Ya existe el empleado");
		}
		
	}
	
	private void imprimirInformacion() {
		
		String menuImprimir="MENU IMPRESIONES\n";
		menuImprimir+="1. Listar Pacientes\n";
		menuImprimir+="2. Listar Empleados Eventuales\n";
		menuImprimir+="3. Listar Empleados Por Planilla\n";
		menuImprimir+="4. Listar Medicos\n";
		menuImprimir+="5. Listar Citar Programadas\n";
		menuImprimir+="Ingrese una opción\n";
		
		System.out.println("****************************************************");
		
		int opcion=Integer.parseInt(JOptionPane.showInputDialog(menuImprimir));
		
		switch(opcion) {
		case 1: miModeloDatos.imprimirPacientes(); break;
		case 2: miModeloDatos.imprimirEmpleadosEventuales(); break;
		case 3: miModeloDatos.imprimirEmpleadosPorPlanilla(); break;
		case 4: miModeloDatos.imprimirMedicos(); break;
		case 5: miModeloDatos.imprimirCitasMedicasProgramadas(); break;
		
		default: System.out.println("No existe esa opción");
		    break;
		}
	}

	private void registrarCitaMedica() {
		String documentoPaciente=JOptionPane.showInputDialog("Ingrese el documento del paciente: ");
		
		Paciente pacienteEncontrado=miModeloDatos.consultarPacientePorDocumento(documentoPaciente);
		
		if (pacienteEncontrado!=null) {
			String nombreMedico=JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
			Medico medicoEncontrado=miModeloDatos.consultarMedicoPorNombre(nombreMedico);
			
			if (medicoEncontrado!=null) {
				String servicio=JOptionPane.showInputDialog("Ingrese el servicio o motivo de la consulta: ");
				String fechaConsulta=JOptionPane.showInputDialog("Ingrese la fecha de la consulta: ");
				String horaConsulta=JOptionPane.showInputDialog("Ingrese la hora de la consulta: ");
				
				String lugar="La cita sera en el consultorio "+medicoEncontrado.getNumeroDeConsultorio();
				CitaMedica miCita=new CitaMedica(pacienteEncontrado, medicoEncontrado, servicio, fechaConsulta, horaConsulta, lugar);
				miModeloDatos.registrarCitaMedica(miCita);
			}else {
				System.out.println("El medico no se encuentra registrado en el sistema");
			}
		}else {
			System.out.println("El paciente no se encuenta registrado en el sistema");
		}
	}
}