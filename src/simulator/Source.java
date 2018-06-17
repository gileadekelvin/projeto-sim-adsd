package simulator;

import eduni.simjava.*;        // Import the SimJava basic package
import eduni.simjava.distributions.*;

class Source extends Sim_entity {
	private Sim_port out;
    private Sim_normal_obj delay;

    Source(String name, double mean, double variance, long seed) {
      super(name);
      this.delay = new Sim_normal_obj("Delay", mean, variance, seed);
      // Port for sending requests to the load Balancer
      out = new Sim_port("Out");
      add_port(out);
    }
  
  public void body() {
      for (int i=0; i < 100; i++) {
        // Send the processor a job
        sim_schedule(out, 0.0, 0);
        double delaySample = delay.sample();
        sim_trace(1, "New request from load balancer. Delay: " + delaySample);        
        // Pause
        sim_pause(delaySample);
      }
    }
}