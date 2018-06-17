package simulator;


import eduni.simjava.*;

public class ProcessorSubsystem {
    // The main method
    @SuppressWarnings("unused")
	public static void main(String[] args) {
      // Initialize Sim_system
      Sim_system.initialise();
      
      long seed = 123;

      // Add Entities
      Source source = new Source("Source", 20, 10, seed);

      LoadBalancer loadBalancer = new LoadBalancer("LoadBalancer", 5, 2, seed);
      
      AplicationService appService1 = new AplicationService("AppService1", 20, 6, seed);
      AplicationService appService2 = new AplicationService("AppService2", 10, 6, seed);
      AplicationService appService3 = new AplicationService("AppService3", 15, 2, seed);
      AplicationService appService4 = new AplicationService("AppService4", 30, 8, seed);
      
      EmailService emailService = new EmailService("EmailService", 5, 1, seed);
      DatabaseService databaseService = new DatabaseService("DatabaseService", 10, 3.5, seed);
            
      // Link the entities' ports
      Sim_system.link_ports("Source", "Out", "LoadBalancer", "In");
      Sim_system.link_ports("LoadBalancer", "Out1", "AppService1", "In");
      Sim_system.link_ports("LoadBalancer", "Out2", "AppService2", "In");
      Sim_system.link_ports("LoadBalancer", "Out3", "AppService3", "In");
      Sim_system.link_ports("LoadBalancer", "Out4", "AppService4", "In");
      
      Sim_system.link_ports("AppService1", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService1", "Out2AppService", "DatabaseService", "In1DBService");
      Sim_system.link_ports("DatabaseService", "Out1DBService", "AppService1", "In2");
      
      Sim_system.link_ports("AppService2", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService2", "Out2AppService", "DatabaseService", "In2DBService");
      Sim_system.link_ports("DatabaseService", "Out2DBService", "AppService2", "In2");
      
      Sim_system.link_ports("AppService3", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService3", "Out2AppService", "DatabaseService", "In3DBService");
      Sim_system.link_ports("DatabaseService", "Out3DBService", "AppService3", "In2");
      
      Sim_system.link_ports("AppService4", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService4", "Out2AppService", "DatabaseService", "In4DBService");
      Sim_system.link_ports("DatabaseService", "Out4DBService", "AppService3", "In2");

      // Configure trace to the simulator (default, entity, event)
      Sim_system.set_trace_detail(false, true, false);
      // Run the simulation
      Sim_system.run();
    }
  }