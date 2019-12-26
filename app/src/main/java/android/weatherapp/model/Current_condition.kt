data class Current_condition (

	 val observation_time : String,
	 val temp_C : Int,
	 val temp_F : Int,
	 val weatherCode : Int,
	 val weatherIconUrl : List<WeatherIconUrl>,
	 val weatherDesc : List<WeatherDesc>,
	 val windspeedMiles : Int,
	 val windspeedKmph : Int,
	 val winddirDegree : Int,
	 val winddir16Point : String,
	 val precipMM : Double,
	 val precipInches : Double,
	 val humidity : Int,
	 val visibility : Int,
	 val visibilityMiles : Int,
	 val pressure : Int,
	 val pressureInches : Int,
	 val cloudcover : Int,
	 val feelsLikeC : Int,
	 val feelsLikeF : Int,
	 val uvIndex : Int
)