package id.co.sinarmaslife.standard.propertyeditors;

import org.springframework.util.StringUtils;
import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyFormatEditor extends PropertyEditorSupport
{
    public String currencyFormat;

    public CurrencyFormatEditor( String decimalPattern )
    {
        this.currencyFormat = decimalPattern;
    }

    public void setAsText( String text ) throws IllegalArgumentException
    {
        if( !StringUtils.hasText( text ) )
        {
            setValue( null );
        }
        else
        {
            Pattern p = Pattern.compile( "[^0-9]" );
            Matcher m = p.matcher( text );
            String value = m.replaceAll( "" );

            BigDecimal amount = ( new BigDecimal( value ) ).divide( new BigDecimal( "100" ) );
            setValue( amount );
        }
    }

    public String getAsText()
    {
        String result = "";
        Object value = getValue();
        if( ( value != null ) && ( value instanceof BigDecimal ) )
        {
            BigDecimal amount = ( BigDecimal ) value;
            amount = amount.setScale( 2, RoundingMode.DOWN );
            DecimalFormat decimalFormat = new DecimalFormat( currencyFormat );
            result = decimalFormat.format( amount );
        }

        return result;
    }
    
}
