import com.hannesdorfmann.gitpodcaster.Episode;
import java.time.LocalDateTime

class Episode5 : Episode(
        title = "Episode 5: Yet Another Episode",
        releaseDate = LocalDateTime.of(2016, 12, 31, 20, 0),
        showNotes = """
# Episode 5: Trying out some things

Sample list
 - Item 1
 - Item 2
"""
)
