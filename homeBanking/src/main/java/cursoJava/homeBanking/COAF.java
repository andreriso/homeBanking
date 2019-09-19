package cursoJava.homeBanking;

import java.util.ArrayList;
import java.util.List;

public final class COAF implements Subject {

	/**
	 * Private constructor so nobody can instantiate the class.
	 */
	private COAF() {
	}

	/**
	 * Static to class instance of the class.
	 */
	private static final COAF INSTANCE = new COAF();

	/**
	 * To be called by user to obtain instance of the class.
	 *
	 * @return instance of the singleton.
	 */
	public static COAF getInstance() {
		return INSTANCE;
	}
	
    private List<Observer> observers = new ArrayList<>();

	@Override
	public void notifyObservers(Movimentacao movimentacao) {
        for(Observer observer : this.observers){
            observer.notificar(movimentacao);
        }		
	}

	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
		
	}
}
