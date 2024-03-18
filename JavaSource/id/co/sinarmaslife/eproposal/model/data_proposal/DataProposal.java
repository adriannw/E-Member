package id.co.sinarmaslife.eproposal.model.data_proposal;

import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstDataProposal;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProduct;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPacket;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPeserta;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductRider;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductTopUp;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductUlink;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Model untuk menampung data json kiriman dari eproposal mobile
 * 
 * @author Daru
 * @since May 31, 2016
 *
 */
public class DataProposal implements Serializable {

    private static final long serialVersionUID = 5423308779015073007L;

    private MstDataProposal mst_data_proposal;
    private MstProposalProduct mst_proposal_product;
    private ArrayList<MstProposalProductPacket> mst_proposal_product_packet;
    private ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta;
    private ArrayList<MstProposalProductRider> mst_proposal_product_rider;
    private ArrayList<MstProposalProductTopUp> mst_proposal_product_topup;
    private ArrayList<MstProposalProductUlink> mst_proposal_product_ulink;
    
    public MstDataProposal getMst_data_proposal() {
        return mst_data_proposal;
    }
    
    public void setMst_data_proposal(MstDataProposal mst_data_proposal) {
        this.mst_data_proposal = mst_data_proposal;
    }
    
    public MstProposalProduct getMst_proposal_product() {
        return mst_proposal_product;
    }
    
    public void setMst_proposal_product(MstProposalProduct mst_proposal_product) {
        this.mst_proposal_product = mst_proposal_product;
    }
    
    public ArrayList<MstProposalProductPacket> getMst_proposal_product_packet() {
        return mst_proposal_product_packet;
    }
    
    public void setMst_proposal_product_packet(
            ArrayList<MstProposalProductPacket> mst_proposal_product_packet) {
        this.mst_proposal_product_packet = mst_proposal_product_packet;
    }
    
    public ArrayList<MstProposalProductPeserta> getMst_proposal_product_peserta() {
        return mst_proposal_product_peserta;
    }
    
    public void setMst_proposal_product_peserta(
            ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta) {
        this.mst_proposal_product_peserta = mst_proposal_product_peserta;
    }
    
    public ArrayList<MstProposalProductRider> getMst_proposal_product_rider() {
        return mst_proposal_product_rider;
    }
    
    public void setMst_proposal_product_rider(
            ArrayList<MstProposalProductRider> mst_proposal_product_rider) {
        this.mst_proposal_product_rider = mst_proposal_product_rider;
    }
    
    public ArrayList<MstProposalProductTopUp> getMst_proposal_product_topup() {
        return mst_proposal_product_topup;
    }
    
    public void setMst_proposal_product_topup(
            ArrayList<MstProposalProductTopUp> mst_proposal_product_topup) {
        this.mst_proposal_product_topup = mst_proposal_product_topup;
    }
    
    public ArrayList<MstProposalProductUlink> getMst_proposal_product_ulink() {
        return mst_proposal_product_ulink;
    }
    
    public void setMst_proposal_product_ulink(
            ArrayList<MstProposalProductUlink> mst_proposal_product_ulink) {
        this.mst_proposal_product_ulink = mst_proposal_product_ulink;
    }
    
}
