package id.co.sinarmaslife.eproposal.common.data;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: HardcodedProducts
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Mar 18, 2008 1:13:15 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.vo.HardcodedProductVO;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HardcodedProducts
{
	protected final static  Log logger = LogFactory.getLog( HardcodedProducts.class );
    private static List<HardcodedProductVO> hardcodedProductVOList;

    public static List<HardcodedProductVO> getHardcodedProductVOList()
    {
       // logger.info( "*-*-*-* HardcodedProducts.getHardcodedProductVOList" );
        logger.info("*-*-*-* HardcodedProducts.getHardcodedProductVOList");
                
        if( hardcodedProductVOList == null )
        {
            hardcodedProductVOList = new ArrayList<HardcodedProductVO>();
            HardcodedProductVO productVO;

            productVO = new HardcodedProductVO( 162, "ARTHA LINK 88", new AssurancePlanList[]{
                    new AssurancePlanList( "162~X1", "ARTHA LINK 88 SINGLE" ),
                    new AssurancePlanList( "162~X2", "ARTHA LINK 88 REGULER" )
            } );
            hardcodedProductVOList.add( productVO );

            productVO = new HardcodedProductVO( 162, "EKA LINK 88", new AssurancePlanList[]{
                    new AssurancePlanList( "162~X5", "EKA LINK 88 SINGLE" ),
                    new AssurancePlanList( "162~X6", "EKA LINK 88 REGULER" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 114, "SMiLe LINK MEDIVEST", new AssurancePlanList[]{
                    new AssurancePlanList( "114~X1", "SMiLe LINK MEDIVEST SINGLE" ),
                    new AssurancePlanList( "114~X2", "SMiLe LINK MEDIVEST" )
            } );
            hardcodedProductVOList.add( productVO );            
          
//            UDA GAK DIJUAL LAGI
//            productVO = new HardcodedProductVO( 162, "EKA LINK 88 PLUS", new AssurancePlanList[]{
//                    new AssurancePlanList( "162~X7", "EKA LINK 88 PLUS SINGLE" ),
//                    new AssurancePlanList( "162~X8", "EKA LINK 88 PLUS REGULER" )
//            } );
//            hardcodedProductVOList.add( productVO );

//            productVO = new HardcodedProductVO( 142, "POWER SAVE (new)", new AssurancePlanList[]{
//                    new AssurancePlanList( "142~X1", "POWER SAVE (new)" )
//            } );
            hardcodedProductVOList.add( productVO );
//         SEMENTARA GAK DIJUAL LAGI : REQUEST DESVINNA@08/10/2013
//            productVO = new HardcodedProductVO( 158, "POWERSAVE BULANAN", new AssurancePlanList[]{
//                    new AssurancePlanList( "158~X1", "POWERSAVE BULANAN (I)" )
//            } );
//           hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 158, "STABLE SAVE MANFAAT BULANAN", new AssurancePlanList[]{
                    new AssurancePlanList( "158~X13", "STABLE SAVE MANFAAT BULANAN" )
            } );
            hardcodedProductVOList.add( productVO );

            productVO = new HardcodedProductVO( 164, "STABLE LINK", new AssurancePlanList[]{
                    new AssurancePlanList( "164~X1", "STABLE LINK" ),
                    new AssurancePlanList( "164~X2", "SIMAS STABLE LINK" ),
                    new AssurancePlanList( "164~X11", "NEW SIMAS STABIL LINK" ),
                    new AssurancePlanList( "164~X12", "STABLE LINK (I) (new)" )
            } );
            hardcodedProductVOList.add( productVO );
//            
//            productVO = new HardcodedProductVO( 164, "SIMAS STABLE LINK", new AssurancePlanList[]{
//                    new AssurancePlanList( "164~X2", "STABLE LINK" )
//            } );
//            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 164, "STABLE LINK (DMTM)", new AssurancePlanList[]{
                    new AssurancePlanList( "164~X7", "STABLE LINK (DMTM)" )
            } );
            hardcodedProductVOList.add( productVO );

            productVO = new HardcodedProductVO( 115, "EKA LINK 80", new AssurancePlanList[]{
                    new AssurancePlanList( "115~X2", "EKA LINK 80" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 134, "SIMAS PRIME LINK", new AssurancePlanList[]{
                    new AssurancePlanList( "134~X5", "SIMAS PRIME LINK" )
              } );
              hardcodedProductVOList.add( productVO );      
              
              productVO = new HardcodedProductVO( 134, "SMiLe LINK PROASSET", new AssurancePlanList[]{
                      new AssurancePlanList( "134~X6", "SMiLe LINK PROASSET" )
                   } );
               hardcodedProductVOList.add( productVO );
               
               productVO = new HardcodedProductVO( 134, "SMiLe LINK INVESTA", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X7", "SMiLe LINK INVESTA" )
                    } );
                hardcodedProductVOList.add( productVO );
                
                productVO = new HardcodedProductVO( 134, "SMiLe PREMIUM LINK", new AssurancePlanList[]{
                        new AssurancePlanList( "134~X8", "SMiLe PREMIUM LINK" )
                     } );
                 hardcodedProductVOList.add( productVO );                               
                
               productVO = new HardcodedProductVO( 134, "SMiLe LINK PRECIOUS", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X9", "SMiLe LINK PRECIOUS" )
                      } );
               hardcodedProductVOList.add( productVO );  
               
               productVO = new HardcodedProductVO( 134, "SIMAS PRIME LINK (RIDER SAVE)", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X10", "SIMAS PRIME LINK (RIDER SAVE)" )
                      } );
               hardcodedProductVOList.add( productVO );  
               
               productVO = new HardcodedProductVO( 134, "SMiLe LINK PROASSET (RIDER SAVE)", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X11", "SMiLe LINK PROASSET (RIDER SAVE)" )
                      } );
               hardcodedProductVOList.add( productVO );  
               
               productVO = new HardcodedProductVO( 134, "B SMiLe INSURANCE", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X12", "B SMiLe INSURANCE" )
                      } );
               hardcodedProductVOList.add( productVO );
               
               productVO = new HardcodedProductVO( 134, "SMART PLATINUM LINK", new AssurancePlanList[]{
                       new AssurancePlanList( "134~X13", "SMART PLATINUM LINK" )
                      } );
               hardcodedProductVOList.add( productVO );
                  
               productVO = new HardcodedProductVO( 208, "SIMAS KID", new AssurancePlanList[]{
                       	new AssurancePlanList( "208~X5", "SIMAS KID PLAN A"),
                       	new AssurancePlanList( "208~X6", "SIMAS KID PLAN B"),
                      	new AssurancePlanList( "208~X7", "SIMAS KID PLAN C"),
                       	new AssurancePlanList( "208~X8", "SIMAS KID PLAN D"),                    	
                       	new AssurancePlanList( "208~X25", "SIMAS KID PLAN A (KRY)"),
                       	new AssurancePlanList( "208~X26", "SIMAS KID PLAN B (KRY)"),
                      	new AssurancePlanList( "208~X27", "SIMAS KID PLAN C (KRY)"),
                       	new AssurancePlanList( "208~X28", "SIMAS KID PLAN D (KRY)")
                       });
                  hardcodedProductVOList.add( productVO );
                  
                  productVO = new HardcodedProductVO( 208, "SMiLe KID INSURANCE", new AssurancePlanList[]{
                       	new AssurancePlanList( "208~X9", "SMiLe KID PLAN A"),
                       	new AssurancePlanList( "208~X10", "SMiLe KID PLAN B"),
                      	new AssurancePlanList( "208~X11", "SMiLe KID PLAN C"),
                       	new AssurancePlanList( "208~X12", "SMiLe KID PLAN D"),                               	
                       });
                  hardcodedProductVOList.add( productVO );
                  
                  productVO = new HardcodedProductVO( 208, "VIP EDU PLAN", new AssurancePlanList[]{
                         	new AssurancePlanList( "208~X13", "VIP EDU PLAN PLAN A"),
                         	new AssurancePlanList( "208~X14", "VIP EDU PLAN PLAN B"),
                        	new AssurancePlanList( "208~X15", "VIP EDU PLAN PLAN C"),
                         	new AssurancePlanList( "208~X16", "VIP EDU PLAN PLAN D")                                      	
                         });
                    hardcodedProductVOList.add( productVO );
                    
                    productVO = new HardcodedProductVO( 208, "SMiLe KIDs", new AssurancePlanList[]{
                         	new AssurancePlanList( "208~X17", "SMiLe KIDs PLAN A (BJB)"),
                         	new AssurancePlanList( "208~X18", "SMiLe KIDs PLAN B (BJB)"),
                        	new AssurancePlanList( "208~X19", "SMiLe KIDs PLAN C (BJB)"),
                         	new AssurancePlanList( "208~X20", "SMiLe KIDs PLAN D (BJB)"),                               	
                         });
                    hardcodedProductVOList.add( productVO );
                    
                    productVO = new HardcodedProductVO( 208, "SMART PLAN PROTECTION PLAN", new AssurancePlanList[]{
                         	new AssurancePlanList( "208~X21", "SMART PLAN PROTECTION PLAN A"),
                         	new AssurancePlanList( "208~X22", "SMART PLAN PROTECTION PLAN B"),
                        	new AssurancePlanList( "208~X23", "SMART PLAN PROTECTION PLAN C"),
                         	new AssurancePlanList( "208~X24", "SMART PLAN PROTECTION PLAN D"),                               	
                         });
                    hardcodedProductVOList.add( productVO );
                    
                    productVO = new HardcodedProductVO( 183, "SMiLe MEDICAL", new AssurancePlanList[]{
                         	new AssurancePlanList( "183~X16", "SMiLe MEDICAL AC PLAN A"),
                        	new AssurancePlanList( "183~X17", "SMiLe MEDICAL AC PLAN B"),
                        	new AssurancePlanList( "183~X18", "SMiLe MEDICAL AC PLAN C"),
                        	new AssurancePlanList( "183~X19", "SMiLe MEDICAL AC PLAN D"),
                        	new AssurancePlanList( "183~X20", "SMiLe MEDICAL AC PLAN E"),
                        	new AssurancePlanList( "183~X21", "SMiLe MEDICAL AC PLAN F"),
                        	new AssurancePlanList( "183~X22", "SMiLe MEDICAL AC PLAN G"),
                        	new AssurancePlanList( "183~X23", "SMiLe MEDICAL AC PLAN H"),
                        	new AssurancePlanList( "183~X24", "SMiLe MEDICAL AC PLAN I"),
                        	new AssurancePlanList( "183~X25", "SMiLe MEDICAL AC PLAN J"),
                        	new AssurancePlanList( "183~X26", "SMiLe MEDICAL AC PLAN K"),
                        	new AssurancePlanList( "183~X27", "SMiLe MEDICAL AC PLAN L"),
                        	new AssurancePlanList( "183~X28", "SMiLe MEDICAL AC PLAN M"), 
                        	new AssurancePlanList( "183~X29", "SMiLe MEDICAL AC PLAN N"),
                        	new AssurancePlanList( "183~X30", "SMiLe MEDICAL AC PLAN O"),                         	
                         	new AssurancePlanList( "183~X46", "SMiLe MEDICAL AC PLAN A (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X47", "SMiLe MEDICAL AC PLAN B (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X48", "SMiLe MEDICAL AC PLAN C (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X49", "SMiLe MEDICAL AC PLAN D (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X50", "SMiLe MEDICAL AC PLAN E (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X51", "SMiLe MEDICAL AC PLAN F (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X52", "SMiLe MEDICAL AC PLAN G (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X53", "SMiLe MEDICAL AC PLAN H (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X54", "SMiLe MEDICAL AC PLAN I (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X55", "SMiLe MEDICAL AC PLAN J (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X56", "SMiLe MEDICAL AC PLAN K (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X57", "SMiLe MEDICAL AC PLAN L (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X58", "SMiLe MEDICAL AC PLAN M (BANCASS/TM)"), 
                        	new AssurancePlanList( "183~X59", "SMiLe MEDICAL AC PLAN N (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X60", "SMiLe MEDICAL AC PLAN O (BANCASS/TM)"),
                        	new AssurancePlanList( "183~X121", "SMiLe MEDICAL AC PLAN P"),
                        	new AssurancePlanList( "183~X122", "SMiLe MEDICAL AC PLAN Q")                        	
                         });
                    hardcodedProductVOList.add( productVO ); 
                    
                    productVO = new HardcodedProductVO( 183, "VIP MEDICAL PLAN", new AssurancePlanList[]{
                         	new AssurancePlanList( "183~X91", "VIP MEDICAL PLAN PLAN A"),
                        	new AssurancePlanList( "183~X92", "VIP MEDICAL PLAN PLAN B"),
                        	new AssurancePlanList( "183~X93", "VIP MEDICAL PLAN PLAN C"),
                        	new AssurancePlanList( "183~X94", "VIP MEDICAL PLAN PLAN D"),
                        	new AssurancePlanList( "183~X95", "VIP MEDICAL PLAN PLAN E"),
                        	new AssurancePlanList( "183~X96", "VIP MEDICAL PLAN PLAN F"),
                        	new AssurancePlanList( "183~X97", "VIP MEDICAL PLAN PLAN G"),
                        	new AssurancePlanList( "183~X98", "VIP MEDICAL PLAN PLAN H"),
                        	new AssurancePlanList( "183~X99", "VIP MEDICAL PLAN PLAN I"),
                        	new AssurancePlanList( "183~X100", "VIP MEDICAL PLAN PLAN J"),
                        	new AssurancePlanList( "183~X101", "VIP MEDICAL PLAN PLAN K"),
                        	new AssurancePlanList( "183~X102", "VIP MEDICAL PLAN PLAN L"),
                        	new AssurancePlanList( "183~X103", "VIP MEDICAL PLAN PLAN M"), 
                        	new AssurancePlanList( "183~X104", "VIP MEDICAL PLAN PLAN N"),
                        	new AssurancePlanList( "183~X105", "VIP MEDICAL PLAN PLAN O")
                         });
                    hardcodedProductVOList.add( productVO );
                    
                    productVO = new HardcodedProductVO( 195, "SMiLe HOSPITAL PROTECTION (+)", new AssurancePlanList[]{
                         	new AssurancePlanList( "195~X25", "SMiLe HOSPITAL PROTECTION (+) R-100"),
                        	new AssurancePlanList( "195~X26", "SMiLe HOSPITAL PROTECTION (+) R-200"),
                        	new AssurancePlanList( "195~X27", "SMiLe HOSPITAL PROTECTION (+) R-300"),
                        	new AssurancePlanList( "195~X28", "SMiLe HOSPITAL PROTECTION (+) R-400"),
                        	new AssurancePlanList( "195~X29", "SMiLe HOSPITAL PROTECTION (+) R-500"),
                        	new AssurancePlanList( "195~X30", "SMiLe HOSPITAL PROTECTION (+) R-600"),
                        	new AssurancePlanList( "195~X31", "SMiLe HOSPITAL PROTECTION (+) R-700"),
                        	new AssurancePlanList( "195~X32", "SMiLe HOSPITAL PROTECTION (+) R-800"),
                        	new AssurancePlanList( "195~X33", "SMiLe HOSPITAL PROTECTION (+) R-900"),
                        	new AssurancePlanList( "195~X34", "SMiLe HOSPITAL PROTECTION (+) R-1000"),
                        	new AssurancePlanList( "195~X35", "SMiLe HOSPITAL PROTECTION (+) R-1500"),
                        	new AssurancePlanList( "195~X36", "SMiLe HOSPITAL PROTECTION (+) R-2000"),
                        	new AssurancePlanList( "195~X37", "SMiLe HOSPITAL PLUS R-100"),
                        	new AssurancePlanList( "195~X38", "SMiLe HOSPITAL PLUS R-200"),
                        	new AssurancePlanList( "195~X39", "SMiLe HOSPITAL PLUS R-300"),
                        	new AssurancePlanList( "195~X40", "SMiLe HOSPITAL PLUS R-400"),
                        	new AssurancePlanList( "195~X41", "SMiLe HOSPITAL PLUS R-500"),
                        	new AssurancePlanList( "195~X42", "SMiLe HOSPITAL PLUS R-600"),
                        	new AssurancePlanList( "195~X43", "SMiLe HOSPITAL PLUS R-700"),
                        	new AssurancePlanList( "195~X44", "SMiLe HOSPITAL PLUS R-800"),
                        	new AssurancePlanList( "195~X45", "SMiLe HOSPITAL PLUS R-900"),
                        	new AssurancePlanList( "195~X46", "SMiLe HOSPITAL PLUS R-1000"),
                        	new AssurancePlanList( "195~X47", "SMiLe HOSPITAL PLUS R-1500"),
                        	new AssurancePlanList( "195~X48", "SMiLe HOSPITAL PLUS R-2000")     
                         });
                    hardcodedProductVOList.add( productVO );                                  
                    
                    
                    productVO = new HardcodedProductVO( 195, "VIP HOSPITAL PLAN", new AssurancePlanList[]{
                         	new AssurancePlanList( "195~X49", "VIP HOSPITAL PLAN R-100"),
                        	new AssurancePlanList( "195~X50", "VIP HOSPITAL PLAN R-200"),
                        	new AssurancePlanList( "195~X51", "VIP HOSPITAL PLAN R-300"),
                        	new AssurancePlanList( "195~X52", "VIP HOSPITAL PLAN R-400"),
                        	new AssurancePlanList( "195~X53", "VIP HOSPITAL PLAN R-500"),
                        	new AssurancePlanList( "195~X54", "VIP HOSPITAL PLAN R-600"),
                        	new AssurancePlanList( "195~X55", "VIP HOSPITAL PLAN R-700"),
                        	new AssurancePlanList( "195~X56", "VIP HOSPITAL PLAN R-800"),
                        	new AssurancePlanList( "195~X57", "VIP HOSPITAL PLAN R-900"),
                        	new AssurancePlanList( "195~X58", "VIP HOSPITAL PLAN R-1000"),
                        	new AssurancePlanList( "195~X59", "VIP HOSPITAL PLAN R-1500"),
                        	new AssurancePlanList( "195~X60", "VIP HOSPITAL PLAN R-2000")                        	
                         });
                    hardcodedProductVOList.add( productVO );                                    

