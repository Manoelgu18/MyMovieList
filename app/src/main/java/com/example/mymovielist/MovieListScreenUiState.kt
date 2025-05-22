package com.example.mymovielist

fun getMovies(): List<Movie> {
    return listOf(
        Movie("Mad Max: Estrada da Fúria", "Ação", "Guerra pós-apocalíptica cheia de perseguições e ação visceral.", R.drawable.acao),
        Movie("Die Hard: Duro de Matar", "Ação", "John McClane enfrenta terroristas em um arranha-céu de Los Angeles.", R.drawable.acao),
        Movie("Vingadores: Ultimato", "Ação", "Heróis unem forças para derrotar Thanos e restaurar o universo.", R.drawable.acao),

        Movie("O Poderoso Chefão", "Drama", "Saga da família Corleone e o poder na máfia americana.", R.drawable.drama),
        Movie("Forrest Gump: O Contador de Histórias", "Drama", "Jornada de um homem simples que vive momentos históricos.", R.drawable.drama),
        Movie("A Lista de Schindler", "Drama", "Baseado em fatos reais sobre salvação de judeus durante o Holocausto.", R.drawable.drama),

        Movie("Comer, Rezar, Amar", "Romance", "Viagem de autodescoberta, amor e espiritualidade.", R.drawable.romance),
        Movie("Diário de uma Paixão", "Romance", "História de amor intensa e duradoura entre jovens apaixonados.", R.drawable.romance),

        Movie("Uma Noite no Museu", "Comédia", "Exposições ganham vida em um museu à noite.", R.drawable.comedia),
        Movie("Se Beber, Não Case!", "Comédia", "Desastre hilário de uma despedida de solteiro em Las Vegas.", R.drawable.comedia),
        Movie("O Terminal", "Comédia", "Homem fica preso no aeroporto e cria vida dentro do terminal.", R.drawable.comedia),

        Movie("Invocação do Mal", "Terror", "Investigação de fenômenos paranormais por famosos demonologistas.", R.drawable.terror),
        Movie("Corra!", "Terror", "Visita a família de uma namorada revela sinistro segredo.", R.drawable.terror),
        Movie("Atividade Paranormal", "Terror", "Câmeras capturam eventos sobrenaturais em uma casa.", R.drawable.terror),

        Movie("Interestelar", "Ficção Científica", "Viagem espacial épica em busca de um novo lar para a humanidade.", R.drawable.ficcao),
        Movie("Blade Runner 2049", "Ficção Científica", "Detetive descobre segredo que pode abalar a sociedade dos replicantes.", R.drawable.ficcao),

        Movie("Procurando Nemo", "Animação", "Peixe-palhaço atravessa o oceano para encontrar seu filho.", R.drawable.animacao),
        Movie("Toy Story", "Animação", "Brinquedos ganham vida quando humanos não estão por perto.", R.drawable.animacao),

        Movie("O Grande Truque", "Mistério", "Rivais mágicos travam batalha de ilusões no século XIX.", R.drawable.misterio),
        Movie("Clube da Luta", "Mistério", "Homem desiludido cria clube clandestino de luta e desvenda sua mente.", R.drawable.misterio)
    )
}

fun generateRandomMovie(): Movie {
    // Listas de títulos, gêneros, descrições e ícones
    val titles = listOf(
        "Mad Max: Estrada da Fúria",
        "O Poderoso Chefão",
        "Comer, Rezar, Amar",
        "Uma Noite no Museu",
        "Invocação do Mal",
        "Interestelar",
        "Procurando Nemo",
        "O Grande Truque"
    )
    val genres = listOf(
        "Ação",
        "Drama",
        "Romance",
        "Comédia",
        "Terror",
        "Ficção Científica",
        "Animação",
        "Mistério"
    )
    val descriptions = listOf(
        "Guerra pós-apocalíptica cheia de perseguições e ação visceral.",
        "Saga da família Corleone e o poder na máfia americana.",
        "Viagem de autodescoberta, amor e espiritualidade.",
        "Exposições ganham vida em um museu à noite.",
        "Investigação de fenômenos paranormais por famosos demonologistas.",
        "Viagem espacial épica em busca de um novo lar para a humanidade.",
        "Peixe-palhaço atravessa o oceano para encontrar seu filho.",
        "Rivais mágicos travam batalha de ilusões no século XIX."
    )
    val icons = listOf(
        R.drawable.acao,
        R.drawable.drama,
        R.drawable.romance,
        R.drawable.comedia,
        R.drawable.terror,
        R.drawable.ficcao,
        R.drawable.animacao,
        R.drawable.misterio
    )

    // Seleção aleatória de cada lista pelo mesmo índice
    val index = titles.indices.random()
    return Movie(
        name = titles[index],
        genre = genres[index],
        description = descriptions[index],
        icon = icons[index],
        isCompleted = false
    )
}

data class MovieListScreenUiState(
    val movies: List<Movie> = getMovies(),
    val nameFilter: String = "",
){
    val filteredMovies: List<Movie>
        get() = movies.filter {
            it.name.contains(nameFilter, ignoreCase = true)
        }
}
