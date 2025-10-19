import java.util.Locale;
import java.util.regex.Pattern;

public record Proprietario(String nome, String telefone, String cpf) {

    private static final Pattern NON_DIGITS = Pattern.compile("\\D+");

    public Proprietario {

        if (cpf != null) {
            cpf = NON_DIGITS.matcher(cpf).replaceAll("");
        }

        if (telefone != null) {
            telefone = NON_DIGITS.matcher(telefone).replaceAll("");
        }

        if (nome != null) nome = nome.trim().toUpperCase(Locale.ROOT);

    }

}
