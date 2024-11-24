fun main() {
    val carro = Carro.Builder()
        .setMotor("V8")
        .setRodas(4)
        .setCor("Vermelho")
        .build()

    println(carro)
}

class Carro private constructor(
    val motor: String?,
    val rodas: Int?,
    val cor: String?
) {
    override fun toString(): String {
        return "Carro(motor=$motor, rodas=$rodas, cor=$cor)"
    }

    class Builder {
        private var motor: String? = null
        private var rodas: Int? = null
        private var cor: String? = null

        fun setMotor(motor: String) = apply { this.motor = motor }
        fun setRodas(rodas: Int) = apply { this.rodas = rodas }
        fun setCor(cor: String) = apply { this.cor = cor }

        fun build(): Carro {
            return Carro(motor, rodas, cor)
        }
    }
}
