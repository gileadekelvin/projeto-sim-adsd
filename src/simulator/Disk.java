package simulator;


import eduni.simjava.*;

//The class for the two disks
class Disk extends Sim_entity {
  private Sim_port in;
  private double delay;

  Disk(String name, double delay) {
    super(name);
    this.delay = delay;
    // Port for receiving events from the processor
    in = new Sim_port("In");
    add_port(in);
  }

  public void body() {
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();
      // Get the next event
      sim_get_next(e);
      sim_trace(1, "Disk request started" );
      // Process the event
      sim_process(delay);
      // The event has completed service
      sim_completed(e);
    }
  }
}