//            productVO = new HardcodedProductVO( 137, "MAXI DEPOSIT RUPIAH", new AssurancePlanList[]{
//                    new AssurancePlanList( "137~X3", "MAXI DEPOSIT RUPIAH (Bancassurance)" )
//            } );
//            hardcodedProductVOList.add( productVO );
//            
//            productVO = new HardcodedProductVO( 137, "MAXI INVEST RUPIAH (Agency)", new AssurancePlanList[]{
//                    new AssurancePlanList( "137~X4", "MAXI INVEST RUPIAH (Agency Distribution)" )
//            } );
//            hardcodedProductVOList.add( productVO );
//            
//            productVO = new HardcodedProductVO( 137, "MAXI INVEST RUPIAH (Corporate)", new AssurancePlanList[]{
//                    new AssurancePlanList( "137~X5", "MAXI INVEST RUPIAH (Corporate Marketing)" )
//            } );
//            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 114, "MAXI DEPOSIT US DOLLAR", new AssurancePlanList[]{
                    //new AssurancePlanList( "114~X3", "MAXI DEPOSIT US DOLLAR (Bancassurance)" )
            		new AssurancePlanList( "114~X9", "MAXI DEPOSIT US DOLLAR (Bancassurance)" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 114, "MAXI INVEST US DOLLAR (Agency)", new AssurancePlanList[]{
                    //new AssurancePlanList( "114~X2", "MAXI INVEST US DOLLAR (Agency Distribution)" )
            		new AssurancePlanList( "114~X8", "MAXI INVEST US DOLLAR (Agency Distribution)" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 114, "MAXI INVEST US DOLLAR (Corporate)", new AssurancePlanList[]{
                    new AssurancePlanList( "114~X4", "MAXI INVEST US DOLLAR (Corporate Marketing)" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 184, "STABLE SAVE", new AssurancePlanList[]{
                    new AssurancePlanList( "184~X1", "STABLE SAVE" )
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 119, "EKA LINK 18", new AssurancePlanList[]{
                    new AssurancePlanList( "119~X4", "EKA LINK 18 SINGLE" ),
                    new AssurancePlanList( "119~X5", "EKA LINK 18 3" ),
                    new AssurancePlanList( "119~X6", "EKA LINK 18 6" )
            } );
            hardcodedProductVOList.add( productVO );
           
            productVO = new HardcodedProductVO( 190, "SMiLe LINK BRIDGE", new AssurancePlanList[]{
                    new AssurancePlanList( "190~X1", "SMiLe LINK BRIDGE SINGLE" ),
                    new AssurancePlanList( "190~X2", "SMiLe LINK BRIDGE REGULER" ) 
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 190, "SMiLe LINK 99", new AssurancePlanList[]{
                    new AssurancePlanList( "190~X3", "SMiLe LINK 99 SINGLE" ),
                    new AssurancePlanList( "190~X4", "SMiLe LINK 99 REGULER" ) 
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 190, "VIP FAMILY PLAN", new AssurancePlanList[]{
                    new AssurancePlanList( "190~X5", "VIP FAMILY PLAN SINGLE" ),
                    new AssurancePlanList( "190~X6", "VIP FAMILY PLAN REGULER" ) 
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 190, "SMILE OPTIMA LINK", new AssurancePlanList[]{
                 	new AssurancePlanList( "190~X7", "SMiLe OPTIMA LINK SINGLE"),
                 	new AssurancePlanList( "190~X8", "SMiLe OPTIMA LINK REGULER")                        	         	
                 });
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 190, "SMiLe LINK ULTIMATE", new AssurancePlanList[]{
                 	new AssurancePlanList( "190~X9", "SMiLe LINK ULTIMATE ")                  	         	
                 });
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 200, "SMiLe LINK BRIDGE SYARIAH", new AssurancePlanList[]{
                    new AssurancePlanList( "200~X1", "SMiLe LINK BRIDGE SYARIAH SINGLE" ),
                    new AssurancePlanList( "200~X2", "SMiLe LINK BRIDGE SYARIAH REGULER" )
                    
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 200, "SMiLe LINK 99 SYARIAH", new AssurancePlanList[]{
                    new AssurancePlanList( "200~X3", "SMiLe LINK 99 SYARIAH SINGLE" ),
                    new AssurancePlanList( "200~X4", "SMiLe LINK 99 SYARIAH REGULER" ) 
            } );
            hardcodedProductVOList.add( productVO );
         
            productVO = new HardcodedProductVO( 200, "SUPERLINK SYARIAH", new AssurancePlanList[]{
                    new AssurancePlanList( "200~X5", "SUPERLINK SYARIAH SINGLE" ),
                    new AssurancePlanList( "200~X6", "SUPERLINK SYARIAH REGULER" ) 
            } );
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 202, "EXCELLENT LINK SYARIAH", new AssurancePlanList[]{
            	new AssurancePlanList( "202~X1", "EXCELLENT LINK 5 SYARIAH"),
            	new AssurancePlanList( "202~X2", "EXCELLENT LINK 10 SYARIAH"),
            	new AssurancePlanList( "202~X3", "EXCELLENT LINK SINGLE SYARIAH"),
            	new AssurancePlanList( "202~X4", "SIMAS POWER LINK 5 SYARIAH"),
            	new AssurancePlanList( "202~X5", "SIMAS POWER LINK 10 SYARIAH"),
            	new AssurancePlanList( "202~X6", "SIMAS POWER LINK SINGLE SYARIAH")
            });
            hardcodedProductVOList.add( productVO );
            
            productVO = new HardcodedProductVO( 202, "KEDAI LINK", new AssurancePlanList[]{
                	new AssurancePlanList( "202~X7", "KEDAI LINK 5"),
                	new AssurancePlanList( "202~X8", "KEDAI LINK 10")
                });
           hardcodedProductVOList.add( productVO );
                             
        
        productVO = new HardcodedProductVO( 212, "SMiLe LIFE CARE PLUS", new AssurancePlanList[]{        		 
              	new AssurancePlanList( "212~X1", "SMART LIFE CARE PLUS (BTN)"),
            	new AssurancePlanList( "212~X3", "SMiLe LIFE CARE PLUS (BJB)")
              });
         hardcodedProductVOList.add( productVO );         
         
         productVO = new HardcodedProductVO( 212, "SMART LIFE PROTECTION (BUKOPIN)", new AssurancePlanList[]{        		 
               	new AssurancePlanList( "212~X6", "SMART LIFE PROTECTION (BUKOPIN)")
               });
          hardcodedProductVOList.add( productVO );
          
          productVO = new HardcodedProductVO( 212, "SMiLe PROTEKSI", new AssurancePlanList[]{        		 
                 	new AssurancePlanList( "212~X9", "SMiLe PROTEKSI"),
                 	new AssurancePlanList( "212~X14", "SMiLe PROTEKSI")
                 });
          hardcodedProductVOList.add( productVO );
          
                 
           productVO = new HardcodedProductVO( 215, "SIMAS PRIME LINK SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "215~X1", "SIMAS PRIME LINK SYARIAH" )
             } );
             hardcodedProductVOList.add( productVO );                       
                                 
             
           productVO = new HardcodedProductVO( 215, "SMiLe LINK PROASSET SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "215~X2", "SMiLe LINK PROASSET SYARIAH" )
             } );
             hardcodedProductVOList.add( productVO );
             
            productVO = new HardcodedProductVO( 215, "SMiLe PREMIUM LINK SYARIAH", new AssurancePlanList[]{
                     new AssurancePlanList( "215~X3", "SMiLe PREMIUM LINK SYARIAH" )
               } );
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 215, "B SMiLe INSURANCE SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "215~X4", "B SMiLe INSURANCE SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO );
             
             productVO = new HardcodedProductVO( 220, "SMiLe LINK PLUS", new AssurancePlanList[]{
                     new AssurancePlanList( "220~X1", "SMiLe LINK PLUS" )
               } );
           hardcodedProductVOList.add( productVO );   
             
           productVO = new HardcodedProductVO( 220, "JEMPOL LINK", new AssurancePlanList[]{
                     new AssurancePlanList( "220~X2", "JEMPOL LINK" )
               } );
           hardcodedProductVOList.add( productVO ); 
           
           productVO = new HardcodedProductVO( 220, "SMiLe LINK PRO", new AssurancePlanList[]{
                   new AssurancePlanList( "220~X3", "SMiLe LINK PRO" )
             } );
           hardcodedProductVOList.add( productVO ); 
           
           productVO = new HardcodedProductVO( 220, "B SMiLe PROTECTION", new AssurancePlanList[]{
                   new AssurancePlanList( "220~X4", "B SMiLe PROTECTION" )
             } );
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 224, "SMiLe LINK PRO SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "224~X1", "SMiLe LINK PRO SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO ); 
           
           productVO = new HardcodedProductVO( 224, "JEMPOL LINK SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "224~X2", "JEMPOL LINK SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 224, "B SMiLe PROTECTION SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "224~X3", "B SMiLe PROTECTION SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 223, "SMiLe LIFE SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "223~X1", "SMiLe LIFE SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 223, "SMART LIFE PROTECTION SYARIAH", new AssurancePlanList[]{
                   new AssurancePlanList( "223~X2", "SMART LIFE PROTECTION SYARIAH" )
             } );
           hardcodedProductVOList.add( productVO );
           
           
           productVO = new HardcodedProductVO( 219, "SIMAS KID INSURANCE SYARIAH", new AssurancePlanList[]{
               	new AssurancePlanList( "219~X1", "SIMAS KID INSURANCE PLAN A SYARIAH"),
               	new AssurancePlanList( "219~X2", "SIMAS KID INSURANCE PLAN B SYARIAH"),
               	new AssurancePlanList( "219~X3", "SIMAS KID INSURANCE PLAN C SYARIAH"),
               	new AssurancePlanList( "219~X4", "SIMAS KID INSURANCE PLAN D SYARIAH")                                      	
               });
          hardcodedProductVOList.add( productVO );
          
           productVO = new HardcodedProductVO( 219, "SMART PLAN PROTECTION SYARIAH", new AssurancePlanList[]{
                	new AssurancePlanList( "219~X5", "SMART PLAN PROTECTION PLAN A SYARIAH"),
                	new AssurancePlanList( "219~X6", "SMART PLAN PROTECTION PLAN B SYARIAH"),
                	new AssurancePlanList( "219~X7", "SMART PLAN PROTECTION PLAN C SYARIAH"),
                	new AssurancePlanList( "219~X8", "SMART PLAN PROTECTION PLAN D SYARIAH")                                      	
                });
           hardcodedProductVOList.add( productVO );
           
           productVO = new HardcodedProductVO( 227, "SMiLe INCOME PROTECTION X-TRA", new AssurancePlanList[]{  /**NCR/2021/02/052	SMiLe Income Protection X-Tra**/     		 
                	new AssurancePlanList( "227~X1", "SMiLe INCOME PROTECTION X-TRA")
                });
         hardcodedProductVOList.add( productVO );
        }
        
        return hardcodedProductVOList;
    }

}