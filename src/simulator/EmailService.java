package simulator;


import eduni.simjava.*;

class EmailService extends Sim_entity {
  private Sim_port in;
  private double delay;

  EmailService(String name, double delay) {
    super(name);
    this.delay = delay;
    in = new Sim_port("In");
    
    add_port(in);
  }

  public void body() {
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();

      sim_get_next(e);
      sim_trace(1, "Email service started");
      sim_process(delay);
      
      sim_completed(e);
    }
  }
}