package stay.walker.com.kotlin

class CountryApp {
    fun filterCountries(countries: List<Country>)
            : List<Country> {

        val res = mutableListOf<Country>()

        for (c in countries) {
            if (c.continient == "EU") {
                res.add(c)
            }
        }

        return res

    }

    fun filterCountries(countries: List<Country>,
                        test: (Country) -> Boolean): List<Country> {

        val res = mutableListOf<Country>()

        for (c in countries) {
            if (test(c)) {
                res.add(c)
            }
        }

        return res
    }
}


