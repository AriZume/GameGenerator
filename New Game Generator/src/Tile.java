public class Tile
{
    private int power;

    public Tile()
    {
        power = 0;
    }

    public Tile(int p)
    {
        power = p;
    }

    public void setPower(int pow)
    {
        this.power = pow;
    }

    public int getPower()
    {
        return power;
    }
}
