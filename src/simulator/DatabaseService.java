package simulator;


import eduni.simjava.*;
import eduni.simjava.distributions.*;

class DatabaseService extends Sim_entity {
  private Sim_port in1DBService, in2DBService, in3DBService, in4DBService, 
  out1DBService, out2DBService, out3DBService, out4DBService;
  private Sim_normal_obj delay;
  Sim_stat stat;


  DatabaseService(String name, double mean, double variance, long seed) {
    super(name);
    this.delay = new Sim_normal_obj("Delay", mean, variance, seed);
    
    stat = new Sim_stat();
    stat.add_measure(Sim_stat.ARRIVAL_RATE);        
    stat.add_measure(Sim_stat.QUEUE_LENGTH);    
    stat.add_measure(Sim_stat.RESIDENCE_TIME);
    stat.add_measure(Sim_stat.SERVICE_TIME);    
    stat.add_measure(Sim_stat.THROUGHPUT);
    stat.add_measure(Sim_stat.UTILISATION);
    stat.add_measure(Sim_stat.WAITING_TIME);    
    set_stat(stat);  
    
    in1DBService = new Sim_port("In1DBService");
    in2DBService = new Sim_port("In2DBService");
    in3DBService = new Sim_port("In3DBService");
    in4DBService = new Sim_port("In4DBService");
    
    out1DBService = new Sim_port("Out1DBService");
    out2DBService = new Sim_port("Out2DBService");
    out3DBService = new Sim_port("Out3DBService");
    out4DBService = new Sim_port("Out4DBService");
    
    add_port(in1DBService);
    add_port(in2DBService);
    add_port(in3DBService);
    add_port(in4DBService);
    
    add_port(out1DBService);
    add_port(out2DBService);
    add_port(out3DBService);
    add_port(out4DBService);

  }

  public void body() {
    while (Sim_system.running()) {
      Sim_event e = new Sim_event();

      sim_get_next(e);
      double delaySample = delay.sample();
      sim_trace(1, "Database service started. Delay: " + delaySample);      
      sim_process(delaySample);
      
      sim_completed(e);
      
      try {
    	  if (e.get_src() != -1) {    		      	  
	    	  String nome = Sim_system.get_entity(e.get_src()).get_name();
	    	  sim_trace(1, "Database responds to " + nome);
	    	  if (nome.equals("AppService1")) {
	    		  sim_schedule(out1DBService, 0.0, 1);
	    	  } else if (nome.equals("AppService2")) {
	    		  sim_schedule(out2DBService, 0.0, 1);
	    	  } else if (nome.equals("AppService3")) {    		  
	    		  sim_schedule(out3DBService, 0.0, 1);
	          } else {        	          	  
	        	  sim_schedule(out4DBService, 0.0, 1);        	  
	          }
    	  }
      } catch(eduni.simjava.Sim_exception exc) {
    	  System.out.println("Algo errado aconteceu!");
    	  System.out.println(exc.getMessage());    	  
      }
                      
    }
  }
}