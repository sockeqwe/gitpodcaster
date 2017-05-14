import com.hannesdorfmann.gitpodcaster.Person
import java.net.URL

object Podcast {
    val title = "The Context #androiddev"
    val link = "https://github.com/artem-zinnatullin/TheContext-Podcast"
    val description = "New Podcast about Android Development"
    val language = "en-us"
    val rssLink = "https://raw.githubusercontent.com/artem-zinnatullin/TheContext-Podcast/master/feed.rss"
    val keywords = "android,androiddev,context,development,software"
    val subtitle = "New Android Developers Podcast"
    val summary = "The Context is a podcast about Android Development"
    val imageUrl = "https://raw.githubusercontent.com/artem-zinnatullin/TheContext-Podcast/master/logo.png"
    val hosts = listOf(ArtemZinnatulin, HannesDorfmann)
    val itunesAuthor = ArtemZinnatulin
}

val ArtemZinnatulin = Person(
        name = "Artem Zinnatulin",
        email = "artem.zinnatullin@gmail.com",
        twitter = "artem_zin",
        website = "http://artemzin.com"
)
val HannesDorfmann = Person(
        name = "Hannes Dorfmann",
        website = "http://hannesdorfmann.com",
        twitter = "sockeqwe"
)

