data class TypeResponse(
    val results: List<TypeFiltro>
)

data class TypeFiltro(
    val name: String,
    val url: String
)