package simulator;

import eduni.simjava.*;

//The class for the processor
class LoadBalancer extends Sim_entity {
  private Sim_port in, out1, out2, out3, out4;
  private double delay;

  LoadBalancer(String name, double delay) {
    super(name);
    this.delay = delay;
    // Port for receiving events from the source
    in = new Sim_port("In");
    
    // Ports    
    out1 = new Sim_port("Out1");    
    out2 = new Sim_port("Out2");
    out3 = new Sim_port("Out3");
    out4 = new Sim_port("Out4");
    
    add_port(in);
    add_port(out1);
    add_port(out2);
    add_port(out3);
    add_port(out4);
  }

  public void body() {
    int i = 0;
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();
      // Get the next event
      sim_get_next(e);
      // Process the event
      sim_process(delay);
      // The event has completed service
      sim_completed(e);
      
      if ((i % 4) == 0) {
    	sim_trace(1, "Service1 selected to receive the request.");
    	sim_schedule(out1, 0.0, 1);
      } else if ((i % 4) == 1) {
    	sim_trace(1, "AppService2 selected to receive the request.");
      	sim_schedule(out2, 0.0, 1);
      } else if ((i % 4) == 2) {
    	sim_trace(1, "AppService3 selected to receive the request.");
      	sim_schedule(out3, 0.0, 1);
      } else {        
    	sim_trace(1, "AppService4 selected to receive the request.");
    	sim_schedule(out4, 0.0, 1);      
      }      
      i++;
    }
  }
}