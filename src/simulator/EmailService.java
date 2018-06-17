package simulator;


import eduni.simjava.*;
import eduni.simjava.distributions.*;

class EmailService extends Sim_entity {
  private Sim_port in;
  private Sim_normal_obj delay;

  EmailService(String name, double mean, double variance, long seed) {
    super(name);
    this.delay = new Sim_normal_obj("Delay", mean, variance, seed);
    in = new Sim_port("In");
    
    add_port(in);
  }

  public void body() {
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();

      sim_get_next(e);
      double delaySample = delay.sample();
      sim_trace(1, "Email service started. Delay: " + delaySample);      
      sim_process(delaySample);

      sim_completed(e);
      
      try {
    	  sim_trace(1, "Email service completed " + Sim_system.get_entity(e.get_src()).get_name() );    	  
      }catch(eduni.simjava.Sim_exception exc) {
    	  System.out.println("Algo errado aconteceu!");
      }
            
    }
  }
}