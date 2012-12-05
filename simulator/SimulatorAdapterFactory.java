package simulator;

import java.util.concurrent.ConcurrentHashMap;

public class SimulatorAdapterFactory {
	private static SimulatorAdapterFactory instance;
	private ConcurrentHashMap<Integer,SimulatorAdapter> available;
	public SimulatorAdapterFactory(){
		available = new ConcurrentHashMap<Integer, SimulatorAdapter>();
	}
	public static synchronized SimulatorAdapterFactory getInstance(){
		if(instance==null){
			return new SimulatorAdapterFactory();
		}
		return instance;
	}
	public synchronized SimulatorAdapter getAdapter(int dimensions){
		try{
			if(available.isEmpty()){
			return (SimulatorAdapter)Class.forName("simulator.Adapter"
					+dimensions+"D").newInstance();
			}
			return available.get(new Integer(available.size()-1));
		}
		catch(Exception e){
			return null;
		}
	}
	public synchronized void returnToFactory(SimulatorAdapter adapter){
		available.put(available.size()-1, adapter);
	}
	
	
}
