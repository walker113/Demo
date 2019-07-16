package stay.walker.com.kotlin



class CountryTest {

//    fun isBigEuropeanCountry(country: Country): Boolean {
//
//        return country.continient == "EU" && country.population > 10000
//
//    }





}



class Book(val name: String)


fun main(args: Array<String>) {
    val bookNames = listOf(
            Book("Thinking in Java"),
            Book("Dive into Kotlin")
    ).map(Book::name)


    println(bookNames)

    val countryApp = CountryApp()
    val countryTest = CountryTest()

    val countries = mutableListOf<Country>()



    countryApp.filterCountries(countries,
            fun(country: Country): Boolean
            {
                return country.continient == "EU" && country.population > 10000
            }
    )

    countryApp.filterCountries(countries, {
                country ->
                country.continient == "EU" && country.population > 10000
            }
    )

    val sum : (Int, Int) -> Int = {
        x: Int, y:Int -> x + y
    }


}