package id.co.sinarmaslife.eproposal.web;
import java.util.HashMap;
import java.util.Map;
import id.co.sinarmaslife.standard.model.vo.Member;


public class DataStore {

	private Map<String, Member> memberMap = new HashMap();
	
	private static DataStore instance = new DataStore();
	public static DataStore getInstance(){
		return instance;
	}
	
	private DataStore(){
		//dummy data
		memberMap.put("Kevin", new Member(1, "Kevin", "ada@gmail.com.", "2323231815"));
		memberMap.put("Ade", new Member(2, "Ade", "ada@gmail.com.", "2323231815"));
		memberMap.put("Jiko", new Member(3, "Jiko", "ada@gmail.com.", "2323231815"));
	}

	public Member getPerson(String name) {
		return memberMap.get(name);
	}

	public void putPerson(Member person) {
		memberMap.put(person.getName(), person);
	}
	
}
