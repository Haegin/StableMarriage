package in.haeg.stablemarriage;

import java.util.HashMap;

public class Project {
	private HashMap<Student, Integer> ranking = new HashMap<Student, Integer>();
	private Student currPartner;
	private Coordinator coord;
	private String id;
	
	public Project(Coordinator c, int id) {
		coord = c;
		this.id = "Project_" + id;
		System.out.println("Project " + this.getID() + " created.");
	}
	
	public void setRankings(HashMap<Student, Integer> a_Ranking) {
		ranking = a_Ranking;
	}

	public Boolean propose(Student proposer) {
		/* If we don't currently have a partner then just accept them */
		if (currPartner == null) {
			System.out.println("Project " + this.getID() + " accepting Student " + proposer.getID());
			currPartner = proposer;
			return true;
		} else {
			/* Otherwise we need to compare to the current partner and see if we prefer this new offer */
			if (ranking.get(proposer) < ranking.get(currPartner)) {
				System.out.println("Project " + this.getID() + " spurning Student " + currPartner.getID());
				currPartner.spurn();
				currPartner = proposer;
				System.out.println("Project " + this.getID() + " accepting Student " + proposer.getID());
				return true;
			} else {
				System.out.println("Project " + this.getID() + " rejecting Student " + proposer.getID());
				return false;
			}
		}
	}
	
	public String getID() {
		return id;
	}

}
