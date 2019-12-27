
data class WeatherDetail (

	 val data : Data
)

data class Data (

	val request : List<Request>,
	val current_condition : List<Current_condition>)

data class Request (
	val type : String,
	val query : String
)

data class Current_condition (

	val temp_C : Int,
	val weatherIconUrl : List<WeatherIconUrl>,
	val weatherDesc : List<WeatherDesc>,
	val humidity : Int

)

data class WeatherIconUrl (

	val value : String
)

data class WeatherDesc (

	val value : String
)