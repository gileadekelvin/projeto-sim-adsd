package simulator;

import eduni.simjava.*;        // Import the SimJava basic package

class Source extends Sim_entity {
	private Sim_port out;
    private double delay;

    Source(String name, double delay) {
      super(name);
      this.delay = delay;
      // Port for sending events to the processor
      out = new Sim_port("Out");
      add_port(out);
    }
  
  public void body() {
      for (int i=0; i < 100; i++) {
        // Send the processor a job
        sim_schedule(out, 0.0, 0);
        sim_trace(1, "New request from processor.");
        // Pause
        sim_pause(delay);
      }
    }
}