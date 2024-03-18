package id.co.sinarmaslife.eproposal.common.util;


import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;

import java.text.DateFormat;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * <p>Merupakan controller yang merupakan parent dari semua controller yang 
 * mempunyai fungsi kelebihan yaitu satu buah controller dapat meng-handle banyak
 * fungsi, contoh penggunaannya seperti ini:</p>
 * <ul>
 * 	<li>Satu buah controller untuk meng-handle semua halaman search, misalnya
 * search spaj, search nomor proposal, search data karyawan</li>
 * </ul>
 * @since 07/08/2006
 * @see abstract parent beans pada applicationContext.xml
 */
public abstract class ParentMultiController extends MultiActionController {


	protected EproposalManager eproposalManager;
	protected Properties props;
    protected CustomDateEditor dateEditor;
    protected CustomNumberEditor integerEditor;
    protected CurrencyFormatEditor currencyEditor;
    protected DateFormat dateFormat;
	protected Validator validator;
	
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void setCurrencyEditor(CurrencyFormatEditor currencyEditor) {
		this.currencyEditor = currencyEditor;
	}

	public void setValidator(Validator validator) {this.validator = validator;}
	public void setProps(Properties props) {this.props = props;}
	public void setBancassManager(EproposalManager eproposalManager) {this.eproposalManager = eproposalManager;}
	public Log getLogger() {return logger;}
	public Properties getProps() {return props;}
	public void setDateEditor(CustomDateEditor dateEditor) {this.dateEditor = dateEditor;}
	public void setIntegerEditor(CustomNumberEditor integerEditor) {this.integerEditor = integerEditor;}
	public CustomDateEditor getDateEditor() {return dateEditor;}
	public CustomNumberEditor getIntegerEditor() {return integerEditor;}
	
	/**
	 * Bind request parameter ke command, sekaligus memvalidasi datanya
	 * 
	 * @since Mar 16, 2007 (10:02:32 AM)
	 * @param request Parameter yang ada dalam request ini akan di bind ke command object
	 * @param command Object command yang akan dibind datanya
	 * @param isValidated Flag ini menentukan apakah datanya perlu divalidasi atau tidak
	 * @return 
	 * @throws Exception
	 */
	protected BindingResult bindAndValidate(HttpServletRequest request, Object command, boolean isValidated) throws Exception {
		logger.debug("Binding request parameters onto MultiActionController command and Validating the results");
		ServletRequestDataBinder binder = createBinder(request, command);
		binder.bind(request);
		initBinder(request, binder);
		
		if(isValidated) {
			Validator[] validators = getValidators();
			if (validators != null) {
				for (int i = 0; i < validators.length; i++) {
					if (validators[i].supports(command.getClass())) {
						ValidationUtils.invokeValidator(validators[i],command, binder.getBindingResult());
					}
				}
			}
		}
		//binder.closeNoCatch();
		return binder.getBindingResult();
	}

}