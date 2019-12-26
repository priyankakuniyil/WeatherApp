
data class Weather (

	 val date : String,
	 val astronomy : List<Astronomy>,
	 val maxtempC : Int,
	 val maxtempF : Int,
	 val mintempC : Int,
	 val mintempF : Int,
	 val avgtempC : Int,
	 val avgtempF : Int,
	 val totalSnow_cm : Double,
	 val sunHour : Double,
	 val uvIndex : Int,
	 val hourly : List<Hourly>
)