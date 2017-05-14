import com.hannesdorfmann.gitpodcaster.Episode;
import java.time.LocalDateTime

class Episode3 : Episode(
        title = "Episode 3: RxJava",
        releaseDate = LocalDateTime.of(2017, 1, 17, 6, 0),
        showNotes = """
# Episode 3: RxJava
An awesome episode ...
""".trimIndent()

)
