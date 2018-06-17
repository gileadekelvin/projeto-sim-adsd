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

      LoadBalancer loadBalancer = new LoadBalancer("LoadBalancer", 30);
      
      AplicationService appService1 = new AplicationService("AppService1", 60);
      AplicationService appService2 = new AplicationService("AppService2", 80);
      AplicationService appService3 = new AplicationService("AppService3", 90);
      AplicationService appService4 = new AplicationService("AppService4", 110);
      
      EmailService emailService = new EmailService("EmailService", 5);
      DatabaseService databaseService = new DatabaseService("DatabaseService", 40);
            
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