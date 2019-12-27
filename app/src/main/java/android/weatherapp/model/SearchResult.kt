
data class SearchResult (

	 val search_api : Search_api
)

data class Search_api (

	val result : List<Result>
)

data class Result (

	val areaName : List<AreaName>,
	val country : List<Country>,
	val weatherUrl : List<WeatherUrl>
)

data class AreaName (

	val value : String
)

data class Country (
	val value : String
)

data class WeatherUrl (

	val value : String
)