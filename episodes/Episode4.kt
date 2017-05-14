import com.hannesdorfmann.gitpodcaster.Episode;
import java.time.LocalDateTime

class Episode4 : Episode(
        title = "Episode 4: Awesome Animations",
        releaseDate = LocalDateTime.of(2016, 12, 31, 20, 0),
        showNotes = """
# Episode 4 : Animations

Sample list
 - Item 1
 - Item 2
"""
)
