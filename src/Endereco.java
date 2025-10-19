import java.util.Locale;
import java.util.regex.Pattern;

public record Endereco(String rua, String bairro, String cidade, String estado, String cep) {

    private static final Pattern NON_DIGITS = Pattern.compile("\\D+");

    public Endereco {

        if (cep != null) {
            cep = NON_DIGITS.matcher(cep).replaceAll("");
        }

        if (rua != null)    rua    = rua.trim().toUpperCase(Locale.ROOT);
        if (bairro != null) bairro = bairro.trim().toUpperCase(Locale.ROOT);
        if (cidade != null) cidade = cidade.trim().toUpperCase(Locale.ROOT);
        if (estado != null) estado = estado.trim().toUpperCase(Locale.ROOT);
    }

}
