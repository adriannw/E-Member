package id.co.sinarmaslife.eproposal.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import id.co.sinarmaslife.standard.model.vo.Member;

public class MemberRESTController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("E-Proposal/member//".length());
		
		Member person = DataStore.getInstance().getPerson(name);
		
		if(person != null){
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
			json += "\"about\": " + JSONObject.quote(person.getEmail()) + ",\n";
			json += "\"birthYear\": " + person.getNomor_ktp() + "\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else{
			//That person wasn't found, so return an empty JSON object. We could also return an error.
			response.getOutputStream().println("{}");
		}
	}
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String noKtp = request.getParameter("noKtp");
		
		DataStore.getInstance().putPerson(new Member(id, name, email, noKtp));
	}
}