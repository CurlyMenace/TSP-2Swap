package tspSolution1;

public class Node 
{
	private double xCoord;
	private double yCoord;
	private String cityName;
	public double getxCoord() {
		return xCoord;
	}
	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}
	public double getyCoord() {
		return yCoord;
	}
	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Node(String cityName, double xCoord, double yCoord)
	{
		this.cityName = cityName;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
}
