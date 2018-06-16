package simulator;


import eduni.simjava.*;

public class ProcessorSubsystem {
    // The main method
    public static void main(String[] args) {
      // Initialize Sim_system
      Sim_system.initialise();

      // Add Entities
      Source source = new Source("Source", 50);

      LoadBalancer loadBalancer = new LoadBalancer("LoadBalancer", 30);

      AplicationService appService1 = new AplicationService("AppService1", 60);
      AplicationService appService2 = new AplicationService("AppService2", 80);
      AplicationService appService3 = new AplicationService("AppService3", 90);
      AplicationService appService4 = new AplicationService("AppService4", 110);
      
      EmailService emailService = new EmailService("EmailService", 5);
            
      // Link the entities' ports
      Sim_system.link_ports("Source", "Out", "LoadBalancer", "In");
      Sim_system.link_ports("LoadBalancer", "Out1", "AppService1", "In");
      Sim_system.link_ports("LoadBalancer", "Out2", "AppService2", "In");
      Sim_system.link_ports("LoadBalancer", "Out3", "AppService3", "In");
      Sim_system.link_ports("LoadBalancer", "Out4", "AppService4", "In");
      
      Sim_system.link_ports("AppService1", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService2", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService3", "Out1AppService", "EmailService", "In");
      Sim_system.link_ports("AppService4", "Out1AppService", "EmailService", "In");

      // Configure trace to the simulator (default, entity, event)
      Sim_system.set_trace_detail(false, true, false);
      // Run the simulation
      Sim_system.run();
    }
  }