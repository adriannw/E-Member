package id.co.sinarmaslife.eproposal.webservice.rest.server;

import id.co.sinarmaslife.eproposal.business.ProposalMappingBusiness;
import id.co.sinarmaslife.eproposal.model.data_proposal.DataProposal;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.com.context.CustomContextListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

@Path("/proposal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProposalService {
	protected final Log logger = LogFactory.getLog( getClass() );
	
    //private ProposalMappingBusiness proposalMappingBusiness;    
    public ProposalService() {
    /*    try {
            this.proposalMappingBusiness = new ProposalMappingBusiness();
        } catch (Exception e) {
            logger.error("ERROR", e);
        }*/
    }

    @GET
    @Path("/generatePdf/{noProposal}")
    public Response generatePdf(@PathParam("noProposal") String noProposal) throws Exception {
        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<String, Object>();
        ProposalMappingBusiness  proposalMappingBusiness = new ProposalMappingBusiness(CustomContextListener.getSpringServletContext());
        
        if(!StringUtil.isEmpty(noProposal)) {
            DataProposal dataProposal = proposalMappingBusiness.getProposalData(noProposal);
            
            if(dataProposal != null) {
                Cepr01030000HoldingForm holdingForm = proposalMappingBusiness.getConvertToHoldingForm(dataProposal);
                HashMap<String, Object> pdf = proposalMappingBusiness.getPdfProposal(holdingForm);
                	File pdfFile = new File(pdf.get("dirName").toString() + "\\" + pdf.get("fileName").toString());
                	if(pdfFile.exists()) {
                        return Response.ok(pdfFile, "application/pdf").build();
                    } else {
                        map.put("result", "error");
                        map.put("message", "Proposal tidak ditemukan");
                    }
            } else {
                map.put("result", "error");
                map.put("message", "Proposal tidak ditemukan");
            }
        } else {
            map.put("result", "error");
            map.put("message", "Parameter required: No Proposal");
        }
        
        return Response.ok(gson.toJson(map)).build();
    }
    
    @GET
    @Path("/flag_kelayakan/{noProposal}")
    public Response flag_kelayakan(@PathParam("noProposal") String noProposal) throws Exception {
        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<String, Object>();
        ProposalMappingBusiness  proposalMappingBusiness = new ProposalMappingBusiness(CustomContextListener.getSpringServletContext());
        
        if(!StringUtil.isEmpty(noProposal)) {
            DataProposal dataProposal = proposalMappingBusiness.getProposalData(noProposal);
            
            if(dataProposal != null) {
                Cepr01030000HoldingForm holdingForm = proposalMappingBusiness.getConvertToHoldingForm(dataProposal);
                HashMap<String, Object> pdf = proposalMappingBusiness.getPdfProposal(holdingForm);
                if (pdf.get("proposalLayak") != null && pdf.get("proposalLayak").equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
                	map.put("flagLayak", false);
                	map.put("error", false);
                }else{
                	map.put("flagLayak", true);
                	map.put("error", false);
                }
                
            } else {
                map.put("error", true);
                map.put("message", "Proposal tidak ditemukan");
            }
        } else {
            map.put("error", true);
            map.put("message", "Parameter required: No Proposal");
        }
        
        return Response.ok(gson.toJson(map)).build();
    }
      
    
    @GET
    @Path("/cariAdminBC")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response cariAdminKodeBC(@QueryParam("cariNamaAdminBC") String cariNamaAdminBC, @QueryParam("type") String tipe, @QueryParam("jn_bank") String jn_bank, @Context ServletContext  context) throws Exception {
    	   Gson gson = new Gson();
    	   HashMap<String, Object> map = new HashMap<String, Object>();
    	   
    	   ProposalMappingBusiness  proposalMappingBusiness = new ProposalMappingBusiness(context);
    	   
    	   List<Map<String, Object>> hasilCariAdminBC = null;
    	   if(tipe.equals("nama")){
    		   hasilCariAdminBC = proposalMappingBusiness.hasilCariAdminBC(cariNamaAdminBC, null, jn_bank);
    	   }else{
    		   hasilCariAdminBC = proposalMappingBusiness.hasilCariAdminBC(null, cariNamaAdminBC, jn_bank);
    	   }
    	   
    	   
    	
    	   if(hasilCariAdminBC != null && hasilCariAdminBC.size()>0){
    		   
    		   map.put("result", "sukses");
    		   map.put("hasil", hasilCariAdminBC);
    		   
    	   }else{
    		   map.put("result", "error");
    		   
    	   }
    	
    	   return Response.ok(gson.toJson(map)).build();
    }

}