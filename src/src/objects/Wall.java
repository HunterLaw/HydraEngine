package src.objects;

public class Wall extends NonTexturedObject2D{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456306774113145109L;

	public Wall(double xs, double ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
	}

	boolean passable = false;

	public void setPassable(boolean pass)
	{
		passable = pass;
	}
	
	public boolean isPassable()
	{
		return passable;
	}
	
	@Override
	public void update() {
		
	}

}
