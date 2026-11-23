import org.example.exception.PokeAPIException;
import org.example.network.PokeAPI;
import org.example.vo.Pokemon;

import static java.lang.IO.println;

void main() {
    PokeAPI api = PokeAPI.getInstance();

    try {
        println(api.getPokemon("a"));
    }catch(PokeAPIException _){}
}
