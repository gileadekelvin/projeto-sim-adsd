package simulator;


import eduni.simjava.*;

public class ProcessorSubsystem {
    // The main method
    public static void main(String[] args) {
      // Initialize Sim_system
      Sim_system.initialise();
      // Add the source
      Source source = new Source("Source", 50);
      // Add the processor
      Sink processor = new Sink("Processor", 30);
      // Add disk 1
      Disk disk1 = new Disk("Disk1", 60);
      // Add disk 2
      Disk disk2 = new Disk("Disk2", 110);
      // Link the entities' ports
      Sim_system.link_ports("Source", "Out", "Processor", "In");
      Sim_system.link_ports("Processor", "Out1", "Disk1", "In");
      Sim_system.link_ports("Processor", "Out2", "Disk2", "In");
      // Configure trace to the simulator (default, entity, event)
      Sim_system.set_trace_detail(false, true, false);
      // Run the simulation
      Sim_system.run();
    }
  }