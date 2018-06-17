package simulator;


import eduni.simjava.*;

class AplicationService extends Sim_entity {
  private Sim_port in, in2, out1AppService, out2AppService;
  private double delay;

  AplicationService(String name, double delay) {
    super(name);
    this.delay = delay;
    // Port for receiving events from the processor
    in = new Sim_port("In");
    in2 = new Sim_port("In2");
    
    out1AppService = new Sim_port("Out1AppService");
    out2AppService = new Sim_port("Out2AppService");    
        
    add_port(in);
    add_port(in2);
    add_port(out1AppService);
    add_port(out2AppService);
    
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
      
      try {
    	  String nome = Sim_system.get_entity(e.get_src()).get_name();    	  
    	  if (nome.equals("DatabaseService")) {
    		  sim_trace(1, "Send request to EmailService");
    		  sim_schedule(out1AppService, 0.0, 1);    	  
          } else {
          	  sim_trace(1, "Send request to DatabaseService");
        	  sim_schedule(out2AppService, 0.0, 1);        	  
          }    	      	  
      } catch(eduni.simjava.Sim_exception exc) {
    	  System.out.println("Algo errado aconteceu!");
    	  System.out.println(exc.getMessage());
      }
      
    }
  }
}