package id.co.sinarmaslife.standard.model.vo;

public class LimitVO
{
    private int down;
    private int up;

    public LimitVO( int down, int up )
    {
        this.up = up;
        this.down = down;
    }

    public int getDown()
    {
        return down;
    }

    public void setDown( int down )
    {
        this.down = down;
    }

    public int getUp()
    {
        return up;
    }

    public void setUp( int up )
    {
        this.up = up;
    }


}
