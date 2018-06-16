package simulator;


import eduni.simjava.*;

class AplicationService extends Sim_entity {
  private Sim_port in, out1AppService;
  private double delay;

  AplicationService(String name, double delay) {
    super(name);
    this.delay = delay;
    // Port for receiving events from the processor
    in = new Sim_port("In");
    
    out1AppService = new Sim_port("Out1AppService");    

    add_port(in);
    add_port(out1AppService);    
  }

  public void body() {
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();
      // Get the next event
      sim_get_next(e);
            
      sim_trace(1, "Aplication service request started");
      // Process the event
      sim_process(delay);
      // The event has completed service
      sim_completed(e);
      
  	  sim_trace(1, "Send request to EmailService");
  	  sim_schedule(out1AppService, 0.0, 2);
    }
  }
}