import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor
public class LevenshteinDto {
    String nameAlgs;
    long procTime;
    String word1;
    String word2;
    int result;
}
