package in.haeg.stablemarriage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import in.haeg.stablemarriage.Student;
import in.haeg.stablemarriage.Project;

public class Coordinator {
	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private int runningCount;

	public static void main(String[] args) {
		Coordinator coordinator;
		for (int i = 0; i < 1000; i++) {
			coordinator = new Coordinator(100);
		}
	}
	
	public static ArrayList<Integer> randomList(int length) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random generator = new Random();
		int r;
		list.add(0, 1);
		for (int i = 1; i < length; i++) {
			// Put the new item in a random place and put the item from that place on the end.
			r = generator.nextInt(i);
			list.add(i, list.get(r));
			list.set(r, i+1);
		}
		return list;
	}
	
	public synchronized void registerMarriage() {
		runningCount -= 1;
		if (runningCount == 0) {
			//System.out.println();
			for (Student s : students) {
				s.printResults();
				s.terminate();
			}
		}
	}
	
	public synchronized void registerDivorce() {
		runningCount += 1;
	}
	
	public Coordinator(int numbPairs) {
		runningCount = numbPairs;
		
		// Create the projects and the students
		for (int i = 0; i < numbPairs; i++) {
			projects.add(new Project(this, i+1));
			students.add(new Student(this, i+1));
		}
		
		ArrayList<Integer> ranking;
		
		// Set up the project rankings
		HashMap<Student, Integer> projectHash;
		for (Project p : projects) {
			projectHash = new HashMap<Student, Integer>();
			ranking = randomList(numbPairs);
			for (int i = 0; i < numbPairs; i++) {
				projectHash.put(students.get(i), ranking.get(i));
			}
			p.setRankings(projectHash);
		}
		
		// Set up the student rankings
		HashMap<Integer, Project> studentHash;
		for (Student s : students) {
			studentHash = new HashMap<Integer, Project>();
			ranking = randomList(numbPairs);
			for (int i=0; i < numbPairs; i++) {
				studentHash.put(ranking.get(i), projects.get(i));
			}
			s.setRankings(studentHash);
		}
		
		// Start the students going
		for (Student s : students) {
			s.start();
		}
		
		for (Student s : students) {
			try {
				s.join();
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Done.");
		
	}
	
}
