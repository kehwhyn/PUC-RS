package pucrs.myflight.modelo;

public class Geo {
	private double latitude;
	private double longitude;
	
	public Geo(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public static double distancia(Geo x, Geo y) {
		var lon1 = Math.toRadians(x.getLongitude());
		var lat1 = Math.toRadians(x.getLatitude());
		var lon2 = Math.toRadians(y.getLongitude());
		var lat2 = Math.toRadians(y.getLatitude());

		var dlon = lon2 - lon1;
		var dlat = lat2 - lat1;

		var a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2);
		var c = 2 * Math.asin(Math.sqrt(a));
		return c * 6371;
	}

	public double distancia(Geo x) {
		var lon1 = Math.toRadians(getLongitude());
		var lat1 = Math.toRadians(getLatitude());
		var lon2 = Math.toRadians(x.getLongitude());
		var lat2 = Math.toRadians(x.getLatitude());

		var dlon = lon2 - lon1;
		var dlat = lat2 - lat1;

		var a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2);
		var c = 2 * Math.asin(Math.sqrt(a));
		return c * 6371;
	}
}
