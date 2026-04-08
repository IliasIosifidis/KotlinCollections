package underTheHood

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
            loadData()
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

    private fun loadData(step: Int = 0, data: Any? = null){
        when(step){
            0 -> {
                loadButton.isEnabled = false
                infoArea.text = "Loading book information..."
                loadBook { loadData(1, it) }
            }
            1 -> {
                val book = data as Book
                infoArea.append("Book: ${book.title} Year: ${book.year} Genre: ${book.genre}\n")
                infoArea.append("Loading author info\n")
                loadAuthor(book){ loadData(2, it) }
            }
            2-> {
                val author = data as Author
                infoArea.append("Author: ${author.name} Bio: ${author.bio}")
                loadButton.isEnabled = true
            }
        }

        loadBook {book ->
            loadAuthor(book) { author ->
            }
        }
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