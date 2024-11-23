data class Aluno(val matricula: Int, val nome: String, val telefone: String)

class Escola private constructor() {
    private val alunos = mutableListOf<Aluno>()

    companion object {
        private var instancia: Escola? = null

        fun getInstance(): Escola {
            if (instancia == null) instancia = Escola()
            return instancia!!
        }
    }

    fun adicionarAluno(aluno: Aluno) = alunos.add(aluno)
    fun removerAluno(matricula: Int): Boolean = alunos.removeIf { it.matricula == matricula }
    fun listarAlunos(): List<Aluno> = alunos
}

fun main() {
    val escola = Escola.getInstance()

    escola.adicionarAluno(Aluno(1, "João", "1234-5678"))
    escola.adicionarAluno(Aluno(2, "Geronimo", "2345-6789"))
    escola.adicionarAluno(Aluno(3, "Pedro", "3456-7890"))

    escola.removerAluno(2)

    val novaEscolaAposRemocao = Escola.getInstance()
    println("Lista de alunos:")
    novaEscolaAposRemocao.listarAlunos().forEach { aluno ->
        println("Matrícula: ${aluno.matricula}, Nome: ${aluno.nome}, Telefone: ${aluno.telefone}")
    }
}