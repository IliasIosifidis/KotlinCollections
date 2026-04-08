package callbacks

import entities.Author
import entities.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.concurrent.thread

object Display {
    private val infoArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information..."
            loadBook {book ->
                infoArea.append("""
                Book: ${book.title}
                Year: ${book.year}
                Genre: ${book.genre}
                """)
                loadAuthor(book) { author ->
                infoArea.append("Loading author information...\n\n")
                infoArea.append("""
                Author: ${author.name}
                Bio: ${author.bio}
                """)
                }

            }
            isEnabled = true
        }
    }
    private val timeLabel = JLabel("Time: 00:00")
    private val topPanel = JPanel(BorderLayout()).apply {
        add(timeLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400,300)
    }

    fun show(){
        mainFrame.isVisible = true
        startTimer()
    }

    private fun loadBook(callback: (Book) -> Unit){
        thread {
            Thread.sleep(500)
            callback(Book("Red Rising", 2014, "Sci-fi"))
        }
    }

    private fun loadAuthor(book: Book, callback: (Author) -> Unit){
        thread {
            Thread.sleep(500)
            callback(Author("Pierce Brown", "cant measure time correctly"))
        }
    }

    private fun startTimer(){
        thread {
            var totalSeconds = 0
            while (true){
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timeLabel.text = String.format("Time: %02d:%02d", minutes, seconds)
                Thread.sleep(1000)
                totalSeconds++
            }
        }
    }
}