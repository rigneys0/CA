package simulator.adapters;

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
	public SimulatorAdapter getSimulator(int dimensions){
		try{
			if(available.isEmpty()){
				SimulatorAdapter someSim=
					(SimulatorAdapter)Class.forName("simulator.adapters.Adapter"
					+dimensions+"D").newInstance();
				return someSim;
			}
			return available.get(new Integer(available.size()-1));
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	public synchronized void returnToFactory(SimulatorAdapter adapter){
		available.put(available.size()-1, adapter);
	}
	public static void main(String[] args){
		
	}
}
