package Types;

public class Pair<F,S>{
	private F fst;
	private S snd;
	
	public Pair(F f, S s){
		assert(f!=null);
		
		fst = f;
		snd = s;
	}
	
	public F Fst() {
		return fst;
	}
	public S Snd() {
		return snd;
	}
	
	public void SetSnd(S s_) {
		snd = s_;
	}
}
