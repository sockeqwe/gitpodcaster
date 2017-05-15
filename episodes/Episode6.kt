import com.hannesdorfmann.gitpodcaster.Episode;
import java.time.LocalDateTime

class Episode6 : Episode(
        title = "Episode 6: RxApps",
        releaseDate = LocalDateTime.of(2016, 12, 31, 20, 0),
        showNotes = """
# Episode 6: RxApps

This example finally shows that travis is pushing back the generated stuff to the branch (like rss feed etc.).

Sample list
 - Item 1
 - Item 2
"""
)
