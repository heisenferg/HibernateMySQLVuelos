package clases;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;


public class interaccionBD {

	private static Session session;
	static Vuelos vuelos =new Vuelos();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
consultar();
insertar();
borrar();
cerrar();
		
		
	}

	
	private static void cerrar() {
		session.close();
		System.exit(0);
		
	}


	public static void consultar() {
		
		try {
			//Conexión
			session = Conexion.getSessionFactory().openSession();
			session.beginTransaction();
			// Query
			Query query = session.createQuery("SELECT E.codVuelo, E.destino, E.procedencia, E.horaSalida FROM Vuelos E");
			List<Object[]> empleado = query.list();
			for	(Object[] vuelos : empleado) {
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Código vuelo: " + vuelos[0]+
						" Destino a "+ vuelos[1] + " , y procedente de " + vuelos[2]);
			}
			// Commit
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		
	}
	
	public static void insertar() {
		// Insertamos un vuelo
		
		String codigoVuelo = "UX1094";
		String horaSalida = "06/01/22-17:55";
		String destino = "Madrid";
		String procedencia = "Tenerife Norte";
		int plazasFumador =150;
		int plazasNoFumador = 10;
		int plazasTurista = 140;
		int plazasBussiness = 8;
		
		vuelos.setCodVuelo(codigoVuelo);
		vuelos.setHoraSalida(horaSalida);
		vuelos.setDestino(destino);
		vuelos.setProcedencia(procedencia);
		vuelos.setPlazasFumador(plazasFumador);
		vuelos.setPlazasNoFumador(plazasNoFumador);
		vuelos.setPlazasTurista(plazasTurista);
		vuelos.setPlazasPrimera(plazasBussiness);
		
		
		// Conexión
		try {
		session = Conexion.getSessionFactory().openSession();
		session.beginTransaction();
		
		System.out.println("Guardando nuevo vuelo...");
		System.out.println("Código: " + vuelos.getCodVuelo() +
				" de " + vuelos.getProcedencia() + " a " + vuelos.getDestino()+
				" sale a las " + vuelos.getHoraSalida());
		System.out.println("----------------------");
		session.save(vuelos);
		System.out.println(" Guardado correctamente ");
		// Commit
		session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error, no se puede insertar este vuelo o ya existe.");
		}
		
		
	}
	
	public static void borrar() {
		try {
			session.beginTransaction();
			System.out.println(" Se va a borrar el vuelo " + vuelos.getCodVuelo());
			session.delete(vuelos);
			System.out.println(" Vuelo borrado correctamente.");
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
}
