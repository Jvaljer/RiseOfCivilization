package Model;

/**
 * the clock's model simply helps to represent an overall time reference.
 * @author abel
 */
public class ClockModel {
	//minutes and seconds variables we wanna increment
	private int minutes;
	private int seconds;
	//predicate 'running' telling if this clock is running or not
	private boolean ticking;
	/**
	 * Constructor -> initializes the clock to 00:00
	 */
	public ClockModel() {
		seconds = 0;
		minutes = 0;
		ticking = false;
	}
	
	/**
	 * ReachedMinute function, used to convert the seconds to a new minute
	 */
	public void ReachedMinute() {
		seconds = 0;
		minutes++;
	}
	/**
	 * ReachedSecond function, used to increment the seconds counter
	 */
	public void ReachedSecond() {
		seconds++;
	}
	
	/**
	 * Getter for the seconds amount
	 */
	public int GetSeconds() {
		return seconds;
	}
	/**
	 * Getter for the minutes amount
	 */
	public int GetMinutes() {
		return minutes;
	}
	
	/**
	 * Getter for the 'ticking' predicate
	 */
	public boolean IsTicking() {
		return ticking;
	}
	
	/**
	 * Setter of the 'ticking' attribute to "true"
	 */
	public void StartsTicking() {
		ticking = true;
	}
	
	/**
	 * Setter of the 'ticking' attribute to "false
	 */
	public void StopsTicking() {
		ticking = false;
	}
	
	/**
	 * Predicate function that indicates if we reached or not the wanted time
	 * @param m the amount of minute we wanna reach
	 * @param s the amount of seconds we wanna reach
	 * @return true if we have equalized or got higher than the wanted amount of time
	 */
	public boolean HasReached(int m, int s) {
		return (minutes>=m) && (seconds>=s);
	}
}
