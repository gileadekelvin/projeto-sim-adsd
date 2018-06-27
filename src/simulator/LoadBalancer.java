package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

//The class for the processor
class LoadBalancer extends Sim_entity {
  private Sim_port in, out1, out2, out3, out4;
  private Sim_normal_obj delay;
  private Sim_random_obj prob;
  Sim_stat stat;


  LoadBalancer(String name, double mean, double variance, long seed) {
    super(name);
    this.delay = new Sim_normal_obj("Delay", mean, variance, seed);
    this.prob = new Sim_random_obj("Probability", seed);
    
    stat = new Sim_stat();
    stat.add_measure(Sim_stat.ARRIVAL_RATE);        
    stat.add_measure(Sim_stat.QUEUE_LENGTH);    
    stat.add_measure(Sim_stat.RESIDENCE_TIME);
    stat.add_measure(Sim_stat.SERVICE_TIME);    
    stat.add_measure(Sim_stat.THROUGHPUT);
    stat.add_measure(Sim_stat.UTILISATION);
    stat.add_measure(Sim_stat.WAITING_TIME);    
    set_stat(stat);    
    
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
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();
      // Get the next event
      sim_get_next(e);
      // Process the event
      double delaySample = delay.sample();
      sim_trace(1, "Load balancer service started. Delay: " + delaySample);
      sim_process(delaySample);
      // The event has completed service
      sim_completed(e);
      
      double probSample = prob.sample();

      if (probSample < 0.25) {
    	sim_trace(1, "Service1 selected to receive the request.");
    	sim_schedule(out1, 0.0, 1);
      } else if (probSample < 0.5) {
    	sim_trace(1, "AppService2 selected to receive the request.");
      	sim_schedule(out2, 0.0, 1);
      } else if (probSample < 0.75) {
    	sim_trace(1, "AppService3 selected to receive the request.");
      	sim_schedule(out3, 0.0, 1);
      } else {        
    	sim_trace(1, "AppService4 selected to receive the request.");
    	sim_schedule(out4, 0.0, 1);      
      }      
    }
  }
}