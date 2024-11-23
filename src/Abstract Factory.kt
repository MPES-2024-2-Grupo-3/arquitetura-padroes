// Produto Abstrato
interface Button { fun paint() }

class WinButton : Button {
    override fun paint() {
        println("Renderizando um botão no estilo Windows")
    }
}

class OSXButton : Button {
    override fun paint() {
        println("Renderizando um botão no estilo macOS")
    }
}

// Fábrica Abstrata
interface GUIFactory {
    fun createButton(): Button
}

class WinFactory : GUIFactory {
    override fun createButton(): Button {
        return WinButton()
    }
}

// Fábrica Concreta 2
class OSXFactory : GUIFactory {
    override fun createButton(): Button {
        return OSXButton()
    }
}

class Application(factory: GUIFactory) {
    private val button: Button = factory.createButton()

    fun render() {
        button.paint()
    }
}

fun main() {

    val os = "méqui-óessi"
    val factory: GUIFactory = if (os == "Windows") WinFactory() else OSXFactory()

    val app = Application(factory)
    app.render()
}