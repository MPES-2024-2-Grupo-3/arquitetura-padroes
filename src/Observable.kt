data class Aluno2(val matricula: Int, val nome: String, val telefone: String) : Observable()

data class Professor(val matricula: Int, val nome: String, val telefone: String): InterfaceObserver {
    override fun atualizar(nomeAluno: String?) {
        println("Professsor $nome, o aluno $nomeAluno convidou-se a sair da escola.")
    }
}

open class Observable {
    private val observers = mutableListOf<InterfaceObserver>()
    fun addObserver(observer: InterfaceObserver?) {
        observer?.let { observers.add(it) }
    }

    fun removeObserver(observer: InterfaceObserver?) {
        observers.remove(observer)
    }

    fun notifyObservers(nomeAluno: String?) {
        for (observer in observers) observer.atualizar(nomeAluno)
    }
}

interface InterfaceObserver {
    fun atualizar(valor: String?)
}

class Escola2 private constructor() {
    private val alunos = mutableListOf<Aluno2>()

    companion object {
        private var instancia: Escola2? = null

        fun getInstance(): Escola2 {
            if (instancia == null) instancia = Escola2()
            return instancia!!
        }
    }

    fun adicionarAluno(aluno: Aluno2) = alunos.add(aluno)
    fun removerAluno(matricula: Int): Boolean {
        val alunoARemover = alunos.find { it.matricula == matricula  }
        alunoARemover?.notifyObservers(alunoARemover.nome)
        return alunos.remove(alunoARemover)
    }
    fun listarAlunos(): List<Aluno2> = alunos
}

fun main() {
    val escola = Escola2.getInstance()

    escola.adicionarAluno(Aluno2(1, "João", "1234-5678"))
    val aluno2 = Aluno2(2, "Geronimo", "2345-6789")
    escola.adicionarAluno(aluno2)
    escola.adicionarAluno(Aluno2(3, "Pedro", "3456-7890"))

    val professor = Professor(3, "Cumpadi Washington", "3456-7890")
    val professor2 = Professor(3, "Washington Luiz", "3456-7890")

    aluno2.addObserver(professor)
    aluno2.addObserver(professor2)

    escola.removerAluno(2)

    val novaEscolaAposRemocao = Escola.getInstance()
    println("Lista de alunos:")
    novaEscolaAposRemocao.listarAlunos().forEach { aluno ->
        println("Matrícula: ${aluno.matricula}, Nome: ${aluno.nome}, Telefone: ${aluno.telefone}")
    }
}