open class Observable {
    private val observers = mutableListOf<Observer>()

    fun add(observer: Observer?) = observer?.let { observers.add(it) }
    fun remove(observer: Observer?) = observers.remove(observer)
    fun notify(nomeVoluntario: String?) { for (observer in observers) observer.atualizar(nomeVoluntario) }
}

interface Observer { fun atualizar(valor: String?) }

data class Voluntario(val matricula: Int, val nome: String, val telefone: String) : Observable()

data class OrganizadorCampanha(val matricula: Int, val nome: String, val telefone: String): Observer {
    override fun atualizar(nomeVoluntario: String?) {
        println("Organizador $nome, o voluntario $nomeVoluntario saiu da campanha.")
    }
}

object Campanha {
    private val voluntarios = mutableListOf<Voluntario>()

    fun adicionar(voluntario: Voluntario) = voluntarios.add(voluntario)
    fun remover(matricula: Int): Boolean {
        val voluntarioARemover = voluntarios.find { it.matricula == matricula  }
        voluntarioARemover?.notify(voluntarioARemover.nome)
        return voluntarios.remove(voluntarioARemover)
    }
    fun listarVoluntarios(): List<Voluntario> = voluntarios
}

fun main() {
    val campanha = Campanha

    campanha.adicionar(Voluntario(1, "João", "1234-5678"))
    val voluntario2 = Voluntario(2, "Geronimo", "2345-6789")
    campanha.adicionar(voluntario2)
    campanha.adicionar(Voluntario(3, "Pedro", "3456-7890"))

    val organizadorCampanha = OrganizadorCampanha(3, "Cumpadi Washington", "3456-7890")
    val organizadorCampanha2 = OrganizadorCampanha(3, "Washington Luiz", "3456-7890")

    voluntario2.add(organizadorCampanha)
    voluntario2.add(organizadorCampanha2)

    campanha.remover(2)
    
    println("Lista de voluntarios:")
    Campanha.listarVoluntarios().forEach { voluntario ->
        println("Matrícula: ${voluntario.matricula}, Nome: ${voluntario.nome}, Telefone: ${voluntario.telefone}")
    }
}